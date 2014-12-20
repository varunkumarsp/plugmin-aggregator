package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class TransportUpdate {

	private String object;
	private String string;
	private String function;
	private Boolean cache = false;
	private String contentType = "application/json";
	private String dataType = "json";
	private String type = "POST";
	private String url;
	
	public TransportUpdate(String url) {
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

}
