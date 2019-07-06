package com.ailhanli.flightapi.dao;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ailhanli.flightapi.beans.FlightSearchCriteria;
import com.ailhanli.flightapi.dao.model.Flight;
import com.ailhanli.flightapi.dao.util.QueryBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

	private final ReactiveMongoTemplate mongoTemplate;
	
	public FlightRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Flux<Flight> queryFlight(FlightSearchCriteria flightSearchCriteria) {

		Integer pageNumber = flightSearchCriteria.getPageNumber();
		Integer pageSize = flightSearchCriteria.getPageSize();
		Direction direction = flightSearchCriteria.getDirection();
		String sortBy = flightSearchCriteria.getSortBy();
		
		Query query = QueryBuilder
				.builder()
				.criteria(flightSearchCriteria.getCriteria())
				.pageable(PageRequest.of(pageNumber, pageSize))
				.sort(new Sort(direction, sortBy))
				.build()
				.getQuery();

		return mongoTemplate.find(query, Flight.class);

	}

	@Override
	public Mono<Long> queryFlightSize(Map<String, Object> flightSearchCriteria) {
		Query query = QueryBuilder
				.builder()
				.criteria(flightSearchCriteria)
				.build()
				.getQuery();

		return mongoTemplate.count(query, Flight.class);
	}

	@Override
	public void removeAllFlights() {
		mongoTemplate
		.remove(new Query(), Flight.class)
		.subscribe();
	}

	@Override
	public void saveFlight(Flight flight) {
		mongoTemplate
		.insert(flight)
		.subscribe();
	}
}