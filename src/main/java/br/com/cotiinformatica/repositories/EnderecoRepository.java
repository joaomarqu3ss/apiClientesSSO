package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
	
	@Query("""
			SELECT COUNT(e) > 0
			FROM Endereco e
			WHERE e.cep = :cep
			""")
	boolean existsByCep(String cep);
	
	@Query("""
			SELECT e FROM Endereco e
			WHERE e.cep = :cep
			""")
	Endereco find(@Param("cep") String cep);
}
