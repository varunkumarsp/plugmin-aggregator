package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.ui.config.editor.Serialization;
import org.openxava.annotations.extended.ui.config.vo.InstantiationRestrictedException;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class SerializationVo {

	@DefaultValue("true")
	private Boolean entities;
	
	@DefaultValue("false")
	private Boolean scripts;
	
	public static SerializationVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(EditorConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new SerializationVo();
	}

	public Boolean getEntities() {
		return entities;
	}

	public void setEntities(Boolean entities) {
		if(entities != null)
			this.entities = entities;
	}

	public Boolean getScripts() {
		return scripts;
	}

	public void setScripts(Boolean scripts) {
		if(scripts != null)
			this.scripts = scripts;
	}

	public void copyFrom(Serialization config) {
		setEntities(config.entities().getBool());
		setScripts(config.scripts().getBool());
	}
}
