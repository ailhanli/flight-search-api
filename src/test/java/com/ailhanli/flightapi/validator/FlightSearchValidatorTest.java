package com.ailhanli.flightapi.validator;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;

public class FlightSearchValidatorTest {

	private FlightSearchCriteriaValidator validator = new FlightSearchCriteriaValidator();
	
	@Test
	public void test_search_value_cannot_be_null(){
		
		List<String> result =validator.validateFlightSearchCriteria(null);
		
		assertTrue(result.size()>0);
	}
	
	@Test
	public void test_criteria_cannot_be_null(){
		FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
		
		List<String> result =validator.validateFlightSearchCriteria(flightSearchCriteria);
		
		assertTrue(result.size()>0);
	}
	
	@Test
	public void test_page_number_cannot_negative() {
		FlightSearchCriteria flightSearchCriteria = FlightSearchCriteria.builder().pageNumber(-1).build();
		
		List<String> result = validator.validateFlightSearchCriteria(flightSearchCriteria);
		assertTrue(result.size()>0);
	}
	
	@Test
	public void test_page_size_cannot_negative(){
		FlightSearchCriteria flightSearchCriteria = FlightSearchCriteria.builder().pageSize(-1).build();
		
		List<String> result =validator.validateFlightSearchCriteria(flightSearchCriteria);
		assertTrue(result.size()>0);
	}
	
	
	@Test
	public void test_criteria_contains_invalid_key(){
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("test", "test1234");
		
		FlightSearchCriteria flightSearchCriteria = FlightSearchCriteria.builder().criteria(criteria).build();
		
		List<String> result =validator.validateFlightSearchCriteria(flightSearchCriteria);
		assertTrue(result.size()>0);
	
	}
	
	@Test
	public void test_criteria_valid(){
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("from", "test1234");
		
		FlightSearchCriteria flightSearchCriteria = FlightSearchCriteria.builder().criteria(criteria).build();
		
		List<String> result =validator.validateFlightSearchCriteria(flightSearchCriteria);
		assertTrue(result.size()==0);
	}
}