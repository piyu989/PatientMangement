package com.pm.patientservice.mapper;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.entity.Patient;

@Component
public class PatientMapper {
	public PatientResponseDto getPatientResponseDto(Patient patient) {
		PatientResponseDto dto=new PatientResponseDto();
		dto.setId(patient.getId().toString());
		dto.setAddress(patient.getAddress());
		dto.setDateofbirth(patient.getDateofbirth().toString());
		dto.setEmail(patient.getEmail());
		dto.setName(patient.getName());
		return dto;
	}
	
	public Patient getPatientObject(PatientRequestDto dto) {
		Patient patient = new Patient();
		patient.setName(dto.getName());
		patient.setAddress(dto.getAddress());
		patient.setDateofbirth(LocalDate.parse(dto.getDateofbirth()));
		patient.setRegisteredDate(LocalDate.parse(dto.getRegisteredDate()));
		patient.setEmail(dto.getEmail());
		return patient;
	}
}
