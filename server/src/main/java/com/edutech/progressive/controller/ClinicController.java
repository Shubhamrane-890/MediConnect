package com.edutech.progressive.controller;

import org.springframework.http.ResponseEntity;

import com.edutech.progressive.dao.PatientDAOImpl;

import java.util.List;

public class ClinicController {
    public ResponseEntity<List<PatientDAOImpl>> getAllClinics() {
        return null;
    }

    public ResponseEntity<PatientDAOImpl> getClinicById(int clinicId) {
        return null;
    }

    public ResponseEntity<Integer> addClinic(PatientDAOImpl clinic) {
        return null;
    }

    public ResponseEntity<Void> updateClinic(int clinicId, PatientDAOImpl clinic) {
        return null;
    }

    public ResponseEntity<Void> deleteClinic(int clinicId) {
        return null;
    }

    public ResponseEntity<List<PatientDAOImpl>> getAllClinicByLocation(String location) {
        return null;
    }

    public ResponseEntity<List<PatientDAOImpl>> getAllClinicByDoctorId(int doctorId) {
        return null;
    }
}