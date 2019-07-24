package com.ailhanli.flightapi.validator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ailhanli.flightapi.beans.FlightSearchCriteriaDTO;
import com.ailhanli.flightapi.exception.InvalidRequestException;

public class FlightSearchValidatorTest {

	private FlightSearchCriteriaValidator validator = new FlightSearchCriteriaValidator();
	
	@Test(expected=InvalidRequestException.class)
	public void test_search_value_cannot_be_null(){
		
		validator.validateFlightSearchCriteria(null);
	}
	
	@Test(expected=InvalidRequestException.class)
	public void test_criteria_cannot_be_null(){		
		validator.validateFlightSearchCriteria(null);
	}
	
	@Test(expected=InvalidRequestException.class)
	public void test_page_number_cannot_negative() {
		FlightSearchCriteriaDTO flightSearchCriteria = FlightSearchCriteriaDTO.builder().pageNumber(-1).build();
		
		validator.validateFlightSearchCriteria(flightSearchCriteria);
	}
	
	@Test(expected=InvalidRequestException.class)
	public void test_page_size_cannot_negative(){
		FlightSearchCriteriaDTO flightSearchCriteria = FlightSearchCriteriaDTO.builder().pageSize(-1).build();
		
		validator.validateFlightSearchCriteria(flightSearchCriteria);
	}
	
	
	@Test(expected=InvalidRequestException.class)
	public void test_criteria_contains_invalid_key(){
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("test", "test1234");
		
		validator.validateCriteria(criteria);	
	}
	
	@Test
	public void test_criteria_valid(){
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("from", "test1234");
		
		FlightSearchCriteriaDTO flightSearchCriteria = FlightSearchCriteriaDTO.builder().criteria(criteria).build();
		
		validator.validateFlightSearchCriteria(flightSearchCriteria);
	}
}