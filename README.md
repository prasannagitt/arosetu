# 🏥 AroSetu — Healthcare Coordination Platform

> A comprehensive, full-stack healthcare coordination platform that connects **Patients**, **Doctors**, **Administrators**, **Lab Technicians**, and **Pharmacists** in a seamless digital healthcare ecosystem.

**Developed by:** Prasanna Ram & Sudarshan K S

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [System Architecture](#system-architecture)
- [Database Design](#database-design)
- [User Roles & Workflows](#user-roles--workflows)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Demo Credentials](#demo-credentials)
- [Screenshots](#screenshots)
- [Future Enhancements](#future-enhancements)
- [License](#license)

---

## 📖 About the Project

**AroSetu** (meaning "Health Bridge" in Sanskrit) is a state-of-the-art healthcare coordination platform built as a full-stack web application. It bridges the gap between patients and healthcare providers by digitizing the entire healthcare workflow — from appointment booking and doctor consultations to prescription management, lab test tracking, and pharmacy fulfillment.

The platform supports **5 distinct user roles**, each with dedicated dashboards and workflows, managing **15 database entities** through **40+ RESTful API endpoints**, all secured with **JWT-based authentication** and **role-based access control (RBAC)**.

### 🎯 Problem Statement

Traditional healthcare systems face challenges like:
- Fragmented communication between patients, doctors, labs, and pharmacies
- Manual appointment scheduling leading to errors and delays
- Paper-based prescriptions causing readability and tracking issues
- No unified platform for end-to-end healthcare workflow management

### 💡 Solution

AroSetu provides a **unified digital platform** that enables:
- Online appointment booking with real-time slot availability
- Digital consultations with structured clinical documentation
- Electronic prescriptions with pharmacy routing
- Lab test ordering, tracking, and result delivery
- Role-based dashboards for all healthcare stakeholders

---

## ✨ Key Features

### For Patients
| Feature | Description |
|---------|-------------|
| **Doctor Search** | Search doctors by specialization and city with real-time results |
| **Appointment Booking** | Book appointments with available time slots based on doctor availability |
| **My Appointments** | View, track, and cancel appointments |
| **My Consultations** | Access consultation records including symptoms, diagnosis, and treatment plans |
| **My Prescriptions** | View digital prescriptions with medication details |
| **My Lab Reports** | Track lab test status and view results |
| **Dashboard** | Overview of all appointments, consultations, prescriptions, and lab reports |

### For Doctors
| Feature | Description |
|---------|-------------|
| **Appointment Management** | Confirm, reject, complete, or cancel patient appointments |
| **Consultation Records** | Create detailed consultation notes (symptoms, diagnosis, clinical notes, treatment plan) |
| **Prescription Writing** | Create digital prescriptions with multiple medications, send to pharmacy |
| **Availability Management** | Set day-of-week availability per hospital with custom time slots |
| **Profile Management** | Update specialization, qualification, experience, consultation fee |
| **Dashboard** | Overview of pending requests, confirmed appointments, total consultations |

### For Administrators
| Feature | Description |
|---------|-------------|
| **User Management** | View all users, toggle active/inactive status |
| **Doctor Approval** | Approve or reject doctor registrations |
| **Hospital Management** | CRUD operations for hospitals (Hospital, Clinic, Diagnostic Center) |
| **Laboratory Management** | CRUD operations for laboratories |
| **Pharmacy Management** | CRUD operations for pharmacies |
| **Dashboard** | Platform-wide statistics (users, doctors, patients, hospitals, labs, pharmacies) |

### For Lab Technicians
| Feature | Description |
|---------|-------------|
| **Lab Selection** | Select assigned laboratory |
| **Test Workflow** | Accept → Collect Sample → Process → Enter Results → Complete |
| **Result Entry** | Enter result summary and optional report file URL |

### For Pharmacists
| Feature | Description |
|---------|-------------|
| **Pharmacy Selection** | Select assigned pharmacy |
| **Prescription Workflow** | Receive prescriptions → Mark Ready → Dispense |
| **Medication Details** | View full prescription details including all medications |

### Platform-Wide Features
- 🔐 **JWT Authentication** with token-based session management
- 🛡️ **Role-Based Access Control** (RBAC) with 5 distinct roles
- 📱 **Responsive Design** — works on desktop, tablet, and mobile
- 🔄 **Real-time Validation** with form-level and server-level error handling
- 🎨 **Professional UI** with Tailwind CSS utility-first styling
- 📊 **Data Seeding** — demo data auto-populated on startup for testing

---

## 🛠️ Technology Stack

### Backend Technologies

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17 (LTS) | Primary programming language |
| **Spring Boot** | 3.4.5 | Application framework & auto-configuration |
| **Spring Web (MVC)** | 6.x | RESTful API controllers & request handling |
| **Spring Data JPA** | 3.4.x | ORM & database abstraction with JPA repositories |
| **Hibernate ORM** | 6.6.13 | Object-Relational Mapping & SQL generation |
| **Spring Security** | 6.x | Authentication, authorization & security filters |
| **JWT (jjwt)** | 0.12.6 | JSON Web Token generation, parsing & validation |
| **H2 Database** | 2.3.232 | In-memory relational database (dev/demo) |
| **PostgreSQL Driver** | 42.x | Production database driver (production-ready) |
| **Lombok** | 1.18.x | Boilerplate code reduction (@Builder, @Getter, @Setter) |
| **Jakarta Validation** | 3.x | Bean validation with @Valid, @NotNull, @Email etc. |
| **Apache Maven** | 3.9.x | Build automation & dependency management |
| **Maven Wrapper** | — | Portable Maven execution without global install |
| **Apache Tomcat** | 10.1.40 | Embedded servlet container |

### Frontend Technologies

| Technology | Version | Purpose |
|-----------|---------|---------|
| **React** | 19.3.1 | Component-based UI library |
| **Vite** | 8.3.8 | Next-generation build tool & dev server |
| **Tailwind CSS** | 4.3.5 | Utility-first CSS framework |
| **@tailwindcss/vite** | 4.3.5 | Tailwind CSS Vite plugin integration |
| **React Router DOM** | 7.18.5 | Client-side routing & navigation |
| **Axios** | 1.21.1 | HTTP client for API communication |
| **React Toastify** | 11.2.0 | Toast notification system |
| **React Icons** | 5.7.0 | Icon library (FontAwesome 6, Material Design, etc.) |
| **JavaScript (ES6+)** | ESM | Module system & modern syntax |
| **HTML5** | — | Semantic markup |
| **CSS3** | — | Custom styling & animations |

### Development & Build Tools

| Tool | Purpose |
|------|---------|
| **Maven Wrapper (mvnw)** | Build backend without globally installed Maven |
| **npm** | Frontend package management |
| **Vite Dev Server** | Hot Module Replacement (HMR) for development |
| **Vite Proxy** | Proxy `/api` calls to backend during development |
| **Git** | Version control |

### Architecture Patterns & Design

| Pattern | Usage |
|---------|-------|
| **MVC (Model-View-Controller)** | Backend request handling flow |
| **Layered Architecture** | Controller → Service → Repository → Entity |
| **DTO Pattern** | Request/Response DTOs to decouple API from entities |
| **Repository Pattern** | Spring Data JPA repositories for data access |
| **Builder Pattern** | Lombok @Builder for entity construction |
| **Interceptor Pattern** | Axios interceptors for JWT token injection & 401 handling |
| **Context API** | React Context for global authentication state |
| **Protected Routes** | HOC-based route guards with role checking |
| **Component-Based UI** | Reusable React components |
| **Single Page Application (SPA)** | Client-side routing with React Router |

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                        CLIENT (Browser)                         │
│                                                                 │
│  ┌──────────────────────────────────────────────────────────┐   │
│  │              React 19 + Vite 8 + Tailwind CSS 4          │   │
│  │                                                          │   │
│  │  ┌────────────┐  ┌──────────────┐  ┌──────────────────┐  │   │
│  │  │  Pages     │  │  Components  │  │   API Layer      │  │   │
│  │  │            │  │              │  │   (Axios)        │  │   │
│  │  │  - Patient │  │  - Navbar    │  │                  │  │   │
│  │  │  - Doctor  │  │  - Layout    │  │  JWT Token ──────│──│───│── Bearer Token
│  │  │  - Admin   │  │  - Footer   │  │  Interceptor     │  │   │
│  │  │  - Lab     │  │  - StatusBdg │  │                  │  │   │
│  │  │  - Pharma  │  │  - Protected │  │                  │  │   │
│  │  └────────────┘  └──────────────┘  └──────────────────┘  │   │
│  └──────────────────────────────────────────────────────────┘   │
│                              │                                  │
│                    HTTP (localhost:5173)                         │
│                    Vite Proxy: /api → :8080                     │
└──────────────────────────────│──────────────────────────────────┘
                               │
                     REST API (JSON)
                               │
┌──────────────────────────────│──────────────────────────────────┐
│                        SERVER (localhost:8080)                   │
│                                                                 │
│  ┌──────────────────────────────────────────────────────────┐   │
│  │          Spring Boot 3.4.5 + Spring Security 6           │   │
│  │                                                          │   │
│  │  ┌─────────────────┐    ┌─────────────────────────────┐  │   │
│  │  │  Security Layer  │    │     REST Controllers (15)   │  │   │
│  │  │                 │    │                             │  │   │
│  │  │  JWT Filter     │───▶│  Auth, Doctor, Patient,    │  │   │
│  │  │  CORS Config    │    │  Appointment, Consultation, │  │   │
│  │  │  SecurityConfig │    │  Prescription, Hospital,    │  │   │
│  │  └─────────────────┘    │  Lab, Pharmacy, Admin ...   │  │   │
│  │                         └────────────┬────────────────┘  │   │
│  │                                      │                   │   │
│  │  ┌──────────────────────────────────────────────────┐    │   │
│  │  │             Service Layer (15 Services)           │    │   │
│  │  │  Business logic, validation, data transformation  │    │   │
│  │  └──────────────────────┬───────────────────────────┘    │   │
│  │                         │                                │   │
│  │  ┌──────────────────────────────────────────────────┐    │   │
│  │  │          Repository Layer (15 JPA Repos)          │    │   │
│  │  │  Spring Data JPA interfaces with Hibernate ORM    │    │   │
│  │  └──────────────────────┬───────────────────────────┘    │   │
│  └─────────────────────────│────────────────────────────────┘   │
│                            │                                    │
│  ┌─────────────────────────│────────────────────────────────┐   │
│  │            H2 In-Memory Database (arosetu_db)            │   │
│  │                                                          │   │
│  │  15 Tables: users, doctors, patients, hospitals,         │   │
│  │  appointments, consultations, prescriptions,             │   │
│  │  prescription_items, availabilities, departments,        │   │
│  │  doctor_hospitals, follow_ups, laboratories,             │   │
│  │  pharmacies, lab_test_requests                           │   │
│  └──────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🗄️ Database Design

### Entity-Relationship Summary

The platform uses **15 JPA entities** with the following relationships:

| Entity | Description | Key Relationships |
|--------|-------------|-------------------|
| **User** | Base user account (email, password, role) | 1:1 → Doctor, Patient |
| **Doctor** | Doctor profile (specialization, fee, license) | N:1 → User, 1:N → DoctorHospital |
| **Patient** | Patient profile | N:1 → User, 1:N → Appointments |
| **Hospital** | Healthcare facility | 1:N → DoctorHospital, Appointments |
| **DoctorHospital** | Many-to-many join (Doctor ↔ Hospital) | N:1 → Doctor, N:1 → Hospital |
| **Availability** | Doctor's weekly schedule per hospital | N:1 → Doctor, N:1 → Hospital |
| **Appointment** | Patient-Doctor meeting | N:1 → Patient, Doctor, Hospital |
| **Consultation** | Clinical consultation record | N:1 → Appointment, Doctor, Patient |
| **Prescription** | Digital prescription | N:1 → Consultation, Doctor, Patient |
| **PrescriptionItem** | Individual medication in prescription | N:1 → Prescription |
| **FollowUp** | Follow-up visit scheduling | N:1 → Consultation, Doctor, Patient |
| **Department** | Hospital department | N:1 → Hospital |
| **Laboratory** | Lab facility | 1:N → LabTestRequest |
| **Pharmacy** | Pharmacy facility | 1:N → Prescription |
| **LabTestRequest** | Lab test order and result | N:1 → Patient, Doctor, Laboratory |

### Enumerations

| Enum | Values |
|------|--------|
| **Role** | `PATIENT`, `DOCTOR`, `ADMIN`, `LAB_TECHNICIAN`, `PHARMACIST` |
| **AppointmentStatus** | `REQUESTED`, `CONFIRMED`, `COMPLETED`, `CANCELLED`, `REJECTED` |
| **ApprovalStatus** | `PENDING`, `APPROVED`, `REJECTED` |
| **PrescriptionStatus** | `DRAFT`, `PRESCRIBED`, `SENT_TO_PHARMACY`, `READY`, `DISPENSED` |
| **LabTestStatus** | `REQUESTED`, `ACCEPTED`, `SAMPLE_COLLECTED`, `PROCESSING`, `COMPLETED` |
| **HospitalType** | `HOSPITAL`, `CLINIC`, `DIAGNOSTIC_CENTER` |
| **Gender** | `MALE`, `FEMALE`, `OTHER` |

---

## 👥 User Roles & Workflows

### Complete Healthcare Workflow

```
 Patient                Doctor               Lab Tech            Pharmacist
    │                     │                     │                     │
    │  1. Register/Login  │                     │                     │
    │────────────────────▶│                     │                     │
    │                     │                     │                     │
    │  2. Search Doctors  │                     │                     │
    │  3. Book Appointment│                     │                     │
    │────────────────────▶│                     │                     │
    │                     │  4. Confirm/Reject  │                     │
    │◀────────────────────│                     │                     │
    │                     │  5. Complete Appt   │                     │
    │                     │  6. Create Consult  │                     │
    │                     │────────────────────▶│                     │
    │                     │  7. Order Lab Test  │                     │
    │                     │                     │  8. Accept Test     │
    │                     │                     │  9. Collect Sample  │
    │                     │                     │  10. Process        │
    │                     │                     │  11. Enter Results  │
    │◀────────────────────│◀────────────────────│                     │
    │  12. View Results   │                     │                     │
    │                     │  13. Write Rx       │                     │
    │                     │  14. Send to Pharma │                     │
    │                     │────────────────────────────────────────▶  │
    │                     │                     │  15. Mark Ready     │
    │                     │                     │  16. Dispense       │
    │◀───────────────────────────────────────────────────────────────│
    │  17. View Prescription                                         │
```

---

## 🔌 API Endpoints

### Authentication (`/api/auth`) — Public

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT token |

### Doctors (`/api/doctors`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/api/doctors/search` | Public | Search doctors by specialization/city |
| GET | `/api/doctors` | Authenticated | List all approved doctors |
| GET | `/api/doctors/{id}` | Authenticated | Get doctor by ID |
| GET | `/api/doctors/me` | Doctor | Get own profile |
| PUT | `/api/doctors/me` | Doctor | Update own profile |
| DELETE | `/api/doctors/{id}` | Admin | Delete a doctor |

### Hospitals (`/api/hospitals`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/api/hospitals/public` | Public | List active hospitals |
| GET | `/api/hospitals` | Admin | List all hospitals |
| GET | `/api/hospitals/{id}` | Authenticated | Get hospital by ID |
| POST | `/api/hospitals` | Admin | Create hospital |
| PUT | `/api/hospitals/{id}` | Admin | Update hospital |
| PATCH | `/api/hospitals/{id}/toggle` | Admin | Toggle active status |

### Appointments (`/api/appointments`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/api/appointments` | Patient | Create appointment |
| GET | `/api/appointments/me` | Authenticated | Get my appointments |
| GET | `/api/appointments/{id}` | Authenticated | Get appointment by ID |
| PATCH | `/api/appointments/{id}/confirm` | Doctor | Confirm appointment |
| PATCH | `/api/appointments/{id}/reject` | Doctor | Reject appointment |
| PATCH | `/api/appointments/{id}/cancel` | Patient/Doctor | Cancel appointment |
| PATCH | `/api/appointments/{id}/complete` | Doctor | Mark as completed |

### Availability (`/api/availability`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/api/availability` | Doctor | Create availability slot |
| GET | `/api/availability/me` | Doctor | Get own availability |
| GET | `/api/availability/doctor/{id}` | Authenticated | Get doctor's availability |
| GET | `/api/availability/slots` | Authenticated | Get available time slots |
| PUT | `/api/availability/{id}` | Doctor | Update slot |
| DELETE | `/api/availability/{id}` | Doctor | Delete slot |

### Consultations (`/api/consultations`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/api/consultations` | Doctor | Create consultation |
| GET | `/api/consultations/me` | Authenticated | Get my consultations |
| GET | `/api/consultations/{id}` | Authenticated | Get by ID |
| PUT | `/api/consultations/{id}` | Doctor | Update consultation |

### Prescriptions (`/api/prescriptions`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/api/prescriptions` | Doctor | Create prescription |
| GET | `/api/prescriptions/me` | Authenticated | Get my prescriptions |
| GET | `/api/prescriptions/{id}` | Authenticated | Get by ID |
| GET | `/api/prescriptions/pharmacy/{id}` | Pharmacist | Get by pharmacy |
| PATCH | `/api/prescriptions/{id}/prescribe` | Doctor | Finalize prescription |
| PATCH | `/api/prescriptions/{id}/send-to-pharmacy` | Doctor | Route to pharmacy |
| PATCH | `/api/prescriptions/{id}/ready` | Pharmacist | Mark ready |
| PATCH | `/api/prescriptions/{id}/dispense` | Pharmacist | Mark dispensed |

### Lab Tests (`/api/lab-tests`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/api/lab-tests` | Doctor | Order lab test |
| GET | `/api/lab-tests/me` | Authenticated | Get my lab tests |
| GET | `/api/lab-tests/{id}` | Authenticated | Get by ID |
| GET | `/api/lab-tests/laboratory/{id}` | Authenticated | Get by laboratory |
| PATCH | `/api/lab-tests/{id}/accept` | Lab Tech | Accept test |
| PATCH | `/api/lab-tests/{id}/collect-sample` | Lab Tech | Mark sample collected |
| PATCH | `/api/lab-tests/{id}/process` | Lab Tech | Start processing |
| PATCH | `/api/lab-tests/{id}/complete` | Lab Tech | Complete with results |

### Pharmacies (`/api/pharmacies`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/api/pharmacies` | Authenticated | List all |
| GET | `/api/pharmacies/active` | Authenticated | List active |
| POST | `/api/pharmacies` | Admin | Create pharmacy |
| PUT | `/api/pharmacies/{id}` | Admin | Update pharmacy |
| PATCH | `/api/pharmacies/{id}/toggle` | Admin | Toggle active |

### Laboratories (`/api/laboratories`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/api/laboratories` | Authenticated | List all |
| GET | `/api/laboratories/active` | Authenticated | List active |
| POST | `/api/laboratories` | Admin | Create laboratory |
| PUT | `/api/laboratories/{id}` | Admin | Update laboratory |
| PATCH | `/api/laboratories/{id}/toggle` | Admin | Toggle active |

### Admin (`/api/admin`)

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/api/admin/stats` | Admin | Platform statistics |
| GET | `/api/admin/users` | Admin | List all users |
| PATCH | `/api/admin/users/{id}/toggle` | Admin | Toggle user active |
| GET | `/api/admin/doctors/pending` | Admin | Pending doctor approvals |
| PATCH | `/api/admin/doctors/{id}/approve` | Admin | Approve doctor |
| PATCH | `/api/admin/doctors/{id}/reject` | Admin | Reject doctor |

---

## 📁 Project Structure

```
arosetu-healthcare-platform/
│
├── README.md                          # This file
├── .gitignore                         # Git ignore rules
│
├── backend/                           # Spring Boot Backend
│   ├── README.md                      # Backend documentation
│   ├── pom.xml                        # Maven dependencies & build config
│   ├── mvnw, mvnw.cmd                 # Maven Wrapper scripts
│   └── src/main/java/com/arosetu/backend/
│       ├── ArosetuBackendApplication.java   # Main entry point
│       ├── config/
│       │   ├── SecurityConfig.java          # Spring Security + JWT config
│       │   ├── CorsConfig.java              # CORS configuration
│       │   └── DataSeeder.java              # Demo data seeding
│       ├── controller/                # 15 REST Controllers
│       │   ├── AuthController.java
│       │   ├── DoctorController.java
│       │   ├── PatientController.java
│       │   ├── AppointmentController.java
│       │   ├── ConsultationController.java
│       │   ├── PrescriptionController.java
│       │   ├── HospitalController.java
│       │   ├── AvailabilityController.java
│       │   ├── LaboratoryController.java
│       │   ├── LabTestController.java
│       │   ├── PharmacyController.java
│       │   ├── AdminController.java
│       │   ├── DepartmentController.java
│       │   ├── FollowUpController.java
│       │   └── UserController.java
│       ├── dto/                       # 20 Request/Response DTOs
│       ├── entity/                    # 15 JPA Entities
│       ├── enums/                     # 7 Enumerations
│       ├── exception/                 # Global exception handling
│       ├── repository/                # 15 JPA Repositories
│       ├── security/                  # JWT filter & service
│       │   ├── JwtAuthenticationFilter.java
│       │   ├── JwtService.java
│       │   └── CustomUserDetailsService.java
│       └── service/                   # 15 Business Services
│
├── frontend/                          # React Frontend
│   ├── README.md                      # Frontend documentation
│   ├── package.json                   # npm dependencies
│   ├── vite.config.js                 # Vite + Tailwind + proxy config
│   ├── index.html                     # HTML entry point
│   └── src/
│       ├── main.jsx                   # React entry point
│       ├── App.jsx                    # Routes & app structure
│       ├── index.css                  # Global styles + Tailwind
│       ├── api/                       # 13 API modules (Axios)
│       ├── components/                # 11 Reusable components
│       │   ├── Navbar.jsx
│       │   ├── PublicNavbar.jsx
│       │   ├── Layout.jsx
│       │   ├── Footer.jsx
│       │   ├── Hero.jsx
│       │   ├── Biography.jsx
│       │   ├── Departments.jsx
│       │   ├── Stats.jsx
│       │   ├── MessageForm.jsx
│       │   ├── ProtectedRoute.jsx
│       │   └── StatusBadge.jsx
│       ├── context/
│       │   └── AuthContext.jsx         # Authentication context
│       └── pages/
│           ├── Home.jsx                # Public landing page
│           ├── About.jsx               # About page
│           ├── auth/                   # Login, Register
│           ├── patient/                # 7 patient pages
│           ├── doctor/                 # 6 doctor pages
│           ├── admin/                  # 6 admin pages
│           ├── lab/                    # 1 lab tech page
│           └── pharmacist/             # 1 pharmacist page
```

---

## 📋 Prerequisites

| Requirement | Version | Download |
|-------------|---------|----------|
| **Java JDK** | 17 or later | [Download](https://adoptium.net/) |
| **Node.js** | 18 or later | [Download](https://nodejs.org/) |
| **npm** | 9 or later | Included with Node.js |
| **Git** | Latest | [Download](https://git-scm.com/) |

> **Note:** Maven is NOT required globally — the project includes Maven Wrapper (`mvnw`).

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/arosetu-healthcare-platform.git
cd arosetu-healthcare-platform
```

### 2. Start the Backend

```bash
cd backend
./mvnw spring-boot:run          # Linux/Mac
.\mvnw.cmd spring-boot:run      # Windows
```

The backend starts on **http://localhost:8080**.

You should see in the console:
```
====== Demo data seeded successfully! ======
Demo credentials:
  ADMIN      : admin@arosetu.com / admin123
  DOCTOR     : doctor@arosetu.com / doctor123
  PATIENT    : patient@arosetu.com / patient123
  LAB TECH   : lab@arosetu.com / lab12345
  PHARMACIST : pharma@arosetu.com / pharma123
```

### 3. Start the Frontend

Open a **new terminal**:

```bash
cd frontend
npm install
npm run dev
```

The frontend starts on **http://localhost:5173**.

### 4. Open the Application

Navigate to **http://localhost:5173** in your browser.

---

## 🔑 Demo Credentials

The platform auto-seeds demo data on startup:

| Role | Email | Password | Description |
|------|-------|----------|-------------|
| **Admin** | admin@arosetu.com | admin123 | Full platform management |
| **Doctor** | doctor@arosetu.com | doctor123 | Dr. Arun Kumar — Cardiology |
| **Doctor** | meena@arosetu.com | doctor123 | Dr. Meena Reddy — Neurology |
| **Doctor** | suresh@arosetu.com | doctor123 | Dr. Suresh Patel — Orthopedics |
| **Patient** | patient@arosetu.com | patient123 | Rahul Sharma |
| **Patient** | priya@arosetu.com | patient123 | Priya Nair |
| **Lab Tech** | lab@arosetu.com | lab12345 | Ravi Lab Tech |
| **Pharmacist** | pharma@arosetu.com | pharma123 | Kavitha Pharmacist |

### Pre-seeded Data

The DataSeeder (`DataSeeder.java`) auto-populates the in-memory H2 database on every startup with realistic demo data:

| Data | Count | Details |
|------|-------|---------|
| **Admin Users** | 1 | Platform administrator |
| **Patients** | 2 | Rahul Sharma (O+, Male), Priya Nair (A+, Female) with full profiles |
| **Doctors** | 3 | Cardiology, Neurology, Orthopedics — all APPROVED |
| **Hospitals** | 3 | AroSetu General Hospital, LifeCare Clinic, MedScan Diagnostics |
| **Doctor-Hospital Links** | 5 | Doctors associated with their practicing hospitals |
| **Availability Slots** | 11 | Mon–Sat schedules with 30/45 min slot durations |
| **Appointments** | 6 | 4 COMPLETED, 1 CONFIRMED, 1 REQUESTED |
| **Consultations** | 4 | Full clinical records (symptoms, diagnosis, treatment plan) |
| **Prescriptions** | 3 | 1 PRESCRIBED, 1 SENT_TO_PHARMACY, 1 DRAFT |
| **Medication Items** | 6 | Enalapril, Amlodipine, Topiramate, Paracetamol, Ibuprofen, Thiocolchicoside |
| **Laboratories** | 2 | AroSetu Central Lab, PathCare Diagnostics |
| **Pharmacies** | 2 | AroSetu Pharmacy, MedPlus Pharmacy |
| **Lab Technicians** | 1 | Ravi Lab Tech |
| **Pharmacists** | 1 | Kavitha Pharmacist |

This ensures **all dropdowns, tables, dashboards, and workflow pages** have data from the first login.

---

## 🖼️ Screenshots

### Landing Page
- Professional hero section with animated gradient visuals
- Department showcase with 9 medical specialties
- Contact form, statistics section, and comprehensive footer

### Patient Dashboard
- Summary cards: Appointments, Consultations, Prescriptions, Lab Reports
- Upcoming appointments with doctor name and hospital
- Recent prescriptions with medication counts

### Doctor Dashboard
- Pending appointment requests with patient details
- Confirmed appointments count
- Quick navigation to consultations and availability management

### Admin Dashboard
- Platform-wide statistics with emoji icons
- Quick links to manage users, doctors, hospitals, labs, pharmacies

---

## 🔮 Future Enhancements

| Enhancement | Description |
|-------------|-------------|
| **PostgreSQL Migration** | Switch from H2 to PostgreSQL for production deployment |
| **Email Notifications** | Send appointment confirmations, lab results via email |
| **Video Consultation** | Integrate WebRTC for telemedicine |
| **Payment Gateway** | Online payment for consultation fees |
| **File Upload** | Upload lab reports, prescriptions as PDF/images |
| **Swagger/OpenAPI** | Auto-generated API documentation |
| **Unit & Integration Tests** | Comprehensive test coverage |
| **Docker Containerization** | Dockerfile + docker-compose for easy deployment |
| **CI/CD Pipeline** | GitHub Actions for automated build & deploy |
| **PWA Support** | Progressive Web App for offline access |
| **Multi-language (i18n)** | Support for Hindi, Kannada, and other languages |

---

## 📊 Project Statistics

| Metric | Count |
|--------|-------|
| Backend Source Files | 105 Java files |
| Frontend Source Files | 140 modules |
| JPA Entities | 15 |
| REST Controllers | 15 |
| Service Classes | 15 |
| JPA Repositories | 15 |
| DTOs (Request + Response) | 23 |
| Enumerations | 7 |
| API Endpoints | 45+ |
| Frontend Pages | 21 |
| React Components | 11 |
| API Modules (Axios) | 13 |
| User Roles | 5 |
| Database Tables | 15 |
| Seeded Demo Records | 40+ (users, appointments, prescriptions, etc.) |
| Production JS Bundle | 469 KB (135 KB gzipped) |
| Production CSS Bundle | 44.8 KB (9.1 KB gzipped) |
| Build Time (Frontend) | ~565ms |

---

## 📄 License

This project is developed for academic purposes as part of a **Full Stack Development** course.

**Developed by:**
- **Prasanna Ram**
- **Sudarshan K S**

© 2026 AroSetu Healthcare. All rights reserved.
