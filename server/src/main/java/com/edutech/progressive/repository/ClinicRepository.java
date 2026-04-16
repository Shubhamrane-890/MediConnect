package com.edutech.progressive.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Clinic;
@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Integer>{
   Optional<Clinic> findByClinicId(int id);
   List<Clinic> findAllByLocation(String location);
   List<Clinic> findAllByDoctorId(int doctorId);
   void deleteById(int doctorId);

}