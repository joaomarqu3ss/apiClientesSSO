package br.com.cotiinformatica.domain.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.CategoriaRequest;
import br.com.cotiinformatica.domain.dtos.CategoriaResponse;
import br.com.cotiinformatica.domain.entities.Categoria;
import br.com.cotiinformatica.domain.exceptions.CategoriaNaoEncontradaException;
import br.com.cotiinformatica.domain.interfaces.CategoriaService;
import br.com.cotiinformatica.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired CategoriaRepository categoriaRepository;
	
	@Override
	public CategoriaResponse criarCategoria(CategoriaRequest request, UUID usuarioId) {
		
		var categoria = cadastrar(request, usuarioId);
		
		categoriaRepository.save(categoria);
		
		return copyToResponse(categoria);
	}

	@Override
	public CategoriaResponse excluirCategoria(UUID id, UUID usuarioId) {
		
		var categoria = categoriaRepository.findByIdAndUsuarioId(id, usuarioId);
		
		if(categoria == null) {
		
			throw new CategoriaNaoEncontradaException();		
		}
		
		categoriaRepository.delete(categoria);
		
		return copyToResponse(categoria);
	}
	
	@Override
	public List<CategoriaResponse> listar(UUID usuarioId){
		
		var categorias = categoriaRepository.findByUsuarioId(usuarioId);
		
		return categorias.stream()
				.map(this::copyToResponse)
				.collect(Collectors.toList());
	}
	
	private Categoria cadastrar(CategoriaRequest request, UUID usuarioId) {
		
		var categoria = new Categoria();
		categoria.setNome(request.getNome());
		categoria.setUsuarioId(usuarioId);
		categoria.setDataCriacao(LocalDate.now());
		return categoria;
	}
	
	private CategoriaResponse copyToResponse(Categoria categoria) {
		var response = new CategoriaResponse();
		
		response.setId(categoria.getId());
		response.setNome(categoria.getNome());
		response.setDataCriacao(categoria.getDataCriacao());
		
		return response;
	}

}
