package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/patient")
public class PatientController {

    PatientService ps;
    public PatientController(PatientService ps){
        this.ps = ps;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        try {
            return new ResponseEntity<>(ps.getAllPatients(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{patientID}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("patientID") int patientId) {
        try {
            return new ResponseEntity<>(ps.getPatientById(patientId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addPatient(@RequestBody Patient patient) {
        try {
            return new ResponseEntity<>(ps.addPatient(patient), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{patientID}")
    public ResponseEntity<Void> updatePatient(@PathVariable("patientID") int patientId, @RequestBody Patient patient) {
        try {
            if(ps.getPatientById(patientId)!=null)
            ps.updatePatient(patient);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{patientID}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patientID") int patientId) {
        try {
            ps.deletePatient(patientId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Patient>> getAllPatientFromArrayList() {
        try {
            return new ResponseEntity<>(ps.getAllPatients(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Void> addPatientToArrayList() {
        try {
            ps.addPatient(null);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Patient>> getAllPatientSortedByNameFromArrayList() {
        try {
            return new ResponseEntity<>(ps.getAllPatientSortedByName(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}