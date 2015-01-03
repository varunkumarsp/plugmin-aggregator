package org.openxava.annotations.extended.ui.config.vo.editor;

import org.openxava.annotations.extended.ui.config.editor.Schema;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class SchemaVo {

	private ModelVo model;

	
	public static SchemaVo instance() {
		return new SchemaVo();
	}
	
	
	public ModelVo getModel_() {
		if(model == null)
			try {
				model = ModelVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return model;
	}
	
	public ModelVo getModel() {
		return model;
	}

	public void setModel(ModelVo model) {
		if(model != null)
			this.model = model;
	}
	
	public void copyFrom(Schema config) {
		getModel_().copyFrom(config.model());
	}

}
