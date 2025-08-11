package br.com.cotiinformatica.domain.dtos;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class CategoriaResponse {
	
	private UUID id;
	
	private String nome;
	
	private LocalDate dataCriacao;
}
