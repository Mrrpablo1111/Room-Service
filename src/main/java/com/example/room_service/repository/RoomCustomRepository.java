package com.example.room_service.repository;

import com.example.room_service.domain.Room;

import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public interface RoomCustomRepository {
    Flux<Room> findByFilter(Query query);

}
