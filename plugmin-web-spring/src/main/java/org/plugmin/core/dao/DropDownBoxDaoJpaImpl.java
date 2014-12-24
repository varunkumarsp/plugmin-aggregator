package org.plugmin.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;

public class DropDownBoxDaoJpaImpl implements DropDownBoxDao {

	@PersistenceContext
	EntityManager entityManager;


	@Override
	public DataSourceResult getList(DataSourceRequest request, Class<?> class1) {
		Session session = entityManager.unwrap(Session.class);
		return request.toDataSourceResult(session, class1);
	}

}
