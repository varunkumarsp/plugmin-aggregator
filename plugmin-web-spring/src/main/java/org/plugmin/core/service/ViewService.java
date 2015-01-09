package org.plugmin.core.service;

import java.util.List;
import java.util.Map;

import org.openxava.model.meta.MetaProperty;
import org.openxava.view.meta.MetaView;

public interface ViewService {

	MetaView metaView(String entity, String view) throws Exception;
	
	List<MetaProperty> metaProperties(String entity, String view) throws Exception;

	Map<String, Object> read(String entityId, MetaView metaView) throws Exception;
	
	Map<String, Object> create(Map<String, Object> model, MetaView metaView) throws Exception;

	Map<String, Object> update(String entityId, Map<String, Object> model, MetaView metaView) throws Exception;

}
