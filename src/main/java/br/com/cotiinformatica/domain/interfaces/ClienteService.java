package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.ClienteRequest;
import br.com.cotiinformatica.domain.dtos.ClienteResponse;
import br.com.cotiinformatica.domain.dtos.MoverClienteRequest;

public interface ClienteService {

	ClienteResponse cadastrarCliente(ClienteRequest request, UUID usuarioId);
	
	ClienteResponse atualizarCliente(UUID id, ClienteRequest request, UUID usuarioId);
	
	ClienteResponse deletarCliente(UUID id, UUID usuarioId);
	
	ClienteResponse moverCliente(UUID id, MoverClienteRequest request, UUID usuarioId);
	
	List<ClienteResponse> listarClientes(UUID usuarioId);
	
	ClienteResponse buscarCliente(UUID id, UUID usuarioId);

	List<ClienteResponse> listarPorCategoria(UUID categoriaId, UUID usuarioId);
}
