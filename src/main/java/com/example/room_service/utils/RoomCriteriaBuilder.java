package com.example.room_service.utils;

import static com.example.room_service.utils.RoomConstants.ALLOWED_SORT__FIELDS;
import static com.example.room_service.utils.RoomConstants.FIELD_AVAILABLE_FROM;
import static com.example.room_service.utils.RoomConstants.FIELD_AVAILABLE_TO;
import static com.example.room_service.utils.RoomConstants.FIELD_FLOOR;
import static com.example.room_service.utils.RoomConstants.FIELD_GENDER_PREFERENCE;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_AIR_CONDITIONER;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_BALCONY;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_ELEVATOR;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_FAN;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_FRIDGE;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_KITCHEN;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_PARKING;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_PRIVATE_BATHROOM;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_TV;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_WASHING_MACHINE;
import static com.example.room_service.utils.RoomConstants.FIELD_HAS_WIFI;
import static com.example.room_service.utils.RoomConstants.FIELD_IS_PET_FRIENDLY;
import static com.example.room_service.utils.RoomConstants.FIELD_IS_SHARED_ROOM;
import static com.example.room_service.utils.RoomConstants.FIELD_IS_SMOKING_ALLOWED;
import static com.example.room_service.utils.RoomConstants.FIELD_LOCATION_CITY;
import static com.example.room_service.utils.RoomConstants.FIELD_LOCATION_DISTRICT;
import static com.example.room_service.utils.RoomConstants.FIELD_MAX_OCCUPANTS;
import static com.example.room_service.utils.RoomConstants.FIELD_MIN_STAY_MONTHS; 
import static com.example.room_service.utils.RoomConstants.FIELD_NAME;
import static com.example.room_service.utils.RoomConstants.FIELD_NEARBY_LANDMARKS;
import static com.example.room_service.utils.RoomConstants.FIELD_PRICE;
import static com.example.room_service.utils.RoomConstants.FIELD_PROPERTY_TYPE;
import static com.example.room_service.utils.RoomConstants.FIELD_ROOM_SIZE;
import static com.example.room_service.utils.RoomConstants.FIELD_ROOM_TYPE;
import static com.example.room_service.utils.RoomConstants.FIELD_VERIFIED_LISTING;
import static com.example.room_service.utils.RoomConstants.OP_EQ;
import static com.example.room_service.utils.RoomConstants.OP_GT;
import static com.example.room_service.utils.RoomConstants.OP_GTE;
import static com.example.room_service.utils.RoomConstants.OP_LT;
import static com.example.room_service.utils.RoomConstants.OP_LTE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import com.example.room_service.dto.RoomFilterDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class RoomCriteriaBuilder {

	private RoomCriteriaBuilder() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static Criteria build(RoomFilterDTO filter) {

		List<Criteria> criterias = new ArrayList<>();

		if (Objects.nonNull(filter.getName())) {
			criterias.add(Criteria.where(FIELD_NAME).is(filter.getName()));
		}
		if (Objects.nonNull(filter.getFloor())) {
			criterias.add(Criteria.where(FIELD_FLOOR).is(filter.getFloor()));
		}
		if (Objects.nonNull(filter.getRoomSizeMin()) || Objects.nonNull(filter.getRoomSizeMax())) {
			Criteria c = Criteria.where(FIELD_ROOM_SIZE);
			if (Objects.nonNull(filter.getRoomSizeMin())) {
				c.gte(filter.getRoomSizeMin());
			}
			if (Objects.nonNull(filter.getRoomSizeMax())) {
				c.lte(filter.getRoomSizeMax());
			}
			criterias.add(c);
		}
		if (Objects.nonNull(filter.getCity())) {
			criterias.add(Criteria.where(FIELD_LOCATION_CITY).is(filter.getCity()));
		}
		if (Objects.nonNull(filter.getDistrict())) {
			criterias.add(Criteria.where(FIELD_LOCATION_DISTRICT).is(filter.getDistrict()));
		}
		if (Objects.nonNull(filter.getPrice()) && Objects.nonNull(filter.getPriceOp())) {
			switch (filter.getPriceOp()) {
			case OP_LT -> criterias.add(Criteria.where(FIELD_PRICE).lt(filter.getPrice()));
			case OP_LTE -> criterias.add(Criteria.where(FIELD_PRICE).lte(filter.getPrice()));
			case OP_GT -> criterias.add(Criteria.where(FIELD_PRICE).gt(filter.getPrice()));
			case OP_GTE -> criterias.add(Criteria.where(FIELD_PRICE).gte(filter.getPrice()));
			case OP_EQ -> criterias.add(Criteria.where(FIELD_PRICE).is(filter.getPrice()));
			default -> log.warn("Invalid price operator: {}", filter.getPriceOp());
			}
		} else if (Objects.nonNull(filter.getPriceMin()) && Objects.nonNull(filter.getPriceMax())) {
			criterias.add(Criteria.where(FIELD_PRICE).gte(filter.getPriceMin()).lte(filter.getPriceMax()));
		}

		// Boolean
		if (Objects.nonNull(filter.getHasFan())) {
			criterias.add(Criteria.where(FIELD_HAS_FAN).is(filter.getHasFan()));
		}
		if (Objects.nonNull(filter.getHasAirConditioner())) {
			criterias.add(Criteria.where(FIELD_HAS_AIR_CONDITIONER).is(filter.getHasAirConditioner()));
		}
		if (Objects.nonNull(filter.getHasParking())) {
			criterias.add(Criteria.where(FIELD_HAS_PARKING).is(filter.getHasParking()));
		}
		if (Objects.nonNull(filter.getHasPrivateBathroom())) {
			criterias.add(Criteria.where(FIELD_HAS_PRIVATE_BATHROOM).is(filter.getHasPrivateBathroom()));
		}
		if (Objects.nonNull(filter.getHasKitchen())) {
			criterias.add(Criteria.where(FIELD_HAS_KITCHEN).is(filter.getHasKitchen()));
		}
		if (Objects.nonNull(filter.getHasFridge())) {
			criterias.add(Criteria.where(FIELD_HAS_FRIDGE).is(filter.getHasFridge()));
		}
		if (Objects.nonNull(filter.getHasWiFi())) {
			criterias.add(Criteria.where(FIELD_HAS_WIFI).is(filter.getHasWiFi()));
		}
		if (Objects.nonNull(filter.getIsSharedRoom())) {
			criterias.add(Criteria.where(FIELD_IS_SHARED_ROOM).is(filter.getIsSharedRoom()));
		}
		if (Objects.nonNull(filter.getIsPetFriendly())) {
			criterias.add(Criteria.where(FIELD_IS_PET_FRIENDLY).is(filter.getIsPetFriendly()));
		}
		if (Objects.nonNull(filter.getIsSmokingAllowed())) {
			criterias.add(Criteria.where(FIELD_IS_SMOKING_ALLOWED));
		}
		if (Objects.nonNull(filter.getHasElevator())) {
			criterias.add(Criteria.where(FIELD_HAS_ELEVATOR).is(filter.getHasElevator()));
		}
		if (Objects.nonNull(filter.getHasBalcony())) {
			criterias.add(Criteria.where(FIELD_HAS_BALCONY).is(filter.getHasBalcony()));
		}
		if (Objects.nonNull(filter.getHasWashingMachine())) {
			criterias.add(Criteria.where(FIELD_HAS_WASHING_MACHINE).is(filter.getHasWashingMachine()));
		}

		if (Objects.nonNull(filter.getHasTV())) {
			criterias.add(Criteria.where(FIELD_HAS_TV).is(filter.getHasTV()));
		}

		// Enum field

		if (Objects.nonNull(filter.getRoomType())) {
			criterias.add(Criteria.where(FIELD_ROOM_TYPE).is(filter.getRoomType()));
		}
		if (Objects.nonNull(filter.getPropertyType())) {
			criterias.add(Criteria.where(FIELD_PROPERTY_TYPE).is(filter.getPropertyType()));
		}
		if (Objects.nonNull(filter.getGenderPreference())) {
			criterias.add(Criteria.where(FIELD_GENDER_PREFERENCE).is(filter.getGenderPreference()));
		}

		// Verified
		if (Objects.nonNull(filter.getVerifiedListing())) {
			criterias.add(Criteria.where(FIELD_VERIFIED_LISTING).is(filter.getVerifiedListing()));
		}

		// Data RangeS
		if (Objects.nonNull(filter.getAvailableFrom())) {
			criterias.add(Criteria.where(FIELD_AVAILABLE_FROM).is(filter.getAvailableFrom()));
		}

		if (Objects.nonNull(filter.getAvailableTo())) {
			criterias.add(Criteria.where(FIELD_AVAILABLE_TO).is(filter.getAvailableTo()));
		}

		// minStayMonths
		if (Objects.nonNull(filter.getMinStayMonth())) {
			criterias.add(Criteria.where(FIELD_MIN_STAY_MONTHS).is(filter.getMinStayMonth()));
		}

		// maxOccupants
		if (Objects.nonNull(filter.getMaxOccupants())) {
			criterias.add(Criteria.where(FIELD_MAX_OCCUPANTS).is(filter.getMaxOccupants()));
		}

		// Nearby landmarks
		if (filter.getNearbyLandmarks() != null && !filter.getNearbyLandmarks().isEmpty()) {
			criterias.add(Criteria.where(FIELD_NEARBY_LANDMARKS).in(filter.getNearbyLandmarks()));
		}

		return criterias.isEmpty()

				? new Criteria()
				: new Criteria().andOperator(criterias.toArray(new Criteria[0]));
	}

	public static Sort sort(RoomFilterDTO filter) throws IllegalAccessException {
		Sort.Direction direction = "desc".equalsIgnoreCase(filter.getDirection()) ? Sort.Direction.DESC
				: Sort.Direction.ASC;

		String sortField = Objects.nonNull(filter.getSortBy()) ? filter.getSortBy() : FIELD_NAME;
		if (!ALLOWED_SORT__FIELDS.contains(sortField)) {
			throw new IllegalAccessException("Invalid sort field: " + sortField);
		}
		return Sort.by(direction, sortField);
	}
}