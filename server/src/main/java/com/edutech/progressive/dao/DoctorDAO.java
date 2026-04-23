package com.edutech.progressive.dao;

import com.edutech.progressive.entity.Doctor;

import java.util.List;

public interface DoctorDAO {
    int addDoctor(Doctor doctor);
    Doctor getDoctorById(int doctorId);
    void updateDoctor(Doctor doctor);
    void deleteDoctor(int doctorId) ;
    List<Doctor> getAllDoctors();
}
