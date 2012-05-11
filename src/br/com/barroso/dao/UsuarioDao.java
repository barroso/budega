package br.com.barroso.dao;

import java.util.List;

import br.com.barroso.model.Usuario;

public interface UsuarioDao extends GenericDao {
	public List<Usuario> list();
	public Usuario load( Long id );
	public Usuario login( String nome, String senha );
	public boolean isLoginExiste( String login );
}