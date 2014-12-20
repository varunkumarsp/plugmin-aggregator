package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import java.util.ArrayList;
import java.util.List;

import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class Filter {

	private String logic;
	private List<FilterItem> filters;

	private Filter() {

	}

	public static Filter instance() throws InstantiationRestrictedException {
		if (!calledFrom(DataSourceVo.class)) {
			throw new InstantiationRestrictedException(
					"No permission to instantiate");
		}
		return new Filter();
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public List<FilterItem> getFilters() {
		return filters;
	}

	public void addFilters(List<FilterItem> filters) {
		if (this.filters == null) {
			this.filters = new ArrayList<FilterItem>();
		}
		this.filters.addAll(filters);
	}

	public void addFilter(FilterItem filter) {
		if (this.filters == null) {
			this.filters = new ArrayList<FilterItem>();
		}
		this.filters.add(filter);
	}
}
