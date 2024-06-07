package com.crud.demo.services.exceptions;

public class IntegrityDbException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public IntegrityDbException(String message) {
		super(message);
	}
}
