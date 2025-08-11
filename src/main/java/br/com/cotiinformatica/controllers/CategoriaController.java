package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.domain.dtos.CategoriaRequest;
import br.com.cotiinformatica.domain.dtos.CategoriaResponse;
import br.com.cotiinformatica.domain.interfaces.CategoriaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired JwtTokenComponent jwt;
	
	@Autowired CategoriaService service;
	
	@PostMapping
	public ResponseEntity<CategoriaResponse> cadastrarCategoria(@RequestBody @Valid CategoriaRequest request, HttpServletRequest http){
		return ResponseEntity.ok(service.criarCategoria(request, getUsuario(http)));
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaResponse>> listar(HttpServletRequest http){
		
		return ResponseEntity.ok(service.listar(getUsuario(http)));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<CategoriaResponse> deletarCategoria(@PathVariable UUID id, HttpServletRequest http){
		return ResponseEntity.ok(service.excluirCategoria(id, getUsuario(http)));
	}
	
	private UUID getUsuario(HttpServletRequest http) {
		var token = http.getHeader("Authorization")
				.replace("Bearer ", "");
		
		return jwt.getIdFromToken(token);
	}
	
}
