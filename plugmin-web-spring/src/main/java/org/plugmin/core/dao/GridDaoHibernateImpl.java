package org.plugmin.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GridDaoHibernateImpl extends AbstractGridDao {

	SessionFactory sessionFactory;
	

	public GridDaoHibernateImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}


	@Override
	protected Session session() {
		return sessionFactory.getCurrentSession();
	}

}
