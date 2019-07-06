package com.ailhanli.flightapi.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ailhanli.flightapi.beans.JsonResponseBean;
import com.ailhanli.flightapi.exception.FlightApiConnectionError;
import com.ailhanli.flightapi.exception.InvalidRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public final class GlobalExceptionHandler {

	public static final String INVALID_REQUEST = "INVALID_REQUEST";
	public static final String FLIGHT_API_CONNECTION_ERROR = "FLIGHT_API_CONNECTION_ERROR";
	public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

	private static final String INTERNAL_ERROR_MESSAGE = "Internal server error occured";

	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public JsonResponseBean invalidRequestException(InvalidRequestException ex) {
		return new JsonResponseBean(INVALID_REQUEST, ex.getMessage());
	}

	@ExceptionHandler(FlightApiConnectionError.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ResponseBody
	public JsonResponseBean flightApiConnectionException(FlightApiConnectionError ex) {
		return new JsonResponseBean(FLIGHT_API_CONNECTION_ERROR, ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public JsonResponseBean handleAllExceptions(Exception exception) {
		log.error("system error occured", exception);
		return new JsonResponseBean(INTERNAL_ERROR, INTERNAL_ERROR_MESSAGE);
	}

}