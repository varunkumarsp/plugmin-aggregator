package org.openxava.annotations.extended.ui.config.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortableMode {
	
	SINGLE("single"),
	MULTIPLE("multiple"),
	NULL(null);

	private final String mode;

	private SortableMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}
	
	public String getValue() {
		return mode;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.mode;
	}
}
