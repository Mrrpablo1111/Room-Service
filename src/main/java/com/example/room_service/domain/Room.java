package com.example.room_service.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.room_service.domain.enumeration.GenderPreference;
import com.example.room_service.domain.enumeration.PropertyType;
import com.example.room_service.domain.enumeration.RoomType;

import lombok.Data;

@Data
@Document
public class Room {
	@Id
	private String id;
	private String name;
	//private Map<String, Object> attributes = new HashMap<>();
	
	private Double price;
	private Integer floor;
	private Double roomSize;
	
	private Location location;
	
	private Boolean hasFan;
	private Boolean hasAireConditioner;
	private Boolean hasParking;
	private Boolean hasPrivateBathroom;
	private Boolean hasBalcony;
	private Boolean hasKitchen;
	private Boolean hasFridge;
	private Boolean hasWashingMachine;
	private Boolean hasTV;
	private Boolean hasWiFi;
	private Boolean hasElevator;
	
	private Integer maxOccupants;
	private Boolean isPetFriendly;
	private Boolean isSmookingAllowed;
	private Boolean isSharedRoom;
	private GenderPreference genderPreference;
	
	private RoomType roomType;
	private PropertyType propertyType;
	
	private Double distanceToCenter;
	private List<String> nearByLandmarks;
	
	private Boolean isUtilityIncluded;
	private Boolean depositRequired;
	private Integer minStayMonths;
	
	private Boolean hasPhotos;
	private Integer photoCount;
	private Boolean hasVideoTour;
	
	private Boolean verifiedListing;
	
	private LocalDateTime availableTime;
	private LocalDateTime availableTo;
	
	private LocalDateTime createdAt;
	private LocalDateTime lastUpdated;
	
	private Map<String, Object> extraAttributes = new HashMap<>();
	
	 
	
	
	
}
