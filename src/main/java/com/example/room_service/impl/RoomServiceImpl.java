package com.example.room_service.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.room_service.domain.Room;
import com.example.room_service.dto.PageDTO;
import com.example.room_service.dto.RoomDTO;
import com.example.room_service.dto.RoomFilterDTO;
import com.example.room_service.exception.RoomNotFoundException;
import com.example.room_service.mapper.RoomMapper;
import com.example.room_service.repository.RoomCustomRepository;
import com.example.room_service.repository.RoomRepository;
import com.example.room_service.service.RoomService;
import com.example.room_service.utils.RoomCriteriaBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{


	private final RoomRepository roomRepository;
	private final RoomMapper roomMapper;
	private final RoomCustomRepository roomCustomRespository;


	@Override
	public Mono<RoomDTO> createRoom(RoomDTO roomDTO) {
		log.info("Save room to DB,{}", roomDTO);
		Room room = roomMapper.toRoom(roomDTO);
		return roomRepository.save(room)
			.doOnSuccess(saved -> log.info("Save Success:{}", saved))
			.map(roomMapper::toRoomDTO);


	}

	@Override
	public Mono<RoomDTO> getRoomById(String id) {
		log.debug("Retriving room with ID:{}", id);
		return roomRepository.findById(id)
				.switchIfEmpty(Mono.error(new RoomNotFoundException(id)))
				.doOnNext(room -> log.info("Room Received: {}", room))
				.map(roomMapper::toRoomDTO);

	}

	@Override
	public Mono<RoomDTO> updateRoom(String id, RoomDTO roomDTO) {
		log.debug("Updating Room id: {}", id, roomDTO);

		return roomRepository.findById(id)
		.switchIfEmpty(Mono.error(new RoomNotFoundException(id)))
		.flatMap(existing -> {
			roomMapper.updateRoomFromDTO(roomDTO, existing);
			return roomRepository.save(existing);

		})
		.map(roomMapper::toRoomDTO);

	}

	@Override
	public Mono<Void> deleteRoom(String id) {
		log.info("Deleting room with ID: {}", id);
		return roomRepository.deleteById(id)
		.switchIfEmpty(Mono.error(new RoomNotFoundException(id)))
		.doOnSuccess(deleted -> log.info("Room Deleted with ID: {}",id));

	}

	@Override
	public Flux<RoomDTO> getRoomByFilter(RoomFilterDTO filterDTO) {
		  Criteria criteria = RoomCriteriaBuilder.build(filterDTO);
		return roomCustomRespository.findByFilter(new Query(criteria))
				.map(roomMapper::toRoomDTO);

	}

	@Override
	public Mono<PageDTO<RoomDTO>> getRoomByFilterPaginate(RoomFilterDTO filterDTO) throws IllegalAccessException {
		Criteria criteria = RoomCriteriaBuilder.build(filterDTO);

		Mono<Long> countMono = roomCustomRespository.countByFilter(new Query(criteria));

		Query query = new Query(criteria)
				.skip((long) filterDTO.getPage() * filterDTO.getSize())
				.limit(filterDTO.getSize());
 
		query.with(RoomCriteriaBuilder.sort(filterDTO));

		Flux<RoomDTO> contentFlux = roomCustomRespository.findByFilter(query).map(roomMapper::toRoomDTO);


		return Mono.zip(countMono, contentFlux.collectList())
		.map(tuple -> {
			long total = tuple.getT1();
			List<RoomDTO> content = tuple.getT2();
			int totalPages = (int) Math.ceil((double)total / filterDTO.getSize());
			return new PageDTO<>(filterDTO.getPage(), filterDTO.getSize(), total, totalPages, content);
		});
	}
}
