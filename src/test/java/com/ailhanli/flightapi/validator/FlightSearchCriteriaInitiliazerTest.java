package com.ailhanli.flightapi.validator;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort.Direction;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchCriteriaInitiliazerTest {

	@Mock
	private Constants Constants;
	
	@InjectMocks
	private FlightSearchCriteriaInitiliazer initiliazer;
	
	@Test
	public void test_default_is_assigned(){
		when(Constants.getDefaultPageNumber()).thenReturn(0);
		when(Constants.getDefaultPageSize()).thenReturn(10);
		when(Constants.getDefaultSortDirection()).thenReturn(Direction.ASC);
		
		FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
		initiliazer.setup(flightSearchCriteria);
		
		assertEquals(0, flightSearchCriteria.getPageNumber().intValue());
		assertEquals(10, flightSearchCriteria.getPageSize().intValue());
		assertEquals("to", flightSearchCriteria.getSortBy());
		assertEquals(Direction.ASC, flightSearchCriteria.getDirection());
	}
}