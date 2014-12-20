package org.openxava.annotations.extended.vo;

import org.openxava.annotations.extended.DefaultValue;

public class ValidationVo {

	@DefaultValue("false")
	private Boolean required;
	
	private Integer min;
	
	private Integer max;

	public Boolean isRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
}
