# 🏥 AroSetu — Healthcare Coordination Platform

> **A Full Stack Web Application for Healthcare Coordination**
> Built with **Spring Boot 3.4.5** (Backend) and **React 19** (Frontend)

**Live Demo:** [https://arosetu.netlify.app](https://arosetu.netlify.app) (Frontend) | [https://arosetu.onrender.com](https://arosetu.onrender.com) (Backend API)
**GitHub:** [https://github.com/prasannagitt/arosetu](https://github.com/prasannagitt/arosetu)

---

## 📋 Table of Contents

1. [Project Overview](#-project-overview)
2. [Problem Statement](#-problem-statement)
3. [Objectives](#-objectives)
4. [System Architecture](#-system-architecture)
5. [Technology Stack — Complete Details](#-technology-stack--complete-details)
6. [Project Structure](#-project-structure)
7. [Backend Architecture — Detailed](#-backend-architecture--detailed)
8. [Frontend Architecture — Detailed](#-frontend-architecture--detailed)
9. [Database Design](#-database-design)
10. [REST API Endpoints — Complete List](#-rest-api-endpoints--complete-list)
11. [Authentication & Security](#-authentication--security)
12. [User Roles & Access Control](#-user-roles--access-control)
13. [Features — Module Wise](#-features--module-wise)
14. [Data Flow & Workflow](#-data-flow--workflow)
15. [Deployment Architecture](#-deployment-architecture)
16. [Pre-Seeded Demo Data](#-pre-seeded-demo-data)
17. [Setup & Installation Guide](#-setup--installation-guide)
18. [Screenshots & UI Pages](#-screenshots--ui-pages)
19. [Project Statistics](#-project-statistics)
20. [Future Enhancements](#-future-enhancements)
21. [Developers](#-developers)

---

## 🏥 Project Overview

**AroSetu** (meaning "Health Bridge" in Sanskrit — Arogya + Setu) is a comprehensive, full-stack healthcare coordination platform that bridges the gap between patients, doctors, hospitals, laboratories, and pharmacies. It digitizes the entire healthcare journey from appointment booking to prescription dispensing.

The platform supports **5 distinct user roles** — Admin, Patient, Doctor, Lab Technician, and Pharmacist — each with their own dedicated dashboard and set of features. It implements a complete clinical workflow: Patient books appointment → Doctor confirms → Consultation happens → Prescription is written → Pharmacy dispenses medication → Lab tests are ordered and reported.

### Key Highlights
- **Full Stack Application** — Spring Boot 3 REST API + React 19 SPA
- **5 Role-Based Dashboards** — Admin, Patient, Doctor, Lab Technician, Pharmacist
- **45+ REST API Endpoints** — Covering all CRUD and business operations
- **JWT Authentication** — Stateless token-based security with 24-hour expiration
- **15 Database Entities** — Comprehensive relational data model with JPA/Hibernate
- **21 Frontend Pages** — Responsive UI with Tailwind CSS 4
- **40+ Pre-Seeded Demo Records** — Ready-to-use sample data with DataSeeder
- **Cloud Deployed** — Frontend on Netlify, Backend on Render.com with Docker

---

## 📌 Problem Statement

In the current healthcare ecosystem, patients face significant challenges:
- **Fragmented Systems** — Appointment booking, consultations, prescriptions, and lab reports exist in separate silos
- **No Centralized Records** — Patients carry physical files between hospitals, labs, and pharmacies
- **Coordination Gaps** — Doctors can't easily share prescriptions with pharmacies or order lab tests digitally
- **Administrative Burden** — Hospital staff manually manage doctor approvals, scheduling, and department assignments
- **Lack of Transparency** — Patients have no visibility into appointment status, lab test progress, or prescription fulfillment

### Our Solution
AroSetu provides a **single unified platform** that connects all healthcare stakeholders digitally, enabling seamless coordination from appointment booking to medication dispensing.

---

## 🎯 Objectives

1. **Digitize Healthcare Workflow** — End-to-end digital flow from appointment booking to prescription dispensing
2. **Role-Based Access Control** — Secure, role-specific dashboards and operations for 5 user types
3. **Real-Time Coordination** — Enable doctors, pharmacies, and labs to coordinate in real-time
4. **Patient Empowerment** — Give patients full visibility into their healthcare journey
5. **Administrative Efficiency** — Automate doctor approvals, hospital management, and scheduling
6. **Cloud Deployment** — Deploy as a scalable, production-ready cloud application

---

## 🏗 System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     CLIENT LAYER                             │
│  ┌────────────────────────────────────────────────────────┐  │
│  │          React 19 Single Page Application               │  │
│  │  ┌──────────┐ ┌──────────┐ ┌───────────┐ ┌──────────┐ │  │
│  │  │  Home    │ │  Auth    │ │  Patient  │ │  Doctor  │ │  │
│  │  │  About   │ │  Login   │ │  Dashboard│ │ Dashboard│ │  │
│  │  │  Public  │ │ Register │ │ Appts,Lab │ │ Consult  │ │  │
│  │  └──────────┘ └──────────┘ └───────────┘ └──────────┘ │  │
│  │  ┌──────────┐ ┌──────────┐ ┌───────────┐              │  │
│  │  │  Admin   │ │   Lab    │ │ Pharmacist│              │  │
│  │  │ Dashboard│ │Dashboard │ │ Dashboard │              │  │
│  │  └──────────┘ └──────────┘ └───────────┘              │  │
│  └────────────────────────────────────────────────────────┘  │
│       Vite 8 Dev Server (port 5173) | Netlify (Production)   │
└──────────────────────────┬──────────────────────────────────┘
                           │ HTTP/REST (JSON)
                           │ Authorization: Bearer <JWT>
┌──────────────────────────▼──────────────────────────────────┐
│                     SERVER LAYER                             │
│  ┌────────────────────────────────────────────────────────┐  │
│  │         Spring Boot 3.4.5 REST API (port 8080)         │  │
│  │  ┌──────────┐ ┌──────────┐ ┌───────────┐              │  │
│  │  │Controller│→│ Service  │→│Repository │              │  │
│  │  │  Layer   │ │  Layer   │ │  Layer    │              │  │
│  │  └──────────┘ └──────────┘ └───────────┘              │  │
│  │  ┌──────────┐ ┌──────────┐ ┌───────────┐              │  │
│  │  │Security  │ │   JWT    │ │   CORS    │              │  │
│  │  │ Config   │ │ Service  │ │  Config   │              │  │
│  │  └──────────┘ └──────────┘ └───────────┘              │  │
│  └────────────────────────────────────────────────────────┘  │
│       Tomcat 10.1.40 (Embedded) | Render.com (Production)    │
└──────────────────────────┬──────────────────────────────────┘
                           │ JDBC / JPA / Hibernate 6.6
┌──────────────────────────▼──────────────────────────────────┐
│                     DATA LAYER                               │
│  ┌────────────────────────────────────────────────────────┐  │
│  │        H2 In-Memory Database (arosetu_db)              │  │
│  │        15 Tables | 40+ Seeded Records                  │  │
│  │        Auto DDL with Hibernate (ddl-auto: update)      │  │
│  └────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

**Design Pattern:** Model-View-Controller (MVC) with Service Layer + Repository Pattern
**Communication:** RESTful APIs over HTTP with JSON payloads
**Authentication:** Stateless JWT-based authentication (no server-side sessions)

---

## 🛠 Technology Stack — Complete Details

### Backend Technologies

| Technology | Version | Purpose |
|---|---|---|
| **Java** | 17 (LTS) | Core programming language |
| **Spring Boot** | 3.4.5 | Application framework, auto-configuration, embedded server |
| **Spring Web** | 6.2.x | REST controllers, request handling, HTTP processing |
| **Spring Data JPA** | 3.4.x | ORM abstraction, repository pattern, database operations |
| **Spring Security** | 6.4.x | Authentication, authorization, CORS, security filter chain |
| **Hibernate ORM** | 6.6.13 | JPA implementation, entity mapping, SQL generation |
| **H2 Database** | 2.3.232 | In-memory relational database for development/demo |
| **PostgreSQL Driver** | 42.x | Production database driver (Render deployment ready) |
| **JJWT (io.jsonwebtoken)** | 0.12.6 | JWT token generation, parsing, and validation |
| **Jakarta Validation** | 3.1.x | Request body validation annotations (@Valid, @NotBlank) |
| **Lombok** | 1.18.x | Boilerplate reduction (@Getter, @Setter, @Builder, etc.) |
| **Apache Maven** | 3.9.x | Build tool, dependency management, project lifecycle |
| **Maven Wrapper** | — | Ensures consistent Maven version across environments |
| **Apache Tomcat** | 10.1.40 | Embedded servlet container (included with Spring Boot) |
| **BCrypt** | — | Password hashing algorithm (via Spring Security) |
| **Docker** | — | Containerization for cloud deployment |

### Frontend Technologies

| Technology | Version | Purpose |
|---|---|---|
| **React** | 19.3.1 | UI library, component-based architecture, virtual DOM |
| **React DOM** | 19.3.1 | DOM rendering for React components |
| **React Router DOM** | 7.18.5 | Client-side routing, navigation, URL management |
| **Vite** | 8.3.6 | Build tool, dev server, HMR (Hot Module Replacement) |
| **Tailwind CSS** | 4.3.5 | Utility-first CSS framework for responsive design |
| **@tailwindcss/vite** | 4.3.5 | Tailwind CSS integration plugin for Vite |
| **Axios** | 1.21.1 | HTTP client for API calls with interceptors |
| **React Toastify** | 11.2.0 | Toast notifications (success, error, info alerts) |
| **React Icons** | 5.7.0 | Icon library (FontAwesome, Material, Game Icons, etc.) |
| **React Multi Carousel** | 2.8.6 | Responsive carousel/slider for the departments section |
| **JavaScript (ES6+)** | — | Programming language (JSX syntax) |
| **HTML5** | — | Semantic markup and structure |
| **CSS3** | — | Styling (via Tailwind utility classes) |
| **TypeScript** | 7.0.2 | Type checking (devDependency for IDE support) |

### DevOps & Deployment Technologies

| Technology | Purpose |
|---|---|
| **Git** | Version control system |
| **GitHub** | Remote repository hosting |
| **Netlify** | Frontend static site hosting (CDN, auto-deploy from GitHub) |
| **Render.com** | Backend hosting (Docker containers, free tier) |
| **Docker** | Multi-stage build for backend containerization |
| **netlify.toml** | Netlify build & deployment configuration |
| **render.yaml** | Render.com infrastructure-as-code configuration |

### Development Tools

| Tool | Purpose |
|---|---|
| **VS Code / IntelliJ IDEA** | IDE for frontend/backend development |
| **H2 Console** | Web-based database browser (accessible at `/h2-console`) |
| **Vite Dev Server** | Hot-reload development server with API proxy |
| **Maven Wrapper (mvnw)** | Portable Maven build without global installation |
| **npm** | Node.js package manager for frontend dependencies |
| **Chrome DevTools** | Frontend debugging and network inspection |
| **Postman / cURL** | API testing and debugging |

---

## 📁 Project Structure

```
arosetu-healthcare-platform-main/
├── backend/                          # Spring Boot Backend Application
│   ├── src/main/java/com/arosetu/backend/
│   │   ├── ArosetuBackendApplication.java       # Main Spring Boot entry point
│   │   ├── config/
│   │   │   ├── CorsConfig.java                  # CORS configuration (localhost + Netlify)
│   │   │   ├── DataSeeder.java                  # Auto-seeds 40+ demo records on startup
│   │   │   └── SecurityConfig.java              # Spring Security + JWT filter chain
│   │   ├── controller/                          # REST API Controllers (15 controllers)
│   │   │   ├── AdminController.java             # Admin operations, stats, doctor approvals
│   │   │   ├── AppointmentController.java       # CRUD + status transitions for appointments
│   │   │   ├── AuthController.java              # Login & Register endpoints
│   │   │   ├── AvailabilityController.java      # Doctor schedule/slot management
│   │   │   ├── ConsultationController.java      # Clinical consultation records
│   │   │   ├── DepartmentController.java        # Hospital department management
│   │   │   ├── DoctorController.java            # Doctor profiles, search, CRUD
│   │   │   ├── FollowUpController.java          # Follow-up appointment scheduling
│   │   │   ├── HospitalController.java          # Hospital CRUD + public listing
│   │   │   ├── LaboratoryController.java        # Laboratory management
│   │   │   ├── LabTestController.java           # Lab test ordering and results
│   │   │   ├── PatientController.java           # Patient profile management
│   │   │   ├── PharmacyController.java          # Pharmacy management
│   │   │   ├── PrescriptionController.java      # Prescription lifecycle management
│   │   │   └── UserController.java              # User account operations
│   │   ├── dto/                                 # Data Transfer Objects (23 DTOs)
│   │   │   ├── AppointmentRequest.java          # Appointment creation payload
│   │   │   ├── AppointmentResponse.java         # Appointment API response
│   │   │   ├── AuthResponse.java                # Login/Register response (JWT + user info)
│   │   │   ├── AvailabilityRequest.java         # Doctor availability input
│   │   │   ├── AvailabilityResponse.java        # Doctor availability output
│   │   │   ├── ConsultationRequest.java         # Consultation data input
│   │   │   ├── ConsultationResponse.java        # Consultation data output
│   │   │   ├── DepartmentRequest.java           # Department input
│   │   │   ├── DepartmentResponse.java          # Department output
│   │   │   ├── DoctorProfileRequest.java        # Doctor profile update input
│   │   │   ├── DoctorResponse.java              # Doctor details output
│   │   │   ├── DoctorSearchResponse.java        # Public doctor search result
│   │   │   ├── FollowUpRequest.java             # Follow-up scheduling input
│   │   │   ├── FollowUpResponse.java            # Follow-up details output
│   │   │   ├── HospitalRequest.java             # Hospital CRUD input
│   │   │   ├── HospitalResponse.java            # Hospital details output
│   │   │   ├── LabTestRequestDto.java           # Lab test order input
│   │   │   ├── LabTestResponse.java             # Lab test result output
│   │   │   ├── LoginRequest.java                # Login credentials (email + password)
│   │   │   ├── PatientProfileRequest.java       # Patient profile update input
│   │   │   ├── PatientResponse.java             # Patient details output
│   │   │   ├── PrescriptionItemRequest.java     # Individual medicine item input
│   │   │   ├── PrescriptionItemResponse.java    # Individual medicine item output
│   │   │   ├── PrescriptionRequest.java         # Prescription creation input
│   │   │   ├── PrescriptionResponse.java        # Prescription details output
│   │   │   ├── UserRegistrationRequest.java     # Registration payload
│   │   │   └── UserResponse.java                # User profile output
│   │   ├── entity/                              # JPA Entities (15 entities)
│   │   │   ├── Appointment.java                 # Appointment entity
│   │   │   ├── Availability.java                # Doctor availability slots
│   │   │   ├── Consultation.java                # Clinical consultation record
│   │   │   ├── Department.java                  # Hospital department
│   │   │   ├── Doctor.java                      # Doctor profile (linked to User)
│   │   │   ├── DoctorHospital.java              # Doctor-Hospital join entity
│   │   │   ├── FollowUp.java                    # Follow-up appointment
│   │   │   ├── Hospital.java                    # Hospital/Clinic/Diagnostic Center
│   │   │   ├── Laboratory.java                  # Laboratory facility
│   │   │   ├── LabTestRequest.java              # Lab test order
│   │   │   ├── Patient.java                     # Patient profile (linked to User)
│   │   │   ├── Pharmacy.java                    # Pharmacy facility
│   │   │   ├── Prescription.java                # Prescription with status workflow
│   │   │   ├── PrescriptionItem.java            # Individual medicine in prescription
│   │   │   └── User.java                        # Base user entity (all roles)
│   │   ├── enums/                               # Enum Definitions (7 enums)
│   │   │   ├── AppointmentStatus.java           # REQUESTED, CONFIRMED, COMPLETED, CANCELLED, REJECTED
│   │   │   ├── ApprovalStatus.java              # PENDING, APPROVED, REJECTED
│   │   │   ├── Gender.java                      # MALE, FEMALE, OTHER
│   │   │   ├── HospitalType.java                # HOSPITAL, CLINIC, DIAGNOSTIC_CENTER
│   │   │   ├── LabTestStatus.java               # REQUESTED, ACCEPTED, SAMPLE_COLLECTED, PROCESSING, COMPLETED, CANCELLED
│   │   │   ├── PrescriptionStatus.java          # DRAFT, PRESCRIBED, SENT_TO_PHARMACY, READY, DISPENSED
│   │   │   └── Role.java                        # ADMIN, PATIENT, DOCTOR, LAB_TECHNICIAN, PHARMACIST
│   │   ├── exception/                           # Exception Handling (4 classes)
│   │   │   ├── DuplicateResourceException.java  # 409 Conflict for duplicate entries
│   │   │   ├── ErrorResponse.java               # Standardized error response DTO
│   │   │   ├── GlobalExceptionHandler.java      # @ControllerAdvice for all exceptions
│   │   │   └── ResourceNotFoundException.java   # 404 Not Found
│   │   ├── repository/                          # Spring Data JPA Repositories (15 repos)
│   │   │   ├── AppointmentRepository.java       # Queries: findByPatientId, findByDoctorId
│   │   │   ├── AvailabilityRepository.java      # Queries: findByDoctorId
│   │   │   ├── ConsultationRepository.java      # Queries: findByPatientId, findByDoctorId
│   │   │   ├── DepartmentRepository.java        # Queries: findByHospitalId
│   │   │   ├── DoctorHospitalRepository.java    # Queries: findByDoctorId, findByHospitalId
│   │   │   ├── DoctorRepository.java            # Queries: findByUserId, findByApprovalStatus
│   │   │   ├── FollowUpRepository.java          # Queries: findByPatientId, findByDoctorId
│   │   │   ├── HospitalRepository.java          # Queries: findByActiveTrue
│   │   │   ├── LaboratoryRepository.java        # Queries: findByActiveTrue
│   │   │   ├── LabTestRequestRepository.java    # Queries: findByPatientId, findByLaboratoryId
│   │   │   ├── PatientRepository.java           # Queries: findByUserId
│   │   │   ├── PharmacyRepository.java          # Queries: findByActiveTrue
│   │   │   ├── PrescriptionItemRepository.java  # Child items of prescriptions
│   │   │   ├── PrescriptionRepository.java      # Queries: findByPatientId, findByDoctorId, findByPharmacyId
│   │   │   └── UserRepository.java              # Queries: findByEmail, existsByEmail, existsByPhone
│   │   ├── security/                            # Security Components (3 classes)
│   │   │   ├── CustomUserDetailsService.java    # Loads user from DB for Spring Security
│   │   │   ├── JwtAuthenticationFilter.java     # OncePerRequestFilter — extracts & validates JWT
│   │   │   └── JwtService.java                  # JWT generation, parsing, validation (HMAC-SHA256)
│   │   └── service/                             # Business Logic Services (15 services)
│   │       ├── AppointmentService.java          # Appointment CRUD + status transitions
│   │       ├── AuthService.java                 # Registration + Login + JWT generation
│   │       ├── AvailabilityService.java         # Doctor schedule management
│   │       ├── ConsultationService.java         # Consultation CRUD
│   │       ├── DepartmentService.java           # Department CRUD
│   │       ├── DoctorHospitalService.java       # Doctor-Hospital associations
│   │       ├── DoctorService.java               # Doctor CRUD + search + approval
│   │       ├── FollowUpService.java             # Follow-up scheduling
│   │       ├── HospitalService.java             # Hospital CRUD + toggle active
│   │       ├── LaboratoryService.java           # Laboratory CRUD
│   │       ├── LabTestService.java              # Lab test ordering + status updates
│   │       ├── PatientService.java              # Patient profile management
│   │       ├── PharmacyService.java             # Pharmacy CRUD
│   │       ├── PrescriptionService.java         # Prescription lifecycle (5 statuses)
│   │       └── UserService.java                 # User CRUD + email lookup
│   ├── src/main/resources/
│   │   └── application.yaml                     # Spring Boot configuration
│   ├── Dockerfile                               # Multi-stage Docker build (Maven + JRE 17)
│   ├── pom.xml                                  # Maven project definition & dependencies
│   └── mvnw / mvnw.cmd                          # Maven wrapper scripts (Linux/Windows)
│
├── frontend/                         # React Frontend Application
│   ├── src/
│   │   ├── api/                                 # API Service Modules (13 modules)
│   │   │   ├── axios.js                         # Axios instance with JWT interceptor + prod URL
│   │   │   ├── adminApi.js                      # Admin API calls
│   │   │   ├── appointmentApi.js                # Appointment API calls
│   │   │   ├── authApi.js                       # Login/Register API calls
│   │   │   ├── availabilityApi.js               # Availability API calls
│   │   │   ├── consultationApi.js               # Consultation API calls
│   │   │   ├── doctorApi.js                     # Doctor API calls
│   │   │   ├── hospitalApi.js                   # Hospital API calls
│   │   │   ├── labApi.js                        # Lab test API calls
│   │   │   ├── laboratoryApi.js                 # Laboratory API calls
│   │   │   ├── patientApi.js                    # Patient API calls
│   │   │   ├── pharmacyApi.js                   # Pharmacy API calls
│   │   │   └── prescriptionApi.js               # Prescription API calls
│   │   ├── components/                          # Reusable UI Components (11 components)
│   │   │   ├── Biography.jsx                    # About page biography section
│   │   │   ├── Departments.jsx                  # Department carousel with icons
│   │   │   ├── Footer.jsx                       # Site footer with developer credits
│   │   │   ├── Hero.jsx                         # Landing page hero section
│   │   │   ├── Layout.jsx                       # Authenticated layout with sidebar navbar
│   │   │   ├── MessageForm.jsx                  # Contact/message form
│   │   │   ├── Navbar.jsx                       # Authenticated sidebar navigation
│   │   │   ├── ProtectedRoute.jsx               # Route guard with role checking
│   │   │   ├── PublicNavbar.jsx                 # Public pages top navigation bar
│   │   │   ├── Stats.jsx                        # Statistics display component
│   │   │   └── StatusBadge.jsx                  # Colored status badge component
│   │   ├── context/
│   │   │   └── AuthContext.jsx                  # React Context for auth state management
│   │   ├── pages/                               # Page Components (21 pages)
│   │   │   ├── Home.jsx                         # Public landing page
│   │   │   ├── About.jsx                        # About page with team info
│   │   │   ├── auth/
│   │   │   │   ├── Login.jsx                    # Login form
│   │   │   │   └── Register.jsx                 # Registration form (role selection)
│   │   │   ├── admin/                           # Admin Dashboard Pages (6 pages)
│   │   │   │   ├── Dashboard.jsx                # Admin overview with stats
│   │   │   │   ├── Users.jsx                    # User management (activate/deactivate)
│   │   │   │   ├── Doctors.jsx                  # Doctor approval/rejection
│   │   │   │   ├── Hospitals.jsx                # Hospital CRUD management
│   │   │   │   ├── Laboratories.jsx             # Laboratory management
│   │   │   │   └── Pharmacies.jsx               # Pharmacy management
│   │   │   ├── doctor/                          # Doctor Dashboard Pages (6 pages)
│   │   │   │   ├── Dashboard.jsx                # Doctor overview
│   │   │   │   ├── Profile.jsx                  # Doctor profile editor
│   │   │   │   ├── Appointments.jsx             # Appointment management (confirm/reject/complete)
│   │   │   │   ├── Availability.jsx             # Schedule/slot management
│   │   │   │   ├── Consultations.jsx            # Create/view consultation records
│   │   │   │   └── Prescriptions.jsx            # Create/manage prescriptions
│   │   │   ├── patient/                         # Patient Dashboard Pages (7 pages)
│   │   │   │   ├── Dashboard.jsx                # Patient overview
│   │   │   │   ├── DoctorSearch.jsx             # Search doctors by specialization/city
│   │   │   │   ├── BookAppointment.jsx          # Book appointment with doctor
│   │   │   │   ├── MyAppointments.jsx           # View appointment history
│   │   │   │   ├── MyConsultations.jsx          # View consultation records
│   │   │   │   ├── MyPrescriptions.jsx          # View prescriptions
│   │   │   │   └── MyLabReports.jsx             # View lab test results
│   │   │   ├── lab/
│   │   │   │   └── Dashboard.jsx                # Lab technician dashboard
│   │   │   └── pharmacist/
│   │   │       └── Dashboard.jsx                # Pharmacist dashboard
│   │   ├── App.jsx                              # Root component with all routes
│   │   ├── main.jsx                             # React entry point (ReactDOM.createRoot)
│   │   └── index.css                            # Global styles + Tailwind CSS import
│   ├── public/
│   │   └── _redirects                           # Netlify SPA redirect rule
│   ├── index.html                               # HTML entry point
│   ├── vite.config.js                           # Vite configuration + API proxy
│   └── package.json                             # Node.js dependencies & scripts
│
├── docs/                             # Documentation assets (images, diagrams)
├── netlify.toml                      # Netlify deployment configuration
├── render.yaml                       # Render.com deployment configuration
├── .gitignore                        # Git ignore rules
└── README.md                        # This file
```

---

## ⚙ Backend Architecture — Detailed

### Layered Architecture (MVC + Service + Repository Pattern)

```
HTTP Request → Controller → Service → Repository → Database
HTTP Response ← Controller ← Service ← Repository ← Database
```

| Layer | Count | Responsibility |
|---|---|---|
| **Controllers** | 15 | Handle HTTP requests, validate input, return responses |
| **Services** | 15 | Business logic, data transformation, workflow orchestration |
| **Repositories** | 15 | Database CRUD operations via Spring Data JPA |
| **Entities** | 15 | JPA-mapped database tables with relationships |
| **DTOs** | 23 | Request/Response payloads (decoupled from entities) |
| **Enums** | 7 | Type-safe constants for statuses and categories |
| **Security** | 3 | JWT filter, user details service, token service |
| **Config** | 3 | CORS, Security chain, DataSeeder |
| **Exception** | 4 | Global error handling with standardized responses |

### Key Design Decisions

1. **DTO Pattern** — All API responses use DTOs (not raw entities) to prevent circular JSON serialization and control data exposure
2. **Constructor Injection** — All dependencies are injected via constructors (no @Autowired field injection) for better testability
3. **@PreAuthorize** — Method-level security annotations (e.g., `@PreAuthorize("hasRole('DOCTOR')")`)
4. **Builder Pattern** — All entities use Lombok's `@Builder` for clean object creation
5. **Stateless Sessions** — `SessionCreationPolicy.STATELESS` — no HTTP sessions, only JWT tokens
6. **Global Exception Handler** — `@ControllerAdvice` catches all exceptions and returns consistent error JSON

### Application Configuration (`application.yaml`)

```yaml
spring:
  application:
    name: arosetu-backend
  datasource:
    url: jdbc:h2:mem:arosetu_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    username: sa
    driver-class-name: org.h2.Driver
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
app:
  jwt:
    secret: ${JWT_SECRET:YVJvc2V0dUhlYW...}  # Base64-encoded 256-bit secret
    expiration: 86400000                       # 24 hours in milliseconds
server:
  port: 8080
```

---

## 🎨 Frontend Architecture — Detailed

### Component Architecture

```
App.jsx (Root)
├── BrowserRouter (React Router 7)
│   ├── AuthProvider (Context API for auth state)
│   ├── ToastContainer (Global notifications)
│   └── Routes
│       ├── Public Routes (/, /about, /login, /register)
│       ├── Patient Routes (/patient/*) → ProtectedRoute(PATIENT) → Layout
│       ├── Doctor Routes (/doctor/*) → ProtectedRoute(DOCTOR) → Layout
│       ├── Lab Routes (/lab/*) → ProtectedRoute(LAB_TECHNICIAN) → Layout
│       ├── Pharmacist Routes (/pharmacist/*) → ProtectedRoute(PHARMACIST) → Layout
│       └── Admin Routes (/admin/*) → ProtectedRoute(ADMIN) → Layout
```

### State Management

- **AuthContext** — React Context API for global authentication state
  - Stores: `user` object, `token`, `isAuthenticated` flag
  - Persists to `localStorage` for session persistence across page refreshes
  - Provides: `login()`, `logout()`, `register()` functions

### API Layer Architecture

```
Component → API Module → Axios Instance → Backend API
                             ↓
                   JWT token auto-attached
                   via request interceptor
                             ↓
                   401 responses auto-redirect
                   to /login via response interceptor
```

**Axios Configuration:**
```javascript
// Production: calls Render backend directly
// Development: Vite proxy forwards /api to localhost:8080
const API_BASE_URL = import.meta.env.PROD
  ? 'https://arosetu.onrender.com/api'
  : '/api';
```

### Routing & Navigation

| Path | Component | Access |
|---|---|---|
| `/` | Home | Public |
| `/about` | About | Public |
| `/login` | Login | Public |
| `/register` | Register | Public |
| `/patient/dashboard` | PatientDashboard | Patient only |
| `/patient/doctors` | DoctorSearch | Patient only |
| `/patient/book-appointment/:doctorId` | BookAppointment | Patient only |
| `/patient/appointments` | MyAppointments | Patient only |
| `/patient/consultations` | MyConsultations | Patient only |
| `/patient/prescriptions` | MyPrescriptions | Patient only |
| `/patient/lab-reports` | MyLabReports | Patient only |
| `/doctor/dashboard` | DoctorDashboard | Doctor only |
| `/doctor/profile` | DoctorProfile | Doctor only |
| `/doctor/appointments` | DoctorAppointments | Doctor only |
| `/doctor/consultations` | DoctorConsultations | Doctor only |
| `/doctor/prescriptions` | DoctorPrescriptions | Doctor only |
| `/doctor/availability` | DoctorAvailability | Doctor only |
| `/lab/dashboard` | LabDashboard | Lab Technician only |
| `/pharmacist/dashboard` | PharmacistDashboard | Pharmacist only |
| `/admin/dashboard` | AdminDashboard | Admin only |
| `/admin/users` | AdminUsers | Admin only |
| `/admin/doctors` | AdminDoctors | Admin only |
| `/admin/hospitals` | AdminHospitals | Admin only |
| `/admin/laboratories` | AdminLaboratories | Admin only |
| `/admin/pharmacies` | AdminPharmacies | Admin only |

---

## 🗃 Database Design

### Entity-Relationship Summary

```
User (1) ──── (1) Doctor ──── (*) DoctorHospital ──── (1) Hospital
User (1) ──── (1) Patient                                    │
                     │                                   Department (*)
                     │
              Appointment (*) ──── (1) Doctor
                     │            ──── (1) Hospital
                     │
              Consultation (1:1) ──── Appointment
                     │
              Prescription (*) ──── (1) Pharmacy
                     │
              PrescriptionItem (*)
                     │
              LabTestRequest (*) ──── (1) Laboratory
                     │
              FollowUp (*) ──── (1) Consultation
```

### All 15 Database Tables

| # | Table | Key Columns | Relationships |
|---|---|---|---|
| 1 | `users` | id, name, email, password, phone, role, is_active, created_at, updated_at | Base table for all roles |
| 2 | `doctors` | id, user_id, specialization, qualification, experience_years, license_number, consultation_fee, approval_status, about | OneToOne → User |
| 3 | `patients` | id, user_id, date_of_birth, gender, blood_group, address, emergency_contact | OneToOne → User |
| 4 | `hospitals` | id, name, type, address, city, state, pin_code, contact_number, email, is_active | Standalone |
| 5 | `departments` | id, hospital_id, name, is_active | ManyToOne → Hospital |
| 6 | `doctor_hospitals` | id, doctor_id, hospital_id, department_id | ManyToOne → Doctor, Hospital, Department |
| 7 | `availabilities` | id, doctor_id, hospital_id, day_of_week, start_time, end_time, slot_duration_minutes | ManyToOne → Doctor, Hospital |
| 8 | `appointments` | id, patient_id, doctor_id, hospital_id, appointment_date, start_time, end_time, status, reason | ManyToOne → Patient, Doctor, Hospital |
| 9 | `consultations` | id, appointment_id, patient_id, doctor_id, symptoms, observations, diagnosis, clinical_notes, treatment_plan, consultation_date | OneToOne → Appointment; ManyToOne → Patient, Doctor |
| 10 | `prescriptions` | id, consultation_id, doctor_id, patient_id, pharmacy_id, status, notes | ManyToOne → Consultation, Doctor, Patient, Pharmacy |
| 11 | `prescription_items` | id, prescription_id, medicine_name, dosage, frequency, duration, instructions | ManyToOne → Prescription |
| 12 | `laboratories` | id, name, address, city, state, contact_number, is_active | Standalone |
| 13 | `pharmacies` | id, name, address, city, state, contact_number, is_active | Standalone |
| 14 | `lab_test_requests` | id, patient_id, doctor_id, laboratory_id, consultation_id, test_name, status, instructions, result_summary, report_file_url | ManyToOne → Patient, Doctor, Laboratory, Consultation |
| 15 | `follow_ups` | id, consultation_id, doctor_id, patient_id, follow_up_date, reason, instructions | ManyToOne → Consultation, Doctor, Patient |

### Enum Values

| Enum | Values |
|---|---|
| `Role` | `ADMIN`, `PATIENT`, `DOCTOR`, `LAB_TECHNICIAN`, `PHARMACIST` |
| `AppointmentStatus` | `REQUESTED`, `CONFIRMED`, `COMPLETED`, `CANCELLED`, `REJECTED` |
| `ApprovalStatus` | `PENDING`, `APPROVED`, `REJECTED` |
| `PrescriptionStatus` | `DRAFT`, `PRESCRIBED`, `SENT_TO_PHARMACY`, `READY`, `DISPENSED` |
| `LabTestStatus` | `REQUESTED`, `ACCEPTED`, `SAMPLE_COLLECTED`, `PROCESSING`, `COMPLETED`, `CANCELLED` |
| `HospitalType` | `HOSPITAL`, `CLINIC`, `DIAGNOSTIC_CENTER` |
| `Gender` | `MALE`, `FEMALE`, `OTHER` |

---

## 🔌 REST API Endpoints — Complete List

### Authentication (`/api/auth`) — Public

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/auth/register` | Register new user (Patient/Doctor/Lab Tech/Pharmacist) | No |
| POST | `/api/auth/login` | Login and receive JWT token | No |

### Doctors (`/api/doctors`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| GET | `/api/doctors` | List all approved doctors | Yes |
| GET | `/api/doctors/{id}` | Get doctor by ID | Yes |
| GET | `/api/doctors/search?specialization=&city=` | Search doctors (public) | No |
| GET | `/api/doctors/me` | Get logged-in doctor's profile | Doctor |
| PUT | `/api/doctors/me` | Update doctor profile | Doctor |
| DELETE | `/api/doctors/{id}` | Delete doctor | Admin |

### Patients (`/api/patients`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| GET | `/api/patients/me` | Get logged-in patient's profile | Patient |
| PUT | `/api/patients/me` | Update patient profile | Patient |
| GET | `/api/patients` | List all patients | Doctor/Admin |
| GET | `/api/patients/{id}` | Get patient by ID | Doctor/Admin |
| DELETE | `/api/patients/{id}` | Delete patient | Admin |

### Appointments (`/api/appointments`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/appointments` | Create new appointment | Patient |
| GET | `/api/appointments/me` | Get my appointments | Patient/Doctor |
| GET | `/api/appointments/{id}` | Get appointment by ID | Yes |
| PATCH | `/api/appointments/{id}/confirm` | Confirm appointment | Doctor |
| PATCH | `/api/appointments/{id}/reject` | Reject appointment | Doctor |
| PATCH | `/api/appointments/{id}/cancel` | Cancel appointment | Patient/Doctor |
| PATCH | `/api/appointments/{id}/complete` | Mark as completed | Doctor |

### Consultations (`/api/consultations`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/consultations` | Create consultation record | Doctor |
| GET | `/api/consultations/{id}` | Get consultation by ID | Yes |
| GET | `/api/consultations/me` | Get my consultations | Patient/Doctor |
| PUT | `/api/consultations/{id}` | Update consultation | Doctor |

### Prescriptions (`/api/prescriptions`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/prescriptions` | Create prescription | Doctor |
| GET | `/api/prescriptions/{id}` | Get prescription by ID | Yes |
| GET | `/api/prescriptions/me` | Get my prescriptions | Patient/Doctor |
| GET | `/api/prescriptions/pharmacy/{pharmacyId}` | Get prescriptions by pharmacy | Pharmacist |
| PATCH | `/api/prescriptions/{id}/prescribe` | Mark as prescribed | Doctor |
| PATCH | `/api/prescriptions/{id}/send-to-pharmacy` | Send to pharmacy | Doctor |
| PATCH | `/api/prescriptions/{id}/ready` | Mark as ready | Pharmacist |
| PATCH | `/api/prescriptions/{id}/dispense` | Mark as dispensed | Pharmacist |

### Hospitals (`/api/hospitals`)

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| GET | `/api/hospitals/public` | List active hospitals (public) | No |
| GET | `/api/hospitals` | List all hospitals | Admin |
| GET | `/api/hospitals/{id}` | Get hospital by ID | Yes |
| POST | `/api/hospitals` | Create hospital | Admin |
| PUT | `/api/hospitals/{id}` | Update hospital | Admin |
| PATCH | `/api/hospitals/{id}/toggle` | Toggle active/inactive | Admin |

### Admin (`/api/admin`) — Admin Only

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/admin/users` | List all users |
| PATCH | `/api/admin/users/{id}/toggle` | Activate/deactivate user |
| GET | `/api/admin/doctors/pending` | List pending doctor approvals |
| PATCH | `/api/admin/doctors/{id}/approve` | Approve doctor |
| PATCH | `/api/admin/doctors/{id}/reject` | Reject doctor |
| GET | `/api/admin/stats` | Get platform statistics |

### Additional Endpoints

| Resource | Endpoints | Auth |
|---|---|---|
| Availability (`/api/availability`) | POST, GET /me, GET /doctor/{id}, DELETE /{id} | Doctor |
| Laboratories (`/api/laboratories`) | GET /active, GET, POST, PUT, PATCH toggle | Admin |
| Pharmacies (`/api/pharmacies`) | GET /active, GET, POST, PUT, PATCH toggle | Admin |
| Lab Tests (`/api/lab-tests`) | POST, GET /me, PATCH status updates | Doctor/Lab Tech |
| Follow-Ups (`/api/follow-ups`) | POST, GET /me | Doctor |
| Departments (`/api/departments`) | POST, GET /hospital/{id}, DELETE | Admin |
| Doctor-Hospitals | POST link, GET by doctor/hospital, DELETE | Doctor/Admin |

**Total: 45+ REST API Endpoints**

---

## 🔐 Authentication & Security

### JWT (JSON Web Token) Flow

```
1. User sends POST /api/auth/login with {email, password}
2. Server validates credentials against BCrypt-hashed password in DB
3. Server generates JWT token with claims:
   - subject: user email
   - role: user role (ADMIN/PATIENT/DOCTOR/etc.)
   - userId: user database ID
   - name: user display name
   - issuedAt: current timestamp
   - expiration: current time + 24 hours
4. Server signs JWT with HMAC-SHA256 (Base64-encoded 256-bit secret)
5. Client stores JWT in localStorage
6. Client sends JWT in Authorization header: "Bearer <token>"
7. JwtAuthenticationFilter extracts & validates token on every request
8. Invalid/expired tokens → 401 Unauthorized → redirect to /login
```

### Security Configuration

```java
// Public endpoints (no authentication required):
.requestMatchers("/api/auth/**").permitAll()
.requestMatchers("/api/doctors/search/**").permitAll()
.requestMatchers("/api/hospitals/public/**").permitAll()
.requestMatchers("/h2-console/**").permitAll()

// Everything else requires authentication:
.anyRequest().authenticated()
```

### Password Security
- **Algorithm:** BCrypt (via Spring Security's BCryptPasswordEncoder)
- **Salt Rounds:** Default (10 rounds)
- **Storage:** Only BCrypt hash stored in database, never plain text

### CORS Configuration
```java
Allowed Origins: ["http://localhost:5173", "http://localhost:3000", "https://arosetu.netlify.app"]
Allowed Methods: [GET, POST, PUT, DELETE, PATCH, OPTIONS]
Allowed Headers: [*]
Credentials: true
```

---

## 👥 User Roles & Access Control

### Role Hierarchy & Permissions

| Feature | Admin | Doctor | Patient | Lab Tech | Pharmacist |
|---|:---:|:---:|:---:|:---:|:---:|
| View Dashboard | ✅ | ✅ | ✅ | ✅ | ✅ |
| Manage Users | ✅ | ❌ | ❌ | ❌ | ❌ |
| Manage Hospitals | ✅ | ❌ | ❌ | ❌ | ❌ |
| Manage Labs/Pharmacies | ✅ | ❌ | ❌ | ❌ | ❌ |
| Approve/Reject Doctors | ✅ | ❌ | ❌ | ❌ | ❌ |
| View Platform Stats | ✅ | ❌ | ❌ | ❌ | ❌ |
| Search Doctors | ❌ | ❌ | ✅ | ❌ | ❌ |
| Book Appointment | ❌ | ❌ | ✅ | ❌ | ❌ |
| View My Appointments | ❌ | ✅ | ✅ | ❌ | ❌ |
| Confirm/Reject Appointment | ❌ | ✅ | ❌ | ❌ | ❌ |
| Create Consultation | ❌ | ✅ | ❌ | ❌ | ❌ |
| View My Consultations | ❌ | ✅ | ✅ | ❌ | ❌ |
| Create Prescription | ❌ | ✅ | ❌ | ❌ | ❌ |
| Send to Pharmacy | ❌ | ✅ | ❌ | ❌ | ❌ |
| View My Prescriptions | ❌ | ✅ | ✅ | ❌ | ❌ |
| Mark Ready/Dispense | ❌ | ❌ | ❌ | ❌ | ✅ |
| Manage Availability | ❌ | ✅ | ❌ | ❌ | ❌ |
| Update Profile | ❌ | ✅ | ✅ | ❌ | ❌ |
| Order Lab Tests | ❌ | ✅ | ❌ | ❌ | ❌ |
| Process Lab Tests | ❌ | ❌ | ❌ | ✅ | ❌ |
| View Lab Reports | ❌ | ❌ | ✅ | ✅ | ❌ |

---

## ⭐ Features — Module Wise

### Module 1: Authentication & User Management
- User registration with role selection (Patient, Doctor, Lab Tech, Pharmacist)
- JWT-based login with 24-hour token expiration
- Password hashing with BCrypt
- Auto-redirect to role-specific dashboard after login
- 401 handling with auto-logout and redirect

### Module 2: Admin Dashboard
- Platform statistics (total users, doctors, patients, hospitals, labs, pharmacies)
- User management (view all, activate/deactivate accounts)
- Doctor approval workflow (approve/reject pending doctors)
- Hospital CRUD (create, update, toggle active/inactive)
- Laboratory CRUD management
- Pharmacy CRUD management

### Module 3: Patient Portal
- Doctor search with filters (specialization, city)
- Appointment booking with hospital and time slot selection
- Appointment history with status tracking
- Consultation records with diagnosis and treatment details
- Prescription viewing with medicine details
- Lab report viewing

### Module 4: Doctor Portal
- Professional profile management (specialization, qualification, experience, fees)
- Appointment management (view, confirm, reject, complete, cancel)
- Availability/schedule management (day, time, hospital, slot duration)
- Clinical consultation creation (symptoms, observations, diagnosis, treatment plan)
- Prescription writing (medicines, dosage, frequency, duration, instructions)
- Send prescriptions to pharmacy
- Lab test ordering

### Module 5: Pharmacy Portal
- View prescriptions sent by doctors
- Mark prescriptions as "Ready"
- Mark prescriptions as "Dispensed"

### Module 6: Laboratory Portal
- View lab test requests from doctors
- Update test status (Accepted → Sample Collected → Processing → Completed)
- Add result summaries

---

## 🔄 Data Flow & Workflow

### Complete Healthcare Workflow

```
Step 1: Patient Registration
   Patient registers → Account created → Patient profile auto-created
   
Step 2: Doctor Registration & Approval
   Doctor registers → Account created (PENDING) → Admin approves → Status: APPROVED
   Doctor sets up: profile, hospital links, availability schedule

Step 3: Appointment Booking
   Patient searches doctors → Selects doctor → Chooses hospital & time slot
   → Appointment created (Status: REQUESTED)

Step 4: Appointment Confirmation
   Doctor views appointment requests → Confirms (CONFIRMED) or Rejects (REJECTED)
   Patient can also Cancel (CANCELLED)

Step 5: Consultation
   Doctor marks appointment as COMPLETED → Creates consultation record
   Records: symptoms, observations, diagnosis, clinical notes, treatment plan

Step 6: Prescription
   Doctor creates prescription (Status: DRAFT)
   → Adds medicine items (name, dosage, frequency, duration, instructions)
   → Marks as PRESCRIBED → Sends to pharmacy (SENT_TO_PHARMACY)

Step 7: Pharmacy Fulfillment
   Pharmacist views prescription → Marks READY → Patient picks up → Marks DISPENSED

Step 8: Lab Tests (Optional)
   Doctor orders lab test → Lab technician accepts → Collects sample
   → Processes → Completes with results → Patient views report

Step 9: Follow-Up (Optional)
   Doctor schedules follow-up appointment → Patient notified
```

### Prescription Status State Machine

```
DRAFT → PRESCRIBED → SENT_TO_PHARMACY → READY → DISPENSED
  ↑         (Doctor)      (Doctor)        (Pharmacist) (Pharmacist)
(Doctor creates)
```

### Appointment Status State Machine

```
REQUESTED → CONFIRMED → COMPLETED
    ↓           ↓
 REJECTED    CANCELLED
(Doctor)   (Patient/Doctor)
```

---

## ☁ Deployment Architecture

```
┌──────────────────┐         ┌──────────────────┐
│   NETLIFY CDN    │  HTTPS  │   RENDER.COM     │
│                  │ ──────→ │                  │
│  React Frontend  │  REST   │ Spring Boot API  │
│  (Static Files)  │  /api/* │ (Docker Container)│
│                  │         │                  │
│ arosetu.         │         │ arosetu.         │
│ netlify.app      │         │ onrender.com     │
└──────────────────┘         └────────┬─────────┘
                                      │ JDBC
                              ┌───────▼─────────┐
                              │  H2 In-Memory   │
                              │   Database      │
                              │  (arosetu_db)   │
                              └─────────────────┘
```

### Frontend Deployment (Netlify)
- **Platform:** Netlify (Free Tier)
- **Source:** GitHub auto-deploy on push to `main`
- **Build Command:** `npm install && npm run build`
- **Publish Directory:** `frontend/dist`
- **SPA Routing:** `/* → /index.html` (200 rewrite)
- **Configuration:** `netlify.toml`

### Backend Deployment (Render.com)
- **Platform:** Render.com (Free Tier)
- **Runtime:** Docker (multi-stage build)
- **Dockerfile:** Maven 3.9 + Eclipse Temurin JRE 17
- **Auto-deploy:** On push to `main`
- **Environment Variables:** `JWT_SECRET`
- **Cold Start:** ~30-60 seconds after 15 min inactivity (free tier)
- **Configuration:** `render.yaml`

### Docker Configuration (Backend)
```dockerfile
# Build Stage — compiles JAR with Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# Run Stage — lightweight JRE image
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## 📊 Pre-Seeded Demo Data

The `DataSeeder.java` auto-populates the database on every startup with 40+ records:

### Demo Login Credentials

| Role | Email | Password |
|---|---|---|
| **Admin** | admin@arosetu.com | admin123 |
| **Patient** | rahul.sharma@email.com | patient123 |
| **Patient** | priya.patel@email.com | patient123 |
| **Doctor** | dr.arun@email.com | doctor123 |
| **Doctor** | dr.meena@email.com | doctor123 |
| **Doctor** | dr.sanjay@email.com | doctor123 |
| **Lab Technician** | labtech@email.com | lab123 |
| **Pharmacist** | pharmacist@email.com | pharma123 |

### Seeded Data Summary

| Entity | Count | Details |
|---|---|---|
| Users | 8 | 1 Admin, 2 Patients, 3 Doctors, 1 Lab Tech, 1 Pharmacist |
| Doctors | 3 | All APPROVED; Cardiology, Dermatology, Orthopedics |
| Patients | 2 | With DOB, gender, blood group, address, emergency contact |
| Hospitals | 3 | City Hospital, MedCare Clinic, Apollo Diagnostic Center |
| Doctor-Hospital Links | 5 | Mapping doctors to hospitals |
| Availability Slots | 11 | Weekly schedules for all doctors across hospitals |
| Laboratories | 2 | City Lab, MedTest Lab |
| Pharmacies | 2 | HealthPlus Pharmacy, MediCare Pharmacy |
| Appointments | 6 | 4 COMPLETED, 1 CONFIRMED, 1 REQUESTED |
| Consultations | 4 | With full clinical data (symptoms, diagnosis, etc.) |
| Prescriptions | 3 | PRESCRIBED, SENT_TO_PHARMACY, DRAFT statuses |
| Prescription Items | 6 | Medications with dosage, frequency, duration |
| **Total Records** | **40+** | |

---

## 🚀 Setup & Installation Guide

### Prerequisites

| Software | Version | Download |
|---|---|---|
| Java JDK | 17 or higher | [adoptium.net](https://adoptium.net/) |
| Node.js | 18 or higher | [nodejs.org](https://nodejs.org/) |
| npm | 9 or higher | Comes with Node.js |
| Git | Latest | [git-scm.com](https://git-scm.com/) |

### Step 1: Clone the Repository

```bash
git clone https://github.com/prasannagitt/arosetu.git
cd arosetu
```

### Step 2: Start the Backend

```bash
cd backend

# Windows:
.\mvnw.cmd spring-boot:run

# Linux/Mac:
./mvnw spring-boot:run
```

The backend starts at **http://localhost:8080**
H2 Console available at **http://localhost:8080/h2-console**

### Step 3: Start the Frontend

```bash
# Open a new terminal
cd frontend
npm install
npm run dev
```

The frontend starts at **http://localhost:5173**

### Step 4: Access the Application

Open your browser and go to **http://localhost:5173**

Use the [demo credentials](#demo-login-credentials) to log in with any role.

### Build for Production

```bash
# Backend JAR:
cd backend
.\mvnw.cmd clean package -DskipTests
# Output: backend/target/arosetu-backend-0.0.1-SNAPSHOT.jar

# Frontend static files:
cd frontend
npm run build
# Output: frontend/dist/
```

---

## 📸 Screenshots & UI Pages

### Public Pages
| Page | URL | Description |
|---|---|---|
| Home | `/` | Landing page with hero, departments carousel, stats |
| About | `/about` | Team information, platform biography |
| Login | `/login` | Email/password login form |
| Register | `/register` | Registration with role selection dropdown |

### Admin Pages
| Page | URL | Description |
|---|---|---|
| Dashboard | `/admin/dashboard` | Platform statistics overview |
| Users | `/admin/users` | User list with activate/deactivate toggle |
| Doctors | `/admin/doctors` | Pending doctor approvals |
| Hospitals | `/admin/hospitals` | Hospital CRUD management |
| Laboratories | `/admin/laboratories` | Laboratory management |
| Pharmacies | `/admin/pharmacies` | Pharmacy management |

### Doctor Pages
| Page | URL | Description |
|---|---|---|
| Dashboard | `/doctor/dashboard` | Doctor overview |
| Profile | `/doctor/profile` | Edit specialization, qualification, etc. |
| Appointments | `/doctor/appointments` | Manage appointment requests |
| Availability | `/doctor/availability` | Set weekly schedule/slots |
| Consultations | `/doctor/consultations` | Create clinical records |
| Prescriptions | `/doctor/prescriptions` | Write & send prescriptions |

### Patient Pages
| Page | URL | Description |
|---|---|---|
| Dashboard | `/patient/dashboard` | Patient overview |
| Doctor Search | `/patient/doctors` | Search by specialization/city |
| Book Appointment | `/patient/book-appointment/:id` | Select hospital, date, time |
| My Appointments | `/patient/appointments` | Appointment history & status |
| My Consultations | `/patient/consultations` | View consultation records |
| My Prescriptions | `/patient/prescriptions` | View prescription details |
| My Lab Reports | `/patient/lab-reports` | View lab test results |

---

## 📈 Project Statistics

| Metric | Count |
|---|---|
| **Total Source Files** | 180+ |
| **Backend Java Files** | 105 |
| **Frontend JSX/JS Files** | 53 |
| **REST API Endpoints** | 45+ |
| **Database Tables** | 15 |
| **JPA Entities** | 15 |
| **JPA Repositories** | 15 |
| **Service Classes** | 15 |
| **Controller Classes** | 15 |
| **DTO Classes** | 23 |
| **Enum Definitions** | 7 |
| **React Pages** | 21 |
| **React Components** | 11 |
| **API Modules** | 13 |
| **User Roles** | 5 |
| **Frontend Routes** | 26 |
| **Pre-seeded Records** | 40+ |
| **Frontend JS Bundle** | 468 KB (135 KB gzipped) |
| **Frontend CSS Bundle** | 44.8 KB (9.1 KB gzipped) |
| **Frontend Build Time** | ~600ms |

---

## 🔮 Future Enhancements

1. **Email Notifications** — Send email alerts for appointment confirmations, lab results
2. **Real-time Chat** — WebSocket-based doctor-patient messaging
3. **Payment Integration** — Razorpay/Stripe for consultation fees
4. **File Uploads** — Lab report PDFs, medical images, doctor certificates
5. **Search & Filtering** — Advanced search with pagination and sorting
6. **Appointment Calendar** — Calendar view for doctor availability
7. **Mobile Responsive** — Enhanced mobile UI with PWA support
8. **PostgreSQL Migration** — Production database for persistent storage
9. **Role-Based Dashboard Widgets** — Customizable dashboard cards
10. **Analytics & Reports** — Charts for admin (appointments per month, revenue, etc.)
11. **Multi-Language Support** — Internationalization (i18n)
12. **Audit Logging** — Track all data changes with timestamps and user info

---

## 👨‍💻 Developers

| Developer | Role |
|---|---|
| **Prasanna Ram** | Full Stack Developer |
| **Sudarshan K S** | Full Stack Developer |

**Course:** Full Stack Development
**Academic Year:** 2026

---

## 📄 License

This project is licensed under the **MIT License**.

---

> **AroSetu** — Bridging Healthcare, Connecting Lives 🏥❤️
