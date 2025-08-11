package com.example.room_service.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.room_service.domain.Room;

import reactor.core.publisher.Flux;

public interface RoomRepository extends ReactiveMongoRepository<Room, String> {
	
	Flux<Room>findByNameContainingIgnoreCase(String name);
	
	
}
