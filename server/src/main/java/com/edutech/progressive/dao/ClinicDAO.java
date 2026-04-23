package com.edutech.progressive.dao;

import com.edutech.progressive.entity.Clinic;

import java.util.List;

public interface ClinicDAO {
    int addClinic(Clinic clinic);
    Clinic getClinicById(int clinicId);
    void updateClinic(Clinic clinic);
    void deleteClinic(int clinicId);
    List<Clinic> getAllClinics();
}