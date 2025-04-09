package com.pm.patientservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.entity.Patient;
import com.pm.patientservice.exception.EmailAlreadyExistException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.repository.PatientRepository;


@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private PatientMapper patientMapper;
	
	public List<PatientResponseDto> getAll(){
		List<Patient> patients = patientRepository.findAll();
		List<PatientResponseDto> ans = new ArrayList<>();
		for(Patient i:patients) {
			ans.add(patientMapper.getPatientResponseDto(i));
		}
		return ans; 
		
	}
	
	public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
		if(patientRepository.existsByEmail(patientRequestDto.getEmail())) {
			throw new EmailAlreadyExistException("Patient with this email already exist "+patientRequestDto.getEmail());
		}
		Patient patient=patientRepository.save(patientMapper.getPatientObject(patientRequestDto));
		return patientMapper.getPatientResponseDto(patient);
	}
	
	public PatientResponseDto updatePatient(UUID id,PatientRequestDto dto) {
		Patient patient = patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient with this id not exist{}"+id));
		if(!patient.getEmail().equals(dto.getEmail())) {
			if(patientRepository.existsByEmail(dto.getEmail())) {
				throw new EmailAlreadyExistException("Email already exist in database "+dto.getEmail());
			}
		}
		patient.setName(dto.getName());
		patient.setAddress(dto.getAddress());
		patient.setDateofbirth(LocalDate.parse(dto.getDateofbirth()));
		patient.setEmail(dto.getEmail());
		
		Patient updatedPatient = patientRepository.save(patient);
		
		return patientMapper.getPatientResponseDto(updatedPatient);
		
	}
	
	public void deletePatient(UUID id) {
		if(patientRepository.findById(id)==null) {
			throw new PatientNotFoundException("Patient with this id not exist{}"+id);
		}
		else {
			patientRepository.deleteById(id);
		}
	}
	
}
