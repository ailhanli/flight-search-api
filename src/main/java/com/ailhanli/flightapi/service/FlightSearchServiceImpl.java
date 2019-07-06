package com.ailhanli.flightapi.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;
import com.ailhanli.flightapi.dao.FlightRepository;
import com.ailhanli.flightapi.dao.model.Flight;
import com.ailhanli.flightapi.exception.InvalidRequestException;
import com.ailhanli.flightapi.validator.FlightSearchCriteriaInitiliazer;
import com.ailhanli.flightapi.validator.FlightSearchCriteriaValidator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {
	private final FlightRepository flightRepository;

	private final FlightSearchCriteriaValidator flightSearchValidator;
	
	private final FlightSearchCriteriaInitiliazer initiliazer;

	public FlightSearchServiceImpl(FlightRepository flightRepository, FlightSearchCriteriaValidator flightSearchValidator, FlightSearchCriteriaInitiliazer initiliazer) {
		super();
		this.flightRepository = flightRepository;
		this.flightSearchValidator = flightSearchValidator;
		this.initiliazer = initiliazer;
	}

	@Override
	public Flux<Flight> searchFlight(FlightSearchCriteria flightSearchCriteria) throws InvalidRequestException {

		initiliazer.setup(flightSearchCriteria);
		
		//validate input
		List<String> errors = flightSearchValidator.validateFlightSearchCriteria(flightSearchCriteria);
		
		if(errors.size()>0){
			throw new InvalidRequestException(errors.stream().reduce("", (a,b)->a+"\n"+b));
		}

		return flightRepository.queryFlight(flightSearchCriteria);
		
	}

	@Override
	public Mono<Long> searchFlightSize(Map<String, Object> flightSeachCriteria) throws InvalidRequestException {
		//validate input
		Optional<String> result=flightSearchValidator.validateCriteria(flightSeachCriteria);
		
		if(result.isPresent()){
			throw new InvalidRequestException(result.get());
		}

		return flightRepository.queryFlightSize(flightSeachCriteria);
	}	
}