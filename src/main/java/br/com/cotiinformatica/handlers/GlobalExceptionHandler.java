package br.com.cotiinformatica.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.cotiinformatica.domain.exceptions.CategoriaNaoEncontradaException;
import br.com.cotiinformatica.domain.exceptions.ClientNotFoundException;
import br.com.cotiinformatica.domain.exceptions.EmailCadastradoException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleClienteNaoEncontradoException(ClientNotFoundException ex, WebRequest request){
		
		var body = new HashMap<String, Object>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("erro", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmailCadastradoException.class)
	public ResponseEntity<Map<String, Object>> handleEmailCadastradoException(EmailCadastradoException ex, WebRequest request) {
		
		var body = new HashMap<String, Object>();
		
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("erro", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CategoriaNaoEncontradaException.class)
	public ResponseEntity<Map<String, Object>> handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException ex, WebRequest request){
		
		var body = new HashMap<String, Object>();
		
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("erro", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
