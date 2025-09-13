package com.example.room_service.controller;




import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.room_service.dto.PageDTO;
import com.example.room_service.dto.RoomDTO;
import com.example.room_service.dto.RoomFilterDTO;
import com.example.room_service.dto.RoomImportSummaryDTO;
import com.example.room_service.service.RoomImportService;
import com.example.room_service.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {


	private final RoomService roomService;
	private final RoomImportService roomImportService;

	@PostMapping
	public Mono<RoomDTO> createRoom(@Valid @RequestBody RoomDTO roomDTO){

		return roomService.createRoom(roomDTO);
	}
	@GetMapping("/{roomId}")
	@Operation(summary = "Get Room By ID:", parameters = @Parameter(in = ParameterIn.PATH, name = "roomId"))
	public Mono<RoomDTO> getRoomById(@PathVariable String roomId){
		return roomService.getRoomById(roomId);
	}

	@PutMapping("/{roomId}")
	public Mono<RoomDTO> updateRoom(@PathVariable String roomId, @RequestBody RoomDTO roomDTO){
		return roomService.updateRoom(roomId, roomDTO);
	}

	@DeleteMapping("/{roomId}")
	public Mono<Void> deleteRoom(@PathVariable String roomId){ 

		return roomService.deleteRoom(roomId);
	}

	@GetMapping("/search")
	public Flux<RoomDTO>getRoomByFilter(@ModelAttribute RoomFilterDTO roomFilterDTO){
		return roomService.getRoomByFilter(roomFilterDTO);
	}

	@GetMapping("/search/pagination")
	public Mono<PageDTO<RoomDTO>>getRoomByFilterPagination(@ModelAttribute RoomFilterDTO roomFilterDTO) throws IllegalAccessException{
		return roomService.getRoomByFilterPaginate(roomFilterDTO);
	}

	@GetMapping("/search/pagination2")
	public Mono<ResponseEntity<PageDTO<RoomDTO>>>getRoomByFiltssssserPaginationWithHeader(@ModelAttribute RoomFilterDTO roomFilterDTO) throws IllegalAccessException{
		return roomService.getRoomByFilterPaginate(roomFilterDTO)
				.map(page -> ResponseEntity.ok()
						.header("X-Total-Count", String.valueOf(page.getTotalElement()))
						.body(page)
						);

	}

	@PostMapping(value = "/upload-excel-file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public Mono<RoomImportSummaryDTO > uploadExcell(@RequestPart("file") FilePart filePart){
		return roomImportService.importRooms(filePart);
	}

}
