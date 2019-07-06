package com.ailhanli.flightapi.service;

import org.springframework.stereotype.Service;

import com.ailhanli.flightapi.dao.FlightRepository;
import com.ailhanli.flightapi.integration.ExternalFlightService;
import com.ailhanli.flightapi.util.FlightConverter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
public class FlightRefreshServiceImpl implements FlightRefreshService {

	private final ExternalFlightService externalService;

	private final FlightRepository flightRepository;
	
	private final FlightConverter flightConverter;

	public FlightRefreshServiceImpl(ExternalFlightService externalService, FlightRepository flightRepository,FlightConverter flightConverter) {
		super();
		this.externalService = externalService;
		this.flightRepository = flightRepository;
		this.flightConverter = flightConverter;
		
		//refresh db on startup
		refreshDatabase();
	}

	@Override
	public void refreshDatabase() {

		// clear database
		flightRepository.removeAllFlights();

		//call api and then save cheap flights on db
		externalService.getCheapFlights()
					.subscribeOn(Schedulers.elastic()) //retrieving cheap flight by elastic thread from thread pool
					.map(r -> r.getData())
					.flatMapMany(fs -> Flux.fromArray(fs))
					.map(flightConverter.cheapFlightConverter())
					.publishOn(Schedulers.elastic())//flight is going to save by elastic thread from thread pool
					.doOnNext(f -> flightRepository.saveFlight(f))
					.subscribe(f -> {}, t->log.error("error occured during the processing cheap flights", t), () -> log.info("******************************Database refresh completed for Economy Api*****************************"));

		//call api and then save business flights on db
		externalService.getBusinessFlights()
					.subscribeOn(Schedulers.elastic())//retrieving business flight by elastic thread from thread pool
					.map(r -> r.getData())
					.flatMapMany(fs -> Flux.fromArray(fs))
					.map(flightConverter.businessFlightConverter())
					.publishOn(Schedulers.elastic())//flight is going to save by elastic thread from thread pool
					.doOnNext(f -> flightRepository.saveFlight(f))
					.subscribe(f -> {}, t->log.error("error occured during the processing business flights", t), () -> log.info("******************************Database refresh completed for Business Api*****************************"));
	}
}