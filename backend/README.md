# 🔧 AroSetu Backend — Spring Boot REST API

> Backend API server for the AroSetu Healthcare Coordination Platform, built with Spring Boot 3.4.5 and Java 17.

**Developed by:** Prasanna Ram & Sudarshan K S

---

## 📋 Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Database](#database)
- [Security](#security)
- [Entities & Relationships](#entities--relationships)
- [REST API Reference](#rest-api-reference)
- [DTOs](#dtos)
- [Services](#services)
- [How to Run](#how-to-run)
- [Build](#build)

---

## 📖 Overview

The AroSetu backend is a **monolithic Spring Boot REST API** that provides:
- User authentication & registration with **JWT tokens**
- **Role-based access control** (RBAC) for 5 user roles
- Complete **CRUD operations** for 15 domain entities
- **Business workflow** management for appointments, consultations, prescriptions, and lab tests
- **Data seeding** for demo/testing environments
- Input **validation** using Jakarta Bean Validation
- Global **exception handling** with structured error responses

---

## 🛠️ Technology Stack

### Core Framework
| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 17 (LTS) | Programming language |
| Spring Boot | 3.4.5 | Application framework |
| Spring Web MVC | 6.x | RESTful API controllers |
| Apache Tomcat | 10.1.40 | Embedded servlet container |

### Data Layer
| Technology | Version | Purpose |
|-----------|---------|---------|
| Spring Data JPA | 3.4.x | JPA repositories & query methods |
| Hibernate ORM | 6.6.13.Final | Object-Relational Mapping |
| H2 Database | 2.3.232 | In-memory database (dev) |
| PostgreSQL Driver | 42.x | Production database support |

### Security
| Technology | Version | Purpose |
|-----------|---------|---------|
| Spring Security | 6.x | Authentication & authorization framework |
| JWT (jjwt-api) | 0.12.6 | JSON Web Token creation & validation |
| jjwt-impl | 0.12.6 | JWT implementation |
| jjwt-jackson | 0.12.6 | JWT JSON serialization |
| BCrypt | — | Password hashing |

### Utilities
| Technology | Version | Purpose |
|-----------|---------|---------|
| Lombok | 1.18.x | @Builder, @Getter, @Setter, @AllArgsConstructor |
| Jakarta Validation | 3.x | @Valid, @NotNull, @NotBlank, @Email |
| Maven Wrapper | — | Portable build execution |

### Testing
| Technology | Purpose |
|-----------|---------|
| Spring Boot Test | Integration testing framework |
| Spring Security Test | Security layer testing |

---

## 🏗️ Architecture

The backend follows a **Layered Architecture** pattern:

```
HTTP Request
    │
    ▼
┌─────────────────────────────────────────┐
│          Security Filter Chain           │
│                                         │
│  CorsFilter → JwtAuthenticationFilter   │
│  → UsernamePasswordAuthFilter           │
└────────────────────┬────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────┐
│        Controller Layer (15 files)       │
│                                         │
│  @RestController + @RequestMapping      │
│  Request validation (@Valid)            │
│  Authentication (SecurityContext)       │
│  Response mapping (Entity → DTO)        │
└────────────────────┬────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────┐
│         Service Layer (15 files)         │
│                                         │
│  @Service + @Transactional              │
│  Business logic & validation            │
│  Entity relationships management        │
│  Status transitions & workflow          │
└────────────────────┬────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────┐
│       Repository Layer (15 files)        │
│                                         │
│  JpaRepository<Entity, Long>            │
│  Derived query methods                  │
│  Custom @Query (JPQL)                   │
└────────────────────┬────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────┐
│          H2 In-Memory Database           │
│                                         │
│  15 tables, auto-created by Hibernate   │
│  DDL strategy: update                   │
│  Console: http://localhost:8080/h2-console│
└─────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
backend/
├── pom.xml                                    # Maven config & dependencies
├── mvnw / mvnw.cmd                            # Maven Wrapper
├── README.md                                  # This file
└── src/
    └── main/
        ├── resources/
        │   └── application.yaml               # App configuration
        └── java/com/arosetu/backend/
            ├── ArosetuBackendApplication.java  # @SpringBootApplication entry
            │
            ├── config/                        # Configuration
            │   ├── SecurityConfig.java        # Security filter chain, auth provider
            │   ├── CorsConfig.java            # CORS origins config
            │   └── DataSeeder.java            # Demo data seeding on startup
            │
            ├── controller/                    # REST Controllers (15)
            │   ├── AuthController.java        # POST /api/auth/register, /login
            │   ├── DoctorController.java      # /api/doctors/*
            │   ├── PatientController.java     # /api/patients/*
            │   ├── AppointmentController.java # /api/appointments/*
            │   ├── ConsultationController.java# /api/consultations/*
            │   ├── PrescriptionController.java# /api/prescriptions/*
            │   ├── HospitalController.java    # /api/hospitals/*
            │   ├── AvailabilityController.java# /api/availability/*
            │   ├── LaboratoryController.java  # /api/laboratories/*
            │   ├── LabTestController.java     # /api/lab-tests/*
            │   ├── PharmacyController.java    # /api/pharmacies/*
            │   ├── AdminController.java       # /api/admin/*
            │   ├── DepartmentController.java  # /api/departments/*
            │   ├── FollowUpController.java    # /api/follow-ups/*
            │   └── UserController.java        # /api/users/*
            │
            ├── dto/                           # Data Transfer Objects (20)
            │   ├── LoginRequest.java          # { email, password }
            │   ├── UserRegistrationRequest.java # { name, email, password, role, phone }
            │   ├── AuthResponse.java          # { token, user }
            │   ├── AppointmentRequest.java    # { doctorId, hospitalId, date, time, reason }
            │   ├── AppointmentResponse.java   # Full appointment details
            │   ├── AvailabilityRequest.java   # { hospitalId, dayOfWeek, times, duration }
            │   ├── AvailabilityResponse.java  # With doctor/hospital names
            │   ├── ConsultationRequest.java   # { appointmentId, symptoms, diagnosis... }
            │   ├── ConsultationResponse.java  # With patient/doctor names
            │   ├── PrescriptionRequest.java   # { consultationId, notes, items[] }
            │   ├── PrescriptionResponse.java  # With items, pharmacy info
            │   ├── PrescriptionItemRequest.java  # { medicineName, dosage, frequency... }
            │   ├── PrescriptionItemResponse.java # Medicine details
            │   ├── DoctorResponse.java        # Doctor profile data
            │   ├── DoctorSearchResponse.java  # Search result with hospital list
            │   ├── DoctorProfileRequest.java  # Profile update fields
            │   ├── HospitalRequest.java       # Hospital CRUD fields
            │   ├── HospitalResponse.java      # Hospital data
            │   ├── LabTestRequestDto.java      # Lab test order fields
            │   ├── LabTestResponse.java       # Lab test with results
            │   ├── DepartmentRequest/Response # Department data
            │   ├── FollowUpRequest/Response   # Follow-up data
            │   ├── PatientProfileRequest.java # Patient profile fields
            │   ├── PatientResponse.java       # Patient data
            │   └── UserResponse.java          # User data (no password)
            │
            ├── entity/                        # JPA Entities (15)
            │   ├── User.java                  # id, name, email, password, role, phone, active
            │   ├── Doctor.java                # id, specialization, qualification, fee, license
            │   ├── Patient.java               # id, user relationship
            │   ├── Hospital.java              # id, name, type, address, city, state, pinCode
            │   ├── DoctorHospital.java        # Many-to-many join entity
            │   ├── Availability.java          # dayOfWeek, startTime, endTime, slotDuration
            │   ├── Appointment.java           # date, startTime, endTime, status, reason
            │   ├── Consultation.java          # symptoms, diagnosis, observations, treatment
            │   ├── Prescription.java          # status, notes, pharmacy reference
            │   ├── PrescriptionItem.java      # medicineName, dosage, frequency, duration
            │   ├── FollowUp.java              # followUpDate, reason, instructions
            │   ├── Department.java            # name, active status
            │   ├── Laboratory.java            # name, address, city, state, contactNumber
            │   ├── Pharmacy.java              # name, address, city, state, contactNumber
            │   └── LabTestRequest.java        # testName, status, resultSummary, reportUrl
            │
            ├── enums/                         # Enumerations (7)
            │   ├── Role.java                  # PATIENT, DOCTOR, ADMIN, LAB_TECHNICIAN, PHARMACIST
            │   ├── AppointmentStatus.java     # REQUESTED → CONFIRMED → COMPLETED / CANCELLED / REJECTED
            │   ├── ApprovalStatus.java        # PENDING → APPROVED / REJECTED
            │   ├── PrescriptionStatus.java    # DRAFT → PRESCRIBED → SENT_TO_PHARMACY → READY → DISPENSED
            │   ├── LabTestStatus.java         # REQUESTED → ACCEPTED → SAMPLE_COLLECTED → PROCESSING → COMPLETED
            │   ├── HospitalType.java          # HOSPITAL, CLINIC, DIAGNOSTIC_CENTER
            │   └── Gender.java                # MALE, FEMALE, OTHER
            │
            ├── exception/                     # Exception Handling
            │   ├── GlobalExceptionHandler.java    # @ControllerAdvice
            │   ├── ResourceNotFoundException.java # 404 errors
            │   ├── DuplicateResourceException.java# 409 conflicts
            │   └── ErrorResponse.java             # Structured error body
            │
            ├── repository/                    # JPA Repositories (15)
            │   ├── UserRepository.java        # findByEmail, existsByEmail
            │   ├── DoctorRepository.java      # findByUserId, findByLicenseNumber
            │   ├── PatientRepository.java     # findByUserId
            │   ├── HospitalRepository.java    # findByActiveTrue
            │   ├── DoctorHospitalRepository.java # findByDoctorId, findByHospitalId
            │   ├── AvailabilityRepository.java   # findByDoctorId, findByDoctorIdAndHospitalId
            │   ├── AppointmentRepository.java    # findByPatientId, findByDoctorId
            │   ├── ConsultationRepository.java   # findByDoctorId, findByPatientId
            │   ├── PrescriptionRepository.java   # findByDoctorId, findByPatientId, findByPharmacyId
            │   ├── PrescriptionItemRepository.java # findByPrescriptionId
            │   ├── FollowUpRepository.java       # findByDoctorId, findByPatientId
            │   ├── DepartmentRepository.java     # findByHospitalId
            │   ├── LaboratoryRepository.java     # findByActiveTrue
            │   ├── PharmacyRepository.java       # findByActiveTrue
            │   └── LabTestRequestRepository.java # findByPatientId, findByLaboratoryId
            │
            ├── security/                      # Security Components
            │   ├── JwtService.java            # Token generation, validation, extraction
            │   ├── JwtAuthenticationFilter.java   # OncePerRequestFilter for JWT
            │   └── CustomUserDetailsService.java  # UserDetailsService implementation
            │
            └── service/                       # Business Services (15)
                ├── AuthService.java           # Registration & login logic
                ├── UserService.java           # User CRUD operations
                ├── DoctorService.java         # Doctor management + search
                ├── PatientService.java        # Patient management
                ├── HospitalService.java       # Hospital CRUD + toggle
                ├── DoctorHospitalService.java # Doctor-Hospital associations
                ├── AvailabilityService.java   # Schedule & slot calculation
                ├── AppointmentService.java    # Booking + status transitions
                ├── ConsultationService.java   # Consultation CRUD
                ├── PrescriptionService.java   # Rx workflow management
                ├── FollowUpService.java       # Follow-up scheduling
                ├── DepartmentService.java     # Department CRUD
                ├── LaboratoryService.java     # Laboratory CRUD
                ├── PharmacyService.java       # Pharmacy CRUD
                └── LabTestService.java        # Lab test workflow
```

---

## ⚙️ Configuration

**File:** `src/main/resources/application.yaml`

| Property | Value | Description |
|----------|-------|-------------|
| `server.port` | 8080 | HTTP server port |
| `spring.datasource.url` | `jdbc:h2:mem:arosetu_db` | In-memory H2 database |
| `spring.jpa.ddl-auto` | `update` | Auto-create/update schema |
| `spring.jpa.show-sql` | `true` | Log SQL queries |
| `spring.h2.console.enabled` | `true` | Enable H2 web console |
| `spring.h2.console.path` | `/h2-console` | H2 console URL path |
| `app.jwt.secret` | Base64 encoded key | 256-bit JWT signing key |
| `app.jwt.expiration` | `86400000` | Token validity: 24 hours |

### H2 Database Console
Access at: **http://localhost:8080/h2-console**
- JDBC URL: `jdbc:h2:mem:arosetu_db`
- Username: `sa`
- Password: *(empty)*

---

## 🔐 Security

### Authentication Flow

```
1. POST /api/auth/login  →  { email, password }
2. Server validates credentials via BCrypt
3. Server generates JWT token (24h validity)
4. Response: { token, user: { id, name, email, role } }
5. Client stores token in localStorage
6. All subsequent requests include: Authorization: Bearer <token>
7. JwtAuthenticationFilter validates token on every request
8. SecurityContext populated with user details
```

### Authorization Rules

| Endpoint Pattern | Access |
|-----------------|--------|
| `/api/auth/**` | Public (permitAll) |
| `/api/doctors/search/**` | Public (permitAll) |
| `/api/hospitals/public/**` | Public (permitAll) |
| `/h2-console/**` | Public (permitAll) |
| All other endpoints | Authenticated (JWT required) |
| `@PreAuthorize("hasRole('ADMIN')")` | Admin-only operations |
| `@PreAuthorize("hasRole('DOCTOR')")` | Doctor-only operations |
| `@PreAuthorize("hasRole('PATIENT')")` | Patient-only operations |

### Password Security
- Passwords hashed with **BCrypt** (strength factor 10)
- Raw passwords never stored or logged

---

## 🗄️ Entities & Relationships

### Entity Relationship Diagram

```
User (1) ────── (1) Doctor ────── (N) DoctorHospital (N) ────── (1) Hospital
  │                  │                                              │
  │                  │                                              │
  └── (1) Patient    ├── (N) Availability ──────────────────────────┘
         │           │
         │           ├── (N) Appointment (N) ──── (1) Hospital
         │           │        │
         ├───────────┘        │
         │                    └── (1) Consultation
         │                              │
         │                              ├── (1) Prescription ── (N) PrescriptionItem
         │                              │        │
         │                              │        └── (N:1) Pharmacy
         │                              │
         │                              └── (1) FollowUp
         │
         └── (N) LabTestRequest (N:1) ── Laboratory
                              (N:1) ── Doctor
```

---

## 🚀 How to Run

### Development

```bash
cd backend
./mvnw spring-boot:run          # Linux/Mac
.\mvnw.cmd spring-boot:run      # Windows
```

### Compile Only

```bash
./mvnw compile -DskipTests
```

### Package as JAR

```bash
./mvnw package -DskipTests
java -jar target/arosetu-backend-0.0.1-SNAPSHOT.jar
```

### Run Tests

```bash
./mvnw test
```

---

## 📊 Statistics

| Metric | Count |
|--------|-------|
| Java Source Files | 105 |
| Controllers | 15 |
| Services | 15 |
| Repositories | 15 |
| Entities | 15 |
| DTOs | 23 |
| Enums | 7 |
| Security Classes | 3 |
| Config Classes | 3 |
| Exception Classes | 4 |
| API Endpoints | 45+ |
| Database Tables | 15 |
| Seeded Demo Records | 40+ (users, doctors, patients, appointments, consultations, prescriptions) |

---

© 2026 AroSetu Healthcare — Built by Prasanna Ram & Sudarshan K S
