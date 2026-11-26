package com.arosetu.backend.service;

import com.arosetu.backend.dto.AuthResponse;
import com.arosetu.backend.dto.LoginRequest;
import com.arosetu.backend.dto.UserRegistrationRequest;
import com.arosetu.backend.entity.Doctor;
import com.arosetu.backend.entity.Patient;
import com.arosetu.backend.entity.User;
import com.arosetu.backend.enums.ApprovalStatus;
import com.arosetu.backend.enums.Role;
import com.arosetu.backend.exception.DuplicateResourceException;
import com.arosetu.backend.repository.DoctorRepository;
import com.arosetu.backend.repository.PatientRepository;
import com.arosetu.backend.repository.UserRepository;
import com.arosetu.backend.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AuthService(AuthenticationManager authenticationManager, UserService userService,
                       JwtService jwtService, PasswordEncoder passwordEncoder,
                       UserRepository userRepository, DoctorRepository doctorRepository,
                       PatientRepository patientRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public AuthResponse register(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered");
        }

        // Normalize empty/blank phone to null to avoid unique constraint issues
        String phone = request.getPhone();
        if (phone != null && phone.isBlank()) {
            phone = null;
        }

        if (phone != null && userRepository.existsByPhone(phone)) {
            throw new DuplicateResourceException("Phone number already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(phone);
        user.setRole(request.getRole());
        user.setActive(true);

        User savedUser = userRepository.save(user);

        if (request.getRole() == Role.PATIENT) {
            Patient patient = new Patient();
            patient.setUser(savedUser);
            patientRepository.save(patient);
        } else if (request.getRole() == Role.DOCTOR) {
            Doctor doctor = new Doctor();
            doctor.setUser(savedUser);
            doctor.setApprovalStatus(ApprovalStatus.APPROVED);
            doctorRepository.save(doctor);
        }

        String token = jwtService.generateToken(savedUser);
        return new AuthResponse(token, savedUser.getId(), savedUser.getName(),
                savedUser.getEmail(), savedUser.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userService.getUserEntityByEmail(request.getEmail());
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getId(), user.getName(),
                user.getEmail(), user.getRole());
    }
}
