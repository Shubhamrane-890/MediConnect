
package com.edutech.progressive.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billingId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private double amount;
    // @Temporal(TemporalType.DATE)

    private Date dateOfIssue;
    // @Temporal(TemporalType.DATE)
    private Date dueDate;
    private String status;

    public Billing() {
    }

    public Billing(Patient patient, double amount, Date dateOfIssue, Date dueDate, String status) {
        this.patient = patient;
        this.amount = amount;
        this.dateOfIssue = dateOfIssue;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    public Billing(int billingId, Patient patient, double amount, Date dateOfIssue, Date dueDate, String status) {
        this.billingId = billingId;
        this.patient = patient;
        this.amount = amount;
        this.dateOfIssue = dateOfIssue;
        this.dueDate = dueDate;
        this.status = status;
    }
    public int getBillingId() {
        return billingId;
    }
    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Date getDateOfIssue() {
        return dateOfIssue;
    }
    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    

}
