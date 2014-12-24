package org.plugmin.core.dao;

import static org.openxava.annotations.parse.EntityUtil.copyProperties;
import static org.openxava.annotations.parse.EntityUtil.modelToEntity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openxava.annotations.extended.vo.PlugminEventListener;
import org.openxava.annotations.parse.EntityUtil;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;

public class GridDaoHibernateImpl implements GridDao {

	SessionFactory sessionFactory;
	
	PlugminEventListener eventListener;
	

	public GridDaoHibernateImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public DataSourceResult getList(DataSourceRequest request, Class<?> entity) {
		Session session = sessionFactory.getCurrentSession();
        return request.toDataSourceResult(session, entity);
	}

	@Override
	public Serializable create(Map<String, Object> model, Class<?> entity, String view) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Object obj = modelToEntity(model, entity);

		if(eventListener != null)
			eventListener.prePersist(obj, view, session);
		
		Serializable generatedId = session.save(obj);
		
		if(eventListener != null)
			eventListener.postPersist(obj, view, session);
		
		return generatedId;
	}

	@Override
	public void update(Map<String, Object> model, Class<?> entity, String view) throws Exception {
		Field idField = EntityUtil.idField2(entity);
		Serializable id = (Serializable) EntityUtil.cast(model.get(idField.getName()), idField.getType());
		
		Session session = sessionFactory.getCurrentSession();
		Object obj = session.get(entity, id);
		
//		model = reConfigure(model);
		copyProperties(model, obj, session);
		
		if(eventListener != null)
			eventListener.preUpdate(obj, view, session);
		
		session.merge(obj);
		
		if(eventListener != null)
			eventListener.postUpdate(obj, view, session);
	}

	@Override
	public void delete(Map<String, Object> model, Class<?> entity, String view) throws Exception {
		Field idField = EntityUtil.idField2(entity);
		Serializable id = (Serializable) EntityUtil.cast(model.get(idField.getName()), idField.getType());
		
		Session session = sessionFactory.getCurrentSession();
		Object obj = session.get(entity, id);
		
		if(eventListener != null)
			eventListener.preDelete(obj, view, session);
		
		session.delete(obj);
		
		if(eventListener != null)
			eventListener.postDelete(obj, view, session);
	}

	@Override
	public void setEventListener(PlugminEventListener eventListener) {
		this.eventListener = eventListener;
	}

}
