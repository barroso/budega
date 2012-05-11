package br.com.barroso.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import br.com.barroso.dao.UsuarioDao;
import br.com.barroso.model.Usuario;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component @RequestScoped
public class UsuarioDaoImpl extends GenericDaoImpl implements UsuarioDao {

	@SuppressWarnings("unchecked")
	public List<Usuario> list() {
		Criteria criteria = getSession().createCriteria(Usuario.class, "user");
		criteria.setProjection(projectionsDeBuscaDeUsuario()).setResultTransformer(new AliasToBeanResultTransformer(Usuario.class));
		criteria.addOrder(Order.asc("user.nome"));
		return criteria.list();
	}


	public Usuario load( Long id ) {
		return (Usuario) getSession().load(Usuario.class, id);
	}

	public boolean isLoginExiste( String login ) {
		Criteria criteria = getSession().createCriteria(Usuario.class, "user");
			criteria.add(Restrictions.eq("user.login", login));
		ProjectionList list = Projections.projectionList().create();
			list.add(Projections.property("user.login"), "login");
		criteria.setProjection(list).setResultTransformer(new AliasToBeanResultTransformer(Usuario.class));

		return ( criteria.uniqueResult() != null );
	}

	public Usuario login( String login, String senha ) {
		try {
			Criteria criteria = getSession().createCriteria(Usuario.class, "user");
				criteria.add(Restrictions.eq("user.login", login));
				criteria.add(Restrictions.eq("user.senha", senha));
			return (Usuario) criteria.uniqueResult();
		} catch(NonUniqueResultException e) {
			e.printStackTrace();
			throw new RuntimeException("Usuário duplicado no sistema.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível acessar o sistema.");
		}
	}

	private ProjectionList projectionsDeBuscaDeUsuario() {
		ProjectionList list = Projections.projectionList().create();
			list.add(Projections.property("user.id"), "id");
			list.add(Projections.property("user.nome"), "nome");

		return list;
	}
}