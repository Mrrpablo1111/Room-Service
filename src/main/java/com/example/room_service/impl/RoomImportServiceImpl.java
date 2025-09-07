package com.example.room_service.impl;

import java.awt.image.DataBuffer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;

import com.example.room_service.domain.Room;
import com.example.room_service.domain.SkippedRoomDocument;
import com.example.room_service.dto.RoomImportSummaryDTO;
import com.example.room_service.service.RoomImportService;


import reactor.core.publisher.Mono;

public class RoomImportServiceImpl implements RoomImportService{

	@Override
	public Mono<RoomImportSummaryDTO> importRooms(FilePart filePart) {
		return filePart.content()
				.map(dataBuffer -> {
					byte[] bytes = new byte[dataBuffer.readableByteCount()];
					dataBuffer.read(bytes);
					DataBufferUtils.release(dataBuffer);
					return new ByteArrayInputStream(bytes);
				}).next().flatMap(inputStream -> parseAndSaveRooms(inputStream));

	} 
	
	private Mono<RoomImportSummaryDTO> parseAndSaveRooms(ByteArrayInputStream inputStream){
		
		String batchId = UUID.randomUUID().toString();
		
		try(Workbook workbook = new XSSFWorkbook(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0);
			List<Room> valid = new ArrayList<>();
			List<Integer> skippedRow = new ArrayList<>();
			Map<Integer, String> reasons = new HashMap<>();
			List<SkippedRoomDocument> skippedRoomDocuments = new ArrayList<>();
			
			for(int i = 1 ; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				int displayRow = i+1;
				
				if(row == null) {
					skippedRow.add(displayRow);
					reasons.put(displayRow, "Empty Row");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
}
