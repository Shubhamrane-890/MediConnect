package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;
@Service
public class PatientServiceImplJpa implements PatientService {

    @Override
    public List<Patient> getAllPatients() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPatients'");
    }

    @Override
    public Integer addPatient(Patient patient) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPatient'");
    }

    @Override
    public List<Patient> getAllPatientSortedByName() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPatientSortedByName'");
    }



    

}