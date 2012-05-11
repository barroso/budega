package br.com.barroso.controller;

import br.com.barroso.dao.UsuarioDao;
import br.com.barroso.dao.UsuarioSession;
import br.com.barroso.model.Usuario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class LoginController {

	private Result result;
	private UsuarioDao usuarioDao;
	private UsuarioSession usuarioSession;

	public LoginController( Result result, UsuarioDao usuarioDao, UsuarioSession usuarioSession ) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.usuarioSession = usuarioSession;
	}

	@Get("/login")
	public void login() {
		if( usuarioSession.getUsuario() != null ) result.redirectTo(IndexController.class).index();
	}

	@Get("/logout")
	public void logout() {
		usuarioSession.setUsuario(null);
		result.redirectTo(this).login();
	}

	@Post("/login")
	public void login(String nome, String senha) {
		Usuario usuario = usuarioDao.login(nome, senha);
		if( usuario == null ) {
			result.include("erro", "Login ou senha inválidos.").redirectTo(this).login();
		} else {
			usuarioSession.setUsuario(usuario);
			result.redirectTo(IndexController.class).index();
		}
	}
}