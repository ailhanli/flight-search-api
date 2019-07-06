package com.ailhanli.flightapi.exception;

public class InvalidRequestException extends Exception {
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public InvalidRequestException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}