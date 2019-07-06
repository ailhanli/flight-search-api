package com.ailhanli.flightapi.validator;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;

@Component
public class FlightSearchCriteriaInitiliazer {

	private Constants constants;
	
	public FlightSearchCriteriaInitiliazer(Constants constants) {
		super();
		this.constants = constants;
	}

	public void setup(FlightSearchCriteria flightSearchCriteria) {
		Integer pageNumber = flightSearchCriteria.getPageNumber();
		Integer pageSize = flightSearchCriteria.getPageSize();

		if (pageNumber == null) {
			pageNumber = constants.getDefaultPageNumber();;
			flightSearchCriteria.setPageNumber(pageNumber);
		} else {
			// page starts from 0
			flightSearchCriteria.setPageNumber(flightSearchCriteria.getPageNumber() - 1);
		}

		if (pageSize == null) {
			pageSize = constants.getDefaultPageSize();
			flightSearchCriteria.setPageSize(pageSize);

		}
		Direction direction = flightSearchCriteria.getDirection();
		String sortBy = flightSearchCriteria.getSortBy();

		if (sortBy == null) {
			flightSearchCriteria.setSortBy("to");
		}

		if (direction == null) {
			direction = constants.getDefaultSortDirection();
			flightSearchCriteria.setDirection(direction);
		}
	}
}
