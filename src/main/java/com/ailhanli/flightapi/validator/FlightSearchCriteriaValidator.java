package com.ailhanli.flightapi.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;
import com.ailhanli.flightapi.dao.model.Flight;

@Component
public class FlightSearchCriteriaValidator {

	private List<String> validFieldNames;

	private String commaSeparatedValidFieldNames;

	public FlightSearchCriteriaValidator() {
		validFieldNames = Arrays.stream(Flight.class.getDeclaredFields()).map(Field::getName)
				.collect(Collectors.toList());

		commaSeparatedValidFieldNames = validFieldNames.stream().reduce(" ", (a, b) -> a + "," + b);
	}

	public List<String> validateFlightSearchCriteria(FlightSearchCriteria flightSearchCriteria) {

		List<String> errors = new ArrayList<>();

		validateInputObject(flightSearchCriteria).ifPresent(errors::add);
		if (flightSearchCriteria != null) {
			validatePageNumberSize(flightSearchCriteria).ifPresent(errors::add);
			validateSortBy(flightSearchCriteria).ifPresent(errors::add);
			validateCriteria(flightSearchCriteria.getCriteria()).ifPresent(errors::add);
		}		
		return errors;
	}

	public Optional<String> validateInputObject(FlightSearchCriteria flightSearchCriteria) {
		if (flightSearchCriteria == null) {
			return Optional.of("flightSearchCriteria can't be null");
		}
		return Optional.empty();
	}

	public Optional<String> validatePageNumberSize(FlightSearchCriteria flightSearchCriteria) {
		Integer pageNumber = flightSearchCriteria.getPageNumber();
		Integer pageSize = flightSearchCriteria.getPageSize();

		if ((pageNumber != null && pageNumber < 0) || (pageSize != null && pageSize <= 0)) {
			return Optional.of("pageNumber/pageSize must be greather than 0");
		}
		return Optional.empty();
	}

	public Optional<String> validateSortBy(FlightSearchCriteria flightSearchCriteria) {
		String sortBy = flightSearchCriteria.getSortBy();
		if (sortBy != null && !validFieldNames.contains(sortBy)) {
			return Optional
					.of("SortBy key is invalid. SortBy key must be one of " + commaSeparatedValidFieldNames + " them");
		}
		return Optional.empty();
	}

	public Optional<String> validateCriteria(Map<String, Object> flightSeachCriteria) {
		if (flightSeachCriteria == null) {
			return Optional.of("criteria cant be null");
		}
		long numberOfInvalidJsonKeys = flightSeachCriteria.entrySet().stream()
				.filter(s -> !validFieldNames.contains(s.getKey())).count();
		if (numberOfInvalidJsonKeys > 0) {
			return Optional.of("Some of search 'criteria' keys are invalid. Search 'criteria' keys must be in "
					+ commaSeparatedValidFieldNames + " list");
		}
		return Optional.empty();
	}
}