package com.pm.patientservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.patientservice.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

}
