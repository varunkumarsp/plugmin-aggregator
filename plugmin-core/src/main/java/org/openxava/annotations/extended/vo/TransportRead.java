package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.HashMap;
import java.util.Map;

import org.openxava.annotations.extended.JsonKeyValuesProviderI;
import org.openxava.annotations.extended.JsonRawKeyValueProvider;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class TransportRead implements JsonKeyValuesProviderI {

	private String object;
	private String string;
	private String function;
	private Boolean cache = false;
	private String contentType = "application/json";
	private String dataType = "json";
	private String type = "POST";
	
	@JsonRawKeyValueProvider
	private String url;

	public TransportRead(String url) {
		setUrl(url);
	}
	
	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		if(isNotEmpty(object))
			this.object = object;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		if(isNotEmpty(string))
			this.string = string;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		if(isNotEmpty(function))
			this.function = function;
	}

	public Boolean isCache() {
		return cache;
	}

	public void setCache(Boolean cache) {
		if(cache != null)
			this.cache = cache;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		if(isNotEmpty(contentType))
			this.contentType = contentType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		if(isNotEmpty(dataType))
			this.dataType = dataType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(isNotEmpty(type))
			this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if(isNotEmpty(url))
			this.url = url;
	}

	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getRawKeyValues(String fieldName) {
		if(fieldName.equals("url")) {
			return resolveUrl();
		}
		return null;
	}

	private Map<String, String> resolveUrl() {
		Map<String, String> map = new HashMap<String, String>();
		StringBuilder url = new StringBuilder();
		if(this.url.contains("parentEntityId")) {
			url.append("'");
			url.append(this.url.substring(0, this.url.indexOf("parentEntityId")));
			url.append("'");
			url.append(" + ");
			url.append("parentEntityId");
			url.append(" + ");
			url.append("'");
			url.append(this.url.substring(this.url.indexOf("parentEntityId") + "parentEntityId".length()));
			url.append("'");
		} else {
			url.append("'");
			url.append(this.url);
			url.append("'");
		}
		map.put("url", url.toString());
		return map;
	}

	@Override
	public Object getValue(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

}
