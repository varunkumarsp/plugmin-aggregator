package org.openxava.annotations.extended.ui.config.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterableMode {
	MENU("menu"),
	ROW("row"),
	MENU_ROW("menu, row"),
	NESTED_GRID_MODE("nested-grid-mode"),
	NULL(null);

	private final String mode;

	private FilterableMode(String mode) {
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
