package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.Toolbar;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Toolbar.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class ToolbarVo {
	
	private String name;
	private String text;
	private String templateStr;
	private String templateFn;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(isNotEmpty(name))
			this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(isNotEmpty(text))
			this.text = text;
	}

	public String getTemplateStr() {
		return templateStr;
	}

	public void setTemplateStr(String templateStr) {
		if(isNotEmpty(templateStr))
			this.templateStr = templateStr;
	}

	public String getTemplateFn() {
		return templateFn;
	}

	public void setTemplateFn(String templateFn) {
		if(isNotEmpty(templateFn))
			this.templateFn = templateFn;
	}

	public ToolbarVo copyFrom(Toolbar toolBar) {
		setName(toolBar.name());
		setTemplateFn(toolBar.templateFn());
		setTemplateStr(toolBar.templateStr());
		setText(toolBar.text());
		return this;
	}

}
