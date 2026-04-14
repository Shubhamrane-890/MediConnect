package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.service.PatientService;
@Service
@Primary
public class PatientServiceImplJpa implements PatientService {

    private final PatientRepository pr;
    public PatientServiceImplJpa(PatientRepository pr){
        this.pr = pr;
    }

    @Override
    public List<Patient> getAllPatients() throws Exception {
        return pr.findAll();
    }

    @Override
    public Integer addPatient(Patient patient) throws Exception {
        Patient p = pr.save(patient);
        return p.getPatientId();
    }

    @Override
    public List<Patient> getAllPatientSortedByName() throws Exception {
        List<Patient> list = pr.findAll();
        Collections.sort(list);
        return list;
    }

    public void updatePatient(Patient patient) throws Exception {
        pr.save(patient);
    }

    public void deletePatient(int patientId) throws Exception {
        if(pr.findById(patientId).isPresent()){
            pr.deleteById(patientId);
        }
    }

    public Patient getPatientById(int patientId) throws Exception {
        if(pr.findById(patientId).isPresent()){
            return pr.findByPatientId(patientId).get();
        }
        return null;
    }

}