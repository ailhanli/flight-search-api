package com.ailhanli.flightapi.dao.util;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import lombok.Builder;

@Builder
public class QueryBuilder {

	private Map<String, Object> criteria;

	private Pageable pageable;

	private Sort sort;

	public Query getQuery() {
		Query query = new Query();

		if (criteria != null) {
			for (Entry<String, Object> queryEntry : criteria.entrySet()) {
				query
				.addCriteria(
						where(queryEntry.getKey())
						.is(queryEntry.getValue())
				);
			}
		}

		if (pageable != null) {
			query.with(pageable);
		}

		if (sort != null) {
			query.with(sort);
		}
		return query;
	}
}