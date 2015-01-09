package org.plugmin.core.dao;

import java.io.Serializable;
import java.util.Map;

import org.openxava.annotations.extended.ui.config.vo.PlugminEventListener;
import org.plugmin.core.service.DataSourceRequest;

public interface ViewDao {

	Serializable create(Map<String, Object> model, Class<?> entity, String view) throws Exception;

	void update(String entityId, Map<String, Object> model, Class<?> entity, String view) throws Exception;

	void setEventListener(PlugminEventListener eventListener);

	Object read(DataSourceRequest req, Class<?> entity);

}
