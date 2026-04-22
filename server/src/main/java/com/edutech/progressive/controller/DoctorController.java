package com.edutech.progressive.controller;

import com.edutech.progressive.dto.DoctorDTO;
import com.edutech.progressive.service.DoctorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService ds;
    public DoctorController(DoctorService ds){
        this.ds = ds;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        try {
            return new ResponseEntity<>(ds.getAllDoctors(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{doctorID}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable("doctorID") int doctorId) {
        try {
            return new ResponseEntity<>(ds.getDoctorById(doctorId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> addDoctor(@RequestBody DoctorDTO doctorDTO) {
        try {
            return new ResponseEntity<>(ds.addDoctor(doctorDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{doctorID}")
    public ResponseEntity<Void> updateDoctor(@PathVariable("doctorID") int doctorId,@RequestBody DoctorDTO doctorDTO) {
        try {
            if(ds.getDoctorById(doctorId)!=null)
            ds.updateDoctor(doctorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{doctorID}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctorID") int doctorId) {
        try {
            ds.deleteDoctor(doctorId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/experience")
    public ResponseEntity<List<DoctorDTO>> getDoctorSortedByExperience() {
        try {
            return new ResponseEntity<>(ds.getDoctorSortedByExperience(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}