package br.com.cotiinformatica.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	List<Cliente> findByUsuarioId(UUID usuarioId);
	
	Optional<Cliente> findByIdAndUsuarioId(UUID id, UUID usuarioId);
	
	
	@Query("""
			SELECT COUNT(c) > 0
			FROM Cliente c 
			WHERE c.email = :email
			""")
	boolean findByEmail(@Param("email") String email);
	

	@Query("""
		    SELECT c
		    FROM Cliente c
		    WHERE c.categoria.id = :categoriaId
		      AND c.usuarioId = :usuarioId
		      AND c.categoria.usuarioId = :usuarioId
		""")
		List<Cliente> findByCategoriaAndUsuarioSeguros(UUID categoriaId, UUID usuarioId);
}
