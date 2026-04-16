package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.exception.DoctorAlreadyExistsException;
import com.edutech.progressive.repository.ClinicRepository;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.service.DoctorService;

@Service
public class DoctorServiceImplJpa implements DoctorService {
    @Autowired
    private ClinicRepository cr;
    
    private final DoctorRepository dr;
    public DoctorServiceImplJpa(DoctorRepository dr){
        this.dr = dr;
    }


    @Override
    public List<Doctor> getAllDoctors() throws Exception {
       return dr.findAll();
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws Exception {
        Optional<Doctor> d = dr.findByEmail(doctor.getEmail());
        if(d.isPresent()){
            throw new DoctorAlreadyExistsException("Doctor already exists with this email");
        }
        dr.save(doctor);
        return doctor.getDoctorId();
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws Exception {
        List<Doctor> list = dr.findAll();
        Collections.sort(list);
        return list;
    }

    public void updateDoctor(Doctor doctor) throws Exception{
        dr.save(doctor);
    }

    public void deleteDoctor(int doctorId) throws Exception {
        Optional<Doctor> o = dr.findByDoctorId(doctorId);
        if(o.isPresent()){
            cr.deleteByDoctorId(doctorId);
            dr.deleteById(doctorId);
        }  
    }

    public Doctor getDoctorById(int doctorId) throws Exception {
        Optional<Doctor> o = dr.findByDoctorId(doctorId);
        if(o.isPresent()){
            return o.get();
        }
        return null;
    }

}