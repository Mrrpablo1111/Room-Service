package com.example.room_service.service;

import com.example.room_service.dto.PageDTO;
import com.example.room_service.dto.RoomDTO;
import com.example.room_service.dto.RoomFilterDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomService {
	Mono<RoomDTO> createRoom(RoomDTO roomDTO);
	Mono<RoomDTO> getRoomById(String id);
	Mono<RoomDTO> updateRoom(String id, RoomDTO roomDTO);
	Mono<Void> deleteRoom(String id);
	Flux<RoomDTO> getRoomByFilter(RoomFilterDTO filterDTO);
	Mono<PageDTO<RoomDTO>> getRoomByFilterPaginate(RoomFilterDTO filterDTO) throws IllegalAccessException;
}
