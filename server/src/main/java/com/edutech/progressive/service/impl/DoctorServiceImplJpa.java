package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.service.DoctorService;

@Service
public class DoctorServiceImplJpa implements DoctorService {

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