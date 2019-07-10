package com.ailhanli.flightapi.integration;

import com.ailhanli.flightapi.dao.model.Flight;

import reactor.core.publisher.Flux;

public interface ExternalFlightService {
	
	Flux<Flight> getCheapFlights();

	Flux<Flight> getBusinessFlights();
}