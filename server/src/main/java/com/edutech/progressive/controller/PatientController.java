package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Patient;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PatientController {

    public ResponseEntity<List<Patient>> getAllPatients() {
        return null;
    }

    public ResponseEntity<Patient> getPatientById(int patientId) {
        return null;
    }

    public ResponseEntity<Integer> addPatient(Patient patient) {
        return null;
    }

    public ResponseEntity<Void> updatePatient(int patientId, Patient patient) {
        return null;
    }

    public ResponseEntity<Void> deletePatient(int patientId) {
        return null;
    }

    public ResponseEntity<List<Patient>> getAllPatientFromArrayList() {
        return null;
    }

    public ResponseEntity<Void> addPatientToArrayList() {
        return null;
    }

    public ResponseEntity<List<Patient>> getAllPatientSortedByNameFromArrayList() {
        return null;
    }
}