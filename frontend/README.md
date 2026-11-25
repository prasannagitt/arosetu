# ⚛️ AroSetu Frontend — React Single Page Application

> Modern, responsive frontend for the AroSetu Healthcare Coordination Platform, built with React 19, Vite 8, and Tailwind CSS 4.

**Developed by:** Prasanna Ram & Sudarshan K S

---

## 📋 Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Pages & Components](#pages--components)
- [State Management](#state-management)
- [API Layer](#api-layer)
- [Routing & Navigation](#routing--navigation)
- [Styling](#styling)
- [How to Run](#how-to-run)
- [Build & Deployment](#build--deployment)

---

## 📖 Overview

The AroSetu frontend is a **Single Page Application (SPA)** that provides:
- 📱 **Responsive UI** that works on desktop, tablet, and mobile
- 🎨 **Professional design** with Tailwind CSS utility-first styling
- 🔐 **JWT-based authentication** with automatic token management
- 🛡️ **Role-based route protection** for 5 user roles
- 📊 **21 pages** with dedicated dashboards for each role
- 🔄 **Real-time form validation** and toast notifications
- ⚡ **Fast development** with Vite HMR (Hot Module Replacement)

---

## 🛠️ Technology Stack

### Core

| Technology | Version | Purpose |
|-----------|---------|---------|
| **React** | 19.3.1 | Component-based UI library with hooks |
| **Vite** | 8.3.8 | Lightning-fast build tool & dev server |
| **JavaScript (ES6+)** | ESM | Modern syntax, arrow functions, destructuring, async/await |

### Styling

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Tailwind CSS** | 4.3.5 | Utility-first CSS framework |
| **@tailwindcss/vite** | 4.3.5 | Vite plugin for Tailwind integration |
| **Custom CSS** | — | Component-specific styles (624 lines) |
| **Google Fonts (Montserrat)** | — | Professional typography |

### Routing & Navigation

| Technology | Version | Purpose |
|-----------|---------|---------|
| **React Router DOM** | 7.18.5 | Declarative client-side routing |

### HTTP & API

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Axios** | 1.21.1 | Promise-based HTTP client |
| **Axios Interceptors** | — | Auto-attach JWT token & handle 401 redirects |

### UI Libraries

| Technology | Version | Purpose |
|-----------|---------|---------|
| **React Toastify** | 11.2.0 | Toast notification system |
| **React Icons** | 5.7.0 | Icon library (FontAwesome 6, Material Design, Game Icons, Ant Design) |

### Build & Dev

| Tool | Purpose |
|------|---------|
| **Vite Dev Server** | HMR with sub-second refresh at http://localhost:5173 |
| **Vite Proxy** | Proxy `/api` requests to `http://localhost:8080` |
| **Vite Build** | Production bundling with tree-shaking |
| **npm** | Package management |

---

## 🏗️ Architecture

```
┌───────────────────────────────────────────────┐
│                   App.jsx                      │
│            (BrowserRouter + Routes)            │
│                                                │
│  ┌──────────────────────────────────────────┐  │
│  │          AuthProvider (Context)           │  │
│  │  ┌────────────────────────────────────┐  │  │
│  │  │  State: user, token, isAuthenticated│  │  │
│  │  │  Actions: login(), logout()        │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
│                      │                         │
│         ┌────────────┼────────────┐            │
│         ▼            ▼            ▼            │
│  ┌──────────┐ ┌──────────┐ ┌──────────────┐   │
│  │ Public   │ │Protected │ │ProtectedRoute│   │
│  │ Routes   │ │ Routes   │ │  (HOC)       │   │
│  │          │ │          │ │              │   │
│  │ /        │ │ /patient │ │ Checks:      │   │
│  │ /about   │ │ /doctor  │ │ - isAuth?    │   │
│  │ /login   │ │ /admin   │ │ - hasRole?   │   │
│  │ /register│ │ /lab     │ │ - redirect   │   │
│  └──────────┘ │ /pharma  │ └──────────────┘   │
│               └──────────┘                     │
│                      │                         │
│                      ▼                         │
│  ┌──────────────────────────────────────────┐  │
│  │              Layout.jsx                   │  │
│  │  ┌────────────────────────────────────┐  │  │
│  │  │          Navbar.jsx                 │  │  │
│  │  │  (role-based navigation links)      │  │  │
│  │  ├────────────────────────────────────┤  │  │
│  │  │          <Outlet />                 │  │  │
│  │  │  (page content renders here)        │  │  │
│  │  ├────────────────────────────────────┤  │  │
│  │  │          Footer.jsx                 │  │  │
│  │  └────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────┘  │
│                      │                         │
│                      ▼                         │
│  ┌──────────────────────────────────────────┐  │
│  │           API Layer (Axios)               │  │
│  │                                          │  │
│  │  axios.js → Base config + interceptors   │  │
│  │  13 API modules → endpoint functions     │  │
│  │                                          │  │
│  │  Request:  Authorization: Bearer <JWT>   │  │
│  │  Response: Auto-redirect on 401          │  │
│  └──────────────────────────────────────────┘  │
└───────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
frontend/
├── index.html                     # HTML entry point (Montserrat font, favicon)
├── package.json                   # Dependencies & scripts
├── vite.config.js                 # Vite + React + Tailwind + proxy config
├── README.md                      # This file
│
└── src/
    ├── main.jsx                   # ReactDOM.createRoot entry point
    ├── App.jsx                    # Root component with all routes
    ├── index.css                  # Tailwind import + 624 lines custom CSS
    │
    ├── api/                       # API Layer (13 modules)
    │   ├── axios.js               # Axios instance + JWT interceptor + 401 handler
    │   ├── authApi.js             # register(), login()
    │   ├── doctorApi.js           # searchDoctors(), getDoctorById(), getMyDoctorProfile()
    │   ├── hospitalApi.js         # getActiveHospitals(), getAllHospitals(), CRUD
    │   ├── appointmentApi.js      # createAppointment(), getMyAppointments(), status updates
    │   ├── availabilityApi.js     # createAvailability(), getAvailableSlots()
    │   ├── consultationApi.js     # createConsultation(), getMyConsultations()
    │   ├── prescriptionApi.js     # createPrescription(), prescribe(), sendToPharmacy()
    │   ├── labApi.js              # createLabTest(), getMyLabTests(), workflow actions
    │   ├── laboratoryApi.js       # getAllLaboratories(), getActiveLaboratories(), CRUD
    │   ├── pharmacyApi.js         # getActivePharmacies(), CRUD
    │   ├── patientApi.js          # getMyPatientProfile(), updateProfile()
    │   └── adminApi.js            # getStats(), getAllUsers(), doctor approvals
    │
    ├── components/                # Reusable Components (11)
    │   ├── Layout.jsx             # App shell: Navbar + content + Footer
    │   ├── Navbar.jsx             # Role-based navigation bar (authenticated)
    │   ├── PublicNavbar.jsx       # Landing page navigation with hamburger menu
    │   ├── Footer.jsx             # 4-column footer with links, contact, developers
    │   ├── ProtectedRoute.jsx     # Route guard: auth check + role check
    │   ├── StatusBadge.jsx        # Color-coded status pill (19 status variants)
    │   ├── Hero.jsx               # Landing page hero section
    │   ├── Biography.jsx          # About section
    │   ├── Departments.jsx        # 9-department grid with icons
    │   ├── Stats.jsx              # Statistics bar (Doctors, Patients, Departments, Support)
    │   └── MessageForm.jsx        # Contact form with toast notification
    │
    ├── context/
    │   └── AuthContext.jsx        # React Context for auth state management
    │                              # Provides: user, token, isAuthenticated, login(), logout()
    │
    └── pages/                     # Page Components (21 pages)
        ├── Home.jsx               # Public landing page (Hero + Stats + Bio + Depts + Contact)
        ├── About.jsx              # About page
        │
        ├── auth/                  # Authentication (2 pages)
        │   ├── Login.jsx          # Email/password login form
        │   └── Register.jsx       # Registration with role selection
        │
        ├── patient/               # Patient Pages (7 pages)
        │   ├── Dashboard.jsx      # Summary cards + upcoming appts + recent Rx
        │   ├── DoctorSearch.jsx   # Search doctors by specialization/city
        │   ├── BookAppointment.jsx# Hospital → Date → Time slot → Book
        │   ├── MyAppointments.jsx # Appointment list with cancel option
        │   ├── MyConsultations.jsx# Consultation records with clinical details
        │   ├── MyPrescriptions.jsx# Prescription cards with medication tables
        │   └── MyLabReports.jsx   # Lab test status and results
        │
        ├── doctor/                # Doctor Pages (6 pages)
        │   ├── Dashboard.jsx      # Pending requests + stats
        │   ├── Appointments.jsx   # Filter + Confirm/Reject/Complete
        │   ├── Consultations.jsx  # Create consultation with clinical fields
        │   ├── Prescriptions.jsx  # Create Rx with multiple medications
        │   ├── Availability.jsx   # Set weekly schedule per hospital
        │   └── Profile.jsx        # Edit specialization, qualification, fee
        │
        ├── admin/                 # Admin Pages (6 pages)
        │   ├── Dashboard.jsx      # Platform-wide statistics
        │   ├── Users.jsx          # User management table
        │   ├── Doctors.jsx        # Doctor approval management
        │   ├── Hospitals.jsx      # Hospital CRUD with type selector
        │   ├── Laboratories.jsx   # Laboratory CRUD
        │   └── Pharmacies.jsx     # Pharmacy CRUD
        │
        ├── lab/                   # Lab Technician (1 page)
        │   └── Dashboard.jsx      # Lab selection → test workflow → result entry
        │
        └── pharmacist/            # Pharmacist (1 page)
            └── Dashboard.jsx      # Pharmacy selection → prescription fulfillment
```

---

## 📄 Pages & Components

### Public Pages (No Login Required)

| Page | URL | Description |
|------|-----|-------------|
| Home | `/` | Landing page with hero, stats, departments, contact form |
| About | `/about` | About the platform and team |
| Login | `/login` | Email/password authentication |
| Register | `/register` | New user registration with role selection |

### Patient Pages (7)

| Page | URL | Key Features |
|------|-----|--------------|
| Dashboard | `/patient/dashboard` | 4 stat cards, upcoming appointments, recent prescriptions |
| Find Doctors | `/patient/doctors` | Search by specialization/city, doctor cards with "Book" button |
| Book Appointment | `/patient/book-appointment/:id` | Hospital selection → date picker → time slots → booking |
| My Appointments | `/patient/appointments` | List with cancel option, status badges |
| My Consultations | `/patient/consultations` | Doctor name, symptoms, diagnosis, treatment plan |
| My Prescriptions | `/patient/prescriptions` | Medication table, pharmacy info, status tracking |
| My Lab Reports | `/patient/lab-reports` | Test name, status, result summary, report download |

### Doctor Pages (6)

| Page | URL | Key Features |
|------|-----|--------------|
| Dashboard | `/doctor/dashboard` | Pending requests, confirmed count, quick stats |
| Appointments | `/doctor/appointments` | Status filters, Confirm/Reject/Complete actions |
| Consultations | `/doctor/consultations` | Create consultation with 5 clinical fields |
| Prescriptions | `/doctor/prescriptions` | Multi-medication prescription builder, pharmacy routing |
| Availability | `/doctor/availability` | Weekly schedule per hospital, slot duration config |
| Profile | `/doctor/profile` | Edit specialization, qualification, fee, about |

### Admin Pages (6)

| Page | URL | Key Features |
|------|-----|--------------|
| Dashboard | `/admin/dashboard` | 6 stat cards with emoji icons |
| Users | `/admin/users` | User table with active/inactive toggle |
| Doctors | `/admin/doctors` | Pending doctor approval/rejection |
| Hospitals | `/admin/hospitals` | CRUD with type enum (Hospital/Clinic/Diagnostic) |
| Laboratories | `/admin/laboratories` | Laboratory CRUD management |
| Pharmacies | `/admin/pharmacies` | Pharmacy CRUD management |

### Lab Technician (1 page)

| Page | URL | Key Features |
|------|-----|--------------|
| Dashboard | `/lab/dashboard` | Lab selection, 5-step test workflow, result entry form |

### Pharmacist (1 page)

| Page | URL | Key Features |
|------|-----|--------------|
| Dashboard | `/pharmacist/dashboard` | Pharmacy selection, prescription details, Mark Ready/Dispense |

---

## 🔄 State Management

### Authentication Context (`AuthContext.jsx`)

```javascript
// Provides global auth state to all components
{
  user: { id, name, email, role },   // Current user object
  token: "eyJhbG...",                // JWT token string
  isAuthenticated: true/false,       // Login status
  loading: true/false,               // Initial auth check
  login(token, user),                // Store credentials
  logout()                           // Clear credentials
}
```

**Storage:** `localStorage` for persistence across page refreshes.

### Component-Level State

Each page manages its own state using React's `useState` and `useEffect` hooks:
- Form data (`useState`)
- API response data (`useState`)
- Loading states (`useState`)
- Error messages (`useState`)
- Data fetching (`useEffect` on mount)

---

## 🌐 API Layer

### Axios Configuration (`axios.js`)

```javascript
const api = axios.create({
  baseURL: '/api',                    // All requests prefixed with /api
  headers: { 'Content-Type': 'application/json' }
});

// Request Interceptor: Auto-attach JWT token
api.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

// Response Interceptor: Auto-redirect on 401 Unauthorized
api.interceptors.response.use(
  response => response,
  error => {
    if (error.response?.status === 401) {
      localStorage.clear();
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);
```

### Vite Proxy Configuration (`vite.config.js`)

```javascript
server: {
  port: 5173,
  proxy: {
    '/api': {
      target: 'http://localhost:8080',   // Forward to Spring Boot
      changeOrigin: true
    }
  }
}
```

---

## 🎨 Styling

### Tailwind CSS 4

The project uses **Tailwind CSS 4** with the Vite plugin for zero-config integration:

```css
/* index.css */
@import "tailwindcss";
```

### Custom CSS Sections (624 lines in `index.css`)

| Section | Lines | Purpose |
|---------|-------|---------|
| Reset | ~10 | Box-sizing, body font |
| Buttons | ~25 | `.btn-primary` with gradient |
| Hero | ~100 | Landing page hero with animations |
| Biography | ~55 | About section layout |
| Departments | ~15 | Department grid |
| Contact Form | ~50 | Message form styling |
| Stats | ~30 | Statistics bar |
| Footer | ~95 | 4-column footer layout |
| Public Navbar | ~80 | Navigation with hamburger menu |
| Auth Forms | ~110 | Login/register card styling |
| Toast | ~5 | Toast notification customization |
| Responsive | ~50 | Media queries for mobile |

### Design System

| Element | Style |
|---------|-------|
| Primary Color | `#1a56db` (Blue 700) |
| Font | Montserrat (Google Fonts) |
| Border Radius | `12px` (cards), `20px` (buttons) |
| Shadow | `shadow-md` (Tailwind) |
| Animations | Float animation, hover transforms |

---

## 🚀 How to Run

### Development

```bash
cd frontend
npm install          # Install dependencies (first time)
npm run dev          # Start dev server with HMR
```

Open **http://localhost:5173** in your browser.

### Production Build

```bash
npm run build        # Outputs to dist/
npm run preview      # Preview production build locally
```

### Build Output

```
dist/
├── index.html              (1.12 kB)
├── assets/
│   ├── index-*.css          (44.6 kB → 9.1 kB gzipped)
│   └── index-*.js          (467 kB → 135 kB gzipped)
```

---

## 📊 Statistics

| Metric | Count |
|--------|-------|
| Total Modules | 140 |
| Pages | 21 |
| Reusable Components | 11 |
| API Modules | 13 |
| Custom CSS Lines | 624 |
| Production JS | 469 kB (135 kB gzip) |
| Production CSS | 44.8 kB (9.1 kB gzip) |
| Build Time | ~565ms |

---

© 2026 AroSetu Healthcare — Built by Prasanna Ram & Sudarshan K S
