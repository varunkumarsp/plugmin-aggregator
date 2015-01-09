package org.plugmin.core.dao;

import static org.openxava.annotations.parse.EntityUtil.cast;
import static org.openxava.annotations.parse.EntityUtil.copyProperties;
import static org.openxava.annotations.parse.EntityUtil.idField2;
import static org.openxava.annotations.parse.EntityUtil.modelToEntity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import org.hibernate.Session;
import org.openxava.annotations.extended.ui.config.vo.PlugminEventListener;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;

public abstract class AbstractViewDao implements ViewDao {

	
	PlugminEventListener eventListener;
	

	@Override
	public Object read(DataSourceRequest req, Class<?> entity) {
		DataSourceResult result = req.toDataSourceResult(session(), entity);
		return result.getData().get(0);
	}
	
	@Override
	public Serializable create(Map<String, Object> model, Class<?> entity, String view) throws Exception {
		Session session = session();
		Object obj = modelToEntity(model, entity);

		if(eventListener != null)
			eventListener.prePersist(obj, view, session);
		
		Serializable generatedId = session.save(obj);
		
		if(eventListener != null)
			eventListener.postPersist(obj, view, session);
		
		return generatedId;
	}

	@Override
	public void update(String entityId, Map<String, Object> model, Class<?> entity, String view) throws Exception {
		Field idField = idField2(entity);
		Serializable id = (Serializable) cast(entityId, idField.getType());
		
		Session session = session();
		Object obj = session.get(entity, id);
		
		copyProperties(model, obj, session);
		
		if(eventListener != null)
			eventListener.preUpdate(obj, view, session);
		
		session.merge(obj);
		
		if(eventListener != null)
			eventListener.postUpdate(obj, view, session);
	}

	protected abstract Session session();

	@Override
	public void setEventListener(PlugminEventListener eventListener) {
		this.eventListener = eventListener;
	}
	
	

}
