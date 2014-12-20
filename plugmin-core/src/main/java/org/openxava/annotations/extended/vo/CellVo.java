package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.Cell;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.JsField;
import org.openxava.annotations.extended.JsFieldObject;
import org.openxava.annotations.extended.JsFieldValueResolver;
import org.openxava.annotations.extended.JsFieldVariable;
import org.openxava.annotations.extended.Operator;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Cell.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class CellVo implements JsFieldValueResolver {
	
	@CompositeField("dataSource")
	private String dataSourceStr;
	
	@CompositeField("dataSource")
	private DataSourceVo dataSourceObj;
	
	private String dataTextField;
	
	@DefaultValue("200")
	private Integer delay;
	
	@DefaultValue("250")
	private Integer inputWidth;
	
	@DefaultValue("startswith")
	private Operator suggestionOperator;
	
	@DefaultValue("1")
	private Integer minLength;
	
	@DefaultValue("true")
	private Boolean enabled;
	
	@DefaultValue("eq")
	private Operator operator;
	
	@DefaultValue("true")
	private Boolean showOperators;
	
	@CompositeField("template")
	private String template;
	
	@JsonIgnore
	private ColumnVo column; // for internal use only

	
	private CellVo() {
	}
	
	private CellVo(ColumnVo column) {
		this.column = column;
	}
	
	public static CellVo instance(ColumnVo column) throws InstantiationRestrictedException {
		if(!calledFrom(FilterableColumnVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new CellVo(column);
	}


	public String getDataSourceStr() {
		return dataSourceStr;
	}

	public void setDataSourceStr(String dataSourceStr) {
		if(isNotEmpty(dataSourceStr))
			this.dataSourceStr = dataSourceStr;
	}

	public DataSourceVo getDataSourceObj() {
		return dataSourceObj;
	}

	public void setDataSourceObj(DataSourceVo dataSourceObj) {
		if(dataSourceObj != null)
			this.dataSourceObj = dataSourceObj;
	}
	
	public String getDataTextField() {
		return dataTextField;
	}

	public void setDataTextField(String dataTextField) {
		if(isNotEmpty(dataTextField))
			this.dataTextField = dataTextField;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		if(delay != null && delay != -1)
			this.delay = delay;
	}

	public Integer getInputWidth() {
		return inputWidth;
	}

	public void setInputWidth(Integer inputWidth) {
		if(inputWidth != null && inputWidth != -1)
			this.inputWidth = inputWidth;
	}

	public Operator getSuggestionOperator() {
		return suggestionOperator;
	}

	public void setSuggestionOperator(Operator suggestionOperator) {
		if(suggestionOperator != null && suggestionOperator.getValue() != null)
			this.suggestionOperator = suggestionOperator;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		if(minLength != null && minLength != -1)
			this.minLength = minLength;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		if(enabled != null)
			this.enabled = enabled;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		if(operator != null && operator.getValue() != null)
			this.operator = operator;
	}

	public Boolean isShowOperators() {
		return showOperators;
	}

	public void setShowOperators(Boolean showOperators) {
		if(showOperators != null)
			this.showOperators = showOperators;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		if(isNotEmpty(template))
			this.template = template;
	}


	public CellVo copyFrom(Cell cell) {
		if(!column.isCommandColumn()) {
			setDataSourceStr(cell.dataSource());
			setDataTextField(cell.dataTextField());
			setDelay(cell.delay());
			setEnabled(cell.enabled().getBool());
			setInputWidth(cell.inputWidth());
			setMinLength(cell.minLength());
			setOperator(cell.operator());
			setShowOperators(cell.showOperators().getBool());
			setSuggestionOperator(cell.suggestionOperator());
			setTemplate(cell.template());
			return this;
		}
		return null;
	}
	
	@Override
	public JsField resolve(String field) {
		if(field.equals("template")) {
			return resolveTemplate();
		} else if(field.equals("dataSource")) {
			return resolveDataSource();
		}
		return null;
	}

	private JsField resolveDataSource() {
		if(dataSourceObj != null)
			return new JsFieldObject("dataSource", dataSourceObj, "dataSource");
		else if(isNotEmpty(dataSourceStr)) {
			return new JsFieldVariable("dataSource", dataSourceStr, "dataSource");
		}
		return null;
	}


	private JsField resolveTemplate() {
		if(isNotEmpty(template)) {
			return new JsFieldVariable("template", template, "template");
		}
		return null;
	}

}
