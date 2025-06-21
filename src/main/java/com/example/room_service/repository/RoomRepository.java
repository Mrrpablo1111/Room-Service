package com.example.room_service.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.room_service.domain.Room;

public interface RoomRepository extends ReactiveMongoRepository<Room, String> {
	

}
