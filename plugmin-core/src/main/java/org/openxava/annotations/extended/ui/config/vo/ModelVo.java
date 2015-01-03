package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class ModelVo {

	private String id;
	private Map<String, Object> fields;

	
	private  ModelVo() {
	}
	
	private ModelVo(List<ColumnVo> columns) {
		init(columns);
	}

	public static ModelVo instance(List<ColumnVo> columns) throws InstantiationRestrictedException {
		if(!calledFrom(DataSourceSchemaVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new ModelVo(columns);
	}
	

	private void init(List<ColumnVo> columns) {
		Map<String, Object> fields = new LinkedHashMap<String, Object>();
		for (ColumnVo column : columns) {
			if(!column.isCommandColumn())
				fields.put(column.getField(), new FieldVo(column));
		}
		this.fields = fields;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(isNotEmpty(id))
			this.id = id;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		if(fields != null)
			this.fields = fields;
	}
	
	public void addModelOnlyField(String field, Class<?> fieldType, String defaultValue) {
		FieldVo fieldvo = new FieldVo(fieldType, defaultValue);
		getFields().put(field, fieldvo);
	}

	@Override
	public String toString() {
		return "ModelVo [id=" + id + ", fields=" + fields + "]";
	}
	
}
