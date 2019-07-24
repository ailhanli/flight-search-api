package com.ailhanli.flightapi.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ailhanli.flightapi.beans.FlightSearchCriteriaDTO;
import com.ailhanli.flightapi.dao.FlightRepository;
import com.ailhanli.flightapi.exception.InvalidRequestException;
import com.ailhanli.flightapi.validator.FlightSearchCriteriaValidator;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchServiceImplTest {

	@Mock
	private FlightRepository flightRepository;

	@Mock
	private FlightSearchCriteriaValidator flightSearchValidator;


	@InjectMocks
	private FlightSearchServiceImpl searchService;

	@Test
	public void verify_validator_thrown_exception() throws InvalidRequestException  {
		FlightSearchCriteriaDTO flightSearchCriteria = new FlightSearchCriteriaDTO();
		
		searchService.searchFlight(flightSearchCriteria);

		verify(flightSearchValidator, times(1)).validateFlightSearchCriteria(flightSearchCriteria);
	}
	
	
	@Test
	public void verify_dependency_called() throws InvalidRequestException  {
		FlightSearchCriteriaDTO flightSearchCriteria = new FlightSearchCriteriaDTO();
		Map<String,Object> criteria= new HashMap<>();
		criteria.put("from", "TEST1234");
		flightSearchCriteria.setCriteria(criteria);
		
		searchService.searchFlight(flightSearchCriteria);

		verify(flightSearchValidator, times(1)).validateFlightSearchCriteria(flightSearchCriteria);
		verify(flightRepository, times(1)).queryFlight(flightSearchCriteria);

	}
}