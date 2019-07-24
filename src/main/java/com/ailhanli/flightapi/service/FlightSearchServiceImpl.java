package com.ailhanli.flightapi.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ailhanli.flightapi.beans.FlightSearchCriteriaDTO;
import com.ailhanli.flightapi.dao.FlightRepository;
import com.ailhanli.flightapi.dao.model.Flight;
import com.ailhanli.flightapi.exception.InvalidRequestException;
import com.ailhanli.flightapi.validator.FlightSearchCriteriaValidator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {
	private final FlightRepository flightRepository;

	private final FlightSearchCriteriaValidator flightSearchValidator;
	

	public FlightSearchServiceImpl(FlightRepository flightRepository, FlightSearchCriteriaValidator flightSearchValidator) {
		super();
		this.flightRepository = flightRepository;
		this.flightSearchValidator = flightSearchValidator;
	}

	@Override
	public Flux<Flight> searchFlight(FlightSearchCriteriaDTO flightSearchCriteria) throws InvalidRequestException {
		
		//validate input
		flightSearchValidator.validateFlightSearchCriteria(flightSearchCriteria);
		
		return flightRepository.queryFlight(flightSearchCriteria);
		
	}

	@Override
	public Mono<Long> searchFlightSize(Map<String, Object> flightSeachCriteria) throws InvalidRequestException {
		//validate input
		flightSearchValidator.validateCriteria(flightSeachCriteria);
		
		return flightRepository.queryFlightSize(flightSeachCriteria);
	}	
}