package com.example.carros.api.exception;

import java.io.Serializable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Anotação que permite que a classe intercepta 
//eventos do rest inclusive as exceções  
@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {
	
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

	@ExceptionHandler({
		IllegalArgumentException.class
	})	
	public ResponseEntity errorBadRequest(Exception ex) {
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return new ResponseEntity<>(new ExceptionError("Operação não permitida."), HttpStatus.METHOD_NOT_ALLOWED);
	}	
}

//Classe criada para permitir que o erro seja enviado no formato JSON
class ExceptionError implements Serializable{
	private String error;
	
	ExceptionError(String error) {
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
}
