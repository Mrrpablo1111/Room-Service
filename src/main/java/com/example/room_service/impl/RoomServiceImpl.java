package com.example.room_service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.room_service.domain.Room;
import com.example.room_service.dto.RoomDTO;
import com.example.room_service.mapper.RoomMapper;
import com.example.room_service.repository.RoomRepository;
import com.example.room_service.service.RoomService;

import reactor.core.publisher.Mono;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Override
	public Mono<RoomDTO> createRoom(RoomDTO roomDTO) {
		Room room = roomMapper.toRoom(roomDTO);
		Mono<RoomDTO> mono = roomRepository.save(room)
			.map(r -> roomMapper.toRoomDTO(r));
		return mono;
	}
	

}
