package com.example.roomservice.service.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import com.example.room_service.dto.RoomFilterDTO;
import com.example.room_service.utils.RoomCriteriaBuilder;


public class RoomCriteriaBuilderTest {
	
	//mimic Constants
	private static final String FIELD_NAME = "name";
	private static final String ATT = "attributes.";
	
	@Test
	void shouldReturnEmptyCriteria_whenNoFilterProvided() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO(); 
		//when
		Criteria criteria = RoomCriteriaBuilder.build(filter);
		//then
		assertThat(criteria.getCriteriaObject().isEmpty(), is(true));
		}
	
	@Test
	void shouldAddName_whenNameProvided() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setName("Small Room");
		//when
		Criteria criteria = RoomCriteriaBuilder.build(filter);
		String json = criteria.getCriteriaObject().toJson();
		//then
		assertThat(json, containsString("name"));
		assertThat(json, containsString("Small Room"));
		
	}
	
	@Test 
	void shouldAddFloor_whenFloorProvided() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setFloor(2);
		//when 
		Criteria criteria = RoomCriteriaBuilder.build(filter);
		String json = criteria.getCriteriaObject().toJson();
		//then
		assertThat(json, containsString("floor"));
		assertThat(json, containsString("2"));
		
	}
	
	@Test
	void shouldAddPrice_withAllOparation() {
		Map<String, String> opToMongo = Map.of(
				"lt","$lt",
				"lte","$lte",
				"gt", "$gt",
				"gte", "$gte"
				
				);
		for(Map.Entry<String, String> entry : opToMongo.entrySet()) {
			//given
			RoomFilterDTO filter = new RoomFilterDTO();
			filter.setPrice(50d);
			filter.setPriceOp(entry.getKey());
			//when
			Criteria criteria = RoomCriteriaBuilder.build(filter);
			String json = criteria.getCriteriaObject().toJson();
			//then
			assertThat(json, containsString("price"));
			assertThat(json, containsString(entry.getValue()));
		}
	}
	
	@Test
	void shouldAddPrice_withEqOperation() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setPrice(2d);
		filter.setPriceOp("eq");
		//when
		Criteria criteria = RoomCriteriaBuilder.build(filter);
		String json = criteria.getCriteriaObject().toJson();
		//then 
		assertThat(json, containsString("price"));
		assertThat(json, containsString("2.0"));
		
	}
	@Test
	void shouldNotAddPrice_withInvalidOperation() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setPrice(2d);
		filter.setPriceOp("Invalid-Operation");
		//when 
		Criteria criteria = RoomCriteriaBuilder.build(filter);
		String json = criteria.getCriteriaObject().toJson();
		//then
		assertThat(json, is("{}"));
	}
	
	@Test
	void shouldAddPrice_withRange() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setPriceMin(2d);
		filter.setPriceMax(5d);
		//when
		Criteria criteria = RoomCriteriaBuilder.build(filter);
		String json = criteria.getCriteriaObject().toJson();
		//then
		assertThat(json, containsString("price"));
	    assertThat(json, containsString("$gte"));
	    assertThat(json, containsString("2.0"));
	    assertThat(json, containsString("$lte"));
	    assertThat(json, containsString("5.0"));
	}
	
	@Test
	void shouldReturnAscSort_whenDirectionNotDesc() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setDirection("asc");
		filter.setSortBy(null);
		//when
		Sort sort = RoomCriteriaBuilder.sort(filter);
		//then
		assertEquals(Sort.Direction.ASC, sort.getOrderFor(FIELD_NAME).getDirection());
	}
	
	@Test
	void shouldReturnDesc_whenDirectionNotDesc() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setDirection("desc");
		filter.setSortBy(FIELD_NAME);
		//when
		Sort sort = RoomCriteriaBuilder.sort(filter);
		//then
		assertEquals(Sort.Direction.DESC, sort.getOrderFor(FIELD_NAME).getDirection());
	}
	
	
	
	@Test
	void shouldThrowException_whenSortFieldNotAllowed() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setDirection("asc");
		filter.setSortBy("invalid");
		//when
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> RoomCriteriaBuilder.sort(filter));
		//then
		assertTrue(ex.getMessage().contains("Invalid sort field"));
	}
	
	@Test
	void shouldReturnPrefixedField_whenSortFieldIsNotFieldName() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setDirection("asc");
		filter.setSortBy("floor");
		//when
		Sort sort = RoomCriteriaBuilder.sort(filter);
		//then
		assertNotNull(sort.getOrderFor(ATT + "floor"));
		
	}
	
	@Test
	void shouldReturnPrefixedField_whenSortFieldIsFieldName() {
		//given
		RoomFilterDTO filter = new RoomFilterDTO();
		filter.setDirection("asc");
		filter.setSortBy(FIELD_NAME);
		//when
		Sort sort = RoomCriteriaBuilder.sort(filter);
		//then
		assertNotNull(sort.getOrderFor(FIELD_NAME));
		assertNull(sort.getOrderFor(ATT + FIELD_NAME));
	}

	
	

}
