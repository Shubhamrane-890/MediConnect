package com.edutech.progressive.service.impl;

import com.edutech.progressive.dto.UserRegistrationDTO;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.entity.User;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserLoginServiceImpl(UserRepository userRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(UserRegistrationDTO dto) throws Exception {

        String role = dto.getRole();
        String email = dto.getEmail();
        String username = dto.getUsername();

        if (role == null || (!role.equalsIgnoreCase("PATIENT") && !role.equalsIgnoreCase("DOCTOR"))) {
            throw new Exception("Invalid role. Only 'PATIENT' or 'DOCTOR' allowed.");
        }

        if (username == null || username.trim().isEmpty()) {
            throw new Exception("Username cannot be empty.");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new Exception("Username '" + username + "' already exists.");
        }

        // ✅ FIX: Proper email check (Optional-style)
        boolean emailExists = patientRepository.findByEmail(email).isPresent()
                || doctorRepository.findByEmail(email).isPresent();

        if (emailExists) {
            throw new Exception("Email '" + email + "' already exists.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role.toUpperCase());

        if (role.equalsIgnoreCase("PATIENT")) {
            Patient patient = new Patient();
            patient.setFullName(dto.getFullName());
            patient.setContactNumber(dto.getContactNumber());
            patient.setEmail(email);
            patient.setAddress(dto.getAddress());
            patient.setDateOfBirth(dto.getDateOfBirth());

            Patient savedPatient = patientRepository.save(patient);
            user.setPatient(savedPatient);

        } else {
            Doctor doctor = new Doctor();
            doctor.setFullName(dto.getFullName());
            doctor.setSpecialty(dto.getSpecialty());
            doctor.setYearsOfExperience(dto.getYearsOfExperience());
            doctor.setEmail(email);
            doctor.setContactNumber(dto.getContactNumber());

            Doctor savedDoctor = doctorRepository.save(doctor);
            user.setDoctor(savedDoctor);
        }

        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserDetails(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // ✅ IMPORTANT: authority must match SecurityConfig ("PATIENT"/"DOCTOR")
        List<SimpleGrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), // ✅ return real username
                user.getPassword(),
                authorities);
    }
}
