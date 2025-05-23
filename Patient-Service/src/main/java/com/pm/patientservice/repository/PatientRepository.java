package com.pm.patientservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.patientservice.entity.Patient;
import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
	Patient findByEmail(String email);
	boolean existsByEmail(String email);
}
