package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import java.util.List;

import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class DataSourceSchemaVo {

	private ModelVo model;
	private String data = "data";
	private String total = "total";
	private String groups = "data";
	private String aggregates = "aggregates";
	

	private DataSourceSchemaVo() {
	}
	
	private DataSourceSchemaVo(List<ColumnVo> columns) {
		init(columns);
	}

	public static DataSourceSchemaVo instance(List<ColumnVo> columns) throws InstantiationRestrictedException {
		if(!calledFrom(DataSourceVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new DataSourceSchemaVo(columns);
	}
	
	
	public ModelVo getModel_(List<ColumnVo> columns) {
		if(model == null)
			try {
				model = ModelVo.instance(columns);
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

	private void init(List<ColumnVo> columns) {
		this.model = getModel_(columns);
	}
	
}
