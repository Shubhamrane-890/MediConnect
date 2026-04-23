package com.edutech.progressive.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.exception.ClinicAlreadyExistsException;
import com.edutech.progressive.repository.ClinicRepository;
import com.edutech.progressive.service.ClinicService;

@Service
public class ClinicServiceImplJpa implements ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicServiceImplJpa(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public List<Clinic> getAllClinics() throws Exception {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic getClinicById(int clinicId) throws Exception {
        Optional<Clinic> c = clinicRepository.findByClinicId(clinicId);
        if (c.isPresent()) {
            return c.get();
        }
        return null;
    }

    @Override
    public Integer addClinic(Clinic clinic) throws Exception {
        Optional<Clinic> c = clinicRepository.findByClinicName(clinic.getClinicName());
        if(c.isPresent()){
            throw new ClinicAlreadyExistsException("Clinic already exists with same email");
        }
        clinicRepository.save(clinic);
        return clinic.getClinicId();
    }

    @Override
    public void updateClinic(Clinic clinic) throws Exception {
        clinicRepository.save(clinic);
    }

    @Override
    public void deleteClinic(int clinicId) throws Exception {
        Optional<Clinic> c = clinicRepository.findByClinicId(clinicId);
        if (c.isPresent()) {
            clinicRepository.deleteById(clinicId);
        }
    }

    public List<Clinic> getAllClinicByLocation(String location) throws Exception {
        return clinicRepository.findAllByLocation(location);
    }

    public List<Clinic> getAllClinicByDoctorId(int doctorId) throws Exception {
        return clinicRepository.findAllByDoctorId(doctorId);
    }

}