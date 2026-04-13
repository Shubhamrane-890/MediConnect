package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Doctor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping
public class DoctorController {

    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return null;
    }

    public ResponseEntity<Doctor> getDoctorById(int doctorId) {
        return null;
    }

    public ResponseEntity<Integer> addDoctor(Doctor doctor) {
        return null;
    }

    public ResponseEntity<Void> updateDoctor(int doctorId, Doctor doctor) {
        return null;
    }

    public ResponseEntity<Void> deleteDoctor(int doctorId) {
        return null;
    }

    public ResponseEntity<List<Doctor>> getDoctorSortedByExperience() {
        return null;
    }
}