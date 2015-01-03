package org.plugmin.core.service;

import java.util.List;

import org.openxava.annotations.extended.ui.config.vo.FilterItem;
import org.openxava.dropdown.MetaDropDown;
import org.openxava.tab.meta.MetaTab;

public interface DataSourceFilterSetter {

	public List<FilterItem> filters(MetaTab metaTab);
	
	public List<FilterItem> filters(MetaDropDown metaDropDown);
}
