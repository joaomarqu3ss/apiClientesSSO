package br.com.cotiinformatica.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

	Categoria findByIdAndUsuarioId(UUID id, UUID usuarioId);
	
	
	List<Categoria> findByUsuarioId(UUID usuarioId);
}
