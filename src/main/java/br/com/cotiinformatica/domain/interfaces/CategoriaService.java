package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.CategoriaRequest;
import br.com.cotiinformatica.domain.dtos.CategoriaResponse;

public interface CategoriaService {

	CategoriaResponse criarCategoria(CategoriaRequest request, UUID usuarioId);

	CategoriaResponse excluirCategoria(UUID id, UUID usuarioId);
	
	List<CategoriaResponse> listar(UUID usuarioId);
}
