package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.Command;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Command.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class CommandVo {
	private String className;
	private String click;
	private String name;
	private String text;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		if(isNotEmpty(className))
			this.className = className;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		if(isNotEmpty(click))
			this.click = click;
	}

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

	public CommandVo copyFrom(Command command) {
		setClassName(command.className());
		setClick(command.click());
		setName(command.name());
		setText(command.text());
		return this;
	}

}
