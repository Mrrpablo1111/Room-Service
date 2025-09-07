package com.example.room_service.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RoomImportSummaryDTO {
	private int inserted;
	private int skipped;
	private List<Integer> skippedRow;
	private Map<Integer, String> reasons;  
}
