package com.ailhanli.flightapi.validator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ailhanli.flightapi.beans.FlightSearchCriteriaDTO;
import com.ailhanli.flightapi.dao.model.Flight;
import com.ailhanli.flightapi.exception.InvalidRequestException;

@Component
public class FlightSearchCriteriaValidator {
	private List<String> validFieldNames;

	private String commaSeparatedValidFieldNames;

	public FlightSearchCriteriaValidator() {
		validFieldNames = Arrays.stream(Flight.class.getDeclaredFields()).map(Field::getName)
				.collect(Collectors.toList());
		validFieldNames.add("_id");
		commaSeparatedValidFieldNames = validFieldNames.stream().reduce(" ", (a, b) -> a + "," + b);
	}

	public void validateFlightSearchCriteria(FlightSearchCriteriaDTO flightSearchCriteria) {

		validateInputObject(flightSearchCriteria);
		if (flightSearchCriteria != null) {
			validatePageNumberSize(flightSearchCriteria);
			validateSortBy(flightSearchCriteria);
			validateCriteria(flightSearchCriteria.getCriteria());
		}
	}

	public void validateInputObject(FlightSearchCriteriaDTO flightSearchCriteria) {
		if (flightSearchCriteria == null) {
			throw new InvalidRequestException("flightSearchCriteria can't be null");
		}
	}

	public void validatePageNumberSize(FlightSearchCriteriaDTO flightSearchCriteria) {
		Integer pageNumber = flightSearchCriteria.getPageNumber();
		Integer pageSize = flightSearchCriteria.getPageSize();

		if ((pageNumber != null && pageNumber < 0) || (pageSize != null && pageSize <= 0)) {
			 throw new InvalidRequestException("pageNumber/pageSize must be greather than 0");
		}
	}

	public void  validateSortBy(FlightSearchCriteriaDTO flightSearchCriteria) {
		String sortBy = flightSearchCriteria.getSortBy();
		if (sortBy != null && !validFieldNames.contains(sortBy)) {
			throw new InvalidRequestException("SortBy key is invalid. SortBy key must be one of " + commaSeparatedValidFieldNames + " them");
		}
	}

	public void validateCriteria(Map<String, Object> flightSeachCriteria) {
		long numberOfInvalidJsonKeys = flightSeachCriteria
				.entrySet()
				.stream()
				.map(s->s.getKey())
				.filter(s -> validFieldNames.contains(s))
				.count();
		if (numberOfInvalidJsonKeys == 0) {
			throw new InvalidRequestException("Some of search 'criteria' keys are invalid. Search 'criteria' keys must be in "
					+ commaSeparatedValidFieldNames + " list");
		}
	}
}