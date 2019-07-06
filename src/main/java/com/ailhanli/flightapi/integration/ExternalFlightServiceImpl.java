package com.ailhanli.flightapi.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ailhanli.flightapi.exception.FlightApiConnectionError;
import com.ailhanli.flightapi.integration.beans.BusinessFlightResult;
import com.ailhanli.flightapi.integration.beans.CheapFlightResult;
import com.ailhanli.flightapi.integration.util.ExternalApiExchangeFilter;

import reactor.core.publisher.Mono;

@Service
public class ExternalFlightServiceImpl implements ExternalFlightService {
	
	private final WebClient webClient;
	
	@Value("${cheap.flight.api.url}")
	private String cheapFlightApiUrl;
	
	@Value("${business.flight.api.url}")
	private String businessFlightApiUrl;
	
	public ExternalFlightServiceImpl(WebClient.Builder webClientBuilder,  ExternalApiExchangeFilter filter) {
		this.webClient = webClientBuilder.filter(filter.logRequest()).filter(filter.logResponseStatus()).build();
	}
	
	@Override
	public Mono<CheapFlightResult> getCheapFlights() {
		return  webClient.get()
				.uri(cheapFlightApiUrl)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse ->
                	Mono.error(new FlightApiConnectionError("4xx error occured during the getting cheap flights"))
	            )
	            .onStatus(HttpStatus::is5xxServerError, clientResponse ->
	                Mono.error(new FlightApiConnectionError("5xx error occured during the getting cheap flights"))
	            )
				.bodyToMono(CheapFlightResult.class);
	}
	
	@Override
	public Mono<BusinessFlightResult> getBusinessFlights() {
		return  webClient.get()
				.uri(businessFlightApiUrl)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse ->
            		Mono.error(new FlightApiConnectionError("4xx error occured during the getting business flights"))
	            )
	            .onStatus(HttpStatus::is5xxServerError, clientResponse ->
	                Mono.error(new FlightApiConnectionError("5xx error occured during the getting business flights"))
	            )
				.bodyToMono(BusinessFlightResult.class);
	}
}