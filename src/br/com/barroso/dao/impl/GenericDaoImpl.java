package br.com.barroso.dao.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.barroso.dao.GenericDao;

public class GenericDaoImpl implements GenericDao {
	
//	private SessionFactory sessionFactory;
//
//	@Autowired
//	public void setSessionFactory( SessionFactory sessionFactory ) {
//		this.sessionFactory = sessionFactory;
//	}
//	
//	public Session getSession() {
//		return sessionFactory.getCurrentSession();
//	}
	
	@Autowired
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void save(Object object) {
		getSession().save(object);
	}
		
	public void update(Object object) {
		getSession().merge(object);
	}

	public void delete(Object object) {
		getSession().delete(object);
	}
}