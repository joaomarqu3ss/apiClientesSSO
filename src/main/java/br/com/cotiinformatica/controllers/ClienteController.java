package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.domain.dtos.ClienteRequest;
import br.com.cotiinformatica.domain.dtos.ClienteResponse;
import br.com.cotiinformatica.domain.dtos.MoverClienteRequest;
import br.com.cotiinformatica.domain.interfaces.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;
	@Autowired
	JwtTokenComponent jwt;

	@PostMapping
	public ResponseEntity<ClienteResponse> cadastrar(@RequestBody @Valid ClienteRequest request,
			HttpServletRequest http) {

		var response = service.cadastrarCliente(request, getUsuario(http));

		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<ClienteResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid ClienteRequest request,
			HttpServletRequest http) {

		var response = service.atualizarCliente(id, request, getUsuario(http));

		return ResponseEntity.ok(response);
	}

	@PutMapping("movercliente/{id}")
	public ResponseEntity<ClienteResponse> moverCliente(@PathVariable UUID id,
			@RequestBody @Valid MoverClienteRequest request, HttpServletRequest http) {

		var response = service.moverCliente(id, request, getUsuario(http));

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ClienteResponse> deletar(@PathVariable UUID id, HttpServletRequest http) {

		var response = service.deletarCliente(id, getUsuario(http));

		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<ClienteResponse>> listar(HttpServletRequest http) {

		var response = service.listarClientes(getUsuario(http));

		return ResponseEntity.ok(response);
	}

	@GetMapping("/categoria/{categoriaId}")
	public ResponseEntity<?> listarPorCategorias(@PathVariable String categoriaId,
	                                             HttpServletRequest http) {
	    try {
	        UUID categoriaUUID = UUID.fromString(categoriaId);
	        var response = service.listarPorCategoria(categoriaUUID, getUsuario(http));
	        return ResponseEntity.ok(response);

	    } catch (IllegalArgumentException e) {
	        // UUID inválido
	        return ResponseEntity.badRequest().body("O ID da categoria informado não é válido: " + categoriaId);
	    }
	}


	@GetMapping("/id/{id}")
	public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID id, HttpServletRequest http) {

		var response = service.buscarCliente(id, getUsuario(http));

		return ResponseEntity.ok(response);
	}

	private UUID getUsuario(HttpServletRequest http) {
		var token = http.getHeader("Authorization").replace("Bearer ", "");

		return jwt.getIdFromToken(token);
	}
}
