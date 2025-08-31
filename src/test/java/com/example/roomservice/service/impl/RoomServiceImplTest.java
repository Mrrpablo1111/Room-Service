package com.example.roomservice.service.impl;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.room_service.domain.Room;
import com.example.room_service.dto.RoomDTO;
import com.example.room_service.impl.RoomServiceImpl;
import com.example.room_service.mapper.RoomMapper;
import com.example.room_service.repository.RoomCustomRepository;
import com.example.room_service.repository.RoomRepository;


import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {
	@Mock
	private RoomRepository roomRepository;
	@Mock
	private RoomMapper roomMapper;
	@Mock
	private RoomCustomRepository roomCustomRespository;
	
	@InjectMocks
	private RoomServiceImpl roomService;
	
	@Test
	void createRoom_success() {
		// given
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setName("SmallRoom");
		
		Room room = new Room();
		room.setName("SmallRoom");
		
		Room savedRoom = new Room();
		savedRoom.setId("123");
		savedRoom.setName("SmallRoom");
		
		// when 
		when(roomMapper.toRoom(roomDTO)).thenReturn(room); 
		when(roomRepository.save(room)).thenReturn(Mono.just(savedRoom));
		when(roomMapper.toRoomDTO(savedRoom)).thenReturn(roomDTO);
		 
		// then 
		
		StepVerifier.create(roomService.createRoom(roomDTO))
		.expectNext(roomDTO)
		.verifyComplete();
	}
	
	@Test
	void getRoomById_success() {
	    // given
	    String id = "123";

	    Room savedRoom = new Room();
	    savedRoom.setId(id);
	    savedRoom.setName("SmallRoom");

	    RoomDTO roomDTO = new RoomDTO();
	    roomDTO.setId(id);
	    roomDTO.setName("SmallRoom");

	    // when
	    when(roomRepository.findById(id)).thenReturn(Mono.just(savedRoom));
	    when(roomMapper.toRoomDTO(savedRoom)).thenReturn(roomDTO);

	    // then
	    StepVerifier.create(roomService.getRoomById(id))
	            .expectNext(roomDTO)
	            .verifyComplete();

	}
	
}
