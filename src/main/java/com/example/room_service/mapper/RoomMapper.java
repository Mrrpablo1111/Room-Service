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
	void updateRoomFromDTO(RoomDTO roomDTO,@MappingTarget Room entity);
	
}
