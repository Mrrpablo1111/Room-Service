package com.example.room_service.utils;

import java.util.Objects;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import static com.example.room_service.utils.RoomConstants.*;

import com.example.room_service.dto.RoomFilterDTO;

public class RoomCriteriaBuilder {
	public static Criteria build(RoomFilterDTO filter) {
		Criteria  criteria = new Criteria();
		
		if(Objects.nonNull(filter.getName())) {
			criteria.and(FIELD_NAME).is(filter.getName());
		}
		if(Objects.nonNull(filter.getFloor())) {
			criteria.and(FIELD_FLOOR).is(filter.getFloor());
		}
		
		if(Objects.nonNull(filter.getPrice()) && Objects.nonNull(filter.getPriceOp())) {
			switch(filter.getPriceOp()) {
			case OP_LT  -> criteria.and(FIELD_PRICE).lt(filter.getPrice());
			case OP_LTE -> criteria.and(FIELD_PRICE).lte(filter.getPrice());
			case OP_GT -> criteria.and(FIELD_PRICE).gt(filter.getPrice());
			case OP_GTE -> criteria.and(FIELD_PRICE).gte(filter.getPrice());
			case OP_EQ -> criteria.and(FIELD_PRICE).is(filter.getPrice());
			}
		}else if(Objects.nonNull(filter.getPriceMin()) && Objects.nonNull(filter.getPriceMax())) {
			criteria.and(RoomConstants.FIELD_PRICE).gte(filter.getPriceMin()).lte(filter.getPriceMax());
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
			if(!sortField.equals(FIELD_NAME)) {
				sortField = ATT + sortField; 
			}
		}
		
		return Sort.by(direction, sortField);
		
		
	}
	
}
