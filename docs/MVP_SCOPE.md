# AroSetu — MVP Scope

## 1. Purpose

The Minimum Viable Product (MVP) of AroSetu is a full-stack healthcare
coordination platform that connects patients, doctors, laboratories,
pharmacies, and healthcare facilities through a single workflow.

The primary objective of the MVP is to demonstrate a complete healthcare
journey from appointment booking through consultation, laboratory testing
and/or prescription, and finally to the patient receiving the relevant
healthcare information or medicines being dispensed.

---

# 2. MVP Goal

The MVP must successfully demonstrate the following end-to-end workflow:

Patient
   ↓
Search Doctor
   ↓
Select Hospital/Clinic
   ↓
Book Appointment
   ↓
Doctor Confirms Appointment
   ↓
Consultation
   ├───────────────┐
   ↓               ↓
Laboratory     Prescription
   ↓               ↓
Lab Report     Pharmacy
   │               ↓
   │            Dispensed
   └───────┬───────┘
           ↓
        Patient

The MVP is considered successful when this complete workflow can be
demonstrated using the application.

---

# 3. MVP User Roles

The MVP contains five primary roles:

## 3.1 Patient

The patient can:

- Register and log in.
- Manage their profile.
- Search for doctors.
- View doctor profiles.
- View hospital/clinic information.
- View doctor availability.
- Book appointments.
- View appointments.
- Cancel eligible appointments.
- View consultation records.
- View prescriptions.
- View laboratory test status.
- View laboratory reports.
- View pharmacy processing status.

---

## 3.2 Doctor

The doctor can:

- Register and log in.
- Manage their professional profile.
- View associated hospitals/clinics.
- Manage basic consultation availability.
- View appointments.
- Confirm appointments.
- Reject appointments.
- Cancel eligible appointments.
- Mark appointments as completed.
- Conduct consultations.
- Record symptoms.
- Record observations.
- Record diagnosis.
- Record treatment plans.
- Request laboratory tests.
- View laboratory test status.
- View laboratory reports.
- Create prescriptions.
- Add medicines to prescriptions.
- Specify dosage, frequency, and duration.
- Send prescriptions to a pharmacy.

---

## 3.3 Laboratory Technician

The laboratory technician can:

- Log in.
- View laboratory test requests.
- Accept test requests.
- Mark samples as collected.
- Mark tests as processing.
- Enter test results.
- Upload laboratory reports.
- Mark tests as completed.
- View laboratory test history.

---

## 3.4 Pharmacist

The pharmacist can:

- Log in.
- View prescriptions assigned to their pharmacy.
- View prescription details.
- Mark prescriptions as ready.
- Mark prescriptions as dispensed.
- View prescription processing history.

The pharmacy module does NOT include:

- Inventory management
- Stock management
- Supplier management
- Purchase orders
- Pharmacy billing
- Medicine expiry management

These features are outside the AroSetu MVP.

---

## 3.5 Administrator

The administrator can:

- Log in.
- View users.
- Activate/deactivate users where appropriate.
- Manage doctor approval.
- Add hospitals/clinics.
- Update hospital/clinic information.
- Activate/deactivate hospitals/clinics.
- Manage hospital/clinic departments.
- Add laboratories.
- Activate/deactivate laboratories.
- Add pharmacies.
- Activate/deactivate pharmacies.

---

# 4. MVP Functional Modules

The MVP consists of the following modules:

## Module 1 — Authentication & Authorization

Features:

- User registration
- User login
- User logout
- JWT authentication
- Role-based authorization

Roles:

- PATIENT
- DOCTOR
- LAB_TECHNICIAN
- PHARMACIST
- ADMIN

---

## Module 2 — Patient Management

Features:

- Patient profile
- Doctor search
- Doctor profile viewing
- Hospital/clinic viewing
- Appointment booking
- Appointment viewing
- Consultation viewing
- Prescription viewing
- Laboratory report viewing
- Pharmacy status viewing

---

## Module 3 — Doctor Management

Features:

- Doctor profile
- Professional information
- Hospital/clinic association
- Department association
- Basic availability management
- Appointment management
- Consultation management
- Laboratory test requests
- Prescription creation

---

## Module 4 — Hospital & Clinic Management

Features:

- Hospital/clinic creation
- Hospital/clinic information
- Hospital/clinic activation/deactivation
- Department management
- Doctor-hospital association
- Doctor availability by location

---

## Module 5 — Appointment Management

Features:

- Doctor availability
- Appointment booking
- Appointment confirmation
- Appointment rejection
- Appointment cancellation
- Appointment completion
- Appointment status tracking
- Double-booking prevention
- Appointment history

Appointment statuses:

- REQUESTED
- CONFIRMED
- REJECTED
- CANCELLED
- COMPLETED

---

## Module 6 — Consultation Management

Features:

- Start consultation
- Record symptoms
- Record observations
- Record diagnosis
- Record clinical notes
- Record treatment plan
- Complete consultation
- View consultation history
- Link consultation with laboratory requests
- Link consultation with prescriptions

---

## Module 7 — Laboratory Management

Features:

- View test requests
- Accept test request
- Sample collection
- Test processing
- Enter results
- Upload laboratory report
- Complete test
- Doctor views report
- Patient views report

Laboratory statuses:

- REQUESTED
- ACCEPTED
- SAMPLE_COLLECTED
- PROCESSING
- COMPLETED
- CANCELLED

---

## Module 8 — Prescription Management

Features:

- Create prescription
- Add multiple medicines
- Add dosage
- Add frequency
- Add duration
- Add instructions
- Save draft
- Finalize prescription
- Send prescription to pharmacy
- Patient views prescription
- Doctor views prescription history

Prescription statuses:

- DRAFT
- PRESCRIBED
- SENT_TO_PHARMACY
- READY
- DISPENSED

---

## Module 9 — Pharmacy Workflow

Features:

- View incoming prescriptions
- View prescription details
- Mark prescription as ready
- Mark prescription as dispensed
- View prescription status
- View pharmacy processing history

The pharmacist does not modify the doctor's prescription.

---

## Module 10 — Basic Administration

Features:

- User management
- Doctor approval
- Hospital/clinic management
- Department management
- Laboratory management
- Pharmacy management

---

# 5. MVP Data Flow

The major data flow is:

Patient
   ↓
Appointment
   ↓
Doctor
   ↓
Consultation
   ├───────────────┐
   ↓               ↓
Laboratory      Prescription
   ↓               ↓
Report          Pharmacy
   │               ↓
   │            Dispensed
   └───────┬───────┘
           ↓
        Patient

Each activity must be associated with the correct patient.

---

# 6. MVP Access Control

AroSetu must enforce role-based access.

## Patient

Can access:

- Own profile
- Own appointments
- Own consultations
- Own prescriptions
- Own laboratory reports
- Own pharmacy status

Cannot access another patient's records.

---

## Doctor

Can access:

- Own profile
- Own appointments
- Authorized patient information
- Relevant consultations
- Relevant laboratory information
- Prescriptions they are authorized to manage

Cannot access unrelated patient information.

---

## Laboratory Technician

Can access:

- Laboratory profile
- Test requests assigned to their laboratory
- Relevant patient information required for testing
- Laboratory results and reports

Cannot modify prescriptions or consultations.

---

## Pharmacist

Can access:

- Pharmacy profile
- Prescriptions assigned to their pharmacy
- Information required for dispensing

Cannot modify:

- Diagnosis
- Consultation
- Prescription medicines
- Dosage
- Frequency
- Duration
- Laboratory reports

---

## Administrator

Can access administrative functionality required to manage the platform.

Administrative access must not automatically imply the ability to modify
clinical records.

---

# 7. MVP Non-Functional Requirements

The MVP should also satisfy basic quality requirements.

## Security

- Passwords must never be stored as plain text.
- Passwords must be securely hashed.
- JWT authentication must be implemented.
- Role-based authorization must be enforced.
- Protected APIs must reject unauthorized requests.
- Sensitive configuration must not be committed to Git.
- Environment variables should be used for secrets and credentials.

## Validation

- Required fields must be validated.
- Invalid input must be rejected.
- Appropriate error responses must be returned.

## Data Integrity

- Patients cannot access other patients' records.
- Pharmacists cannot modify prescriptions.
- Laboratory technicians cannot modify prescriptions.
- Double appointment booking must be prevented.
- Invalid workflow transitions must be rejected.

## Maintainability

- Backend and frontend should be separated.
- REST APIs should follow a consistent structure.
- Code should be organized into appropriate layers/modules.
- Git should be used for version control.
- Documentation should be maintained throughout development.

---

# 8. MVP Technology Scope

The MVP will be developed as a full-stack web application.

## Frontend

- React
- HTML
- CSS
- JavaScript
- REST API integration

## Backend

- Java
- Spring Boot
- Spring Security
- REST APIs
- JWT authentication

## Database

- PostgreSQL
- JPA/Hibernate

## Development Tools

- Git
- GitHub
- VS Code
- IntelliJ IDEA where appropriate
- Postman

Additional tools may be introduced when required.

---

# 9. Features Excluded From MVP

The following features are intentionally excluded from Version 1:

- Medical timeline
- AI assistant
- AI diagnosis
- AI treatment recommendations
- Email notifications
- SMS notifications
- Push notifications
- Video consultation
- Online payment
- Billing
- Insurance processing
- Pharmacy inventory
- Pharmacy stock management
- Supplier management
- Purchase orders
- Medicine expiry management
- Advanced analytics
- Advanced reporting
- Advanced audit dashboard
- Mobile application
- Multi-language support
- Advanced appointment rescheduling
- Advanced recommendation systems

These features may be considered for future versions.

---

# 10. Future Version Roadmap

## Version 2 — Professional Features

Potential features:

- Medical timeline
- Notifications
- Follow-up reminders
- Appointment rescheduling
- Advanced search and filtering
- Better dashboards
- Audit logging
- Advanced reporting

---

## Version 3 — AI & Intelligent Features

Potential features:

- Medical history summarization
- Natural-language search over authorized records
- Intelligent timeline summaries
- Workflow assistance

AI features must be designed as assistive features and must not independently
diagnose patients or prescribe treatment.

---

## Version 4 — Production Enhancements

Potential features:

- Cloud deployment improvements
- CI/CD
- Docker
- Monitoring
- Advanced security
- Performance optimization
- Mobile application
- Notification infrastructure

---

# 11. MVP Definition of Done

The AroSetu MVP is considered complete when:

- All five user roles can authenticate.
- Role-based access control works.
- Patient can find a doctor.
- Patient can select a hospital/clinic.
- Patient can book an available appointment.
- Doctor can manage the appointment.
- Doctor can conduct a consultation.
- Doctor can request a laboratory test.
- Laboratory can process the test.
- Laboratory can upload a report.
- Patient and doctor can view the report.
- Doctor can create a prescription.
- Doctor can send the prescription to a pharmacy.
- Pharmacist can view the prescription.
- Pharmacist can mark it READY.
- Pharmacist can mark it DISPENSED.
- Patient can see the prescription and dispensing status.
- Unauthorized users cannot access protected records.
- Double appointment booking is prevented.
- Major workflows are tested.
- MVP can be demonstrated from beginning to end.

---

# 12. MVP Success Scenario

A successful demonstration should follow this scenario:

1. Patient registers.
2. Patient logs in.
3. Patient searches for a doctor.
4. Patient views the doctor's profile.
5. Patient selects a hospital/clinic.
6. Patient selects an available appointment slot.
7. Patient books the appointment.
8. Doctor logs in.
9. Doctor confirms the appointment.
10. Doctor conducts the consultation.
11. Doctor records symptoms, diagnosis, and treatment plan.
12. Doctor requests a laboratory test.
13. Laboratory technician receives the request.
14. Laboratory technician processes the test.
15. Laboratory technician uploads the report.
16. Doctor views the report.
17. Patient views the report.
18. Doctor creates a prescription.
19. Doctor sends the prescription to the pharmacy.
20. Pharmacist views the prescription.
21. Pharmacist marks the prescription as READY.
22. Pharmacist marks the prescription as DISPENSED.
23. Patient sees the final dispensing status.

If this scenario works reliably, the AroSetu MVP is considered complete.