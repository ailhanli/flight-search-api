package com.ailhanli.flightapi.util;

public enum FlightClass {

	BUSINESS("Business"), ECONOMY("Economy");
	
	private String businessClass;

	FlightClass(String businessClass) {
		this.businessClass = businessClass;
	}
	
	public String getBusinessClass() {
		return businessClass;
	}
}