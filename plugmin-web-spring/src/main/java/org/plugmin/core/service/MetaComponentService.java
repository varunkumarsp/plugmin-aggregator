package org.plugmin.core.service;

import java.util.List;

import org.openxava.component.MetaComponent;
import org.openxava.model.meta.MetaProperty;

public interface MetaComponentService {

	MetaComponent find(String entity) throws Exception;

	List<MetaProperty> metaPropertiesTab(String entity, String view) throws Exception;
	
	List<MetaProperty> metaPropertiesDropDown(String entity, String view) throws Exception;
	
	List<MetaProperty> metaPropertiesView(String entity, String view) throws Exception;

}
