package com.edutech.progressive.service;

import com.edutech.progressive.dto.PatientDTO;
import com.edutech.progressive.entity.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();

    Integer addPatient(Patient patient);

    List<Patient> getAllPatientSortedByName();

    default void emptyArrayList() {
    }

    //Do not implement these methods in PatientServiceImplArraylist.java class
    default void updatePatient(Patient patient) {}

    default void deletePatient(int patientId) {}

    default Patient getPatientById(int patientId) {
        return null;
    }

    //Do not implement these methods in PatientServiceImplArraylist.java and PatientServiceImplJdbc.java class
    //Do not implement this method until day-13
    default public void modifyPatientDetails(PatientDTO patientDTO) { }
}
