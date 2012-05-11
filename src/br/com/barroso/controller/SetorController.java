package br.com.barroso.controller;

import br.com.barroso.dao.SetorDao;
import br.com.barroso.model.Setor;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource @Path("/setores")
public class SetorController {
	
	private Result result;
	private SetorDao setorDao;
	
	public SetorController(Result result, SetorDao setorDao ) {
		this.result = result;
		this.setorDao = setorDao;
	}
	
	@Get("")
	public void setores() {
		result.include("setores", setorDao.list());
	}

	@Get("/new")
	public void formulario() {}
	
	@Get("/{id}/edit")
	public void edit( Long id ) {
		result.include("setor", setorDao.load(id))
		.forwardTo(this).formulario();
	}
	
	@Post("")
	public void save( Setor setor ) {
		setorDao.save(setor);
		result.redirectTo(this).setores();
	}
	
	@Put("")
	public void update( Setor setor ) {
		setorDao.update(setor);
		result.redirectTo(this).setores();
	}
	
	@Delete("/{id}")
	public void delete( Long id ) {
		Setor setor = setorDao.load(id);
		setorDao.delete(setor);
		result.redirectTo(this).setores();
	}
}
