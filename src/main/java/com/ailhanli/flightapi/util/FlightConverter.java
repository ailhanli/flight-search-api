package com.ailhanli.flightapi.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.ailhanli.flightapi.dao.model.Flight;
import com.ailhanli.flightapi.integration.beans.BusinessFlightBean;
import com.ailhanli.flightapi.integration.beans.CheapFlightBean;

@Component
public class FlightConverter {

	public Function<? super BusinessFlightBean, ? extends Flight> businessFlightConverter() {
		return c->{ 
			LocalDateTime departureTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(c.getDepartureTime()), ZoneId.systemDefault());
			LocalDateTime arrivalTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(c.getArrivalTime()), ZoneId.systemDefault());
			return new Flight(c.getDeparture(), c.getArrival(), departureTime, arrivalTime, FlightClass.BUSINESS) ;
		};
	}

	public Function<? super CheapFlightBean, ? extends Flight> cheapFlightConverter() {
		return c->{ 
			String[] routeArray = c.getRoute().split("-");
			LocalDateTime departureTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(c.getDeparture()), ZoneId.systemDefault());
			LocalDateTime arrivalTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(c.getArrival()), ZoneId.systemDefault());
			return new Flight(routeArray[0],routeArray[1], departureTime, arrivalTime, FlightClass.ECONOMY) ;
		};
	}
}