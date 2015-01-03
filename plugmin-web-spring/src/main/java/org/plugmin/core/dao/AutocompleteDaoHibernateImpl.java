package org.plugmin.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;

public class AutocompleteDaoHibernateImpl implements AutocompleteDao {

	SessionFactory sessionFactory;
	

	public AutocompleteDaoHibernateImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}


	@Override
	public DataSourceResult getList(DataSourceRequest request, Class<?> class1) {
		Session session = sessionFactory.getCurrentSession();
		return request.toDataSourceResult(session, class1);
	}

}
