package org.plugmin.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;

public class DropDownBoxDaoHibernateImpl implements DropDownBoxDao {

	SessionFactory sessionFactory;
	

	public DropDownBoxDaoHibernateImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}


	@Override
	public DataSourceResult getList(DataSourceRequest request, Class<?> class1) {
		Session session = sessionFactory.getCurrentSession();
		return request.toDataSourceResult(session, class1);
	}

}
