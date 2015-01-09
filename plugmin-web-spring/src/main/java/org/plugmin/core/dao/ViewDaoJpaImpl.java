package org.plugmin.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public class ViewDaoJpaImpl extends AbstractViewDao {

	@PersistenceContext(unitName = "mysql2")
	EntityManager entityManager;

	
	@Override
	protected Session session() {
		return entityManager.unwrap(Session.class);
	}

}
