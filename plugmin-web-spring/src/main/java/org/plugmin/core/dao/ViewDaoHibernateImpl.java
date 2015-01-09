package org.plugmin.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ViewDaoHibernateImpl extends AbstractViewDao {

	SessionFactory sessionFactory;


	public ViewDaoHibernateImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	protected Session session() {
		return sessionFactory.getCurrentSession();
	}

}
