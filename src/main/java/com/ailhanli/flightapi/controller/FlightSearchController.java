package com.ailhanli.flightapi.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ailhanli.flightapi.beans.FlightSearchCriteriaDTO;
import com.ailhanli.flightapi.dao.model.Flight;
import com.ailhanli.flightapi.service.FlightSearchService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/flight-api/v1")
public class FlightSearchController {

	private final FlightSearchService flightSearchService;

	public FlightSearchController(FlightSearchService flightService) {
		super();
		this.flightSearchService = flightService;
	}

	@ResponseBody
	@PostMapping(value = "/flight", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Flight> seachFlight(@RequestBody FlightSearchCriteriaDTO flightSeachCriteria) {
		log.info("Flight Search Criteria input:{}", flightSeachCriteria);
		return flightSearchService.searchFlight(flightSeachCriteria);
	}

	@ResponseBody
	@PostMapping(value = "/flight/size", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Mono<Long> getNumberOfFlights(@RequestBody Map<String, Object> flightSeachCriteria) {
		log.info("getNumberOfFlight input: {}", flightSeachCriteria);
		return flightSearchService.searchFlightSize(flightSeachCriteria);
	}
}