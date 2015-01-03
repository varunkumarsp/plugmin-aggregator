package org.openxava.annotations.extended.ui.config.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Start {
	MONTH("month"),
	YEAR("year"),
	DECADE("decade"),
	CENTURY("century"),
	NULL(null);

	private final String start;

	private Start(String start) {
		this.start = start;
	}

	public String getStart() {
		return start;
	}
	
	public String getValue() {
		return start;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.start;
	}
}
