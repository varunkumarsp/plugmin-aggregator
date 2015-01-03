package org.openxava.annotations.extended.ui.config.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Aggregates {
	AVERAGE("average"),
	SUM("sum"), 
	COUNT("count"), 
	MIN("min"), 
	MAX("max");
	
	private final String aggregate;

    private Aggregates(String aggregate) {
        this.aggregate = aggregate;
    }

	public String getAggregate() {
		return aggregate;
	}
	
	public String getValue() {
		return aggregate;
	}

	@JsonValue
	@Override
	public String toString() {
		return this.aggregate;
	}
	
}
