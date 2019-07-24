package com.ailhanli.flightapi.beans;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ailhanli.flightapi.util.FlightClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
@Document("flight")
public class FlightSearchResultDTO {

	private String from;

	private String to;

	private LocalDateTime departureTime;

	private LocalDateTime arrivalTime;
	
	private FlightClass flightClass;
}