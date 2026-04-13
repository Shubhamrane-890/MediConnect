package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.dao.ClinicDAO;
import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.service.ClinicService;

public class ClinicServiceImplJdbc implements ClinicService {

    ClinicDAO clinicDAO;

    public ClinicServiceImplJdbc(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }

    @Override
    public List<Clinic> getAllClinics() throws SQLException {

        return clinicDAO.getAllClinics();
    }

    @Override
    public Clinic getClinicById(int clinicId) throws SQLException {
        return clinicDAO.getClinicById(clinicId);

    }

    @Override
    public Integer addClinic(Clinic clinic) throws SQLException {
        return clinicDAO.addClinic(clinic);
    }

    @Override
    public void updateClinic(Clinic clinic) throws SQLException {

        clinicDAO.updateClinic(clinic);

    }

    @Override
    public void deleteClinic(int clinicId) throws SQLException {
        clinicDAO.deleteClinic(clinicId);

    }

}