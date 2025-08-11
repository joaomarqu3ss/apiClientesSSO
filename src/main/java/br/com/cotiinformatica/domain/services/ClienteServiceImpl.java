package br.com.cotiinformatica.domain.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.CategoriaResponse;
import br.com.cotiinformatica.domain.dtos.ClienteRequest;
import br.com.cotiinformatica.domain.dtos.ClienteResponse;
import br.com.cotiinformatica.domain.dtos.EnderecoResponse;
import br.com.cotiinformatica.domain.dtos.MoverClienteRequest;
import br.com.cotiinformatica.domain.entities.Cliente;
import br.com.cotiinformatica.domain.entities.Endereco;
import br.com.cotiinformatica.domain.exceptions.CategoriaNaoEncontradaException;
import br.com.cotiinformatica.domain.exceptions.ClientNotFoundException;
import br.com.cotiinformatica.domain.exceptions.EmailCadastradoException;
import br.com.cotiinformatica.domain.interfaces.ClienteService;
import br.com.cotiinformatica.domain.interfaces.ViaCepService;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import br.com.cotiinformatica.repositories.ClienteRepository;
import br.com.cotiinformatica.repositories.EnderecoRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired ClienteRepository clienteRepository;
	
	@Autowired EnderecoRepository enderecoRepository;
	
	@Autowired ViaCepService cepService;
	
	@Autowired CategoriaRepository categoriaRepository; 
	
	
	@Override
	public ClienteResponse cadastrarCliente(ClienteRequest request, UUID usuarioId) {
		
		
		var endereco = getEndereco(request.getCep());
		
		var cliente = cadastrar(request, usuarioId, endereco);
		
		clienteRepository.save(cliente);

		return copyToResponse(cliente);
	}

	@Override
	public ClienteResponse atualizarCliente(UUID id, ClienteRequest request, UUID usuarioId) {

		long start = System.currentTimeMillis();
		
		var endereco = getEndereco(request.getCep());
		
		var cliente = atualizar(id, request, usuarioId, endereco);
		
		clienteRepository.save(cliente);
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);

		return copyToResponse(cliente);

	}

	@Override
	public ClienteResponse deletarCliente(UUID id, UUID usuarioId) {

		var cliente = clienteRepository.findByIdAndUsuarioId(id, usuarioId)
				.orElseThrow(() -> new ClientNotFoundException());

		clienteRepository.delete(cliente);

		return copyToResponse(cliente);
	}

	@Override
	public List<ClienteResponse> listarClientes(UUID usuarioId) {
		
		var clientes = clienteRepository.findByUsuarioId(usuarioId);

		return clientes.stream().
				map(this::copyToResponse)
				.collect(Collectors.toList());
		
	}
	
	@Override
	public List<ClienteResponse> listarPorCategoria(UUID categoriaId, UUID usuarioId) {
	    var clientes = clienteRepository.findByCategoriaAndUsuarioSeguros(categoriaId, usuarioId);

	    return clientes.stream()
	                   .map(this::copyToResponse)
	                   .collect(Collectors.toList());
	}
	
	@Override
	public ClienteResponse moverCliente(UUID id, MoverClienteRequest request, UUID usuarioId) {
		
		var cliente = mover(id, request, usuarioId);
		
		clienteRepository.save(cliente);
		
		return copyToResponse(cliente);
	}

	@Override
	public ClienteResponse buscarCliente(UUID id, UUID usuarioId) {
		var cliente = clienteRepository.findByIdAndUsuarioId(id, usuarioId)
				.orElseThrow(() -> new ClientNotFoundException());
		
		return copyToResponse(cliente);
	}
	
	private ClienteResponse copyToResponse(Cliente cliente) {

		
		var response = new ClienteResponse();

		var cat = cliente.getCategoria();
		var categoria = new CategoriaResponse();
		
		if(cat != null) {
		categoria.setId(cat.getId());
		categoria.setNome(cat.getNome());
		categoria.setDataCriacao(cat.getDataCriacao());
		} 
		
		var e = cliente.getEndereco();
		var endereco = new EnderecoResponse();

		endereco.setId(e.getId());
		endereco.setCep(e.getCep());
		endereco.setBairro(e.getBairro());
		endereco.setCidade(e.getCidade());
		endereco.setEstado(e.getEstado());
		endereco.setRua(e.getRua());

		response.setId(cliente.getId());
		response.setNome(cliente.getNome());
		response.setEmail(cliente.getEmail());
		response.setTelefone(cliente.getTelefone());
		response.setEndereco(endereco);
		response.setCategoria(categoria);
		
		
		
		
		return response;
		
	}

	private Endereco getEndereco(String cep) {
		var endereco = enderecoRepository.find(cep);
		
		if(endereco == null) {
			EnderecoResponse enderecoViaCep = cepService.getEndereco(cep);

			Endereco novoEndereco = new Endereco();
			novoEndereco.setCep(enderecoViaCep.getCep());
			novoEndereco.setBairro(enderecoViaCep.getBairro());
			novoEndereco.setCidade(enderecoViaCep.getCidade());
			novoEndereco.setRua(enderecoViaCep.getRua());
			novoEndereco.setEstado(enderecoViaCep.getEstado());
			
			return enderecoRepository.save(novoEndereco);
		}  else {
			
			return endereco;
		}
	
		
	}
	
	private Cliente cadastrar(ClienteRequest request, UUID usuarioId, Endereco endereco) {
		
		var categoria = categoriaRepository.findByIdAndUsuarioId(request.getCategoriaId(),usuarioId);
				
		
		if(clienteRepository.findByEmail(request.getEmail())){
			throw new EmailCadastradoException();
		}
		
		var cliente = new Cliente();
		cliente.setNome(request.getNome());
		cliente.setEmail(request.getEmail());
		cliente.setTelefone(request.getTelefone());
		cliente.setUsuarioId(usuarioId);
		cliente.setEndereco(endereco);
		cliente.setCategoria(categoria);
		
		return cliente;
	}
	private Cliente atualizar(UUID id, ClienteRequest request, UUID usuarioId, Endereco endereco) {
		
		var categoria = categoriaRepository.findByIdAndUsuarioId(request.getCategoriaId(), usuarioId);
		
		var cliente = clienteRepository.findByIdAndUsuarioId(id, usuarioId)
				.orElseThrow(() -> new ClientNotFoundException());
		
		cliente.setNome(request.getNome());
		cliente.setEmail(request.getEmail());
		cliente.setTelefone(request.getTelefone());
		cliente.setUsuarioId(usuarioId);
		cliente.setEndereco(endereco);
		cliente.setCategoria(categoria);
		
		return cliente;
	}
	
	private Cliente mover(UUID id, MoverClienteRequest request, UUID usuarioId ) {
		
		var categoria = categoriaRepository.findByIdAndUsuarioId(request.getCategoriaId(), usuarioId);
		if(categoria == null) {
			throw new CategoriaNaoEncontradaException();
		}
		
		var cliente = clienteRepository.findByIdAndUsuarioId(id, usuarioId)
				.orElseThrow(() -> new ClientNotFoundException());
		
		cliente.setNome(cliente.getNome());
		cliente.setEmail(cliente.getEmail());
		cliente.setEndereco(cliente.getEndereco());
		cliente.setTelefone(cliente.getTelefone());
		cliente.setUsuarioId(usuarioId);
		cliente.setCategoria(categoria);
		
		
		return cliente;
	}

}