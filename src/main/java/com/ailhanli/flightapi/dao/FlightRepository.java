package com.ailhanli.flightapi.dao;

import java.util.Map;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;
import com.ailhanli.flightapi.dao.model.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightRepository{
		
	Flux<Flight> queryFlight(FlightSearchCriteria flightSearchCriteria);

	Mono<Long> queryFlightSize(Map<String, Object> flightSearchCriteria);
	
	void removeAllFlights();
	
	void saveFlight(Flight flight);
}