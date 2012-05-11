package br.com.barroso.exception;

@SuppressWarnings("serial")
public class SaveException extends DBException {

	public SaveException() {
		super("Erro ao salvar.");
	}
}