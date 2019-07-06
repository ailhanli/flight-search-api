package com.ailhanli.flightapi.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Constants {

	@Value("${page.size}")
	private Integer defaultPageSize;

	@Value("${page.number}")
	private Integer defaultPageNumber;

	@Value("${sort.direction}")
	private Direction defaultSortDirection;
}
