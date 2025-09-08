package com.example.room_service.domain;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection="skipped_rooms")
public class SkippedRoomDocument {
	@Id
	private String id;
	private int rowNumber;
	private Map<String, Object> rowData;
	private String reason;
	private LocalDateTime uploadedDate;
	private String uploadBatchId;
	
}
