package com.crud.demo.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.demo.services.exceptions.IntegrityDbException;
import com.crud.demo.services.exceptions.NotFoundResourceException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(NotFoundResourceException.class)
	public ResponseEntity<StandardError> entityNotFound(NotFoundResourceException e, HttpServletRequest request){
		
		StandardError stand = new StandardError();
		stand.setTimestamp(Instant.now());
		stand.setStatus(HttpStatus.NOT_FOUND.value());
		stand.setError(e.getMessage());
		stand.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stand);
	}
	
	@ExceptionHandler(IntegrityDbException.class)
	public ResponseEntity<StandardError> IntegrityError(IntegrityDbException e, HttpServletRequest request){
		
		StandardError stand = new StandardError();
		stand.setTimestamp(Instant.now());
		stand.setStatus(HttpStatus.BAD_REQUEST.value());
		stand.setError(e.getMessage());
		stand.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stand);
	}
}
