package com.example.room_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.room_service.domain.Room;
import com.example.room_service.dto.RoomDTO;


@Mapper(componentModel = "Spring")
public interface RoomMapper{
	Room toRoom(RoomDTO roomDTO); 
	RoomDTO toRoomDTO(Room room);
	
	@Mapping(target = "id", ignore = true)
	void updateRoomFromDTO(RoomDTO roomDTO,@MappingTarget Room Entity);
	//DTO toEntity
//	public Room toRoom(RoomDTO roomDTO) {
//		Room room = new Room();
//		room.setAttributes(roomDTO.getAttributes());
//		room.setName(roomDTO.getName());
//		return room;	
//	}
	
	//Entity -> DTO
	
//	public RoomDTO toRoomDTO(Room room) {
//		RoomDTO dto = new RoomDTO();
//		dto.setAttributes(room.getAttributes());
//		dto.setName(room.getName());
//		return dto;
//	}
	
	
}
