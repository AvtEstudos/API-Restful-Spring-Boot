package com.example.carros.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Anotação que permite que a classe intercepta 
//eventos do rest inclusive as exceções  
@RestControllerAdvice
public class ExceptionConfig {
	
	/*
	 * Fazendo o mapeamento da exceção para o Spring direcionar
	 * a execução para o novo tratamento de erro criado.
	 * Se você quiser que o método capture outras execeções,
	 * basta adicionar os tipos na anotação @ExceptionHandler 	   
	 */
	@ExceptionHandler({
		EmptyResultDataAccessException.class
	})	
	public ResponseEntity errorNotFound(Exception ex) {
		
		return ResponseEntity.notFound().build();
		
	}
}
