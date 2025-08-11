package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class ClientNotFoundException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Cliente não encontrado. Verifique o ID informado.";
	}
}
