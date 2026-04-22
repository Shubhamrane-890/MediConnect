package com.edutech.progressive.service;

import com.edutech.progressive.dto.PatientDTO;
import java.util.List;


public interface PatientService {

    List<PatientDTO> getAllPatients() throws Exception;

    Integer addPatient(PatientDTO patientDTO) throws Exception;

    List<PatientDTO> getAllPatientSortedByName() throws Exception;

    default void emptyArrayList() {
    }

    //Do not implement these methods in PatientServiceImplArraylist.java class
    default void updatePatient(PatientDTO patientDTO) throws Exception {}

    default void deletePatient(int patientId) throws Exception {}

    default PatientDTO getPatientById(int patientId) throws Exception {
        return null;
    }

    //Do not implement these methods in PatientServiceImplArraylist.java and PatientServiceImplJdbc.java class
    //Do not implement this method until day-13
    default public void modifyPatientDetails(PatientDTO patientDTO) throws Exception { }
}