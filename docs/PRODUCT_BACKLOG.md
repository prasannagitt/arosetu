# AroSetu Product Backlog

## Agile Methodology

AroSetu follows a Scrum-based Agile workflow adapted for a solo
development project.

Development will be divided into iterative sprints. Features will be
developed, tested, reviewed, and improved incrementally.

---

# Product Backlog

## Priority Levels

- P0 - Critical
- P1 - High
- P2 - Medium
- P3 - Low

## Status

- Backlog
- Ready
- In Progress
- Testing
- Done

---

# EPIC 01 — Authentication & Authorization

## US-AUTH-01 — User Registration

**User Story**

As a user, I want to create a AroSetu account so that I can access
the platform.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

### Acceptance Criteria

- User can provide required registration information.
- Email must be unique.
- Password must satisfy security requirements.
- Password must be securely hashed.
- User account is created successfully.
- Appropriate role is assigned.
- Invalid input is rejected.
- Duplicate email registration is prevented.

---

## US-AUTH-02 — User Login

**User Story**

As a registered user, I want to log in securely so that I can access
features available to my role.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

### Acceptance Criteria

- User can log in using valid credentials.
- Invalid credentials are rejected.
- Successful login generates an authentication token.
- User is redirected to the appropriate dashboard.
- User role is identified correctly.

---

## US-AUTH-03 — Logout

**User Story**

As a user, I want to log out so that I can securely end my session.

**Priority:** P0  
**Story Points:** 1  
**Status:** Backlog

### Acceptance Criteria

- User can log out.
- Authentication state is cleared.
- Protected pages cannot be accessed after logout.

---

## US-AUTH-04 — Role-Based Authorization

**User Story**

As a system administrator, I want users to have role-based permissions
so that users can only access functionality appropriate to their role.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

### Roles

- Patient
- Doctor
- Laboratory Technician
- Pharmacist
- Administrator

### Acceptance Criteria

- Each user has a defined role.
- Protected APIs verify user authentication.
- Protected APIs verify user authorization.
- Patients cannot access doctor-only functions.
- Doctors cannot access administrator functions.
- Laboratory technicians cannot modify prescriptions.
- Pharmacists cannot modify prescriptions.
- Administrators can access administrative functions.

---

# EPIC 02 — Patient Management

## US-PAT-01 — Patient Profile

**User Story**

As a patient, I want to manage my personal profile so that my information
is up to date.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

### Acceptance Criteria

Patient can manage:

- Name
- Date of birth
- Gender
- Phone
- Email
- Address
- Blood group
- Emergency contact

---

## US-PAT-02 — Search Doctors

**User Story**

As a patient, I want to search for doctors so that I can find a suitable
doctor.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

### Search/Filter Options

- Doctor name
- Specialization
- Location
- Hospital/clinic

---

## US-PAT-03 — View Doctor Profile

**User Story**

As a patient, I want to view a doctor's profile so that I can decide
whether the doctor is suitable for my needs.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

### Doctor information

- Name
- Specialization
- Qualification
- Experience
- Hospital/clinic
- Department
- Location
- Consultation availability

---

# EPIC 03 — Doctor Management

## US-DOC-01 — Doctor Profile

**User Story**

As a doctor, I want to manage my professional profile so that patients
can understand my professional background.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

### Profile Information

- Name
- Profile photo
- Qualification
- Specialization
- Experience
- Professional registration information
- Hospital/clinic affiliation
- Department
- Consultation availability

---

## US-DOC-02 — Hospital/Clinic Association

**User Story**

As a doctor, I want to associate myself with hospitals or clinics where
I practice so that patients can find my consultation locations.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

### Hospital/Clinic information

- Name
- Type
- Address
- City
- State
- PIN code
- Contact information
- Department

A doctor may be associated with multiple hospitals or clinics.

---

# EPIC 04 — Appointment Management

## US-APT-01 — View Doctor Availability

**User Story**

As a patient, I want to view a doctor's available appointment slots so
that I can select a suitable time.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

---

## US-APT-02 — Book Appointment

**User Story**

As a patient, I want to book an appointment with a doctor so that I can
receive medical consultation.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

### Acceptance Criteria

- Patient must be authenticated.
- Patient selects a doctor.
- Patient selects a hospital/clinic.
- Patient selects an available time slot.
- System prevents double booking.
- Appointment is stored.
- Patient can view the appointment.

---

## US-APT-03 — Manage Appointment

**User Story**

As a doctor, I want to manage my appointments so that I can organize
patient consultations.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

Doctor can:

- View appointments
- Confirm appointments
- Reject appointments
- Cancel appointments
- Mark appointments as completed

---

## US-APT-04 — Cancel Appointment

**User Story**

As a patient, I want to cancel an appointment so that an unavailable
appointment slot can be released.

**Priority:** P1  
**Story Points:** 3  
**Status:** Backlog

---

# EPIC 05 — Consultation Management

## US-CON-01 — Create Consultation

**User Story**

As a doctor, I want to record consultation information so that the
patient's healthcare history is maintained.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

### Consultation information

- Symptoms
- Observations
- Diagnosis
- Clinical notes
- Treatment plan

---

## US-CON-02 — View Consultation History

**User Story**

As an authorized doctor or patient, I want to view relevant consultation
history so that previous healthcare information can be reviewed.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

---

# EPIC 06 — Prescription Management

## US-PRE-01 — Create Prescription

**User Story**

As a doctor, I want to create a prescription for a patient so that the
patient receives the required medication instructions.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

### Prescription contains

- Patient
- Doctor
- Medicine
- Dosage
- Frequency
- Duration
- Instructions

---

## US-PRE-02 — Send Prescription to Pharmacy

**User Story**

As a doctor, I want to send a prescription to the pharmacy so that the
pharmacy can prepare the prescribed medicines.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

---

## US-PRE-03 — Patient Views Prescription

**User Story**

As a patient, I want to view my prescriptions so that I understand my
medication instructions.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

---

# EPIC 07 — Laboratory Management

## US-LAB-01 — Request Laboratory Test

**User Story**

As a doctor, I want to request a laboratory test for a patient so that
the patient's condition can be investigated.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

---

## US-LAB-02 — Process Test Request

**User Story**

As a laboratory technician, I want to process test requests so that
laboratory tests can be completed.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

### Test Status

REQUESTED  
↓  
ACCEPTED  
↓  
SAMPLE COLLECTED  
↓  
PROCESSING  
↓  
COMPLETED

---

## US-LAB-03 — Upload Laboratory Report

**User Story**

As a laboratory technician, I want to upload a completed laboratory
report so that the doctor and patient can access the result.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

---

## US-LAB-04 — View Laboratory Report

**User Story**

As an authorized patient or doctor, I want to view completed laboratory
reports so that medical results can be reviewed.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

---

# EPIC 08 — Pharmacy Workflow

## US-PHA-01 — View Prescriptions

**User Story**

As a pharmacist, I want to view prescriptions sent by doctors so that
I can prepare the prescribed medicines.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

---

## US-PHA-02 — Mark Prescription Ready

**User Story**

As a pharmacist, I want to mark a prescription as ready so that the
system can track its dispensing status.

**Priority:** P0  
**Story Points:** 2  
**Status:** Backlog

---

## US-PHA-03 — Dispense Prescription

**User Story**

As a pharmacist, I want to mark a prescription as dispensed so that
the patient and system know that the medicine has been provided.

**Priority:** P0  
**Story Points:** 2  
**Status:** Backlog

### Pharmacy workflow

SENT TO PHARMACY  
↓  
READY  
↓  
DISPENSED

### Pharmacy does NOT manage

- Inventory
- Suppliers
- Stock
- Billing
- Pharmacy employees

---

# EPIC 09 — Medical Timeline

## US-TIM-01 — Unified Medical Timeline

**User Story**

As a patient, I want to view my healthcare activities in chronological
order so that I can understand my complete care journey.

**Priority:** P0  
**Story Points:** 8  
**Status:** Backlog

### Timeline can include

- Appointments
- Consultations
- Laboratory requests
- Laboratory reports
- Prescriptions
- Medicine dispensing
- Follow-ups

---

# EPIC 10 — Follow-up Management

## US-FOL-01 — Schedule Follow-up

**User Story**

As a doctor, I want to schedule a follow-up appointment so that the
patient can continue their care.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

---

## US-FOL-02 — View Follow-up

**User Story**

As a patient, I want to view upcoming follow-ups so that I do not miss
important appointments.

**Priority:** P0  
**Story Points:** 2  
**Status:** Backlog

---

# EPIC 11 — Notifications

## US-NOT-01 — Appointment Notifications

**User Story**

As a patient, I want to receive appointment notifications so that I
know when my appointment is confirmed, cancelled, or completed.

**Priority:** P1  
**Story Points:** 5  
**Status:** Backlog

---

## US-NOT-02 — Lab Report Notification

**User Story**

As a patient, I want to receive a notification when my lab report is
available.

**Priority:** P1  
**Story Points:** 3  
**Status:** Backlog

---

## US-NOT-03 — Prescription/Dispensing Notification

**User Story**

As a patient, I want to know when my prescription has been processed
and dispensed.

**Priority:** P1  
**Story Points:** 3  
**Status:** Backlog

---

# EPIC 12 — Administration

## US-ADM-01 — User Management

**User Story**

As an administrator, I want to manage user accounts so that the
platform remains secure and organized.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

---

## US-ADM-02 — Doctor Management

**User Story**

As an administrator, I want to manage doctor accounts so that only
approved doctors can use the platform.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

---

## US-ADM-03 — Hospital/Clinic Management

**User Story**

As an administrator, I want to manage hospitals and clinics so that
doctor affiliations and patient searches use reliable information.

**Priority:** P0  
**Story Points:** 5  
**Status:** Backlog

---

## US-ADM-04 — Laboratory Management

**User Story**

As an administrator, I want to manage laboratories so that approved
laboratories can operate on the platform.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

---

## US-ADM-05 — Pharmacy Management

**User Story**

As an administrator, I want to manage pharmacy accounts so that
approved pharmacies can receive prescriptions.

**Priority:** P0  
**Story Points:** 3  
**Status:** Backlog

---

# EPIC 13 — Analytics

## US-ANA-01 — Admin Dashboard

**User Story**

As an administrator, I want to view system statistics so that I can
understand how AroSetu is being used.

**Priority:** P1  
**Story Points:** 5  
**Status:** Backlog

### Statistics

- Total patients
- Total doctors
- Total laboratories
- Total pharmacies
- Appointments
- Laboratory tests
- Prescriptions
- Dispensed prescriptions

---

# EPIC 14 — Audit & Security

## US-AUD-01 — Audit Logging

**User Story**

As an administrator, I want important system actions to be recorded so
that activities can be traced for security and accountability.

**Priority:** P1  
**Story Points:** 5  
**Status:** Backlog

### Example

Doctor creates prescription:

Doctor → Action → Timestamp → Prescription ID

---

# EPIC 15 — Advanced AroSetu Features

These features will be considered after the MVP is working.

## Advanced Features

- Automated appointment reminders
- Automated follow-up reminders
- Medication reminders
- Advanced doctor search
- Location-based search
- Timeline filtering
- Analytics improvements
- Advanced audit logs

---

# EPIC 16 — AI Assistant

AI features will be considered only after the core AroSetu platform
is stable.

Potential features:

- Patient medical-history summarization
- Natural-language search over authorized patient records
- Appointment information assistant

AI must not independently diagnose patients or prescribe treatment.

---

# EPIC 17 — Testing & Quality

## Testing Areas

- Unit testing
- Integration testing
- API testing
- Authentication testing
- Authorization testing
- Input validation
- Error handling
- Security testing
- Frontend testing

---

# EPIC 18 — Deployment & DevOps

## Features

- Git repository
- Branching strategy
- Docker
- CI/CD
- Automated tests
- Backend deployment
- Frontend deployment
- Database deployment
- Cloud file storage
- Production configuration

---

# Definition of Done

A user story is considered Done when:

- Required functionality is implemented.
- Backend API is implemented where required.
- Frontend interface is implemented where required.
- Validation is implemented.
- Authorization is implemented.
- Relevant tests pass.
- Major edge cases are handled.
- Code is committed to Git.
- Feature has been manually tested.
- Documentation is updated where necessary.