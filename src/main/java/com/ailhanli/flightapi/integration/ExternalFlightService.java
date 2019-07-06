package com.ailhanli.flightapi.integration;

import com.ailhanli.flightapi.integration.beans.BusinessFlightResult;
import com.ailhanli.flightapi.integration.beans.CheapFlightResult;

import reactor.core.publisher.Mono;

public interface ExternalFlightService {
	
	Mono<CheapFlightResult> getCheapFlights();

	Mono<BusinessFlightResult> getBusinessFlights();
}