package com.pm.patientservice.dto;

import com.pm.patientservice.dto.validator.CreatePatientValidatioGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDto {
	@NotBlank(message = "name is required")
	@Size(max = 100, message = "Name can not exces 100 length")
	private String name;
	@Email(message = "Email should be valid")
	@NotBlank(message = "email is required")
	private String email;
	@NotBlank(message = "address is required")
	@Size(max = 100, message = "Can not exceed 1000")
	private String address;
	@NotBlank(message = "name is required")
	private String dateofbirth;
	@NotBlank(groups = CreatePatientValidatioGroup.class ,message = "registered date is required")
	private String registeredDate;
}
