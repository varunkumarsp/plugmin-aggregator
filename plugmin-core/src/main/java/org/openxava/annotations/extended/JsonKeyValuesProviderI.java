package org.openxava.annotations.extended;

import java.util.Map;

public interface JsonKeyValuesProviderI {

	public Map<String, Object> getKeyValues(String fieldName);
	
	public Map<String, String> getRawKeyValues(String fieldName);
	
	public Object getValue(String fieldName);
}
