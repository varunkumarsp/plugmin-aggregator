package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.Editable;
import org.openxava.annotations.extended.EditableConfig;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Editable.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class EditableVo {

	@DefaultValue("false")
	private Boolean editable;

	private EditableConfigVo config;

	private EditableVo() {
	}
	
	public static EditableVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new EditableVo();
	}
	
	public Boolean isEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		if(editable != null)
			this.editable = editable;
	}

	public EditableConfigVo getConfig_() {
		if(config == null)
			try {
				config = EditableConfigVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return config;
	}
	
	public EditableConfigVo getConfig() {
		return config;
	}
	
	public void setConfig(EditableConfigVo config) {
		if(config != null)
			this.config = config;
	}

	public EditableVo copyFrom(Editable editable) {
		setEditable(editable.editable().getBool());

		EditableConfig config = editable.config();
		Object defaultConfig = AnnotationUtils.getDefaultValue(Editable.class, "config");
		if(!config.equals(defaultConfig)) {
			getConfig_().copyFrom(config);
		}
		return this;
	}

}
