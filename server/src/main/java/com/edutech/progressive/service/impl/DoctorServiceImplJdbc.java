package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.print.Doc;

import com.edutech.progressive.dao.DoctorDAO;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

public class DoctorServiceImplJdbc implements DoctorService {

    DoctorDAO doctorDAO;

    public DoctorServiceImplJdbc(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException{
            return doctorDAO.getAllDoctors();
        
    }

    @Override
    public Doctor getDoctorById(int doctorId) throws SQLException{

            return doctorDAO.getDoctorById(doctorId);
        
        
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws SQLException{

            return doctorDAO.addDoctor(doctor);
       
        
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws SQLException{
        List<Doctor> doctors = new ArrayList<>();
            doctors = doctorDAO.getAllDoctors();
            Collections.sort(doctors);
            return doctors;

        
    }

    @Override
    public void updateDoctor(Doctor doctor)throws SQLException{
                doctorDAO.updateDoctor(doctor);
        

    }

    @Override
    public void deleteDoctor(int doctorId)throws SQLException{

            doctorDAO.deleteDoctor(doctorId);

    }

}