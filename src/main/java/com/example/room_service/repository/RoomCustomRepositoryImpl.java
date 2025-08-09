package com.example.room_service.repository;

import com.example.room_service.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class RoomCustomRepositoryImpl implements RoomCustomRepository{
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Room> findByFilter(Query query) {
        return mongoTemplate.find(query, Room.class);
    }
}
