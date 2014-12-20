package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import org.openxava.annotations.extended.Event;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Event.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class EventVo {
	
	private String name;
	private String handler;

	public EventVo(String name, String handler) {
		setName(name);
		setHandler(handler);
	}
	
	public EventVo() {
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(isNotEmpty(name))
			this.name = name;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		if(isNotEmpty(handler))
			this.handler = handler;
	}

	public EventVo copyFrom(Event event) {
		setHandler(event.handler());
		setName(event.name());
		return this;
	}
	
}
