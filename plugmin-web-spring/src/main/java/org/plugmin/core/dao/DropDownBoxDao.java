package org.plugmin.core.dao;

import org.plugmin.core.service.DataSourceRequest;
import org.plugmin.core.service.DataSourceResult;

public interface DropDownBoxDao {

	DataSourceResult getList(DataSourceRequest request, Class<?> class1);

}
