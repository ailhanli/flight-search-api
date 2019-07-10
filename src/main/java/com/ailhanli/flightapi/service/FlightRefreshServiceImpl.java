package com.ailhanli.flightapi.service;

import org.springframework.stereotype.Service;

import com.ailhanli.flightapi.dao.FlightRepository;
import com.ailhanli.flightapi.integration.ExternalFlightService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
public class FlightRefreshServiceImpl implements FlightRefreshService {

	private final ExternalFlightService externalService;

	private final FlightRepository flightRepository;

	public FlightRefreshServiceImpl(ExternalFlightService externalService, FlightRepository flightRepository) {
		super();
		this.externalService = externalService;
		this.flightRepository = flightRepository;
	}

	@Override
	public void refreshDatabase() {

		Flux
		.just("Started")
		.subscribeOn(Schedulers.elastic())//flights are going to delete by elastic thread from thread pool
		.flatMap(s->flightRepository.removeAllFlights())
		.doOnNext(s->log.info("all flights removed from db successfully"))
		.publishOn(Schedulers.elastic())//getting all flights from external by elastic thread from thread pool
		.flatMap(s-> Flux.concat(externalService.getCheapFlights(), externalService.getBusinessFlights()))
		.publishOn(Schedulers.elastic())//a flight is going to save by elastic thread from thread pool
		.flatMap(flightRepository::saveFlight)
		.subscribe(f->log.info(f.toString()), t->log.error("error occured during the processing business flights", t), () -> log.info("******************************Database refresh completed*****************************"));
	}
}