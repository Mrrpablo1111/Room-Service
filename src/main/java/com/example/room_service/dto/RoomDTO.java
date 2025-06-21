package com.example.room_service.dto;

import java.util.Map;

import lombok.Data;

@Data
public class RoomDTO {
	private String name;
	private Map<String, Object> attributes; 
}
