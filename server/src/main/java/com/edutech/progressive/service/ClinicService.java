package com.edutech.progressive.service;

import com.edutech.progressive.entity.Clinic;

import java.sql.SQLException;
import java.util.List;

public interface ClinicService {

    public List<Clinic> getAllClinics() throws SQLException;

    public Clinic getClinicById(int clinicId) throws SQLException;

    public Integer addClinic(Clinic clinic) throws SQLException;

    public void updateClinic(Clinic clinic) throws SQLException;

    public void deleteClinic(int clinicId) throws SQLException;

    //Do not implement these methods in ClinicServiceImplJdbc.java class
    default public List<Clinic> getAllClinicByLocation(String location) throws SQLException{ return null; }

    default public List<Clinic> getAllClinicByDoctorId(int doctorId) throws SQLException{ return null; }
}