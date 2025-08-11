package br.com.cotiinformatica.domain.dtos;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientesPorCategoriasResponse {

	private UUID categoriaId;
	
	private String categoriaNome;
	
	private List<ClienteResponse> clientes;
}
