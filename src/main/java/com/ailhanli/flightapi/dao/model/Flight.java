package com.ailhanli.flightapi.dao.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ailhanli.flightapi.util.FlightClass;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = Flight.FlightBuilder.class)
@Document("flight")
public class Flight {

	private String from;

	private String to;

	private LocalDateTime departureTime;

	private LocalDateTime arrivalTime;
	
	private FlightClass flightClass;
}