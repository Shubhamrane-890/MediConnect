package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.edutech.progressive.dto.PatientDTO;
import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.exception.PatientAlreadyExistsException;
import com.edutech.progressive.exception.PatientNotFoundException;
import com.edutech.progressive.repository.BillingRepository;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.service.PatientService;
@Service
@Primary
public class PatientServiceImplJpa implements PatientService {

    private final PatientRepository pr;
    public PatientServiceImplJpa(PatientRepository pr){
        this.pr = pr;
    }

    @Autowired
    BillingRepository br;

    @Override
    public List<PatientDTO> getAllPatients() throws Exception {
        List<Patient> list = pr.findAll();
        if(list.isEmpty()){
            throw new PatientNotFoundException("Patient not found");
        }
        List<PatientDTO> patientDTO = new ArrayList<>();
        for(Patient p : list){
            patientDTO.add(convertToDto(p));
        }
        return patientDTO;
    }

    @Override
    public Integer addPatient(PatientDTO patientDTO) throws Exception {
        Patient patient = convertToEntity(patientDTO);
        Optional<Patient> o = pr.findByEmail(patient.getEmail());
        if(o.isPresent()){
            throw new PatientAlreadyExistsException("Patient already exists with same email");
        }
        Patient p = pr.save(patient);
        return p.getPatientId();
    }

    @Override
    public List<PatientDTO> getAllPatientSortedByName() throws Exception {
        List<Patient> list = pr.findAll();
        if(list.isEmpty()){
            throw new PatientNotFoundException("Patient not found");
        }
        Collections.sort(list);
        List<PatientDTO> patientDTO = new ArrayList<>();
        for(Patient p : list){
            patientDTO.add(convertToDto(p));
        }
        return patientDTO;
    }

    public void updatePatient(PatientDTO patientDTO) throws Exception {
        Patient patient = convertToEntity(patientDTO);
        pr.save(patient);
    }

    public void deletePatient(int patientId) throws Exception {
        if(pr.findById(patientId).isPresent()){
            br.deleteByPatientId(patientId);
            pr.deleteById(patientId);
        }
    }

    public PatientDTO getPatientById(int patientId) throws Exception {
        if(pr.findById(patientId).isPresent()){
            return convertToDto(pr.findByPatientId(patientId).get());
        }
        throw new PatientNotFoundException("Patient not found");
    }

    public Patient convertToEntity(PatientDTO dto){
        Patient patient = new Patient();
        patient.setPatientId(dto.getPatientId());
        patient.setFullName(dto.getFullName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setContactNumber(dto.getContactNumber());
        patient.setEmail(dto.getEmail());
        patient.setAddress(dto.getAddress());
        return patient;
    }

    public PatientDTO convertToDto(Patient patient){
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(patient.getPatientId());
        patientDTO.setFullName(patient.getFullName());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setContactNumber(patient.getContactNumber());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        return patientDTO;
    }
}