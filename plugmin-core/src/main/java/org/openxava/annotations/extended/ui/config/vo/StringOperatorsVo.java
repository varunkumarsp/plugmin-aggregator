package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.StringOperators;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(StringOperators.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class StringOperatorsVo {
	
	@DefaultValue("Is equal to")
	private String eq;
	
	@DefaultValue("Is not equal to")
	private String neq;
	
	@DefaultValue("Starts with")
	private String startsWith;
	
	@DefaultValue("Contains")
	private String contains;
	
	@DefaultValue("Does not contain")
	private String doesNotContain;
	
	@DefaultValue("Ends with")
	private String endsWith;

	
	private StringOperatorsVo() {
	}
	
	public static StringOperatorsVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(OperatorsVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new StringOperatorsVo();
	}
	
	
	public String getEq() {
		return eq;
	}

	public void setEq(String eq) {
		if(isNotEmpty(eq))
			this.eq = eq;
	}

	public String getNeq() {
		return neq;
	}

	public void setNeq(String neq) {
		if(isNotEmpty(neq))
			this.neq = neq;
	}

	public String getStartsWith() {
		return startsWith;
	}

	public void setStartsWith(String startsWith) {
		if(isNotEmpty(startsWith))
			this.startsWith = startsWith;
	}

	public String getContains() {
		return contains;
	}

	public void setContains(String contains) {
		if(isNotEmpty(contains))
			this.contains = contains;
	}

	public String getDoesNotContain() {
		return doesNotContain;
	}

	public void setDoesNotContain(String doesNotContain) {
		if(isNotEmpty(doesNotContain))
			this.doesNotContain = doesNotContain;
	}

	public String getEndsWith() {
		return endsWith;
	}

	public void setEndsWith(String endsWith) {
		if(isNotEmpty(endsWith))
			this.endsWith = endsWith;
	}

	public StringOperatorsVo copyFrom(StringOperators stringOperators) {
		setContains(stringOperators.contains());
		setDoesNotContain(stringOperators.doesNotContain());
		setEndsWith(stringOperators.endsWith());
		setEq(stringOperators.eq());
		setNeq(stringOperators.neq());
		setStartsWith(stringOperators.startsWith());
		return this;
	}

}
