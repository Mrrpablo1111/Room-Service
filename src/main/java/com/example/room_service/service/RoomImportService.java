package com.example.room_service.service;

import org.springframework.http.codec.multipart.FilePart;

import com.example.room_service.dto.RoomImportSummaryDTO;

import reactor.core.publisher.Mono;

public interface RoomImportService {

	Mono<RoomImportSummaryDTO> importRooms(FilePart filePart);

}
