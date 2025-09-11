package com.example.room_service.repository;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.room_service.domain.Room;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
@AllArgsConstructor
public class RoomCustomRepositoryImpl implements RoomCustomRepository {

	private final ReactiveMongoTemplate mongoTemplate;

	@Override
	public Flux<Room> findByFilter(Query query) {

		return mongoTemplate.find(query, Room.class);
	}

	@Override
	public Mono<Long> countByFilter(Query query) {
		return mongoTemplate.count(query, Room.class);
	}



}
