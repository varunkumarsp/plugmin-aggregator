package org.openxava.annotations.extended.ui.config.vo;

import java.util.LinkedList;
import java.util.List;

import org.openxava.annotations.parse.JsonUtil;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DataConfig {

	private String name;
	
	private List<String> fields = new LinkedList<String>();
	
	private Object data;
	
	private String json;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFields() {
		return fields;
	}

	public void addFields(List<String> fields) {
		this.fields.addAll(fields);
	}
	
	public void addField(String field) {
		this.fields.add(field);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) throws JsonProcessingException {
		this.data = data;
		json = JsonUtil.toJson(data);
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
}
