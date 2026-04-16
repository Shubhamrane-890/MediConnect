package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Appointment;
import com.edutech.progressive.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        try {
            return new ResponseEntity<List<Appointment>>(appointmentService.getAllAppointments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createAppointment(@RequestBody Appointment appointment) {
        try {
            return new ResponseEntity<Integer>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Void> updateAppointment(@PathVariable int appointmentId,@RequestBody Appointment appointment) {
        try {
            appointment.setAppointmentId(appointmentId);
            appointmentService.updateAppointment(appointment);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int appointmentId) {
        try {
            return new ResponseEntity<Appointment>(appointmentService.getAppointmentById(appointmentId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<Appointment>> getAppointmentByClinic(@PathVariable int clinicId) {
        try {
            return new ResponseEntity<List<Appointment>>(appointmentService.getAppointmentByClinic(clinicId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentByPatient(@PathVariable int patientId) {
        try {
            return new ResponseEntity<List<Appointment>>(appointmentService.getAppointmentByPatient(patientId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Appointment>> getAppointmentByStatus(@PathVariable String status) {
        try {
            return new ResponseEntity<List<Appointment>>(appointmentService.getAppointmentByStatus(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}