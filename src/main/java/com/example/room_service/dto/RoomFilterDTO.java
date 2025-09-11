package com.example.room_service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.room_service.domain.enumeration.GenderPreference;
import com.example.room_service.domain.enumeration.PropertyType;
import com.example.room_service.domain.enumeration.RoomType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data

public class RoomFilterDTO {
	@Schema(description = "Exact match for room name", example ="Cozy Studio Apartment")
	private String name;


	@Schema(description = "Exact match for floor number", example ="3")
	private Integer floor;

	@Schema(description = "Price filter with operator (lt, lte, gt, gte, eq)", example ="1200")
	private Double price;

	@Schema(description = "price operator lt, lte, gt, gte, eq", example ="lt")
	private  String priceOp;

	@Schema(description = "minimum price for range search", example ="200")
	private Double priceMin;

	@Schema(description = "maximum price for rang search", example ="1300")
	private Double priceMax;

	@Schema(description = "minimum room size in square meters", example ="20")
	private Double roomSizeMin;

	@Schema(description = "maximum room size in square meters", example ="50")
	private Double roomSizeMax;


	@Schema(description = "City where the room is located", example ="Kohkong")
	private String city ;

	@Schema(description = "District where the room is located", example ="50")
	private String district;

	@Schema(description = "Has fan", example ="true")
	private Boolean hasFan;

	@Schema(description = "has Air-Conditioner", example ="true")
	private Boolean hasAirConditioner;

	@Schema(description = "Has parking", example ="true")
	private Boolean hasParking;

	@Schema(description = "Has private bathroom", example ="true")
	private Boolean hasPrivateBathroom;

	@Schema(description = "has balcony", example ="true")
	private Boolean hasBalcony;

	@Schema(description = "has kitchen", example ="true")
	private Boolean hasKitchen;

	@Schema(description = "has fridge", example ="false")
	private Boolean hasFridge;

	@Schema(description = "has washing machine", example ="true")
	private Boolean hasWashingMachine ;

	@Schema(description = "has TV", example ="true")
	private Boolean hasTV ;

	@Schema(description = "has WIFI", example ="true")
	private Double hasWiFi;

	@Schema(description = "has Elevator", example ="true")
	private Boolean hasElevator;

	@Schema(description = "Pet friendly", example ="true")
	private Boolean isPetFriendly;

	@Schema(description = "Smoking Allowed", example ="false")
	private Boolean isSmokingAllowed;

	@Schema(description = "Share Room", example ="true")
	private Boolean isSharedRoom;

	@Schema(description = "Gender Preference for tenant", example ="FEMALE")
	private GenderPreference genderPreference;

	@Schema(description = "Room type", example ="STUDIO")
	private RoomType roomType;

	@Schema(description = "Property type", example ="APARTMENT")
	private PropertyType propertyType ;

	@Schema(description = "Utility included in price", example ="true")
	private Boolean isUtilityIncluded;

	@Schema(description = "Deposit Required", example ="false")
	private Boolean depositRequired;

	@Schema(description = "Minimum stay in months (>= filter)", example ="6")
	private Integer minStayMonth;

	@Schema(description = "Maximum occupants allowed (<= filter)", example ="4")
	private Integer maxOccupants;

	@Schema(description = "Available from date (>= filter)", example ="2025-09-10T14:30:00")
	private LocalDateTime availableFrom ;

	@Schema(description = "Available to date (<= filter)", example ="2025-10-10T14:30:00")
	private LocalDateTime availableTo;

	@Schema(description = "Nearby Landmark (contains match)", example ="[\\\"Central Park\\\", \\\"City Mall\\\", \\\"Riverside Station\\\"]")
	private  List<String> nearbyLandmarks;

	@Schema(description = "Listing is Verified", example ="true")
	private  Boolean verifiedListing;

	@Schema(description = "Sort By field name (ex: name, floor, price, createdAt)", example ="")
	private  String sortBy;

	@Schema(description = "Sort direction: asc or desc", example ="asc")
	private  String direction;

	@Schema(description = "Page number (for pagination)", example ="2")
	private  Integer page;

	@Schema(description = "Page size (for pagination)", example ="20")
	private  Integer size;

	@Schema(description = "Longitude for geo search", example ="104.911")
	private  Double nearLng;

	@Schema(description = "Longitude for geo search ", example ="11.5566")
	private  Double  nearLat;

	@Schema(description = "Max distance in meters for geo search", example ="5000")
	private  Double maxDistanceMeters;







}
