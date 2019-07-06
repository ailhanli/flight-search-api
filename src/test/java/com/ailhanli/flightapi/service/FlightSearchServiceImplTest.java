package com.ailhanli.flightapi.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;
import com.ailhanli.flightapi.dao.FlightRepository;
import com.ailhanli.flightapi.exception.InvalidRequestException;
import com.ailhanli.flightapi.validator.FlightSearchCriteriaInitiliazer;
import com.ailhanli.flightapi.validator.FlightSearchCriteriaValidator;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchServiceImplTest {

	@Mock
	private FlightRepository flightRepository;

	@Mock
	private FlightSearchCriteriaValidator flightSearchValidator;

	@Mock
	private FlightSearchCriteriaInitiliazer initiliazer;

	@InjectMocks
	private FlightSearchServiceImpl searchService;

	@Test(expected=InvalidRequestException.class)
	public void verify_validator_thrown_exception() throws InvalidRequestException  {
		FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
		
		List<String> errors = new ArrayList<>();
		errors.add("TEST ERROR");
		
		when(flightSearchValidator.validateFlightSearchCriteria(flightSearchCriteria)).thenReturn(errors);

		searchService.searchFlight(flightSearchCriteria);

		verify(initiliazer, times(1)).setup(flightSearchCriteria);
		verify(flightSearchValidator, times(1)).validateFlightSearchCriteria(flightSearchCriteria);
	}
	
	
	@Test
	public void verify_dependency_called() throws InvalidRequestException  {
		FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
		Map<String,Object> criteria= new HashMap<>();
		criteria.put("from", "TEST1234");
		flightSearchCriteria.setCriteria(criteria);
		
		when(flightSearchValidator.validateFlightSearchCriteria(flightSearchCriteria)).thenReturn(new ArrayList<>());

		searchService.searchFlight(flightSearchCriteria);

		verify(initiliazer, times(1)).setup(flightSearchCriteria);
		verify(flightSearchValidator, times(1)).validateFlightSearchCriteria(flightSearchCriteria);
		verify(flightRepository, times(1)).queryFlight(flightSearchCriteria);

	}
}