package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class CategoriaNaoEncontradaException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Categoria não encontrada. Por favor crie uma e agrupe o cliente depois.";
	}
}
