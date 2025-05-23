package com.pm.patientservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.dto.validator.CreatePatientValidatioGroup;
import com.pm.patientservice.service.PatientService;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/patients")
//@Tag(name = "Patient",description = "APIs for managing Patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
//    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDto>> getPatients(){
        List<PatientResponseDto> ans = patientService.getAll();
        return ResponseEntity.ok().body(ans);
    }
    
    @PostMapping
//    @Operation(summary = "Save Patients")
    public ResponseEntity<PatientResponseDto> savePatient(@Validated({Default.class,CreatePatientValidatioGroup.class}) @RequestBody PatientRequestDto dto){
    	PatientResponseDto responseDto = patientService.createPatient(dto);
    	return ResponseEntity.ok().body(responseDto);
    }
    
    @PutMapping("/{id}")
//    @Operation(summary = "Edit Patients")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PatientRequestDto dto){
    	PatientResponseDto resp = patientService.updatePatient(id, dto);
    	return ResponseEntity.ok().body(resp);
    }
    
    @DeleteMapping("/{id}")
//    @Operation(summary = "Delete Patients")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
    	patientService.deletePatient(id);
    	return ResponseEntity.noContent().build();
    }
}
