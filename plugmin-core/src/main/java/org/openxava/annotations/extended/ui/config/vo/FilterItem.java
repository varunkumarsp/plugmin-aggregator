package org.openxava.annotations.extended.ui.config.vo;

import java.util.HashMap;
import java.util.Map;

import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class FilterItem implements JsonKeyValuesProviderI {

	private String logic;
	private String field;

	@JsonRawKeyValueProvider
	private Object value;

	private String operator;
	private boolean ignoreCase = true;

	public FilterItem() {
	}

	public FilterItem(String field, Object value, String operator) {
		this.field = field;
		this.value = value;
		this.operator = operator;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getRawKeyValues(String fieldName) {
		if (fieldName.equals("value")) {
			return resolveValue();
		}
		return null;
	}

	private Map<String, String> resolveValue() {
		Map<String, String> map = new HashMap<String, String>();
		StringBuilder value = new StringBuilder();
		if (this.value instanceof String
				&& ((String) this.value).contains("parentEntityId")) {
			value.append(this.value);
		} else {
			value.append("'");
			value.append(this.value);
			value.append("'");
		}
		map.put("value", value.toString());
		return map;
	}

	@Override
	public Object getValue(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

}
