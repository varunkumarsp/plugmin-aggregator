package org.openxava.annotations.extended.ui.config.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Depth {
	MONTH("month"),
	YEAR("year"),
	DECADE("decade"),
	CENTURY("century"),
	NULL(null);

	private final String depth;

	private Depth(String depth) {
		this.depth = depth;
	}

	public String getDepth() {
		return depth;
	}
	
	public String getValue() {
		return depth;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.depth;
	}
}
