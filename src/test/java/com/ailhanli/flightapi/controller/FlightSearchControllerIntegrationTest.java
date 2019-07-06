package com.ailhanli.flightapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlightSearchControllerIntegrationTest {

	
	@Test
	public void test_by_only_from_is_valid(){
		
		Map<String,Object> testCriteria = new HashMap<>();
		testCriteria.put("from", "Istanbul");
		
		FlightSearchCriteria criteria = FlightSearchCriteria
				.builder()
				.criteria(testCriteria)
				.build();
		
		
		WebTestClient
		  .bindToServer()
		    .baseUrl("http://localhost:8095/flight-api/v1/flight")
		    .build()
		    .post()
		    .syncBody(criteria)
		    .exchange()
		    .expectStatus().isOk()
		    .expectHeader().valueEquals("Content-Type", "application/stream+json;charset=UTF-8")
		    .expectBody();
	}
	
	@Test
	public void test_by_with_invalid_search_criteria_return_4xx(){
		
		Map<String,Object> testCriteria = new HashMap<>();
		testCriteria.put("test1234", "Tests");
		
		FlightSearchCriteria criteria = FlightSearchCriteria
				.builder()
				.criteria(testCriteria)
				.pageNumber(2)
				.build();
		
		
		WebTestClient
		  .bindToServer()
		    .baseUrl("http://localhost:8095/flight-api/v1/flight")
		    .build()
		    .post()
		    .syncBody(criteria)
		    .exchange()
		    .expectStatus().is4xxClientError()
		    .expectHeader().valueEquals("Content-Type", "application/json;charset=UTF-8")
		    .expectBody();
	}
	
	@Test
	public void test_by_with_invalid_body_return_4xx(){
		
		Map<String,Object> testCriteria = new HashMap<>();
		testCriteria.put("test1234", "Tests");
		
		WebTestClient
		  .bindToServer()
		    .baseUrl("http://localhost:8095/flight-api/v1/flight")
		    .build()
		    .post()
		    .exchange()
		    .expectStatus().is4xxClientError()
		    .expectHeader().valueEquals("Content-Type", "application/json;charset=UTF-8")
		    .expectBody();
	}

}