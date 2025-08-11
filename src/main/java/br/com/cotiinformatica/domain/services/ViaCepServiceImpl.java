package br.com.cotiinformatica.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cotiinformatica.domain.dtos.EnderecoResponse;
import br.com.cotiinformatica.domain.interfaces.ViaCepService;

@Service
public class ViaCepServiceImpl implements ViaCepService {

	@Override
	public EnderecoResponse getEndereco(String cep) {
		
		RestTemplate restTemp = new RestTemplate();
		
		String url = "https://viacep.com.br/ws/" + cep + "/json";
			
		EnderecoResponse response = restTemp.getForObject(url, EnderecoResponse.class);
		
		return response;
	}

}
