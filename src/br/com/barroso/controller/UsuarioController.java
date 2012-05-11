package br.com.barroso.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.barroso.dao.SetorDao;
import br.com.barroso.dao.UsuarioDao;
import br.com.barroso.dao.UsuarioSession;
import br.com.barroso.model.Usuario;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
@Path("/usuarios")
public class UsuarioController {

	private Result result;
	private UsuarioDao usuarioDao;
	private SetorDao setorDao;
	private UsuarioSession usuarioSession;

	public UsuarioController(Result result, UsuarioDao usuarioDao, SetorDao setorDao,
			UsuarioSession usuarioSession) {
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.setorDao = setorDao;
		this.usuarioSession = usuarioSession;
	}

	@Get("")
	public void usuarios() {
		List<Usuario> usuarios = usuarioDao.list();
		result.include("usuarios", usuarios);
	}

	@Get("/new")
	public void formulario() {
		result.include("setores", setorDao.list());
	}

	@Get("/{id}/show")
	public void show(Long id) {
		result.include("usuario", usuarioDao.load(id));
	}

	@Get("/{id}/edit")
	public void edit(Long id) {
			result.include("usuario", usuarioDao.load(id)).forwardTo(this).formulario();
	}

	@Post("")
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
		result.redirectTo(this).usuarios();
	}

	@Get("/verificardisponibilidade/{login}")
	public void verificarDisponibilidade(String login) {
		result.use(json()).from(usuarioDao.isLoginExiste(login)).serialize();
	}

	@Put("")
	public void update(Usuario usuario) {
		Usuario user = usuarioDao.load(usuario.getId());
		usuario.setSenha(user.getSenha());
		usuario.setLogin(user.getLogin());
		usuarioDao.update(usuario);
		if (usuarioSession.getUsuario().getId().equals(user.getId()))
			usuarioSession.setUsuario(usuario);
		result.redirectTo(this).usuarios();
	}

	@Get("/editsenha")
	public void editSenha() {
	}

	@Put("/editsenha")
	public void editSenha(String senhaAtualCriptografada, String novaSenha) {
		Usuario usuario = usuarioDao.load(usuarioSession.getUsuario().getId());
		if (usuario.getSenha().equals(senhaAtualCriptografada)) {
			usuarioDao.update(usuario);
			usuarioSession.setUsuario(usuario);
			result.include("message", "Senha atualizada com sucesso.");
		} else {
			result.include("message", "Senha atual não confere com a senha salva.");
		}
		result.redirectTo(this).editSenha();
	}

	@Delete("/{id}")
	public void delete(Long id) {
		Usuario usuario = usuarioDao.load(id);
		usuarioDao.delete(usuario);
		result.redirectTo(this).usuarios();
	}
}