package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import org.openxava.annotations.extended.ui.config.editor.Item;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class ItemVo {

	private String text;

	private String value;

	private String context;

	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(isNotEmpty(text))
			this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if(isNotEmpty(value))
			this.value = value;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		if(isNotEmpty(context))
			this.context = context;
	}

	public ItemVo copyFrom(Item config) {
		setContext(config.context());
		setText(config.text());
		setValue(config.value());
		return this;
	}

}
