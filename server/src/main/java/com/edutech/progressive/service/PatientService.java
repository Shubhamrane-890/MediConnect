package com.edutech.progressive.service;

// File: src/main/java/com/edutech/progressive/service/PatientService.java


import java.util.List;

import com.edutech.progressive.entity.Patient;

public interface PatientService {

    List<Patient> getAllPatients();

    Integer addPatient(Patient patient);

    List<Patient> getAllPatientSortedByName();

    Patient getPatientById(int patientId);

    void updatePatient(Patient patient);

    void deletePatient(int patientId);
}
