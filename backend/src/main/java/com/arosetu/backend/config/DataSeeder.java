package com.arosetu.backend.config;

import com.arosetu.backend.entity.*;
import com.arosetu.backend.enums.AppointmentStatus;
import com.arosetu.backend.enums.ApprovalStatus;
import com.arosetu.backend.enums.HospitalType;
import com.arosetu.backend.enums.PrescriptionStatus;
import com.arosetu.backend.enums.Role;
import com.arosetu.backend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Seeds the in-memory H2 database with realistic demo data on every startup
 * so that all dropdowns, search results, and dashboards are pre-populated.
 *
 * Demo credentials:
 *   ADMIN      — admin@arosetu.com / admin123
 *   DOCTOR     — doctor@arosetu.com / doctor123
 *   PATIENT    — patient@arosetu.com / patient123
 *   LAB TECH   — lab@arosetu.com / lab12345
 *   PHARMACIST — pharma@arosetu.com / pharma123
 */
@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    CommandLineRunner seedDatabase(
            UserRepository userRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            HospitalRepository hospitalRepository,
            LaboratoryRepository laboratoryRepository,
            PharmacyRepository pharmacyRepository,
            DoctorHospitalRepository doctorHospitalRepository,
            AvailabilityRepository availabilityRepository,
            AppointmentRepository appointmentRepository,
            ConsultationRepository consultationRepository,
            PrescriptionRepository prescriptionRepository,
            PrescriptionItemRepository prescriptionItemRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (userRepository.count() > 0) {
                log.info("Database already seeded — skipping.");
                return;
            }

            log.info("====== Seeding demo data ======");

            // ─── ADMIN ────────────────────────────────────
            User adminUser = userRepository.save(User.builder()
                    .name("Admin")
                    .email("admin@arosetu.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .active(true)
                    .build());

            // ─── PATIENTS ─────────────────────────────────
            User patientUser1 = userRepository.save(User.builder()
                    .name("Rahul Sharma")
                    .email("patient@arosetu.com")
                    .password(passwordEncoder.encode("patient123"))
                    .phone("+91 9876543210")
                    .role(Role.PATIENT)
                    .active(true)
                    .build());
            Patient patient1 = patientRepository.save(Patient.builder()
                    .user(patientUser1)
                    .dateOfBirth(LocalDate.of(1995, 6, 15))
                    .gender("Male")
                    .bloodGroup("O+")
                    .address("42 MG Road, Bangalore")
                    .emergencyContact("+91 9876500000")
                    .build());

            User patientUser2 = userRepository.save(User.builder()
                    .name("Priya Nair")
                    .email("priya@arosetu.com")
                    .password(passwordEncoder.encode("patient123"))
                    .phone("+91 9876543211")
                    .role(Role.PATIENT)
                    .active(true)
                    .build());
            Patient patient2 = patientRepository.save(Patient.builder()
                    .user(patientUser2)
                    .dateOfBirth(LocalDate.of(1990, 3, 22))
                    .gender("Female")
                    .bloodGroup("A+")
                    .address("15 Jayanagar, Bangalore")
                    .emergencyContact("+91 9876500001")
                    .build());

            // ─── DOCTORS ──────────────────────────────────
            User doctorUser1 = userRepository.save(User.builder()
                    .name("Dr. Arun Kumar")
                    .email("doctor@arosetu.com")
                    .password(passwordEncoder.encode("doctor123"))
                    .phone("+91 9900112233")
                    .role(Role.DOCTOR)
                    .active(true)
                    .build());
            Doctor doctor1 = doctorRepository.save(Doctor.builder()
                    .user(doctorUser1)
                    .specialization("Cardiology")
                    .qualification("MBBS, MD (Cardiology)")
                    .experienceYears(12)
                    .licenseNumber("KA-MCI-28001")
                    .consultationFee(new BigDecimal("800"))
                    .approvalStatus(ApprovalStatus.APPROVED)
                    .about("Senior Cardiologist with expertise in interventional cardiology and heart failure management.")
                    .build());

            User doctorUser2 = userRepository.save(User.builder()
                    .name("Dr. Meena Reddy")
                    .email("meena@arosetu.com")
                    .password(passwordEncoder.encode("doctor123"))
                    .phone("+91 9900445566")
                    .role(Role.DOCTOR)
                    .active(true)
                    .build());
            Doctor doctor2 = doctorRepository.save(Doctor.builder()
                    .user(doctorUser2)
                    .specialization("Neurology")
                    .qualification("MBBS, DM (Neurology)")
                    .experienceYears(8)
                    .licenseNumber("KA-MCI-28002")
                    .consultationFee(new BigDecimal("1000"))
                    .approvalStatus(ApprovalStatus.APPROVED)
                    .about("Specialist in neuro-degenerative diseases, epilepsy, and stroke management.")
                    .build());

            User doctorUser3 = userRepository.save(User.builder()
                    .name("Dr. Suresh Patel")
                    .email("suresh@arosetu.com")
                    .password(passwordEncoder.encode("doctor123"))
                    .role(Role.DOCTOR)
                    .active(true)
                    .build());
            Doctor doctor3 = doctorRepository.save(Doctor.builder()
                    .user(doctorUser3)
                    .specialization("Orthopedics")
                    .qualification("MBBS, MS (Ortho)")
                    .experienceYears(15)
                    .licenseNumber("KA-MCI-28003")
                    .consultationFee(new BigDecimal("700"))
                    .approvalStatus(ApprovalStatus.APPROVED)
                    .about("Expert in joint replacement surgery and sports medicine.")
                    .build());

            // ─── HOSPITALS ────────────────────────────────
            Hospital hospital1 = hospitalRepository.save(Hospital.builder()
                    .name("AroSetu General Hospital")
                    .type(HospitalType.HOSPITAL)
                    .address("123 MG Road, Indiranagar")
                    .city("Bangalore")
                    .state("Karnataka")
                    .pinCode("560038")
                    .contactNumber("+91 80 4567 8901")
                    .email("info@arosetu-hospital.com")
                    .active(true)
                    .build());

            Hospital hospital2 = hospitalRepository.save(Hospital.builder()
                    .name("LifeCare Clinic")
                    .type(HospitalType.CLINIC)
                    .address("45 Brigade Road, Ashok Nagar")
                    .city("Bangalore")
                    .state("Karnataka")
                    .pinCode("560025")
                    .contactNumber("+91 80 2345 6789")
                    .email("info@lifecare-clinic.com")
                    .active(true)
                    .build());

            Hospital hospital3 = hospitalRepository.save(Hospital.builder()
                    .name("MedScan Diagnostics")
                    .type(HospitalType.DIAGNOSTIC_CENTER)
                    .address("78 Koramangala 4th Block")
                    .city("Bangalore")
                    .state("Karnataka")
                    .pinCode("560034")
                    .contactNumber("+91 80 6789 0123")
                    .email("info@medscan.com")
                    .active(true)
                    .build());

            // ─── DOCTOR–HOSPITAL ASSOCIATIONS ─────────────
            doctorHospitalRepository.save(DoctorHospital.builder()
                    .doctor(doctor1).hospital(hospital1).build());
            doctorHospitalRepository.save(DoctorHospital.builder()
                    .doctor(doctor1).hospital(hospital2).build());
            doctorHospitalRepository.save(DoctorHospital.builder()
                    .doctor(doctor2).hospital(hospital1).build());
            doctorHospitalRepository.save(DoctorHospital.builder()
                    .doctor(doctor3).hospital(hospital1).build());
            doctorHospitalRepository.save(DoctorHospital.builder()
                    .doctor(doctor3).hospital(hospital3).build());

            // ─── AVAILABILITY SLOTS ───────────────────────
            // Dr. Arun Kumar — Mon-Fri at Hospital 1, Sat at Hospital 2
            for (DayOfWeek day : new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY, DayOfWeek.FRIDAY}) {
                availabilityRepository.save(Availability.builder()
                        .doctor(doctor1).hospital(hospital1).dayOfWeek(day)
                        .startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(13, 0))
                        .slotDurationMinutes(30).build());
            }
            availabilityRepository.save(Availability.builder()
                    .doctor(doctor1).hospital(hospital2).dayOfWeek(DayOfWeek.SATURDAY)
                    .startTime(LocalTime.of(10, 0)).endTime(LocalTime.of(14, 0))
                    .slotDurationMinutes(30).build());

            // Dr. Meena Reddy — Mon/Wed/Fri at Hospital 1
            for (DayOfWeek day : new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY}) {
                availabilityRepository.save(Availability.builder()
                        .doctor(doctor2).hospital(hospital1).dayOfWeek(day)
                        .startTime(LocalTime.of(14, 0)).endTime(LocalTime.of(18, 0))
                        .slotDurationMinutes(30).build());
            }

            // Dr. Suresh Patel — Tue/Thu at Hospital 1, Sat at Hospital 3
            for (DayOfWeek day : new DayOfWeek[]{DayOfWeek.TUESDAY, DayOfWeek.THURSDAY}) {
                availabilityRepository.save(Availability.builder()
                        .doctor(doctor3).hospital(hospital1).dayOfWeek(day)
                        .startTime(LocalTime.of(10, 0)).endTime(LocalTime.of(16, 0))
                        .slotDurationMinutes(45).build());
            }
            availabilityRepository.save(Availability.builder()
                    .doctor(doctor3).hospital(hospital3).dayOfWeek(DayOfWeek.SATURDAY)
                    .startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(12, 0))
                    .slotDurationMinutes(30).build());

            // ─── LABORATORIES ─────────────────────────────
            laboratoryRepository.save(Laboratory.builder()
                    .name("AroSetu Central Lab")
                    .address("Building A, AroSetu General Hospital, Indiranagar")
                    .city("Bangalore").state("Karnataka")
                    .contactNumber("+91 80 4567 8910")
                    .active(true).build());

            laboratoryRepository.save(Laboratory.builder()
                    .name("PathCare Diagnostics")
                    .address("22 Jayanagar 4th Block")
                    .city("Bangalore").state("Karnataka")
                    .contactNumber("+91 80 2222 3333")
                    .active(true).build());

            // ─── PHARMACIES ───────────────────────────────
            Pharmacy pharmacy1 = pharmacyRepository.save(Pharmacy.builder()
                    .name("AroSetu Pharmacy")
                    .address("Ground Floor, AroSetu General Hospital, Indiranagar")
                    .city("Bangalore").state("Karnataka")
                    .contactNumber("+91 80 4567 8920")
                    .active(true).build());

            Pharmacy pharmacy2 = pharmacyRepository.save(Pharmacy.builder()
                    .name("MedPlus Pharmacy")
                    .address("56 HSR Layout, Sector 2")
                    .city("Bangalore").state("Karnataka")
                    .contactNumber("+91 80 5555 6666")
                    .active(true).build());

            // ─── LAB TECHNICIAN ───────────────────────────
            userRepository.save(User.builder()
                    .name("Ravi Lab Tech")
                    .email("lab@arosetu.com")
                    .password(passwordEncoder.encode("lab12345"))
                    .role(Role.LAB_TECHNICIAN)
                    .active(true)
                    .build());

            // ─── PHARMACIST ───────────────────────────────
            userRepository.save(User.builder()
                    .name("Kavitha Pharmacist")
                    .email("pharma@arosetu.com")
                    .password(passwordEncoder.encode("pharma123"))
                    .role(Role.PHARMACIST)
                    .active(true)
                    .build());

            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // APPOINTMENTS — provides data for all appointment dropdowns
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            LocalDate pastDate1 = LocalDate.now().minusDays(10);
            LocalDate pastDate2 = LocalDate.now().minusDays(7);
            LocalDate pastDate3 = LocalDate.now().minusDays(5);
            LocalDate futureDate1 = LocalDate.now().plusDays(3);
            LocalDate futureDate2 = LocalDate.now().plusDays(5);

            // Appointment 1 — COMPLETED (Rahul with Dr. Arun)
            Appointment appt1 = appointmentRepository.save(Appointment.builder()
                    .patient(patient1).doctor(doctor1).hospital(hospital1)
                    .appointmentDate(pastDate1)
                    .startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(9, 30))
                    .status(AppointmentStatus.COMPLETED)
                    .reason("Chest pain and shortness of breath")
                    .build());

            // Appointment 2 — COMPLETED (Rahul with Dr. Meena)
            Appointment appt2 = appointmentRepository.save(Appointment.builder()
                    .patient(patient1).doctor(doctor2).hospital(hospital1)
                    .appointmentDate(pastDate2)
                    .startTime(LocalTime.of(14, 0)).endTime(LocalTime.of(14, 30))
                    .status(AppointmentStatus.COMPLETED)
                    .reason("Persistent headaches and dizziness")
                    .build());

            // Appointment 3 — COMPLETED (Priya with Dr. Arun)
            Appointment appt3 = appointmentRepository.save(Appointment.builder()
                    .patient(patient2).doctor(doctor1).hospital(hospital1)
                    .appointmentDate(pastDate2)
                    .startTime(LocalTime.of(10, 0)).endTime(LocalTime.of(10, 30))
                    .status(AppointmentStatus.COMPLETED)
                    .reason("Routine cardiac check-up")
                    .build());

            // Appointment 4 — CONFIRMED (Rahul upcoming with Dr. Suresh)
            appointmentRepository.save(Appointment.builder()
                    .patient(patient1).doctor(doctor3).hospital(hospital1)
                    .appointmentDate(futureDate1)
                    .startTime(LocalTime.of(10, 0)).endTime(LocalTime.of(10, 45))
                    .status(AppointmentStatus.CONFIRMED)
                    .reason("Knee pain follow-up")
                    .build());

            // Appointment 5 — REQUESTED (Priya upcoming with Dr. Meena)
            appointmentRepository.save(Appointment.builder()
                    .patient(patient2).doctor(doctor2).hospital(hospital1)
                    .appointmentDate(futureDate2)
                    .startTime(LocalTime.of(15, 0)).endTime(LocalTime.of(15, 30))
                    .status(AppointmentStatus.REQUESTED)
                    .reason("Migraine evaluation")
                    .build());

            // Appointment 6 — COMPLETED (Priya with Dr. Suresh)
            Appointment appt6 = appointmentRepository.save(Appointment.builder()
                    .patient(patient2).doctor(doctor3).hospital(hospital3)
                    .appointmentDate(pastDate3)
                    .startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(9, 30))
                    .status(AppointmentStatus.COMPLETED)
                    .reason("Lower back pain")
                    .build());

            log.info("  → 6 appointments seeded (4 COMPLETED, 1 CONFIRMED, 1 REQUESTED)");

            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // CONSULTATIONS — provides data for consultation dropdowns
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            Consultation consult1 = consultationRepository.save(Consultation.builder()
                    .appointment(appt1).patient(patient1).doctor(doctor1)
                    .symptoms("Chest tightness, mild dyspnea on exertion")
                    .observations("BP 140/90, Heart rate 88 bpm, mild murmur detected")
                    .diagnosis("Mild hypertension with early-stage valve insufficiency")
                    .clinicalNotes("ECG shows normal sinus rhythm. Echo recommended.")
                    .treatmentPlan("Start ACE inhibitor, low-sodium diet, follow-up in 2 weeks")
                    .consultationDate(pastDate1.atTime(9, 15))
                    .build());

            Consultation consult2 = consultationRepository.save(Consultation.builder()
                    .appointment(appt2).patient(patient1).doctor(doctor2)
                    .symptoms("Recurrent throbbing headaches, episodes of vertigo")
                    .observations("Cranial nerves intact, no focal deficits")
                    .diagnosis("Tension-type headache with vestibular migraine")
                    .clinicalNotes("MRI brain ordered to rule out structural abnormalities")
                    .treatmentPlan("Topiramate 25mg daily, lifestyle modifications, MRI follow-up")
                    .consultationDate(pastDate2.atTime(14, 15))
                    .build());

            Consultation consult3 = consultationRepository.save(Consultation.builder()
                    .appointment(appt3).patient(patient2).doctor(doctor1)
                    .symptoms("No acute symptoms, routine screening")
                    .observations("BP 120/80, Heart rate 72 bpm, no murmurs")
                    .diagnosis("Normal cardiac function — no pathology detected")
                    .clinicalNotes("All vitals within normal limits. Lipid profile WNL.")
                    .treatmentPlan("Continue healthy lifestyle, annual cardiac screening recommended")
                    .consultationDate(pastDate2.atTime(10, 15))
                    .build());

            Consultation consult4 = consultationRepository.save(Consultation.builder()
                    .appointment(appt6).patient(patient2).doctor(doctor3)
                    .symptoms("Chronic lower back pain radiating to left leg")
                    .observations("Lumbar tenderness L4-L5, reduced flexion ROM")
                    .diagnosis("Lumbar disc herniation (L4-L5)")
                    .clinicalNotes("X-ray shows disc space narrowing. MRI advised.")
                    .treatmentPlan("Physiotherapy 3x/week, NSAIDs for pain, MRI lumbar spine")
                    .consultationDate(pastDate3.atTime(9, 15))
                    .build());

            log.info("  → 4 consultations seeded");

            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // PRESCRIPTIONS — provides data for prescription dropdowns
            // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
            // Prescription 1 — PRESCRIBED (ready to send to pharmacy)
            Prescription rx1 = prescriptionRepository.save(Prescription.builder()
                    .consultation(consult1).doctor(doctor1).patient(patient1)
                    .status(PrescriptionStatus.PRESCRIBED)
                    .notes("Take medications with food. Monitor blood pressure daily.")
                    .build());
            prescriptionItemRepository.save(PrescriptionItem.builder()
                    .prescription(rx1).medicineName("Enalapril 5mg")
                    .dosage("5mg").frequency("Once daily").duration("30 days")
                    .instructions("Take in the morning with water").build());
            prescriptionItemRepository.save(PrescriptionItem.builder()
                    .prescription(rx1).medicineName("Amlodipine 5mg")
                    .dosage("5mg").frequency("Once daily").duration("30 days")
                    .instructions("Take at bedtime").build());

            // Prescription 2 — SENT_TO_PHARMACY
            Prescription rx2 = prescriptionRepository.save(Prescription.builder()
                    .consultation(consult2).doctor(doctor2).patient(patient1)
                    .pharmacy(pharmacy1)
                    .status(PrescriptionStatus.SENT_TO_PHARMACY)
                    .notes("Avoid bright lights and loud noises during episodes.")
                    .build());
            prescriptionItemRepository.save(PrescriptionItem.builder()
                    .prescription(rx2).medicineName("Topiramate 25mg")
                    .dosage("25mg").frequency("Once daily").duration("60 days")
                    .instructions("Take at night before sleep").build());
            prescriptionItemRepository.save(PrescriptionItem.builder()
                    .prescription(rx2).medicineName("Paracetamol 500mg")
                    .dosage("500mg").frequency("As needed (max 3/day)").duration("30 days")
                    .instructions("Take for acute headache episodes").build());

            // Prescription 3 — DRAFT
            Prescription rx3 = prescriptionRepository.save(Prescription.builder()
                    .consultation(consult4).doctor(doctor3).patient(patient2)
                    .status(PrescriptionStatus.DRAFT)
                    .notes("Start physiotherapy immediately. Avoid heavy lifting.")
                    .build());
            prescriptionItemRepository.save(PrescriptionItem.builder()
                    .prescription(rx3).medicineName("Ibuprofen 400mg")
                    .dosage("400mg").frequency("Twice daily").duration("14 days")
                    .instructions("Take after meals").build());
            prescriptionItemRepository.save(PrescriptionItem.builder()
                    .prescription(rx3).medicineName("Thiocolchicoside 4mg")
                    .dosage("4mg").frequency("Twice daily").duration("7 days")
                    .instructions("Muscle relaxant — take after meals").build());

            log.info("  → 3 prescriptions with 6 medication items seeded");

            log.info("====== Demo data seeded successfully! ======");
            log.info("Demo credentials:");
            log.info("  ADMIN      : admin@arosetu.com / admin123");
            log.info("  DOCTOR     : doctor@arosetu.com / doctor123");
            log.info("  PATIENT    : patient@arosetu.com / patient123");
            log.info("  LAB TECH   : lab@arosetu.com / lab12345");
            log.info("  PHARMACIST : pharma@arosetu.com / pharma123");
        };
    }
}
