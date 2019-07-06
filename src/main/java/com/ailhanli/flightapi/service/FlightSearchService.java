package com.ailhanli.flightapi.service;

import java.util.Map;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;
import com.ailhanli.flightapi.dao.model.Flight;
import com.ailhanli.flightapi.exception.InvalidRequestException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightSearchService {
	
	Flux<Flight> searchFlight(FlightSearchCriteria flightSeachCriteria) throws InvalidRequestException;
	
	Mono<Long> searchFlightSize(Map<String, Object> flightSeachCriteria) throws InvalidRequestException;
}