package com.example.room_service.mapper;

import org.springframework.stereotype.Component;

import com.example.room_service.domain.Room;
import com.example.room_service.dto.RoomDTO;

@Component
public class RoomMapper{
	//DTO toEntity
	public Room toRoom(RoomDTO roomDTO) {
		Room room = new Room();
		room.setAttributes(roomDTO.getAttributes());
		room.setName(roomDTO.getName());
		return room;	
	}
	
	//Entity -> DTO
	
	public RoomDTO toRoomDTO(Room room) {
		RoomDTO dto = new RoomDTO();
		dto.setAttributes(room.getAttributes());
		dto.setName(room.getName());
		return dto;
	}
	
	
}
