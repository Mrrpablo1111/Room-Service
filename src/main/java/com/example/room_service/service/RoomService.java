package com.example.room_service.service;

import com.example.room_service.dto.RoomDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomService {
	Mono<RoomDTO> createRoom(RoomDTO roomDTO);
	Mono<RoomDTO> getRoomById(String id);
	Mono<RoomDTO> updateRoom(String id, RoomDTO roomDTO);
	Mono<Void> deleteRoom(String id);
	
	//Study Purpose Only
	Flux<RoomDTO>searchRoomByName(String name) ;
}
