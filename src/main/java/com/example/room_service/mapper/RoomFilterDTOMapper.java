package com.example.room_service.mapper;

import java.util.Map;

import com.example.room_service.dto.RoomFilterDTO;

public final class RoomFilterDTOMapper {
	private RoomFilterDTOMapper() {
		throw new UnsupportedOperationException("Utility class");
	}
	public static RoomFilterDTO toRoomFilterDTO(Map<String, String> params) {
		RoomFilterDTO dto= new RoomFilterDTO();
		if(params.containsKey("name")) {
			dto.setName(params.get("name"));
		}
		if(params.containsKey("floor")) {
			dto.setFloor(Integer.parseInt(params.get("floor")));
		}
		return dto;
	}
}
