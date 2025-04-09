package com.pm.patientservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidateException(MethodArgumentNotValidException ex){
		Map<String,String> response = new HashMap<>();
		log.warn("method argument not valid {}",ex.getMessage());
		ex.getBindingResult().getFieldErrors().forEach(
				error -> response.put(error.getField(), error.getDefaultMessage())
		);
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Map<String,String>> handleJdbcSQLIntegrityConstraintViolationException(JdbcSQLIntegrityConstraintViolationException ex){
		Map<String,String> response = new HashMap<>();
		log.warn("JDBC sql integrity DB {}",ex.getMessage());
		response.put("error", "Got duplicatie entry in the database");
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<Map<String,String>> handleEmailAlreadyExistException(EmailAlreadyExistException ex){
		Map<String,String> response = new HashMap<>();
		log.warn("Email already exist inn DB {}",ex.getMessage());
		response.put("error", "Patient with this email already exist");
		return ResponseEntity.badRequest().body(response);
	}
}
