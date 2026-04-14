package com.edutech.progressive.service.impl;





import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.service.DoctorService;

@Service
public class DoctorServiceImplJpa implements DoctorService {

    private DoctorRepository doctorRepository;
    public DoctorServiceImplJpa(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


    @Override
    public Integer addDoctor(Doctor doctor) {
        Doctor doc=doctorRepository.save(doctor);
        return doc.getDoctorId();
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() {
        List<Doctor> doc=doctorRepository.findAll();
        Collections.sort(doc,Comparator.comparing(Doctor::getYearsOfExperience));
        return doc;
    }

  

    public void deleteDoctor(int doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    

}