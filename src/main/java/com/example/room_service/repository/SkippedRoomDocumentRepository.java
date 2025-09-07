package com.example.room_service.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.room_service.domain.SkippedRoomDocument;

public interface SkippedRoomDocumentRepository extends ReactiveMongoRepository<SkippedRoomDocument, String>{

}
