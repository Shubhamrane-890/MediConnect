package com.edutech.progressive.service;

import com.edutech.progressive.dto.PatientDTO;
import com.edutech.progressive.entity.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients() throws SQLException;

    Integer addPatient(Patient patient) throws SQLException;

    List<Patient> getAllPatientSortedByName() throws SQLException;

    default void emptyArrayList() throws SQLException{
    }

    //Do not implement these methods in PatientServiceImplArraylist.java class
    default void updatePatient(Patient patient) throws SQLException{}

    default void deletePatient(int patientId) throws SQLException{}

    default Patient getPatientById(int patientId) throws SQLException{
        return null;
    }

    //Do not implement these methods in PatientServiceImplArraylist.java and PatientServiceImplJdbc.java class
    //Do not implement this method until day-13
    default public void modifyPatientDetails(PatientDTO patientDTO) throws SQLException{ }
}