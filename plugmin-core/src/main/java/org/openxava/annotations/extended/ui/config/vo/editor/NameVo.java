package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.ui.config.editor.Name;
import org.openxava.annotations.extended.ui.config.vo.InstantiationRestrictedException;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsObject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class NameVo {

	private String field;
	
	@JsObject
	private String parse;
	
	
	public static NameVo instance() throws InstantiationRestrictedException {
		if (!calledFrom(FieldsVo.class)) {
			throw new InstantiationRestrictedException(
					"No permission to instantiate");
		}
		return new NameVo();
	}


	public String getField() {
		return field;
	}

	public void setField(String field) {
		if(isNotEmpty(field))
			this.field = field;
	}

	public String getParse() {
		return parse;
	}

	public void setParse(String parse) {
		if(isNotEmpty(parse))
			this.parse = parse;
	}
	
	public void copyFrom(Name config) {
		setField(config.field());
		setParse(config.parse());
	}

}
