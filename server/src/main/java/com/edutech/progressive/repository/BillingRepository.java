package com.edutech.progressive.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edutech.progressive.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing,Integer>{
 List<Billing> findAllByPatient_PatientId(int PatientId);
   @Modifying
    @Transactional
   @Query("DELETE FROM Billing b WHERE b.patient.patientId = :patientId")
    void deleteByPatientId(@Param("patientId") int patientId);
}
