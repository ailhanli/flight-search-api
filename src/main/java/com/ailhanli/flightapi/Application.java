package com.ailhanli.flightapi;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.ailhanli.flightapi.service.FlightRefreshService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableMongoRepositories
@EnableWebFlux
@SpringBootApplication
public class Application {

	@Autowired
	private FlightRefreshService refreshService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void init() {
		log.info("refreshing database on startup...");
		refreshService.refreshDatabase();
	}

}