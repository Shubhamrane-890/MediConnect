package com.edutech.progressive.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.repository.ClinicRepository;
import com.edutech.progressive.service.ClinicService;

@Service
public class ClinicServiceImplJpa implements ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicServiceImplJpa(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    @Override
    public Integer addClinic(Clinic clinic) {
        Clinic saved = clinicRepository.save(clinic);
        if (saved == null) {
            return 0;
        }
        return 1;
    }

    @Override
    public Clinic getClinicById(int clinicId) {
        return clinicRepository.findById(clinicId).orElse(null);
    }

    @Override
    public void updateClinic(Clinic clinic) {
        if (clinic == null) return;
        if (clinic.getClinicId() == 0) return;

        if (clinicRepository.existsById(clinic.getClinicId())) {
            clinicRepository.save(clinic);
        }
    }

    @Override
    public void deleteClinic(int clinicId) {
        if (clinicRepository.existsById(clinicId)) {
            clinicRepository.deleteById(clinicId);
        }
    }
}