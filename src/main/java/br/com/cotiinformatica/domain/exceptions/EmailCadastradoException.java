package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class EmailCadastradoException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Esse email já está cadastrado para outro cliente. Por favor tente cadastrar novamente.";
	}
}
