package com.ailhanli.flightapi.integration.beans;

import lombok.Data;

@Data
public class BusinessFlightBean {

	private String departure;
	
	private String arrival;
	
	private Integer departureTime;
	
	private Integer arrivalTime;
}