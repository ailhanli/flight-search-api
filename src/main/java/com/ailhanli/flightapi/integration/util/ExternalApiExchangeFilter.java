package com.ailhanli.flightapi.integration.util;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExternalApiExchangeFilter {

	public ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
			clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
			return Mono.just(clientRequest);
		});
	}

	public ExchangeFilterFunction logResponseStatus() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			log.info("Response Status {}", clientResponse.statusCode());
			return Mono.just(clientResponse);
		});
	}
}