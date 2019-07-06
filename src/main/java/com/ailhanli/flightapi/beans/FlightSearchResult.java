package com.ailhanli.flightapi.beans;

import com.ailhanli.flightapi.dao.model.Flight;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@Builder
public class FlightSearchResult {

	private Flux<Flight> flights;
	
	private Mono<Long> count;
	
	private int pageNumber;
	
	private int pageSize;
}