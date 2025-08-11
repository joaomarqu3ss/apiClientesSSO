package br.com.cotiinformatica.domain.dtos;


import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnderecoResponse {
	
	private UUID id;
	
	private String cep;
	
	@JsonProperty("logradouro")
	private String rua;
	
	private String bairro;
	
	@JsonProperty("localidade")
	private String cidade;
	
	@JsonProperty("uf")
	private String estado;
}
