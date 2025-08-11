package br.com.cotiinformatica.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ClienteRequest {

	private String nome;
	
	private String email;
	
	private String telefone;
	
	private String cep;

	private UUID categoriaId;
}
