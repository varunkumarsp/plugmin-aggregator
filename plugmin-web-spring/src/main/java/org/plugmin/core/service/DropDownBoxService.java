package org.plugmin.core.service;

import org.openxava.dropdown.MetaDropDown;

public interface DropDownBoxService {


	DataSourceResult getList(DataSourceRequest req, MetaDropDown metaDropDown);

	MetaDropDown metaDropDown(String entity, String view) throws Exception;

}
