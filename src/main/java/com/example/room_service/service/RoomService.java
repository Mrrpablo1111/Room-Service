package com.example.room_service.service;

import com.example.room_service.dto.RoomDTO;

import reactor.core.publisher.Mono;

public interface RoomService {
	Mono<RoomDTO> createRoom(RoomDTO roomDTO);
}
