package org.plugmin.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;
import org.springframework.stereotype.Repository;

@Repository
public class DropDownBoxDaoImpl implements DropDownBoxDao {

	@PersistenceContext(unitName = "mysql2")
	private EntityManager entityManager;

	@Override
	public DataSourceResult getList(DataSourceRequest request, Class<?> class1) {
		Session session = entityManager.unwrap(Session.class);
		return request.toDataSourceResult(session, class1);
	}
	
	


}
