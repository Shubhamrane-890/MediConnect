package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

public class DoctorServiceImplArraylist implements DoctorService {

    private static List<Doctor> doctorList = new ArrayList<>();

    @Override
    public void emptyArrayList() {
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorList;
    }

    @Override
    public Integer addDoctor(Doctor doctor) {
        doctorList.add(doctor);
        return 1;
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() {
        Collections.sort(doctorList);
        return doctorList;
    }

}