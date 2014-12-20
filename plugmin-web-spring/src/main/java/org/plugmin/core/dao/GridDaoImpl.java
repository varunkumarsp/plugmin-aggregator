package org.plugmin.core.dao;

import static org.openxava.annotations.parse.EntityUtil.*;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.openxava.annotations.extended.vo.PlugminEventListener;
import org.openxava.annotations.parse.EntityUtil;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GridDaoImpl implements GridDao {

	@PersistenceContext(unitName = "mysql2")
	EntityManager entityManager;
	
	@Autowired
	PlugminEventListener eventListener;
	

	@Override
	public DataSourceResult getList(DataSourceRequest request, Class<?> entity) {
		Session session = entityManager.unwrap(Session.class);
        return request.toDataSourceResult(session, entity);
	}

	@Override
	public Serializable create(Map<String, Object> model, Class<?> entity, String view) throws Exception {
		Session session = entityManager.unwrap(Session.class);
		Object obj = modelToEntity(model, entity);
		
		eventListener.prePersist(obj, view, session);
		Serializable generatedId = session.save(obj);
		eventListener.postPersist(obj, view, session);
		
		return generatedId;
	}

	@Override
	public void update(Map<String, Object> model, Class<?> entity, String view) throws Exception {
		Session session = entityManager.unwrap(Session.class);
		Serializable id = (Serializable) model.get(EntityUtil.idField(entity));
		Object obj = session.get(entity, id);
		
//		model = reConfigure(model);
		copyProperties(model, obj, session);
		
		eventListener.preUpdate(obj, view, session);
		session.merge(obj);
		eventListener.postUpdate(obj, view, session);
	}

	@Override
	public void delete(Map<String, Object> model, Class<?> entity, String view) throws Exception {
		Session session = entityManager.unwrap(Session.class);
		Serializable id = (Serializable) model.get(EntityUtil.idField(entity));
		Object obj = session.get(entity, id);
		
		eventListener.preDelete(obj, view, session);
		session.delete(obj);
		eventListener.postDelete(obj, view, session);
	}


}
