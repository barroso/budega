package br.com.barroso.dao;

import br.com.barroso.model.Usuario;

public interface UsuarioSession {
	
	public Usuario getUsuario();
	
	public void setUsuario(Usuario usuario);
}