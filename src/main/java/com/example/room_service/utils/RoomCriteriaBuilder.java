package com.example.room_service.utils;

import java.util.Objects;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.room_service.dto.RoomFilterDTO;

public class RoomCriteriaBuilder {
	public static Criteria build(RoomFilterDTO filter) {
		Criteria  criteria = new Criteria();
		
		if(Objects.nonNull(filter.getName())) {
			criteria.and("name").is(filter.getName());
		}
		if(Objects.nonNull(filter.getFloor())) {
			criteria.and("attributes.floor").is(filter.getFloor());
		}
		
		if(Objects.nonNull(filter.getPrice()) && Objects.nonNull(filter.getPriceOp())) {
			switch(filter.getPriceOp()) {
			case "lt"  -> criteria.and("attributes.price").lt(filter.getPrice());
			case "lte" -> criteria.and("attributes.price").lte(filter.getPrice());
			case "gt" -> criteria.and("attributes.price").gt(filter.getPrice());
			case "gte" -> criteria.and("attributes.price").gte(filter.getPrice());
			case "eq" -> criteria.and("attributes.price").is(filter.getPrice());
			}
		}else if(Objects.nonNull(filter.getPriceMin()) && Objects.nonNull(filter.getPriceMax())) {
			criteria.and("attributes.price").gte(filter.getPriceMin()).lte(filter.getPriceMax());
		}
//		Query query = new Query(criteria);
				//.skip((long) filter.getPage() * filter.getSize())
				//.limit(filter.getSize());
		return criteria;
	}
	
	public static Sort sort(RoomFilterDTO filter) {
		
		//sort direction
		Sort.Direction direction = Sort.Direction.ASC;
		if("desc".equalsIgnoreCase(filter.getDirection())) {
			direction = Sort.Direction.DESC;
		}
		
		//sort field
		String sortField = filter.getSortBy();
		if(!sortField.contains(".")) {
			if(!sortField.equals("name")) {
				sortField = "attributes." + sortField; 
			}
		}
		
		return Sort.by(direction, sortField);
		
		
	}
	
}
