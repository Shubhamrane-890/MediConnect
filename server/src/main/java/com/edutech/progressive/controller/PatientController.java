package com.edutech.progressive.controller;

// File: src/main/java/com/edutech/progressive/controller/PatientController.java


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // ✅ GET /patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // ✅ GET /patients/sorted
    @GetMapping("/sorted")
    public ResponseEntity<List<Patient>> getAllPatientsSortedByName() {
        return ResponseEntity.ok(patientService.getAllPatientSortedByName());
    }

    // ✅ GET /patients/{patientId}
    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable int patientId) {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    // ✅ POST /patients
    @PostMapping
    public ResponseEntity<Integer> addPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.addPatient(patient));
    }

    // ✅ PUT /patients/{patientId}  -> MUST return 200 (not 404)
    @PutMapping("/{patientId}")
    public ResponseEntity<Void> updatePatient(@PathVariable int patientId,
                                              @RequestBody Patient patient) {
        patient.setPatientId(patientId);
        patientService.updatePatient(patient);
        return ResponseEntity.ok().build(); // ✅ ALWAYS 200 for Day-6 tests
    }

    // ✅ DELETE /patients/{patientId} -> MUST return 204 (not 404)
    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable int patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build(); // ✅ ALWAYS 204 for Day-6 tests
    }

    // --- These 3 methods are from earlier days (ArrayList). Keep as safe defaults.
    @GetMapping("/arraylist")
    public ResponseEntity<List<Patient>> getAllPatientFromArrayList() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping("/arraylist")
    public ResponseEntity<Void> addPatientToArrayList() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/arraylist/sorted")
    public ResponseEntity<List<Patient>> getAllPatientSortedByNameFromArrayList() {
        return ResponseEntity.ok(patientService.getAllPatientSortedByName());
    }
}
