package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.exception.PatientAlreadyExistsException;
import com.edutech.progressive.exception.PatientNotFoundException;
import com.edutech.progressive.repository.BillingRepository;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.service.PatientService;

@Service
public class PatientServiceImplJpa implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    public PatientServiceImplJpa(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() throws Exception {
        return patientRepository.findAll();
    }

    @Override
    public Integer addPatient(Patient patient) throws Exception {
        Patient existingPatient = patientRepository.findByEmail(patient.getEmail());
        if (existingPatient != null) {
            throw new PatientAlreadyExistsException("Patient already exists");
        }
        return patientRepository.save(patient).getPatientId();
    }

    @Override
    public List<Patient> getAllPatientSortedByName() throws Exception {
        List<Patient> patientList = patientRepository.findAll();
        patientList.sort(Comparator.comparing(Patient::getFullName));
        return patientList;
    }

    public void updatePatient(Patient patient) throws Exception {
        Patient existingPatient = patientRepository.findByEmail(patient.getEmail());
        if (existingPatient != null) {
            throw new PatientAlreadyExistsException("Patient already exists");
        }
        Patient patientObj = patientRepository.findById(patient.getPatientId()).get();
        if (patientObj != null) {
            patientObj.setFullName(patient.getFullName());
            patientObj.setContactNumber(patient.getContactNumber());
            patientObj.setDateOfBirth(patient.getDateOfBirth());
            patientObj.setEmail(patient.getEmail());
            patientObj.setAddress(patient.getEmail());

            patientRepository.save(patientObj);
        }
    }

    public void deletePatient(int patientId) throws Exception {
        billingRepository.deleteByPatientId(patientId);
        patientRepository.deleteById(patientId);
    }

    public Patient getPatientById(int patientId) throws Exception {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found");
        }
        return patientRepository.findByPatientId(patientId);
    }

}