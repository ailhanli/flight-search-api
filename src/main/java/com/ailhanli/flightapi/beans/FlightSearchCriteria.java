package com.ailhanli.flightapi.beans;

import java.util.Map;

import org.springframework.data.domain.Sort.Direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightSearchCriteria {

	private Map<String, Object> criteria;
	
	private Integer pageSize;
	
	private Integer pageNumber;
	
	private String sortBy;
	
	private Direction direction;
}