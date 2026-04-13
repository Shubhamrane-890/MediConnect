package com.edutech.progressive.service;

import com.edutech.progressive.dto.DoctorDTO;
import com.edutech.progressive.entity.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorService {

    public List<Doctor> getAllDoctors() throws SQLException;

    public Integer addDoctor(Doctor doctor) throws SQLException;

    public List<Doctor> getDoctorSortedByExperience() throws SQLException;

    default void emptyArrayList() throws SQLException{}

    //Do not implement these methods in DoctorServiceImplArraylist.java class
    default public void updateDoctor(Doctor doctor) throws SQLException{ }

    default public void deleteDoctor(int doctorId) throws SQLException{ }

    default Doctor getDoctorById(int doctorId) throws SQLException{ return null; }

    //Do not implement these methods in DoctorServiceImplArraylist.java and DoctorServiceImplJdbc.java class
    // Do not implement this method until day-13
    default public void modifyDoctorDetails(DoctorDTO doctorDTO) throws SQLException{ }
}