package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.ui.config.editor.Model;
import org.openxava.annotations.extended.ui.config.vo.InstantiationRestrictedException;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class ModelVo {

	private String id;
	
	private FieldsVo fields;
	
	public static ModelVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(SchemaVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new ModelVo();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(isNotEmpty(id))
			this.id = id;
	}

	public FieldsVo getFields_() {
		if(fields == null)
			try {
				fields = FieldsVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return fields;
	}
	
	public FieldsVo getFields() {
		return fields;
	}

	public void setFields(FieldsVo fields) {
		if(fields != null)
			this.fields = fields;
	}

	public void copyFrom(Model config) {
		setId(config.id());
		getFields_().copyFrom(config.fields());
	}
}
