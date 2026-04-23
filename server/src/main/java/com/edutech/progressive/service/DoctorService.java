package com.edutech.progressive.service;

import com.edutech.progressive.dto.DoctorDTO;
import com.edutech.progressive.entity.Doctor;

import java.util.List;

public interface DoctorService {

    public List<Doctor> getAllDoctors();

    public Integer addDoctor(Doctor doctor);

    public List<Doctor> getDoctorSortedByExperience();

    default void emptyArrayList() {
    }

    //Do not implement these methods in DoctorServiceImplArraylist.java class
    default public void updateDoctor(Doctor doctor) { }

    default public void deleteDoctor(int doctorId) { }

    default Doctor getDoctorById(int doctorId) { return null; }

    //Do not implement these methods in DoctorServiceImplArraylist.java and DoctorServiceImplJdbc.java class
    // Do not implement this method until day-13
    default public void modifyDoctorDetails(DoctorDTO doctorDTO) { }
}
