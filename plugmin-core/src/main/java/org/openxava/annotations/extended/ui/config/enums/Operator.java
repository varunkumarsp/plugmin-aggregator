package org.openxava.annotations.extended.ui.config.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Operator {

	EQUAL_TO("eq"),
	NOT_EQUAL_TO("neq"), 
	STARTS_WITH("startswith"),
	ENDS_WITH("endswith"),
	CONTAINS("contains"),
	NULL(null);
	
	
	private final String operator;

    private Operator(String operator) {
        this.operator = operator;
    }

	public String getOperator() {
		return operator;
	}

	public String getValue() {
		return operator;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.operator;
	}
	
}
