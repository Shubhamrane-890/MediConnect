package com.edutech.progressive.service;

import com.edutech.progressive.dto.DoctorDTO;
import java.util.List;

public interface DoctorService {

    public List<DoctorDTO> getAllDoctors() throws Exception;

    public Integer addDoctor(DoctorDTO doctorDTO) throws Exception;

    public List<DoctorDTO> getDoctorSortedByExperience() throws Exception;

    default void emptyArrayList()  {
    }

    //Do not implement these methods in DoctorServiceImplArraylist.java class
    default public void updateDoctor(DoctorDTO doctordDto) throws Exception{ }

    default public void deleteDoctor(int doctorId) throws Exception { }

    default DoctorDTO getDoctorById(int doctorId) throws Exception { return null; }

    //Do not implement these methods in DoctorServiceImplArraylist.java and DoctorServiceImplJdbc.java class
    // Do not implement this method until day-13
    default public void modifyDoctorDetails(DoctorDTO doctorDTO) throws Exception{ }
}