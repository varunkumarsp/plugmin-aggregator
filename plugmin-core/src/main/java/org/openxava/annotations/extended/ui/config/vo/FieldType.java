package org.openxava.annotations.extended.ui.config.vo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FieldType {
	STRING("string"),
	NUMBER("number"),
	BOOLEAN("boolean"),
	DATE("date");
	
	private final String type;

	private FieldType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public String getValue() {
		return type;
	}

	@JsonValue
	@Override
	public String toString() {
		return this.type;
	}
	
}
