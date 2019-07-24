package com.ailhanli.flightapi.beans;

import java.util.HashMap;
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
public class FlightSearchCriteriaDTO {

	@Builder.Default
	private Map<String, Object> criteria = new HashMap<>();
	
	@Builder.Default
	private Integer pageSize=10;
	
	@Builder.Default
	private Integer pageNumber=0;
	
	@Builder.Default
	private String sortBy="_id";
	
	@Builder.Default
	private Direction direction=Direction.ASC;
}