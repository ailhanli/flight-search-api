package com.ailhanli.flightapi.listener;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ailhanli.flightapi.service.FlightRefreshService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FlightListener {

	private final FlightRefreshService flightRefreshService;

	public FlightListener(FlightRefreshService flightRefreshService) {
		super();
		this.flightRefreshService = flightRefreshService;
	}

	@Scheduled(fixedDelayString = "${flight.listener.interval}")
	public void execute() {
		log.info("Flight database is refreshing...");
		flightRefreshService.refreshDatabase();
	}
}