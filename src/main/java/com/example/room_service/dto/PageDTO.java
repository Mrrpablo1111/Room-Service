package com.example.room_service.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Paginated response wrapper ")
public class PageDTO<T> {
	@Schema(description = "Current page number (0-base)")
	private int page;
	
	@Schema(description = "Number of records per-page")
	private int size;
	
	@Schema(description = "Total number of records")
	private long totalElement;
	
	@Schema(description = "Total number of pages")
	private int totalPage;
	
	@Schema(description = "Current page data list")
	private List<T> content;
	

}
