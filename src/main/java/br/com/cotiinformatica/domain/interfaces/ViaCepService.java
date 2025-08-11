package br.com.cotiinformatica.domain.interfaces;

import br.com.cotiinformatica.domain.dtos.EnderecoResponse;

public interface ViaCepService {
	
	EnderecoResponse getEndereco(String cep);
	
	
}
