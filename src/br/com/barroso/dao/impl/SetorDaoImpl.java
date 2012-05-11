package br.com.barroso.dao.impl;

import java.util.List;

import br.com.barroso.dao.SetorDao;
import br.com.barroso.model.Setor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component @RequestScoped
public class SetorDaoImpl extends GenericDaoImpl implements SetorDao {

	@SuppressWarnings("unchecked")
	public List<Setor> list() {
		return getSession().createCriteria(Setor.class).list();
	}

	public Setor load(Long id) {
		return (Setor) getSession().load(Setor.class, id);
	}
}
