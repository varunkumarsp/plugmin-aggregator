package org.plugmin.core.util;

import org.openxava.annotations.extended.ui.config.vo.FilterItem;
import org.plugmin.core.service.DataSourceRequest.FilterDescriptor;

public class FilterUtil {

	public static FilterDescriptor cast(FilterItem filterItem) {
		FilterDescriptor filter = new FilterDescriptor();
		filter.setField(filterItem.getField());
		filter.setValue(filterItem.getValue());
		filter.setOperator(filterItem.getOperator());
		filter.setIgnoreCase(filterItem.isIgnoreCase());
		return filter;
	}
}
