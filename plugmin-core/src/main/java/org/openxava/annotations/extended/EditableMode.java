package org.openxava.annotations.extended;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EditableMode {
	INCELL("incell"),
	INLINE("inline"),
	POPUP("popup"),
	NULL(null);

	private final String mode;

	private EditableMode(String mode) {
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
