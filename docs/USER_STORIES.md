# AroSetu User Stories

# EPIC 01 — Authentication & Authorization

## US-AUTH-01 — User Registration

### User Story

> As a new user, I want to create a AroSetu account so that I can
> securely access the platform according to my role.

### Actors

- Patient
- Doctor
- Laboratory Technician
- Pharmacist

### Acceptance Criteria

- User can open the registration page.
- User must provide all required registration information.
- Email address must be unique.
- Password must satisfy the defined security requirements.
- Password must never be stored as plain text.
- The password must be securely hashed before storage.
- User account is created successfully when valid information is provided.
- Invalid input must be rejected.
- Duplicate email registration must be prevented.
- The appropriate user role must be assigned.
- Successful registration should provide appropriate feedback to the user.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-AUTH-02 — User Login

### User Story

> As a registered user, I want to log in securely so that I can access
> the features available to my role.

### Actors

- Patient
- Doctor
- Laboratory Technician
- Pharmacist
- Administrator

### Acceptance Criteria

- User can enter email and password.
- Valid credentials should authenticate the user.
- Invalid credentials should be rejected.
- Successful authentication should generate a secure authentication token.
- The user's role should be identified.
- User should be directed to the appropriate dashboard.
- Authentication failures should return an appropriate error message.
- Protected resources must not be accessible without authentication.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-AUTH-03 — User Logout

### User Story

> As an authenticated user, I want to log out so that I can securely
> end my session.

### Actors

- Patient
- Doctor
- Laboratory Technician
- Pharmacist
- Administrator

### Acceptance Criteria

- Authenticated user can log out.
- Authentication state is cleared appropriately.
- User cannot access protected frontend pages after logout.
- Subsequent requests must not be treated as authenticated after logout
  according to the application's token/session strategy.

### Priority

P0 — Critical

### Story Points

1

### Status

Backlog


---

## US-AUTH-04 — Role-Based Authorization

### User Story

> As a system administrator, I want users to have role-based permissions
> so that users can only access functionality appropriate to their role.

### Actors

- Patient
- Doctor
- Laboratory Technician
- Pharmacist
- Administrator

### Roles

- PATIENT
- DOCTOR
- LAB_TECHNICIAN
- PHARMACIST
- ADMIN

### Acceptance Criteria

- Every authenticated user has an assigned role.
- Protected APIs verify authentication.
- Protected APIs verify authorization.
- Patients cannot access doctor-only operations.
- Doctors cannot access administrator-only operations.
- Laboratory technicians cannot create or modify prescriptions.
- Pharmacists cannot create or modify prescriptions.
- Patients can access only their authorized records.
- Doctors can access only records they are authorized to access.
- Laboratory technicians can access only relevant laboratory information.
- Pharmacists can access only prescriptions relevant to their pharmacy.
- Administrators can access administrative functions.
- Unauthorized requests are rejected.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

# EPIC 02 — Patient Management

## US-PAT-01 — Patient Profile

### User Story

> As a patient, I want to manage my personal profile so that my information is accurate and up to date.

### Acceptance Criteria

- Patient can view their profile.
- Patient can update permitted personal information.
- Patient can update their phone number.
- Patient can update their address.
- Patient can update emergency contact information.
- Patient can view their blood group.
- Patient cannot change system-generated information such as their user ID.
- Invalid information must be rejected.
- Only the authenticated patient can modify their own profile.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PAT-02 — Search Doctors

### User Story

> As a patient, I want to search for doctors so that I can find a doctor suitable for my healthcare needs.

### Acceptance Criteria

- Patient can view available doctors.
- Patient can search by doctor name.
- Patient can filter by specialization.
- Patient can filter by location.
- Patient can filter by hospital/clinic.
- Search results should display relevant doctor information.
- Only active/approved doctors should appear to patients.
- Search should handle cases where no matching doctor is found.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-PAT-03 — View Doctor Profile

### User Story

> As a patient, I want to view a doctor's professional profile so that I can decide whether the doctor is suitable for my needs.

### Acceptance Criteria

The patient can view:

- Doctor name
- Profile photo
- Specialization
- Qualifications
- Years of experience
- Professional registration information where appropriate
- Hospital/clinic affiliation
- Department
- Hospital/clinic address
- City
- State
- PIN code
- Contact information intended for public display
- Consultation availability

If a doctor works at multiple locations, the patient can see the relevant locations.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PAT-04 — Manage Emergency Contact

### User Story

> As a patient, I want to maintain an emergency contact so that someone can be contacted when necessary.

### Acceptance Criteria

Patient can add:

- Emergency contact name
- Relationship
- Phone number
- Optional address

Patient can update or remove the emergency contact.

Only the authenticated patient can manage their emergency contact.

### Priority

P1 — High

### Story Points

2

### Status

Backlog


---

## US-PAT-05 — View Medical Records

### User Story

> As a patient, I want to view my healthcare records so that I can understand my previous care.

### Acceptance Criteria

Patient can view their authorized:

- Consultation records
- Diagnoses recorded by the doctor
- Clinical notes intended for patient access
- Previous medical history
- Prescriptions
- Laboratory reports

Patient cannot modify doctor-created or laboratory-created records.

Patient cannot access another patient's records.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-PAT-06 — View Prescriptions

### User Story

> As a patient, I want to view my prescriptions so that I understand the medicines and instructions provided by my doctor.

### Acceptance Criteria

Patient can view:

- Prescription ID
- Doctor
- Prescription date
- Medicine
- Dosage
- Frequency
- Duration
- Instructions
- Prescription status

Patient cannot modify the prescription.

Patient can view the pharmacy/dispensing status where applicable.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PAT-07 — View Laboratory Tests

### User Story

> As a patient, I want to view my laboratory test requests and their status so that I know the progress of my tests.

### Acceptance Criteria

Patient can view:

- Test name
- Requesting doctor
- Request date
- Test status
- Completion date when available
- Laboratory handling the test

The patient can only see their own laboratory information.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PAT-08 — View/Download Laboratory Reports

### User Story

> As a patient, I want to view and download my completed laboratory reports so that I can keep track of my test results.

### Acceptance Criteria

- Patient can view completed reports.
- Patient can download permitted report files.
- Reports are accessible only to authorized users.
- Incomplete reports cannot be presented as completed reports.
- Missing or unavailable files are handled gracefully.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PAT-09 — View Medical Timeline

### User Story

> As a patient, I want to view my healthcare activities chronologically so that I can understand my complete care journey.

### Acceptance Criteria

The timeline can contain:

- Appointments
- Consultations
- Laboratory requests
- Laboratory reports
- Prescriptions
- Medicine dispensing
- Follow-ups

- Events are displayed in chronological order.
- Each event contains its date and relevant information.
- Patient can open an event to view permitted details.
- Patient cannot view another patient's timeline.
- Timeline should handle missing event types correctly.

### Priority

P0 — Critical

### Story Points

8

### Status

Backlog


---

## US-PAT-10 — View Notifications

### User Story

> As a patient, I want to view notifications so that I don't miss important updates about my healthcare.

### Acceptance Criteria

Patient can receive/view notifications for:

- Appointment confirmation
- Appointment cancellation
- Lab report availability
- Prescription updates
- Medicine dispensing
- Follow-up reminders

Patient can mark notifications as read.

### Priority

P1 — High

### Story Points

3

### Status

Backlog

# EPIC 03 — Doctor Management

## US-DOC-01 — Doctor Profile

### User Story

> As a doctor, I want to create and manage my professional profile so that patients can understand my qualifications and professional background.

### Acceptance Criteria

- Doctor can view their profile.
- Doctor can update permitted profile information.
- Doctor can add/update:
  - Name
  - Profile photo
  - Qualifications
  - Specialization
  - Years of experience
  - Professional registration information
  - Areas of expertise
- Required information must be validated.
- Only the authenticated doctor can modify their own profile.
- Doctor profile should have an approval/verification status.
- Unapproved doctors should not appear in patient doctor searches.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-02 — Hospital/Clinic Association

### User Story

> As a doctor, I want to associate myself with hospitals or clinics where I practice so that patients can find my consultation locations.

### Acceptance Criteria

- Doctor can view their associated hospitals/clinics.
- Doctor can request or add an association with an approved hospital/clinic.
- A doctor can be associated with multiple hospitals or clinics.
- Each association must identify the hospital/clinic.
- Doctor can specify the department for each association.
- Doctor can specify consultation details for each location.
- Doctor cannot create an unverified hospital/clinic directly.
- Hospital/clinic association must have an appropriate status.

### Example

Dr. Arun Kumar may work at:

- City Hospital — Cardiology Department
- Care Clinic — Cardiology

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-03 — View Hospital/Clinic Details

### User Story

> As a doctor, I want to view the details of hospitals or clinics where I practice so that I can manage my consultation information correctly.

### Acceptance Criteria

Doctor can view:

- Hospital/clinic name
- Type
- Address
- City
- State
- PIN code
- Contact number
- Department
- Consultation location

Hospital/clinic information maintained by the administrator cannot be modified directly by the doctor unless explicitly permitted.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-04 — Manage Consultation Availability

### User Story

> As a doctor, I want to define my consultation availability so that patients can book appointments during appropriate time slots.

### Acceptance Criteria

Doctor can define:

- Working days
- Start time
- End time
- Consultation duration
- Break periods
- Available/unavailable status

Availability can be different for different hospitals or clinics.

### Example

City Hospital:

- Monday: 10:00 AM – 1:00 PM
- Wednesday: 10:00 AM – 1:00 PM

Care Clinic:

- Tuesday: 4:00 PM – 7:00 PM
- Thursday: 4:00 PM – 7:00 PM

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-05 — Manage Doctor Appointments

### User Story

> As a doctor, I want to manage my appointments so that I can organize and conduct patient consultations.

### Acceptance Criteria

Doctor can:

- View today's appointments.
- View upcoming appointments.
- View appointment details.
- Confirm an appointment.
- Reject an appointment.
- Cancel an appointment when appropriate.
- Mark an appointment as completed.
- View the patient associated with the appointment.

Doctor cannot modify appointments belonging to another doctor.

### Appointment Status

REQUESTED
↓
CONFIRMED
↓
COMPLETED

Alternative flows:

REQUESTED → REJECTED

REQUESTED → CANCELLED

CONFIRMED → CANCELLED

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-06 — View Patient Information

### User Story

> As a doctor, I want to view relevant information about patients associated with my care so that I can provide appropriate consultation.

### Acceptance Criteria

Doctor can view relevant patient information for an authorized patient-care relationship.

Doctor can view:

- Patient name
- Age/date of birth where appropriate
- Relevant profile information
- Emergency contact where appropriate
- Previous consultations
- Relevant medical history
- Prescriptions
- Laboratory reports
- Follow-up information

Doctor must not automatically have unrestricted access to every patient's records.

Doctor cannot modify records created by another healthcare professional unless the system explicitly allows such an action.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-07 — Create Consultation Record

### User Story

> As a doctor, I want to create a consultation record so that the patient's clinical information and treatment plan are documented.

### Acceptance Criteria

Doctor can create a consultation record for an appropriate appointment.

Consultation can contain:

- Symptoms
- Observations
- Diagnosis
- Clinical notes
- Treatment plan
- Consultation date

The consultation must be associated with:

- Patient
- Doctor
- Appointment

Doctor cannot create a consultation for an unrelated patient.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-08 — View Consultation History

### User Story

> As a doctor, I want to view previous consultation records for authorized patients so that I can understand their healthcare history.

### Acceptance Criteria

Doctor can view relevant previous consultations.

Each consultation displays:

- Date
- Doctor
- Symptoms
- Diagnosis
- Clinical notes
- Treatment plan

Access must be restricted to authorized patient records.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-09 — Create Prescription

### User Story

> As a doctor, I want to create a prescription for a patient so that the patient can receive the required medication instructions.

### Acceptance Criteria

Doctor can create a prescription containing:

- Patient
- Medicine
- Dosage
- Frequency
- Duration
- Instructions
- Prescription date

Doctor can add multiple medicines to one prescription.

The prescription must be associated with the appropriate consultation.

Required prescription information must be validated.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-10 — Send Prescription to Pharmacy

### User Story

> As a doctor, I want to send a prescription to a pharmacy so that the pharmacist can prepare the prescribed medicines.

### Acceptance Criteria

- Doctor can select an appropriate pharmacy.
- Doctor can send the prescription to the selected pharmacy.
- Prescription status changes appropriately.
- Pharmacy can view the prescription after it is sent.
- Patient can see that the prescription has been sent to the pharmacy.
- Pharmacist cannot modify the medicine, dosage, frequency, or duration.
- Doctor cannot silently modify a prescription after it has been dispensed.

### Prescription Workflow

DRAFT
↓
PRESCRIBED
↓
SENT TO PHARMACY
↓
READY
↓
DISPENSED

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-11 — Request Laboratory Test

### User Story

> As a doctor, I want to request a laboratory test for a patient so that the patient's condition can be investigated.

### Acceptance Criteria

Doctor can:

- Select patient.
- Select laboratory.
- Select test type.
- Add test instructions.
- Submit the test request.
- View the request status.

The test request must be associated with the appropriate patient and doctor.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-12 — View Laboratory Test Status

### User Story

> As a doctor, I want to track laboratory test requests so that I know whether the requested tests have been completed.

### Acceptance Criteria

Doctor can view:

- Test name
- Patient
- Laboratory
- Request date
- Current status
- Completion date where available

The status should reflect the laboratory workflow.

### Laboratory Workflow

REQUESTED
↓
ACCEPTED
↓
SAMPLE COLLECTED
↓
PROCESSING
↓
COMPLETED

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-13 — Review Laboratory Report

### User Story

> As a doctor, I want to review a patient's completed laboratory report so that I can use the results during the patient's care.

### Acceptance Criteria

- Doctor can view completed reports for authorized patients.
- Doctor can download permitted report files.
- Doctor can identify the test and patient associated with the report.
- Doctor can review the report after laboratory submission.
- Doctor cannot alter the original laboratory report.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-14 — Schedule Follow-up

### User Story

> As a doctor, I want to schedule a follow-up for a patient so that the patient's treatment can be continued and reviewed.

### Acceptance Criteria

Doctor can:

- Select patient.
- Select follow-up date.
- Select time where applicable.
- Add follow-up reason.
- Add instructions.
- Associate the follow-up with the relevant consultation.

Patient should be able to view the scheduled follow-up.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-15 — Doctor Dashboard

### User Story

> As a doctor, I want a dashboard showing my important activities so that I can quickly understand my daily workload.

### Dashboard can display

- Today's appointments
- Upcoming appointments
- Pending consultations
- Pending laboratory reports
- Active prescriptions
- Upcoming follow-ups

### Acceptance Criteria

- Dashboard displays information belonging to the authenticated doctor.
- Information is updated appropriately.
- Doctor cannot see another doctor's dashboard data.

### Priority

P1 — High

### Story Points

5

### Status

Backlog

# EPIC 03 — Doctor Management

## US-DOC-01 — Doctor Profile

### User Story

> As a doctor, I want to create and manage my professional profile so that patients can understand my qualifications and professional background.

### Acceptance Criteria

- Doctor can view their profile.
- Doctor can update permitted profile information.
- Doctor can add/update:
  - Name
  - Profile photo
  - Qualifications
  - Specialization
  - Years of experience
  - Professional registration information
  - Areas of expertise
- Required information must be validated.
- Only the authenticated doctor can modify their own profile.
- Doctor profile should have an approval/verification status.
- Unapproved doctors should not appear in patient doctor searches.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-02 — Hospital/Clinic Association

### User Story

> As a doctor, I want to associate myself with hospitals or clinics where I practice so that patients can find my consultation locations.

### Acceptance Criteria

- Doctor can view their associated hospitals/clinics.
- Doctor can request or add an association with an approved hospital/clinic.
- A doctor can be associated with multiple hospitals or clinics.
- Each association must identify the hospital/clinic.
- Doctor can specify the department for each association.
- Doctor can specify consultation details for each location.
- Doctor cannot create an unverified hospital/clinic directly.
- Hospital/clinic association must have an appropriate status.

### Example

Dr. Arun Kumar may work at:

- City Hospital — Cardiology Department
- Care Clinic — Cardiology

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-03 — View Hospital/Clinic Details

### User Story

> As a doctor, I want to view the details of hospitals or clinics where I practice so that I can manage my consultation information correctly.

### Acceptance Criteria

Doctor can view:

- Hospital/clinic name
- Type
- Address
- City
- State
- PIN code
- Contact number
- Department
- Consultation location

Hospital/clinic information maintained by the administrator cannot be modified directly by the doctor unless explicitly permitted.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-04 — Manage Consultation Availability

### User Story

> As a doctor, I want to define my consultation availability so that patients can book appointments during appropriate time slots.

### Acceptance Criteria

Doctor can define:

- Working days
- Start time
- End time
- Consultation duration
- Break periods
- Available/unavailable status

Availability can be different for different hospitals or clinics.

### Example

City Hospital:

- Monday: 10:00 AM – 1:00 PM
- Wednesday: 10:00 AM – 1:00 PM

Care Clinic:

- Tuesday: 4:00 PM – 7:00 PM
- Thursday: 4:00 PM – 7:00 PM

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-05 — Manage Doctor Appointments

### User Story

> As a doctor, I want to manage my appointments so that I can organize and conduct patient consultations.

### Acceptance Criteria

Doctor can:

- View today's appointments.
- View upcoming appointments.
- View appointment details.
- Confirm an appointment.
- Reject an appointment.
- Cancel an appointment when appropriate.
- Mark an appointment as completed.
- View the patient associated with the appointment.

Doctor cannot modify appointments belonging to another doctor.

### Appointment Status

REQUESTED
↓
CONFIRMED
↓
COMPLETED

Alternative flows:

REQUESTED → REJECTED

REQUESTED → CANCELLED

CONFIRMED → CANCELLED

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-06 — View Patient Information

### User Story

> As a doctor, I want to view relevant information about patients associated with my care so that I can provide appropriate consultation.

### Acceptance Criteria

Doctor can view relevant patient information for an authorized patient-care relationship.

Doctor can view:

- Patient name
- Age/date of birth where appropriate
- Relevant profile information
- Emergency contact where appropriate
- Previous consultations
- Relevant medical history
- Prescriptions
- Laboratory reports
- Follow-up information

Doctor must not automatically have unrestricted access to every patient's records.

Doctor cannot modify records created by another healthcare professional unless the system explicitly allows such an action.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-07 — Create Consultation Record

### User Story

> As a doctor, I want to create a consultation record so that the patient's clinical information and treatment plan are documented.

### Acceptance Criteria

Doctor can create a consultation record for an appropriate appointment.

Consultation can contain:

- Symptoms
- Observations
- Diagnosis
- Clinical notes
- Treatment plan
- Consultation date

The consultation must be associated with:

- Patient
- Doctor
- Appointment

Doctor cannot create a consultation for an unrelated patient.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-08 — View Consultation History

### User Story

> As a doctor, I want to view previous consultation records for authorized patients so that I can understand their healthcare history.

### Acceptance Criteria

Doctor can view relevant previous consultations.

Each consultation displays:

- Date
- Doctor
- Symptoms
- Diagnosis
- Clinical notes
- Treatment plan

Access must be restricted to authorized patient records.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-09 — Create Prescription

### User Story

> As a doctor, I want to create a prescription for a patient so that the patient can receive the required medication instructions.

### Acceptance Criteria

Doctor can create a prescription containing:

- Patient
- Medicine
- Dosage
- Frequency
- Duration
- Instructions
- Prescription date

Doctor can add multiple medicines to one prescription.

The prescription must be associated with the appropriate consultation.

Required prescription information must be validated.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-10 — Send Prescription to Pharmacy

### User Story

> As a doctor, I want to send a prescription to a pharmacy so that the pharmacist can prepare the prescribed medicines.

### Acceptance Criteria

- Doctor can select an appropriate pharmacy.
- Doctor can send the prescription to the selected pharmacy.
- Prescription status changes appropriately.
- Pharmacy can view the prescription after it is sent.
- Patient can see that the prescription has been sent to the pharmacy.
- Pharmacist cannot modify the medicine, dosage, frequency, or duration.
- Doctor cannot silently modify a prescription after it has been dispensed.

### Prescription Workflow

DRAFT
↓
PRESCRIBED
↓
SENT TO PHARMACY
↓
READY
↓
DISPENSED

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-11 — Request Laboratory Test

### User Story

> As a doctor, I want to request a laboratory test for a patient so that the patient's condition can be investigated.

### Acceptance Criteria

Doctor can:

- Select patient.
- Select laboratory.
- Select test type.
- Add test instructions.
- Submit the test request.
- View the request status.

The test request must be associated with the appropriate patient and doctor.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-DOC-12 — View Laboratory Test Status

### User Story

> As a doctor, I want to track laboratory test requests so that I know whether the requested tests have been completed.

### Acceptance Criteria

Doctor can view:

- Test name
- Patient
- Laboratory
- Request date
- Current status
- Completion date where available

The status should reflect the laboratory workflow.

### Laboratory Workflow

REQUESTED
↓
ACCEPTED
↓
SAMPLE COLLECTED
↓
PROCESSING
↓
COMPLETED

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-13 — Review Laboratory Report

### User Story

> As a doctor, I want to review a patient's completed laboratory report so that I can use the results during the patient's care.

### Acceptance Criteria

- Doctor can view completed reports for authorized patients.
- Doctor can download permitted report files.
- Doctor can identify the test and patient associated with the report.
- Doctor can review the report after laboratory submission.
- Doctor cannot alter the original laboratory report.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-14 — Schedule Follow-up

### User Story

> As a doctor, I want to schedule a follow-up for a patient so that the patient's treatment can be continued and reviewed.

### Acceptance Criteria

Doctor can:

- Select patient.
- Select follow-up date.
- Select time where applicable.
- Add follow-up reason.
- Add instructions.
- Associate the follow-up with the relevant consultation.

Patient should be able to view the scheduled follow-up.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-DOC-15 — Doctor Dashboard

### User Story

> As a doctor, I want a dashboard showing my important activities so that I can quickly understand my daily workload.

### Dashboard can display

- Today's appointments
- Upcoming appointments
- Pending consultations
- Pending laboratory reports
- Active prescriptions
- Upcoming follow-ups

### Acceptance Criteria

- Dashboard displays information belonging to the authenticated doctor.
- Information is updated appropriately.
- Doctor cannot see another doctor's dashboard data.

### Priority

P1 — High

### Story Points

5

### Status

Backlog

# EPIC 04 — Hospital & Clinic Management

## US-HOS-01 — Add Hospital/Clinic

### User Story

> As an administrator, I want to add a hospital or clinic to AroSetu so that doctors and patients can use verified healthcare locations.

### Acceptance Criteria

- Administrator can create a hospital/clinic record.
- Administrator must provide required information.
- Hospital/clinic name must be provided.
- Hospital/clinic type must be specified.
- Address must be provided.
- City must be provided.
- State must be provided.
- PIN code must be validated.
- Contact information must be validated.
- Hospital/clinic should have an active/inactive status.
- Duplicate hospital/clinic records should be prevented where appropriate.

### Hospital/Clinic Information

- Name
- Type
- Address
- City
- State
- PIN code
- Contact number
- Email
- Status

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-HOS-02 — View Hospital/Clinic

### User Story

> As a patient, doctor, or administrator, I want to view hospital or clinic information so that I can understand where healthcare services are provided.

### Acceptance Criteria

Authorized users can view:

- Hospital/clinic name
- Type
- Address
- City
- State
- PIN code
- Contact information
- Available departments
- Active/inactive status where appropriate

Patients should only see hospitals/clinics that are active and available on the platform.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-HOS-03 — Update Hospital/Clinic

### User Story

> As an administrator, I want to update hospital or clinic information so that the information available on AroSetu remains accurate.

### Acceptance Criteria

- Administrator can update permitted hospital/clinic information.
- Changes must be validated.
- Updated information should be reflected wherever the hospital/clinic is displayed.
- Administrator cannot accidentally create a duplicate hospital/clinic.
- Important changes should be recorded in the audit log.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-HOS-04 — Activate/Deactivate Hospital/Clinic

### User Story

> As an administrator, I want to activate or deactivate a hospital or clinic so that only currently available healthcare locations can be used.

### Acceptance Criteria

- Administrator can activate a hospital/clinic.
- Administrator can deactivate a hospital/clinic.
- Deactivated hospitals/clinics should not appear in new patient searches.
- Deactivated hospitals/clinics should not be available for new appointments.
- Existing historical records should continue to reference the location.
- Deactivation should not delete historical healthcare data.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-HOS-05 — Manage Hospital Departments

### User Story

> As an administrator, I want to manage hospital or clinic departments so that doctors can be associated with the appropriate department.

### Acceptance Criteria

Administrator can:

- Add a department.
- Update a department.
- Activate/deactivate a department.
- View departments belonging to a hospital/clinic.

Examples:

- Cardiology
- Neurology
- Orthopedics
- General Medicine
- Dermatology
- Pediatrics

A department must belong to a specific hospital/clinic.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-HOS-06 — Associate Doctor With Hospital/Clinic

### User Story

> As a doctor, I want to associate myself with a hospital or clinic where I practice so that patients can find my consultation location.

### Acceptance Criteria

- Doctor can request an association with an approved hospital/clinic.
- Doctor can select the appropriate department.
- Administrator can approve or reject the association.
- Only approved associations become visible to patients.
- A doctor can be associated with multiple hospitals/clinics.
- The same doctor cannot have duplicate active associations with the same location.

### Example

Dr. Arun Kumar:

City Hospital
→ Cardiology

Care Clinic
→ Cardiology

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-HOS-07 — Manage Doctor-Hospital Details

### User Story

> As a doctor, I want to manage my consultation details for each hospital or clinic so that patients know when and where I am available.

### Acceptance Criteria

For each approved hospital/clinic association, the doctor can specify:

- Department
- Consultation room where applicable
- Consultation days
- Consultation hours
- Consultation duration
- Availability status

The doctor cannot modify the hospital's core information such as:

- Hospital name
- Address
- Contact number

unless explicitly authorized.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-HOS-08 — Search Hospitals/Clinics

### User Story

> As a patient, I want to search for hospitals and clinics so that I can find healthcare providers near my preferred location.

### Acceptance Criteria

Patient can search by:

- Hospital/clinic name
- City
- State
- PIN code
- Department

Search results should only contain active hospitals/clinics.

### Priority

P1 — High

### Story Points

3

### Status

Backlog


---

## US-HOS-09 — View Doctors at Hospital/Clinic

### User Story

> As a patient, I want to view doctors practicing at a hospital or clinic so that I can choose an appropriate doctor.

### Acceptance Criteria

Patient can:

- Open a hospital/clinic profile.
- View its departments.
- View active doctors associated with the location.
- Filter doctors by department/specialization.
- Open a doctor's profile.
- View available consultation slots where applicable.

Only approved doctor-hospital associations should be displayed.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-HOS-10 — Hospital/Clinic Profile

### User Story

> As a patient, I want to view a hospital or clinic profile so that I can understand the services and doctors available at that location.

### Acceptance Criteria

Hospital/clinic profile can display:

- Name
- Type
- Address
- City
- State
- PIN code
- Contact information
- Departments
- Doctors
- Available consultation locations
- Basic facility information where available

### Priority

P1 — High

### Story Points

3

### Status

Backlog

# EPIC 05 — Appointment Management

## US-APT-01 — View Doctor Availability

### User Story

> As a patient, I want to view a doctor's available consultation slots so that I can choose a suitable appointment time.

### Acceptance Criteria

* Patient can select a doctor.
* Patient can select a hospital/clinic where the doctor practices.
* Patient can view the doctor's available days.
* Patient can view available time slots.
* Only active doctor-hospital associations are shown.
* Only available slots can be selected.
* Already booked slots cannot be selected.
* Past time slots cannot be booked.
* Doctor's availability must be associated with the correct hospital/clinic.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-APT-02 — Book Appointment

### User Story

> As a patient, I want to book an appointment with a doctor so that I can receive a medical consultation.

### Acceptance Criteria

* Patient must be authenticated.
* Patient can select a doctor.
* Patient can select a hospital/clinic.
* Patient can select an available date.
* Patient can select an available time slot.
* System verifies that the slot is still available before booking.
* System prevents double booking.
* Appointment is associated with:

  * Patient
  * Doctor
  * Hospital/Clinic
  * Date
  * Time
* Appointment receives a unique identifier.
* Patient can view the created appointment.
* Appropriate confirmation is displayed after successful booking.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-APT-03 — Appointment Confirmation

### User Story

> As a doctor, I want to confirm or reject appointment requests so that I can manage my consultation schedule.

### Acceptance Criteria

* Doctor can view pending appointment requests.
* Doctor can confirm an appointment.
* Doctor can reject an appointment.

### Status Flow

REQUESTED
↓
CONFIRMED

Or:

REQUESTED
↓
REJECTED

* Only the doctor associated with the appointment can confirm or reject it.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-APT-04 — View Patient Appointments

### User Story

> As a patient, I want to view my appointments so that I can keep track of my upcoming and previous consultations.

### Acceptance Criteria

Patient can view:

* Upcoming appointments
* Previous appointments
* Cancelled appointments
* Rejected appointments

Each appointment displays:

* Appointment ID
* Doctor
* Specialization
* Hospital/Clinic
* Date
* Time
* Status

Patient can open an appointment to view its details.

Patient can only view their own appointments.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-APT-05 — Doctor Appointment Dashboard

### User Story

> As a doctor, I want to view my appointments so that I can manage my daily consultation schedule.

### Acceptance Criteria

Doctor can view:

* Today's appointments
* Upcoming appointments
* Completed appointments
* Cancelled appointments
* Pending appointment requests

Doctor can filter appointments by:

* Date
* Hospital/Clinic
* Status

Doctor can only view appointments associated with them.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-APT-06 — Mark Appointment as Completed

### User Story

> As a doctor, I want to mark an appointment as completed after the consultation so that the system can track the patient's care journey.

### Acceptance Criteria

* Doctor can mark a confirmed appointment as completed.
* Completed appointments cannot be booked again.
* Completion date and time are recorded.
* A consultation record can be associated with the completed appointment.
* Patient can see the updated appointment status.

### Status Flow

REQUESTED
↓
CONFIRMED
↓
COMPLETED

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-APT-07 — Cancel Appointment by Patient

### User Story

> As a patient, I want to cancel an appointment so that I can inform the doctor and release the appointment slot.

### Acceptance Criteria

* Patient can cancel an eligible appointment.
* Patient cannot cancel an already completed appointment.
* Cancelled appointment receives a cancellation status.
* Appointment slot becomes available again where appropriate.
* Cancellation date and time are recorded.
* Doctor can see that the appointment was cancelled.
* Patient cannot cancel another patient's appointment.

### Status Flow

REQUESTED → CANCELLED

CONFIRMED → CANCELLED

### Priority

P1 — High

### Story Points

3

### Status

Backlog

---

## US-APT-08 — Cancel Appointment by Doctor

### User Story

> As a doctor, I want to cancel an appointment when necessary so that patients are informed when I cannot conduct the consultation.

### Acceptance Criteria

* Doctor can cancel an eligible appointment.
* Doctor must provide a cancellation reason where required.
* Patient can see that the appointment was cancelled.
* Patient receives an appropriate notification.
* Cancelled slot can become available again where appropriate.
* Doctor cannot cancel another doctor's appointment.

### Priority

P1 — High

### Story Points

3

### Status

Backlog

---

## US-APT-09 — Reschedule Appointment

### User Story

> As a patient, I want to request a new appointment time so that I can attend the consultation when I am available.

### Acceptance Criteria

* Patient can request rescheduling for an eligible appointment.
* Patient can view available alternative slots.
* Patient can select a new available slot.
* System verifies that the new slot is still available.
* Original slot is released only after rescheduling succeeds.
* Doctor can see the updated appointment information.
* Rescheduling history is maintained.

### Priority

P1 — High

### Story Points

5

### Status

Backlog

---

## US-APT-10 — Prevent Double Booking

### User Story

> As a system, I want to prevent multiple patients from booking the same doctor, location, date, and time slot so that appointment schedules remain consistent.

### Acceptance Criteria

* A consultation slot can have only the allowed number of appointments.
* For a normal consultation slot, only one patient can book it.
* System checks slot availability before creating an appointment.
* Database constraints should help prevent duplicate bookings.
* Simultaneous booking attempts must be handled safely.
* Failed booking attempts should return an appropriate message.

### Example

Doctor: Dr. Arun
Hospital: City Hospital
Date: 20 August 2026
Time: 10:00 AM

Patient A → BOOKED

Patient B → BOOKING REJECTED

### Priority

P0 — Critical

### Story Points

8

### Status

Backlog

---

## US-APT-11 — Appointment Status Tracking

### User Story

> As a patient or doctor, I want to see the current status of an appointment so that I know what is happening with the consultation.

### Appointment Statuses

* REQUESTED
* CONFIRMED
* REJECTED
* CANCELLED
* COMPLETED

### Acceptance Criteria

* Appointment status is displayed to authorized users.
* Status changes follow valid workflow rules.
* Invalid status transitions are rejected.
* Status history can be recorded.
* Patient and doctor see the appropriate current status.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-APT-12 — Appointment Details

### User Story

> As a patient, I want to view complete appointment details so that I know where and when my consultation will take place.

### Acceptance Criteria

Patient can view:

* Appointment ID
* Doctor name
* Doctor specialization
* Hospital/Clinic
* Department
* Address
* Date
* Time
* Appointment status
* Booking date
* Relevant instructions

Patient should not see information belonging to unrelated users.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-APT-13 — Doctor Availability Management

### User Story

> As a doctor, I want to manage my consultation availability for each hospital or clinic so that patients can book appointments only during my available hours.

### Acceptance Criteria

Doctor can:

* Add available days.
* Set start time.
* Set end time.
* Set consultation duration.
* Define break periods.
* Temporarily disable availability.
* Manage availability separately for different hospitals/clinics.

### Example

City Hospital:

Monday: 10:00 AM – 1:00 PM
Wednesday: 10:00 AM – 1:00 PM

Care Clinic:

Tuesday: 4:00 PM – 7:00 PM
Thursday: 4:00 PM – 7:00 PM

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-APT-14 — Appointment Notifications

### User Story

> As a patient, I want to receive notifications when my appointment status changes so that I do not miss important updates.

### Acceptance Criteria

Patient can receive notifications when:

* Appointment is confirmed.
* Appointment is rejected.
* Appointment is cancelled.
* Appointment is rescheduled.

### Priority

P1 — High

### Story Points

3

### Status

Backlog

---

## US-APT-15 — Appointment History

### User Story

> As a patient, I want to view my appointment history so that I can understand my previous consultations.

### Acceptance Criteria

Patient can view:

* Completed appointments
* Cancelled appointments
* Rejected appointments
* Previous doctors
* Hospital/clinic
* Appointment dates

Appointment history should contribute relevant events to the medical timeline.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

# EPIC 06 — Consultation Management

## US-CON-01 — Start Consultation

### User Story

> As a doctor, I want to start a consultation for a confirmed appointment so that I can record the patient's healthcare information.

### Acceptance Criteria

* Doctor can start a consultation for an eligible appointment.
* The appointment must belong to the authenticated doctor.
* The appointment must be confirmed.
* Consultation is associated with:

  * Patient
  * Doctor
  * Appointment
* Consultation start date and time are recorded.
* Patient can see that the consultation has started where appropriate.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-CON-02 — Record Patient Symptoms

### User Story

> As a doctor, I want to record the patient's reported symptoms so that they become part of the consultation record.

### Acceptance Criteria

Doctor can record:

* Primary symptoms

* Additional symptoms

* Duration of symptoms

* Relevant observations provided during consultation

* Doctor can save the consultation.

* Required fields must be validated.

* Patient cannot modify the doctor's clinical record.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-CON-03 — Record Clinical Observations

### User Story

> As a doctor, I want to record my clinical observations so that relevant findings from the consultation are documented.

### Acceptance Criteria

Doctor can record:

* General observations

* Relevant clinical findings

* Notes

* Other observations required for the consultation

* Information is associated with the correct patient and consultation.

* Only authorized doctors can create or update the record.

* Patient has read-only access to information intended for patient viewing.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-CON-04 — Record Diagnosis

### User Story

> As a doctor, I want to record my diagnosis so that the patient's consultation history contains the doctor's clinical assessment.

### Acceptance Criteria

* Doctor can record a diagnosis.
* Diagnosis is associated with the consultation.
* Diagnosis is associated with the patient and doctor.
* Only the authorized doctor can create or update the diagnosis according to the application's record policy.
* Patient can view the diagnosis when it is made available to the patient.
* Diagnosis cannot be modified by the patient.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-CON-05 — Record Treatment Plan

### User Story

> As a doctor, I want to record a treatment plan so that the patient's next steps are documented.

### Acceptance Criteria

Doctor can record:

* Treatment plan
* General instructions
* Recommended actions
* Follow-up instructions

The treatment plan is associated with the consultation.

Patient can view permitted treatment information.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-CON-06 — Complete Consultation

### User Story

> As a doctor, I want to complete a consultation after recording the required information so that the appointment can be finalized.

### Acceptance Criteria

* Doctor can complete an active consultation.
* Required consultation information must be validated before completion.
* Consultation completion date and time are recorded.
* Associated appointment is marked as completed.
* Completed consultation becomes part of the patient's medical history.
* Patient can view the completed consultation where appropriate.

### Workflow

```text
APPOINTMENT CONFIRMED
        ↓
CONSULTATION STARTED
        ↓
CONSULTATION IN PROGRESS
        ↓
CONSULTATION COMPLETED
```

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-CON-07 — View Consultation History

### User Story

> As a doctor, I want to view previous consultation records for authorized patients so that I can understand their previous care.

### Acceptance Criteria

Doctor can view relevant previous consultations.

Each consultation can display:

* Consultation date
* Doctor
* Symptoms
* Clinical observations
* Diagnosis
* Treatment plan
* Follow-up instructions

Doctor can only access consultations they are authorized to view.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-CON-08 — Patient Views Consultation

### User Story

> As a patient, I want to view my completed consultation records so that I can understand the care I received.

### Acceptance Criteria

Patient can view their authorized consultation records.

Patient can view:

* Consultation date
* Doctor
* Hospital/Clinic
* Symptoms
* Diagnosis
* Treatment information
* Follow-up instructions where available

Patient cannot:

* Edit consultation records
* Delete consultation records
* Modify diagnosis
* Modify clinical notes

Patient cannot access another patient's consultation.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-CON-09 — Update Consultation Record

### User Story

> As a doctor, I want to update an eligible consultation record so that I can correct incomplete or inaccurate information.

### Acceptance Criteria

* Only the authorized doctor can update an eligible consultation.
* System records when the consultation was updated.
* Important changes should be traceable through audit logging.
* Completed/locked records cannot be silently changed after downstream actions where applicable.
* Patient cannot modify the consultation record.

### Priority

P1 — High

### Story Points

5

### Status

Backlog

---

## US-CON-10 — Consultation Record Validation

### User Story

> As a system, I want to validate consultation records so that incomplete or invalid healthcare records are not stored.

### Acceptance Criteria

* Required fields are validated.
* Invalid data is rejected.
* Consultation must reference a valid patient.
* Consultation must reference a valid doctor.
* Consultation must reference a valid appointment.
* Doctor must be authorized to create the consultation for the selected patient.
* Invalid consultation requests must return appropriate errors.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-CON-11 — Link Consultation With Prescription

### User Story

> As a doctor, I want to associate a prescription with the consultation so that the prescribed medicines are connected to the patient's treatment.

### Acceptance Criteria

* Doctor can create a prescription from an eligible consultation.
* Prescription is linked to the correct patient.
* Prescription is linked to the correct doctor.
* Prescription references the relevant consultation.
* Patient can access the prescription through their healthcare records.
* Pharmacy can receive the prescription through the prescription workflow.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-CON-12 — Link Consultation With Laboratory Request

### User Story

> As a doctor, I want to request laboratory tests from a consultation so that the investigation is connected to the patient's care journey.

### Acceptance Criteria

* Doctor can create a laboratory request from an eligible consultation.
* Laboratory request is linked to:

  * Patient
  * Doctor
  * Consultation
  * Laboratory
* Doctor can track the laboratory request.
* Completed laboratory reports can be linked back to the consultation.
* Patient can see the relevant laboratory activity in their medical records.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-CON-13 — Consultation Timeline Integration

### User Story

> As a patient, I want completed consultations to appear in my medical timeline so that I can understand my healthcare journey chronologically.

### Acceptance Criteria

* Completed consultation appears in the patient's medical timeline.
* Timeline displays consultation date and relevant summary information.
* Consultation can be opened from the timeline.
* Related prescription and laboratory activities can be connected to the consultation.
* Patient cannot view another patient's timeline.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

# EPIC 07 — Prescription Management

## US-PRE-01 — Create Prescription

### User Story

> As a doctor, I want to create a prescription for a patient so that the patient receives the required medication instructions.

### Acceptance Criteria

* Doctor can create a prescription for an eligible consultation.
* Prescription is associated with:

  * Patient
  * Doctor
  * Consultation
* Doctor can add one or more medicines.
* Each medicine can contain:

  * Medicine name
  * Dosage
  * Frequency
  * Duration
  * Instructions
* Required fields must be validated.
* Prescription receives a unique identifier.
* Prescription creation date and time are recorded.
* Patient can view the prescription after it is finalized.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-PRE-02 — Add Multiple Medicines

### User Story

> As a doctor, I want to add multiple medicines to a prescription so that I can provide complete medication instructions to the patient.

### Acceptance Criteria

* Doctor can add multiple medicines to one prescription.
* Each medicine has its own dosage.
* Each medicine has its own frequency.
* Each medicine has its own duration.
* Each medicine can have separate instructions.
* Doctor can remove a medicine before finalizing the prescription.
* Duplicate or invalid medicine entries should be handled appropriately.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-PRE-03 — Save Prescription as Draft

### User Story

> As a doctor, I want to save a prescription as a draft so that I can complete it before sending it to the pharmacy.

### Acceptance Criteria

* Doctor can save an incomplete prescription as a draft.
* Draft prescription is visible only to authorized users.
* Doctor can edit the draft.
* Draft is not sent to the pharmacy.
* Draft is not marked as dispensed.
* Draft can be finalized after required information is completed.

### Prescription Status

```text id="d1l4x8"
DRAFT
```

### Priority

P1 — High

### Story Points

3

### Status

Backlog

---

## US-PRE-04 — Finalize Prescription

### User Story

> As a doctor, I want to finalize a prescription so that it becomes an official prescription for the patient.

### Acceptance Criteria

* Doctor can finalize an eligible draft prescription.
* Required information must be completed.
* Finalized prescription cannot be treated as a draft.
* Prescription receives a finalized date and time.
* Patient can view the finalized prescription.
* Doctor can send the finalized prescription to an appropriate pharmacy.

### Status Flow

```text id="6nq5vn"
DRAFT
   ↓
PRESCRIBED
```

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-PRE-05 — Send Prescription to Pharmacy

### User Story

> As a doctor, I want to send a finalized prescription to a pharmacy so that the pharmacist can prepare the prescribed medicines.

### Acceptance Criteria

* Doctor can select an appropriate pharmacy.
* Doctor can send a finalized prescription.
* Prescription is associated with the selected pharmacy.
* Pharmacy receives the prescription.
* Prescription status changes appropriately.
* Patient can see that the prescription has been sent to the pharmacy.
* Pharmacist can view the prescription.
* Pharmacist cannot modify the prescription contents.

### Status Flow

```text id="qz4t8x"
PRESCRIBED
     ↓
SENT_TO_PHARMACY
```

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-PRE-06 — Pharmacist Views Prescription

### User Story

> As a pharmacist, I want to view prescriptions sent to my pharmacy so that I can prepare the prescribed medicines.

### Acceptance Criteria

Pharmacist can view prescriptions assigned to their pharmacy.

Pharmacist can view:

* Prescription ID
* Patient information required for dispensing
* Doctor
* Prescription date
* Medicine name
* Dosage
* Frequency
* Duration
* Instructions

Pharmacist cannot:

* Change medicine
* Change dosage
* Change frequency
* Change duration
* Change doctor's instructions
* Change diagnosis
* Create a prescription

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-PRE-07 — Mark Prescription as Ready

### User Story

> As a pharmacist, I want to mark a prescription as ready so that the patient and system know that the prescribed medicines have been prepared.

### Acceptance Criteria

* Pharmacist can mark an eligible prescription as ready.
* Only the pharmacy assigned to the prescription can update the status.
* Prescription contents cannot be modified.
* Patient can see the updated status.
* Doctor can see the updated status.

### Status Flow

```text id="9n3nqj"
SENT_TO_PHARMACY
        ↓
READY
```

### Priority

P0 — Critical

### Story Points

2

### Status

Backlog

---

## US-PRE-08 — Dispense Prescription

### User Story

> As a pharmacist, I want to mark a prescription as dispensed so that the system records that the prescribed medicines have been provided.

### Acceptance Criteria

* Pharmacist can mark an eligible prescription as dispensed.
* Only the assigned pharmacy can perform the action.
* Dispensing date and time are recorded.
* Patient can see that the prescription has been dispensed.
* Doctor can see the dispensing status.
* A dispensed prescription cannot be silently changed back to an earlier status.

### Status Flow

```text id="j7w4e2"
READY
  ↓
DISPENSED
```

### Priority

P0 — Critical

### Story Points

2

### Status

Backlog

---

## US-PRE-09 — Patient Views Prescription

### User Story

> As a patient, I want to view my prescriptions so that I understand the medicines and instructions provided by my doctor.

### Acceptance Criteria

Patient can view:

* Prescription ID
* Doctor
* Hospital/Clinic where applicable
* Prescription date
* Medicine name
* Dosage
* Frequency
* Duration
* Instructions
* Prescription status

Patient cannot modify prescription information.

Patient can view pharmacy processing status where applicable.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-PRE-10 — Prescription Status Tracking

### User Story

> As a patient, doctor, or pharmacist, I want to see the current prescription status so that I know where the prescription is in the dispensing process.

### Prescription Statuses

```text id="m2a8ke"
DRAFT
PRESCRIBED
SENT_TO_PHARMACY
READY
DISPENSED
```

### Acceptance Criteria

* Authorized users can view the current status.
* Status transitions follow valid workflow rules.
* Invalid status transitions are rejected.
* Status changes are recorded.
* Patient sees the appropriate patient-facing status.
* Doctor sees the prescription workflow status.
* Pharmacist sees prescriptions assigned to their pharmacy.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-PRE-11 — Prescription History

### User Story

> As a patient, I want to view my previous prescriptions so that I can review my medication history.

### Acceptance Criteria

Patient can view previous prescriptions.

Each prescription can display:

* Prescription date
* Doctor
* Medicines
* Dosage
* Frequency
* Duration
* Instructions
* Dispensing status

Patient cannot modify historical prescriptions.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-PRE-12 — Doctor Views Prescription History

### User Story

> As a doctor, I want to view prescription history for authorized patients so that I can understand their previous medication records.

### Acceptance Criteria

Doctor can view prescriptions associated with authorized patients.

Doctor can view:

* Prescription date
* Medicines
* Dosage
* Frequency
* Duration
* Instructions
* Dispensing status
* Prescribing doctor

Doctor cannot modify another doctor's prescription unless explicitly permitted by the system.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-PRE-13 — Prescription Validation

### User Story

> As a system, I want to validate prescriptions so that incomplete or invalid prescriptions cannot be finalized.

### Acceptance Criteria

* Prescription must reference a valid patient.
* Prescription must reference a valid doctor.
* Prescription must reference an eligible consultation.
* Prescription must contain at least one medicine before finalization.
* Medicine name cannot be empty.
* Dosage cannot be empty.
* Frequency cannot be empty.
* Duration cannot be empty.
* Invalid prescription requests are rejected.
* Unauthorized users cannot create or modify prescriptions.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-PRE-14 — Prescription Immutability After Dispensing

### User Story

> As a system, I want dispensed prescriptions to remain immutable so that the original prescription cannot be silently altered after the medicine has been provided.

### Acceptance Criteria

* Dispensed prescription contents cannot be edited.
* Medicine cannot be changed.
* Dosage cannot be changed.
* Frequency cannot be changed.
* Duration cannot be changed.
* Doctor instructions cannot be changed.
* Any correction after dispensing must follow a separate correction/versioning workflow.
* Original prescription information remains available for audit purposes.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-PRE-15 — Prescription Timeline Integration

### User Story

> As a patient, I want prescription events to appear in my medical timeline so that I can understand when medicines were prescribed and dispensed.

### Acceptance Criteria

The timeline can display:

* Prescription created
* Prescription sent to pharmacy
* Prescription marked ready
* Prescription dispensed

Each event contains:

* Date
* Time
* Prescription ID
* Relevant status

Prescription events are linked to the appropriate consultation and patient.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

# EPIC 08 — Laboratory Management

## US-LAB-01 — Laboratory Profile

### User Story

> As a laboratory technician, I want to view my laboratory's profile so that I can verify the laboratory information associated with my account.

### Acceptance Criteria

Laboratory technician can view:

* Laboratory name
* Address
* City
* State
* PIN code
* Contact number
* Email
* Available test types
* Laboratory status

Laboratory technician cannot modify administrator-managed laboratory information unless explicitly permitted.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-LAB-02 — View Test Requests

### User Story

> As a laboratory technician, I want to view incoming laboratory test requests so that I can process tests requested by doctors.

### Acceptance Criteria

Laboratory technician can view test requests assigned to their laboratory.

Each request displays:

* Test request ID
* Patient
* Requesting doctor
* Test type
* Request date
* Doctor's instructions
* Current status

Laboratory technician cannot view unrelated test requests belonging to other laboratories.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-LAB-03 — Accept Test Request

### User Story

> As a laboratory technician, I want to accept a test request so that the laboratory can begin processing it.

### Acceptance Criteria

* Technician can accept an eligible test request.
* Only the assigned laboratory can accept the request.
* Acceptance date and time are recorded.
* Test status changes from REQUESTED to ACCEPTED.
* Doctor and patient can see the updated status.

### Status Flow

```text
REQUESTED
    ↓
ACCEPTED
```

### Priority

P0 — Critical

### Story Points

2

### Status

Backlog

---

## US-LAB-04 — Record Sample Collection

### User Story

> As a laboratory technician, I want to record sample collection so that the system knows that the patient's sample has been collected.

### Acceptance Criteria

* Technician can mark a test as sample collected.
* Only an accepted test can move to sample collection.
* Sample collection date and time are recorded.
* Test status changes appropriately.
* Doctor and patient can view the updated status.

### Status Flow

```text
ACCEPTED
    ↓
SAMPLE_COLLECTED
```

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-LAB-05 — Process Laboratory Test

### User Story

> As a laboratory technician, I want to mark a test as processing so that the system can track its progress.

### Acceptance Criteria

* Technician can mark an eligible test as processing.
* Only a test with a collected sample can enter processing.
* Processing date and time are recorded.
* Test status changes appropriately.
* Patient and doctor can view the status.

### Status Flow

```text
SAMPLE_COLLECTED
        ↓
PROCESSING
```

### Priority

P0 — Critical

### Story Points

2

### Status

Backlog

---

## US-LAB-06 — Enter Laboratory Results

### User Story

> As a laboratory technician, I want to enter laboratory test results so that the patient's test information can be recorded.

### Acceptance Criteria

* Technician can enter results for an eligible test.
* Results must be associated with the correct test request.
* Results must be associated with the correct patient.
* Required result fields must be validated.
* Invalid result data must be rejected.
* Results cannot be entered for an unrelated test request.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-LAB-07 — Upload Laboratory Report

### User Story

> As a laboratory technician, I want to upload the completed laboratory report so that the doctor and patient can access the results.

### Acceptance Criteria

* Technician can upload a report for an eligible test.
* Report must be associated with the correct test request.
* Supported file types are validated.
* File size limits are enforced.
* Uploaded file is stored securely.
* Report upload date and time are recorded.
* Unauthorized users cannot access the file.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-LAB-08 — Complete Laboratory Test

### User Story

> As a laboratory technician, I want to mark a test as completed after submitting the results and report so that the doctor and patient know the test is finished.

### Acceptance Criteria

* Required test results must be available.
* Required report must be uploaded where applicable.
* Technician can mark the test as completed.
* Completion date and time are recorded.
* Test status changes to COMPLETED.
* Doctor can view the completed report.
* Patient can view the completed report.

### Status Flow

```text
REQUESTED
    ↓
ACCEPTED
    ↓
SAMPLE_COLLECTED
    ↓
PROCESSING
    ↓
COMPLETED
```

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-LAB-09 — View Laboratory Test History

### User Story

> As a laboratory technician, I want to view previous tests handled by my laboratory so that I can track completed and historical work.

### Acceptance Criteria

Technician can view:

* Test request ID
* Patient
* Doctor
* Test type
* Request date
* Completion date
* Test status

Technician can filter tests by:

* Date
* Test type
* Status

Technician can only access tests handled by their laboratory.

### Priority

P1 — High

### Story Points

3

### Status

Backlog

---

## US-LAB-10 — Doctor Views Laboratory Report

### User Story

> As a doctor, I want to view completed laboratory reports for my authorized patients so that I can use the results during treatment and follow-up.

### Acceptance Criteria

Doctor can view:

* Test name
* Patient
* Laboratory
* Requesting doctor
* Test date
* Results
* Report file
* Completion date

Doctor can download permitted reports.

Doctor cannot modify the original laboratory report.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-LAB-11 — Patient Views Laboratory Report

### User Story

> As a patient, I want to view my completed laboratory reports so that I can understand my test results.

### Acceptance Criteria

Patient can view their completed reports.

Patient can view:

* Test name
* Test date
* Laboratory
* Results
* Report file
* Completion date

Patient can download permitted reports.

Patient cannot modify the report.

Patient cannot access another patient's report.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-LAB-12 — Laboratory Test Status Tracking

### User Story

> As a patient or doctor, I want to track the status of a laboratory test so that I know whether the test is pending or completed.

### Test Statuses

```text
REQUESTED
ACCEPTED
SAMPLE_COLLECTED
PROCESSING
COMPLETED
CANCELLED
```

### Acceptance Criteria

* Authorized users can view the current test status.
* Status changes follow valid workflow rules.
* Invalid status transitions are rejected.
* Status changes are recorded.
* Patient can see the appropriate patient-facing status.
* Doctor can track tests requested for authorized patients.
* Laboratory technician can track tests assigned to their laboratory.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-LAB-13 — Cancel Laboratory Test

### User Story

> As an authorized user, I want an eligible laboratory test request to be cancelled when it is no longer required so that the laboratory does not process an unnecessary test.

### Acceptance Criteria

* Only eligible test requests can be cancelled.
* Cancellation reason is recorded where required.
* Completed tests cannot be cancelled.
* Cancelled tests cannot continue through normal processing.
* Patient, doctor, and laboratory see the updated status according to their permissions.

### Priority

P1 — High

### Story Points

3

### Status

Backlog

---

## US-LAB-14 — Link Laboratory Test With Consultation

### User Story

> As a doctor, I want a laboratory request to be linked to the relevant consultation so that the patient's diagnostic activity remains connected to their care.

### Acceptance Criteria

* Laboratory request references the relevant consultation.
* Laboratory request references the patient.
* Laboratory request references the requesting doctor.
* Completed report can be traced back to the test request.
* Test activity can be displayed in the patient's medical timeline.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog

---

## US-LAB-15 — Laboratory Report Immutability

### User Story

> As a system, I want submitted laboratory reports to remain protected from unauthorized modification so that medical results remain trustworthy.

### Acceptance Criteria

* Patient cannot modify a report.
* Doctor cannot modify the original laboratory report.
* Pharmacist cannot access or modify laboratory reports.
* Laboratory technician cannot silently replace a submitted report.
* Corrections, if required, must follow a controlled report correction/versioning process.
* Original report information remains available for audit purposes.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

---

## US-LAB-16 — Laboratory Timeline Integration

### User Story

> As a patient, I want laboratory activities to appear in my medical timeline so that I can understand the progress of my diagnostic care.

### Acceptance Criteria

The timeline can display:

* Test requested
* Test accepted
* Sample collected
* Test processing
* Test completed
* Report uploaded

Each event contains:

* Date
* Time
* Test
* Status

Laboratory events are associated with the correct patient and consultation.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

# EPIC 09 — Pharmacy Workflow

## US-PHA-01 — Pharmacy Profile

### User Story

> As a pharmacist, I want to view my pharmacy profile so that I can verify the pharmacy information associated with my account.

### Acceptance Criteria

Pharmacist can view:

- Pharmacy name
- Address
- City
- State
- PIN code
- Contact number
- Email
- Pharmacy status

Pharmacist cannot modify administrator-managed pharmacy information unless explicitly permitted.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PHA-02 — View Incoming Prescriptions

### User Story

> As a pharmacist, I want to view prescriptions sent to my pharmacy so that I can prepare the medicines prescribed by the doctor.

### Acceptance Criteria

Pharmacist can view prescriptions assigned to their pharmacy.

Each prescription displays:

- Prescription ID
- Patient information required for dispensing
- Doctor
- Prescription date
- Medicine name
- Dosage
- Frequency
- Duration
- Instructions
- Prescription status

Pharmacist cannot view prescriptions assigned to another pharmacy.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PHA-03 — View Prescription Details

### User Story

> As a pharmacist, I want to view the complete prescription details so that I can correctly prepare the medicines prescribed by the doctor.

### Acceptance Criteria

Pharmacist can view:

- Patient name
- Prescription ID
- Doctor name
- Prescription date
- Medicine name
- Dosage
- Frequency
- Duration
- Instructions

Pharmacist cannot modify:

- Medicine
- Dosage
- Frequency
- Duration
- Doctor's instructions
- Diagnosis
- Consultation information

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PHA-04 — Mark Prescription as Ready

### User Story

> As a pharmacist, I want to mark a prescription as ready so that the patient and doctor know that the prescribed medicines have been prepared.

### Acceptance Criteria

- Pharmacist can mark an eligible prescription as READY.
- Only the pharmacy assigned to the prescription can perform this action.
- Prescription contents cannot be modified.
- Ready date and time are recorded.
- Patient can see the updated status.
- Doctor can see the updated status.

### Status Flow

SENT_TO_PHARMACY
↓
READY

### Priority

P0 — Critical

### Story Points

2

### Status

Backlog


---

## US-PHA-05 — Mark Prescription as Dispensed

### User Story

> As a pharmacist, I want to mark a prescription as dispensed so that the system records that the prescribed medicines have been provided to the patient.

### Acceptance Criteria

- Pharmacist can mark an eligible prescription as DISPENSED.
- Only the assigned pharmacy can perform this action.
- Dispensing date and time are recorded.
- Patient can see the updated status.
- Doctor can see the updated status.
- A dispensed prescription cannot be silently changed back to READY.
- Pharmacist cannot modify the original prescription.

### Status Flow

READY
↓
DISPENSED

### Priority

P0 — Critical

### Story Points

2

### Status

Backlog


---

## US-PHA-06 — View Prescription Status

### User Story

> As a pharmacist, I want to view the status of prescriptions so that I know which prescriptions require action.

### Acceptance Criteria

Pharmacist can filter prescriptions by:

- SENT_TO_PHARMACY
- READY
- DISPENSED

Pharmacist can view the most recent prescriptions first.

Only prescriptions assigned to the pharmacist's pharmacy are displayed.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PHA-07 — Prescription Processing Dashboard

### User Story

> As a pharmacist, I want a dashboard showing pending prescriptions so that I can quickly identify prescriptions that require processing.

### Dashboard can display

- New prescriptions
- Prescriptions awaiting preparation
- Ready prescriptions
- Dispensed prescriptions
- Today's processed prescriptions

### Acceptance Criteria

- Dashboard displays data belonging to the authenticated pharmacist's pharmacy.
- New prescriptions are clearly identifiable.
- Pharmacist can open a prescription from the dashboard.
- Pharmacist cannot access another pharmacy's prescription data.

### Priority

P1 — High

### Story Points

5

### Status

Backlog


---

## US-PHA-08 — Patient Views Pharmacy Status

### User Story

> As a patient, I want to see the pharmacy processing status of my prescription so that I know whether my medicines are ready or have been dispensed.

### Acceptance Criteria

Patient can view:

- Prescription status
- Pharmacy name
- Prescription date
- Ready date where applicable
- Dispensed date where applicable

Patient can see statuses such as:

- SENT_TO_PHARMACY
- READY
- DISPENSED

Patient cannot modify the status.

### Priority

P0 — Critical

### Story Points

3

### Status

Backlog


---

## US-PHA-09 — Doctor Views Pharmacy Status

### User Story

> As a doctor, I want to view the pharmacy processing status of prescriptions I issued so that I know whether the prescribed medicines have been dispensed.

### Acceptance Criteria

Doctor can view:

- Prescription ID
- Patient
- Pharmacy
- Prescription date
- Current status
- Ready date where applicable
- Dispensed date where applicable

Doctor cannot modify the pharmacy status directly.

### Priority

P1 — High

### Story Points

3

### Status

Backlog


---

## US-PHA-10 — Pharmacy Prescription Validation

### User Story

> As a system, I want to validate prescriptions before pharmacy processing so that only valid prescriptions can be processed.

### Acceptance Criteria

- Prescription must belong to a valid patient.
- Prescription must belong to a valid doctor.
- Prescription must contain at least one medicine.
- Prescription must be finalized.
- Prescription must be assigned to the pharmacy.
- Invalid or incomplete prescriptions cannot be processed.
- Pharmacist must be authorized to process the prescription.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-PHA-11 — Prescription Immutability

### User Story

> As a system, I want prescriptions to remain unchanged during pharmacy processing so that the pharmacist cannot accidentally alter the doctor's instructions.

### Acceptance Criteria

Pharmacist cannot modify:

- Medicine name
- Dosage
- Frequency
- Duration
- Instructions
- Doctor
- Diagnosis
- Consultation

Pharmacist can only update the pharmacy processing status.

Any correction to a prescription must be handled by the appropriate doctor through a controlled workflow.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-PHA-12 — Pharmacy Processing History

### User Story

> As a pharmacist, I want to view previously processed prescriptions so that I can track the pharmacy's completed work.

### Acceptance Criteria

Pharmacist can view:

- Prescription ID
- Patient
- Doctor
- Prescription date
- Processing status
- Ready date
- Dispensed date

Pharmacist can filter history by:

- Date
- Status

Only prescriptions processed by the pharmacist's pharmacy are displayed.

### Priority

P1 — High

### Story Points

3

### Status

Backlog


---

## US-PHA-13 — Pharmacy Timeline Integration

### User Story

> As a patient, I want prescription dispensing events to appear in my medical timeline so that I can see when my prescribed medicines were prepared and dispensed.

### Acceptance Criteria

The patient's timeline can display:

- Prescription sent to pharmacy
- Prescription marked ready
- Prescription dispensed

Each event contains:

- Date
- Time
- Prescription ID
- Pharmacy
- Status

The timeline event must belong to the correct patient.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog


---

## US-PHA-14 — Pharmacy Access Control

### User Story

> As a system, I want pharmacy users to access only authorized prescription information so that patient and medical information remains protected.

### Acceptance Criteria

- Pharmacist must be authenticated.
- Pharmacist can access only their assigned pharmacy.
- Pharmacist can access only prescriptions assigned to their pharmacy.
- Pharmacist cannot access unrelated patient medical records.
- Pharmacist cannot access consultation details beyond what is required for prescription processing.
- Pharmacist cannot access laboratory reports unless explicitly authorized.
- Pharmacist cannot modify doctor-created medical records.
- Unauthorized requests are rejected.

### Priority

P0 — Critical

### Story Points

5

### Status

Backlog

                 PATIENT
                    │
                    ▼
        ┌───────────────────────┐
        │    MEDICAL TIMELINE   │
        └───────────────────────┘
                    │
       ┌────────────┼────────────┐
       ▼            ▼            ▼
  Appointment   Consultation   Lab Test
                                  │
                                  ▼
                             Lab Report
       │            │
       │            ▼
       │       Prescription
       │            │
       │            ▼
       │        Pharmacy
       │            │
       │            ▼
       │        Dispensed
       │
       └──────────────→ Follow-up

20 Aug 2026
10:00 AM
🩺 Consultation
Dr. Arun Kumar
City Hospital

        ↓

20 Aug 2026
10:25 AM
💊 Prescription
2 medicines prescribed

        ↓

20 Aug 2026
10:30 AM
🧪 Laboratory Test
Blood Test requested

        ↓

21 Aug 2026
04:15 PM
📄 Lab Report
Report completed

        ↓

21 Aug 2026
05:00 PM
💊 Prescription
Medicine dispensed

        ↓

28 Aug 2026
10:00 AM
📅 Follow-up
Scheduled with Dr. Arun