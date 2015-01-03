package org.openxava.annotations.extended.ui.config.vo;

import org.openxava.annotations.parse.JsonUtil;

import com.fasterxml.jackson.core.JsonProcessingException;

public class WidgetConfig {

	private String name;
	
	private Object config;
	
	private String json;

	
	public WidgetConfig(String name, Object config, String json) {
		this.name = name;
		this.config = config;
		this.json = json;
	}


	public Object getConfig() {
		return config;
	}

	public void setConfig(Object config) {
		this.config = config;
	}

	public String getJson() throws JsonProcessingException {
		if(json == null) {
			if(config != null)
				json = JsonUtil.toJson(config);
		}
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
