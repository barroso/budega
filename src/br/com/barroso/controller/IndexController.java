package br.com.barroso.controller;

import static br.com.caelum.vraptor.view.Results.http;
import br.com.barroso.dao.UsuarioDao;
import br.com.barroso.dao.UsuarioSession;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private Result result;
	private UsuarioSession usuarioSession;

	public IndexController( Result result, UsuarioDao usuarioDao, UsuarioSession usuarioSession ) {
		this.result = result;
		this.usuarioSession = usuarioSession;
	}

	@Get("/")
	public void index() {
		if( usuarioSession.getUsuario() == null ) result.redirectTo(LoginController.class).login();
	}

	@Get("/aniversariantes")
	public void aniversariantes() {
	}

	public void negado() {
		result.use(http()).sendError(403, "Acesso negado!");
	}
}