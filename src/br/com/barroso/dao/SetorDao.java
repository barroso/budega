package br.com.barroso.dao;

import java.util.List;

import br.com.barroso.model.Setor;

public interface SetorDao extends GenericDao {
	public List<Setor> list();
	public Setor load(Long id);
}
