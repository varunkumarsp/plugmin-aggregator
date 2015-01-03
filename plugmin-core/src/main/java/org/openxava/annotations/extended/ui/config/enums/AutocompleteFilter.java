package org.openxava.annotations.extended.ui.config.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AutocompleteFilter {

	STARTS_WITH("startswith"),
	ENDS_WITH("endswith"),
	CONTAINS("contains"),
	NULL(null);

	private final String filter;

	private AutocompleteFilter(String filter) {
		this.filter = filter;
	}

	public String getFilter() {
		return filter;
	}
	
	public String getValue() {
		return filter;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.filter;
	}
}
