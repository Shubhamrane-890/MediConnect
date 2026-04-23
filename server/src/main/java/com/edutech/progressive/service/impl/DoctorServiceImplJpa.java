package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.dto.DoctorDTO;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.exception.DoctorAlreadyExistsException;
import com.edutech.progressive.repository.ClinicRepository;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.service.DoctorService;

@Service
public class DoctorServiceImplJpa implements DoctorService {
    @Autowired
    private ClinicRepository cr;

    private final DoctorRepository dr;

    public DoctorServiceImplJpa(DoctorRepository dr) {
        this.dr = dr;
    }

    @Override
    public List<DoctorDTO> getAllDoctors() throws Exception {
        List<Doctor> doctors = dr.findAll();
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        for (Doctor d : doctors) {
            doctorDTOs.add(convertToDto(d));
        }
        return doctorDTOs;
    }

    @Override
    public Integer addDoctor(DoctorDTO doctorDTO) throws Exception {
        Doctor doctor = convertToEntity(doctorDTO);
        Optional<Doctor> d = dr.findByEmail(doctor.getEmail());
        if (d.isPresent()) {
            throw new DoctorAlreadyExistsException("Doctor already exists with this email");
        }
        dr.save(doctor);
        return doctor.getDoctorId();
    }

    @Override
    public List<DoctorDTO> getDoctorSortedByExperience() throws Exception {
        List<Doctor> doctors = dr.findAll();
        Collections.sort(doctors);
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        for (Doctor d : doctors) {
            doctorDTOs.add(convertToDto(d));
        }
        return doctorDTOs;
    }

    public void updateDoctor(DoctorDTO doctorDTO) throws Exception {
        Doctor doctor = convertToEntity(doctorDTO);
        dr.save(doctor);
    }

    public void deleteDoctor(int doctorId) throws Exception {
        Optional<Doctor> o = dr.findByDoctorId(doctorId);
        if (o.isPresent()) {
            cr.deleteByDoctorId(doctorId);
            dr.deleteById(doctorId);
        }
    }

    public DoctorDTO getDoctorById(int doctorId) throws Exception {
        Optional<Doctor> o = dr.findByDoctorId(doctorId);
        if (o.isPresent()) {
            return convertToDto(o.get());
        }
        return null;
    }

    public Doctor convertToEntity(DoctorDTO dto) {
        Doctor doctor = new Doctor();

        doctor.setDoctorId(dto.getDoctorId());
        doctor.setFullName(dto.getFullName());
        doctor.setSpecialty(dto.getSpecialty());
        doctor.setContactNumber(dto.getContactNumber());
        doctor.setEmail(dto.getEmail());
        doctor.setYearsOfExperience(dto.getYearsOfExperience());
        return doctor;
    }

    public DoctorDTO convertToDto(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setDoctorId(doctor.getDoctorId());
        doctorDTO.setFullName(doctor.getFullName());
        doctorDTO.setSpecialty(doctor.getSpecialty());
        doctorDTO.setContactNumber(doctor.getContactNumber());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setYearsOfExperience(doctor.getYearsOfExperience());
        return doctorDTO;
    }

}