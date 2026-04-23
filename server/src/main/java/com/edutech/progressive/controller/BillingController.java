package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Billing;
import com.edutech.progressive.service.BillingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping
    public ResponseEntity<List<Billing>> getAllBills() {
        try {
            return new ResponseEntity<List<Billing>>(billingService.getAllBills(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> createBill(@RequestBody Billing billing) {
        try {
            int billingId = billingService.createBill(billing);
            return new ResponseEntity<>(billingId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{billingId}")
    public ResponseEntity<Integer> deleteBill(Billing billing) {
        try {
            billingService.deleteBill(billing.getBillingId());
            return new ResponseEntity<Integer>(billing.getBillingId(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{billingId}")
    public ResponseEntity<List<Billing>> getBillByid(@PathVariable int billingId) {
        try {
            return new ResponseEntity<List<Billing>>(List.of(billingService.getBillById(billingId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Billing>> getBillsByPatient(@PathVariable int patientId) {
        try {
            return new ResponseEntity<List<Billing>>(billingService.getBillsByPatientId(patientId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}