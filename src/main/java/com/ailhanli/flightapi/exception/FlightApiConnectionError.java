package com.ailhanli.flightapi.exception;

public class FlightApiConnectionError extends Throwable {
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public FlightApiConnectionError(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}