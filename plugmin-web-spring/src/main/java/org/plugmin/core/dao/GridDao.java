package org.plugmin.core.dao;

import java.io.Serializable;
import java.util.Map;

import org.openxava.annotations.extended.ui.config.vo.PlugminEventListener;
import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;

public interface GridDao {

	DataSourceResult getList(DataSourceRequest request, Class<?> entity);

	Serializable create(Map<String, Object> model, Class<?> entity, String view) throws Exception;

	void update(Map<String, Object> model, Class<?> entity, String view) throws Exception;

	void delete(Map<String, Object> model, Class<?> entity, String view) throws Exception;

	void setEventListener(PlugminEventListener eventListener);

}
