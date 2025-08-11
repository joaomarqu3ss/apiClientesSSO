package br.com.cotiinformatica.domain.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class MoverClienteRequest {

	private UUID categoriaId;
}
