package com.edutech.progressive.service.impl;





import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.service.PatientService;

@Service
public class PatientServiceImplJpa implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImplJpa(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Integer addPatient(Patient patient) {
        Patient saved = patientRepository.save(patient);
        if (saved == null) {
            return 0;
        }
        return 1;
    }

    @Override
    public List<Patient> getAllPatientSortedByName() {
        List<Patient> list = patientRepository.findAll();
        Collections.sort(list); // assumes Patient implements Comparable
        return list;
    }

    @Override
    public Patient getPatientById(int patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    @Override
    public void updatePatient(Patient patient) {
        if (patient == null) return;
        if (patient.getPatientId() == 0) return;

        if (patientRepository.existsById(patient.getPatientId())) {
            patientRepository.save(patient);
        }
    }

    @Override
    public void deletePatient(int patientId) {
        if (patientRepository.existsById(patientId)) {
            patientRepository.deleteById(patientId);
        }
    }
}