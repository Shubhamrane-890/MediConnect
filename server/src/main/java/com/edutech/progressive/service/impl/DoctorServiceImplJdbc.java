package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.DoctorDAO;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

public class DoctorServiceImplJdbc implements DoctorService {

    DoctorDAO doctorDAO;

    public DoctorServiceImplJdbc(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    public List<Doctor> getAllDoctors() throws Exception {

        return doctorDAO.getAllDoctors();

    }

    @Override
    public Integer addDoctor(Doctor doctor) throws Exception {
        return doctorDAO.addDoctor(doctor);

    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws Exception {

        List<Doctor> list = new ArrayList<>(doctorDAO.getAllDoctors());
        Collections.sort(list);
        return list;

    }

    public void updateDoctor(Doctor doctor) throws Exception {

        doctorDAO.updateDoctor(doctor);

    }

    public void deleteDoctor(int doctorId) throws Exception {

        doctorDAO.deleteDoctor(doctorId);

    }

    public Doctor getDoctorById(int doctorId) throws Exception {

        return doctorDAO.getDoctorById(doctorId);

    }

}