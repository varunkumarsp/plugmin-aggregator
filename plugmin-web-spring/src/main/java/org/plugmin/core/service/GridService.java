package org.plugmin.core.service;

import java.util.List;
import java.util.Map;

import org.openxava.model.meta.MetaProperty;
import org.openxava.tab.meta.MetaTab;

public interface GridService {

	MetaTab metaTab(String entity, String view) throws Exception;
	
	List<MetaProperty> metaProperties(String entity, String view) throws Exception;

	DataSourceResult read(DataSourceRequest req, MetaTab metaTab)
			throws Exception;

	DataSourceResult create(List<Map<String, Object>> models, MetaTab metaTab) throws Exception;

	DataSourceResult update(List<Map<String, Object>> models, MetaTab metaTab) throws Exception;

	DataSourceResult delete(List<Map<String, Object>> models, MetaTab metaTab) throws Exception;
}
