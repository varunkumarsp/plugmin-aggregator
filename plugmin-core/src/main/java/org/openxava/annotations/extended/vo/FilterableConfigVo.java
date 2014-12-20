package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import java.util.Map;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.FilterableConfig;
import org.openxava.annotations.extended.FilterableMode;
import org.openxava.annotations.extended.JsonKeyValuesProviderI;
import org.openxava.annotations.extended.JsonValueProvider;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(FilterableConfig.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class FilterableConfigVo implements JsonKeyValuesProviderI {
	
	@DefaultValue("true")
	private Boolean extra;
	
	private OperatorsVo operators;
	
	@DefaultValue("menu")
	@JsonValueProvider
	private FilterableMode mode;

	
	private FilterableConfigVo() {
	}
	
	public static FilterableConfigVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(FilterableVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new FilterableConfigVo();
	}
	
	
	public Boolean isExtra() {
		return extra;
	}

	public void setExtra(Boolean extra) {
		if(extra != null)
			this.extra = extra;
	}

	public OperatorsVo getOperators_() {
		if(operators == null)
			try {
				operators = OperatorsVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return operators;
	}
	
	public OperatorsVo getOperators() {
		return operators;
	}

	public void setOperators(OperatorsVo operators) {
		if(operators != null)
			this.operators = operators;
	}

	public FilterableMode getMode() {
		return mode;
	}

	public void setMode(FilterableMode mode) {
		if(mode != null && mode.getValue() != null)
			this.mode = mode;
	}

	public FilterableConfigVo copyFrom(FilterableConfig config) {
		setExtra(config.extra().getBool());
		setMode(config.mode());
		getOperators_().copyFrom(config.operators());
		return this;
	}

	public FilterableConfigVo resetMode() {
		this.mode = null;
		return this;
	}

	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getRawKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String fieldName) {
		if(fieldName.equals("mode")) {
			return resolveMode();
		}
		return null;
	}

	private FilterableMode resolveMode() {
		if(mode != null && mode.getValue() != null && mode.getValue().equals("nested-grid-mode")) {
			return FilterableMode.MENU;
		}
		return mode;
	}

}
