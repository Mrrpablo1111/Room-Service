package com.example.room_service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import static com.example.room_service.utils.RoomConstants.*;

import com.example.room_service.dto.RoomFilterDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomCriteriaBuilder {
	public static Criteria build(RoomFilterDTO filter) {

		// we need to add to array list
		List<Criteria> criterias = new ArrayList<>();
		if (Objects.nonNull(filter.getName())) {
			criterias.add(Criteria.where(FIELD_NAME).is(filter.getName()));
		}

		if (Objects.nonNull(filter.getFloor())) {
			criterias.add(Criteria.where(FIELD_FLOOR).is(filter.getFloor()));
		}

		if (Objects.nonNull(filter.getPrice()) && Objects.nonNull(filter.getPriceOp())) {
			switch (filter.getPriceOp()) {
			case OP_LT -> criterias.add(Criteria.where(FIELD_PRICE).lt(filter.getPrice()));
			case OP_LTE -> criterias.add(Criteria.where(FIELD_PRICE).lte(filter.getPrice()));
			case OP_GT -> criterias.add(Criteria.where(FIELD_PRICE).gt(filter.getPrice()));
			case OP_GTE -> criterias.add(Criteria.where(FIELD_PRICE).gte(filter.getPrice()));
			case OP_EQ -> criterias.add(Criteria.where(FIELD_PRICE).is(filter.getPrice()));
			default -> log.warn("Invalid priceOperation: {}", filter.getPriceOp());
			}
		} else if (Objects.nonNull(filter.getPriceMin()) && Objects.nonNull(filter.getPriceMax())) {
			criterias.add(Criteria.where(FIELD_PRICE).gte(filter.getPriceMin()).lte(filter.getPriceMax()));
		}

		return criterias.isEmpty() ? new Criteria() : new Criteria().andOperator(criterias.toArray(new Criteria[0]));

		// This is not flexible code
//		Criteria criteria = new Criteria();
//		
//		if(Objects.nonNull(filter.getName())) {
//			criteria.and(FIELD_NAME).is(filter.getName());
//		}
//		if(Objects.nonNull(filter.getFloor())) {
//			criteria.and(FIELD_FLOOR).is(filter.getFloor());
//		}
//		
//		if(Objects.nonNull(filter.getPrice()) && Objects.nonNull(filter.getPriceOp())) {
//			switch(filter.getPriceOp()) {
//			case OP_LT  -> criteria.and(FIELD_PRICE).lt(filter.getPrice());
//			case OP_LTE -> criteria.and(FIELD_PRICE).lte(filter.getPrice());
//			case OP_GT -> criteria.and(FIELD_PRICE).gt(filter.getPrice());
//			case OP_GTE -> criteria.and(FIELD_PRICE).gte(filter.getPrice());
//			case OP_EQ -> criteria.and(FIELD_PRICE).is(filter.getPrice());
//			}
//		}else if(Objects.nonNull(filter.getPriceMin()) && Objects.nonNull(filter.getPriceMax())) {
//			criteria.and(RoomConstants.FIELD_PRICE).gte(filter.getPriceMin()).lte(filter.getPriceMax());
//		}
////		Query query = new Query(criteria);
//				//.skip((long) filter.getPage() * filter.getSize())
//				//.limit(filter.getSize());
//		return criteria;
	}

	public static Sort sort(RoomFilterDTO filter) {

		// sort direction
		//Sort.Direction direction = Sort.Direction.ASC;
		//if ("desc".equalsIgnoreCase(filter.getDirection())) {
		//	direction = Sort.Direction.DESC;
		//}
		
		Sort.Direction direction = "desc".equalsIgnoreCase(filter.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;

		// sort field
		String sortField = Objects.nonNull(filter.getSortBy()) ? filter.getSortBy() : FIELD_NAME;
		if (!ALLOWED_SORT_FIELD.contains(sortField)) {
			throw new IllegalArgumentException("Invalid sort fields: " + sortField);
		}

		if (!sortField.equals(FIELD_NAME)) {
			sortField = ATT + sortField;
		}

		return Sort.by(direction, sortField);
	}

}
