package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import org.openxava.annotations.extended.Aggregates;

public class Aggregate {

	private String field;
	
	private Aggregates aggregate;

	public Aggregate(String field, Aggregates aggregates) {
		this.field = field;
		this.aggregate = aggregates;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		if(isNotEmpty(field))
			this.field = field;
	}

	public Aggregates getAggregate() {
		return aggregate;
	}

	public void setAggregate(Aggregates aggregate) {
		if(aggregate != null)
			this.aggregate = aggregate;
	}
	
	
}
