package com.ailhanli.flightapi.integration.beans;

import lombok.Data;

@Data
public class CheapFlightBean {

	private String route;
	
	private Integer departure;
	
	private Integer arrival;
}