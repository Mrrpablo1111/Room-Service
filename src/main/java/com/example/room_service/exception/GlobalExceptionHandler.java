package com.example.room_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@RestControllerAdvice
@Hidden
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	
	private final ProblemDetailFactory problemFactory;
	@ExceptionHandler(RoomNotFoundException.class)
	public Mono<ProblemDetail> handleRoomNotFound(RoomNotFoundException ex, ServerWebExchange exchnage){
		log.warn("Room not Found:{}", ex.getMessage());
		return Mono.just(problemFactory.create(
				HttpStatus.NOT_FOUND,
				ex.getMessage(),
				ErrorCode.ROOM_NOT_FOUND.name(),
				exchnage));
		
	}
	
	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<ProblemDetail> handleConstrainViolation(WebExchangeBindException ex, ServerWebExchange exchange){
		log.warn("Room not Found:{}", ex.getMessage());
		return Mono.just(problemFactory.create(HttpStatus.BAD_REQUEST,ex.getMessage(), ErrorCode.CONSTRAIN_VIOLIDATION.name(), exchange));
	}
	
	@ExceptionHandler(Exception.class)
	public Mono<ProblemDetail> handleGeneric(Exception ex, ServerWebExchange exchange){
		log.warn("Unexspacted Error", ex.getMessage());
		return Mono.just(problemFactory.create(HttpStatus.INTERNAL_SERVER_ERROR ,"Unexspacted Error:"+ex.getMessage(),ErrorCode.SYSTEM_ERROR.name(), exchange));
	}

}
 