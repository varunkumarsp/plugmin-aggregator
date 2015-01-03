package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.ui.config.Month;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class MonthVo {

	private String content;
	
	private String empty;

	
	public static MonthVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(DatePickerConfigVo.class) && !calledFrom(DateTimePickerConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new MonthVo();
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(isNotEmpty(content))
			this.content = content;
	}

	public String getEmpty() {
		return empty;
	}

	public void setEmpty(String empty) {
		if(isNotEmpty(empty))
			this.empty = empty;
	}

	public void copyFrom(Month config) {
		setContent(config.content());
		setEmpty(config.empty());
	}
	
	
}
