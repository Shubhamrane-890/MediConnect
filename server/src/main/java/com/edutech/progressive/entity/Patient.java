package com.edutech.progressive.entity;

// File: src/main/java/com/edutech/progressive/entity/Patient.java


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "patient")
public class Patient implements Comparable<Patient> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    private String fullName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String contactNumber;
    private String email;
    private String address;

    public Patient() {
    }

    public Patient(int patientId, String fullName, Date dateOfBirth,
                   String contactNumber, String email, String address) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public int compareTo(Patient o) {
        return this.fullName.compareTo(o.getFullName());
    }
}