package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.DropDownConfig;
import org.openxava.annotations.extended.ui.config.enums.Operator;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.FieldResolver;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldArray;
import org.openxava.annotations.parse.JsFieldObject;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class DropDownConfigVo implements JsFieldValueResolver, JsonKeyValuesProviderI, Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	@DefaultValue("false")
	private Boolean autoBind;
	
	private String cascadeFrom;
	
	private String cascadeFromField;
	
	@CompositeField("dataSource")
	private DataSourceVo dataSourceObj;
	
	@CompositeField("dataSource")
	private String[] dataSourceArray;
	
	@DefaultValue("")
	private String dataTextField;
	
	@DefaultValue("")
	private String dataValueField;
	
	@DefaultValue("500")
	private Integer delay;
	
	private Operator filter;
	
	@DefaultValue("200")
	private Integer height;
	
	@DefaultValue("true")
	private Boolean ignoreCase;
	
	@DefaultValue("0")
	private Integer index;
	
	@DefaultValue("1")
	private Integer minLength;
	
	@DefaultValue("")
	private String optionLabel;
	
	@CompositeField("headerTemplate")
	private String headerTemplateStr;

	@CompositeField("headerTemplate")
	private String headerTemplateFn;

	@CompositeField("template")
	private String templateStr;

	@CompositeField("template")
	private String templateFn;
	
	@CompositeField("valueTemplate")
	private String valueTemplateStr;

	@CompositeField("valueTemplate")
	private String valueTemplateFn;

	@DefaultValue("")
	private String text;
	
	@DefaultValue("")
	private String value;
	
	@DefaultValue("false")
	private Boolean valuePrimitive;

	@JsonRawKeyValueProvider
	private List<EventVo> events;

	@JsonIgnore
	private FieldResolver fieldResolver; //for internal use

	
	
	public DropDownConfigVo(String dataValueField, String dataTextField) {
		this.dataValueField = dataValueField;
		this.dataTextField = dataTextField;
	}

	/**
	 * Used by enum dropdowns
	 * @param enumValues
	 */
	public DropDownConfigVo(String[] enumValues) {
		this.dataValueField = null;
		this.dataTextField = null;
	}


	public Boolean getAutoBind() {
		return autoBind;
	}

	public void setAutoBind(Boolean autoBind) {
		if(autoBind != null)
			this.autoBind = autoBind;
	}

	public String getCascadeFrom() {
		return cascadeFrom;
	}

	public void setCascadeFrom(String cascadeFrom) {
		if(isNotEmpty(cascadeFrom))
			this.cascadeFrom = cascadeFrom;
	}

	public String getCascadeFromField() {
		return cascadeFromField;
	}

	public void setCascadeFromField(String cascadeFromField) {
		if(isNotEmpty(cascadeFromField))
			this.cascadeFromField = cascadeFromField;
	}

	public DataSourceVo getDataSourceObj() {
		return dataSourceObj;
	}

	public void setDataSourceObj(DataSourceVo dataSourceObj) {
		this.dataSourceObj = dataSourceObj;
	}

	public String[] getDataSourceArray() {
		return dataSourceArray;
	}

	public void setDataSourceArray(String[] dataSourceArray) {
		if(ArrayUtils.isNotEmpty(dataSourceArray))
			this.dataSourceArray = dataSourceArray;
	}

	public String getDataTextField() {
		return dataTextField;
	}

	public void setDataTextField(String dataTextField) {
		if(isNotEmpty(dataTextField))
			this.dataTextField = dataTextField;
	}

	public String getDataValueField() {
		return dataValueField;
	}

	public void setDataValueField(String dataValueField) {
		if(isNotEmpty(dataValueField))
			this.dataValueField = dataValueField;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		if(delay != null && delay != -1)
			this.delay = delay;
	}

	public Operator getFilter() {
		return filter;
	}

	public void setFilter(Operator filter) {
		if(filter != null && filter.getValue() != null)
			this.filter = filter;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		if(delay != null && delay != -1)
			this.height = height;
	}

	public Boolean getIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		if(ignoreCase != null)
			this.ignoreCase = ignoreCase;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		if(index != null && index != -1)
		this.index = index;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		if(minLength != null && minLength != -1)
			this.minLength = minLength;
	}

	public String getOptionLabel() {
		return optionLabel;
	}

	public void setOptionLabel(String optionLabel) {
		if(isNotEmpty(optionLabel))
			this.optionLabel = optionLabel;
	}

	public String getHeaderTemplateStr() {
		return headerTemplateStr;
	}

	public void setHeaderTemplateStr(String headerTemplateStr) {
		if(isNotEmpty(headerTemplateStr))
			this.headerTemplateStr = headerTemplateStr;
	}

	public String getHeaderTemplateFn() {
		return headerTemplateFn;
	}

	public void setHeaderTemplateFn(String headerTemplateFn) {
		if(isNotEmpty(headerTemplateFn))
			this.headerTemplateFn = headerTemplateFn;
	}

	public String getTemplateStr() {
		return templateStr;
	}

	public void setTemplateStr(String templateStr) {
		if(isNotEmpty(templateStr))
			this.templateStr = templateStr;
	}

	public String getTemplateFn() {
		return templateFn;
	}

	public void setTemplateFn(String templateFn) {
		if(isNotEmpty(templateFn))
			this.templateFn = templateFn;
	}

	public String getValueTemplateStr() {
		return valueTemplateStr;
	}

	public void setValueTemplateStr(String valueTemplateStr) {
		if(isNotEmpty(valueTemplateStr))
			this.valueTemplateStr = valueTemplateStr;
	}

	public String getValueTemplateFn() {
		return valueTemplateFn;
	}

	public void setValueTemplateFn(String valueTemplateFn) {
		if(isNotEmpty(valueTemplateFn))
			this.valueTemplateFn = valueTemplateFn;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if(isNotEmpty(text))
			this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if(isNotEmpty(value))
			this.value = value;
	}

	public Boolean getValuePrimitive() {
		return valuePrimitive;
	}

	public void setValuePrimitive(Boolean valuePrimitive) {
		if(valuePrimitive != null)
			this.valuePrimitive = valuePrimitive;
	}

	public List<EventVo> getEvents() {
		return events;
	}

	public void setEvents(List<EventVo> events) {
		this.events = events;
	}

	public void copyFrom(DropDownConfig dropDownConfig) {
		setAutoBind(dropDownConfig.autoBind().getBool());
		setCascadeFrom(dropDownConfig.cascadeFrom());
		setCascadeFromField(dropDownConfig.cascadeFromField());
		setDataSourceArray(null);
		setDataSourceObj(null);
		setDelay(dropDownConfig.delay());
		setFilter(dropDownConfig.filter());
		setHeaderTemplateFn(dropDownConfig.headerTemplateFn());
		setHeaderTemplateStr(dropDownConfig.headerTemplateStr());
		setHeight(dropDownConfig.height());
		setIgnoreCase(dropDownConfig.ignoreCase().getBool());
		setIndex(dropDownConfig.index());
		setMinLength(dropDownConfig.minLength());
		setOptionLabel(dropDownConfig.optionLabel());
		setTemplateFn(dropDownConfig.templateFn());
		setTemplateStr(dropDownConfig.templateStr());
		setText(dropDownConfig.text());
		setValue(dropDownConfig.value());
		setValuePrimitive(dropDownConfig.valuePrimitive().getBool());
		setValueTemplateFn(dropDownConfig.valueTemplateFn());
		setValueTemplateStr(dropDownConfig.valueTemplateStr());
	}
	
	@Override
	public JsField resolve(String field) {
		if(field.equals("dataSource")) {
			return resolveDataSource();
		} else if(field.equals("headerTemplate")) {
			return resolveHeaderTemplate();
		} else if(field.equals("template")) {
			return resolveTemplate();
		} else if(field.equals("valueTemplate")) {
			return resolveValueTemplate();
		}
		return null;
	}

	private JsField resolveValueTemplate() {
		if(StringUtils.isNotEmpty(valueTemplateFn)) {
			return new JsFieldVariable("valueTemplate", valueTemplateFn, "valueTemplateFn");
		} else if(StringUtils.isNotEmpty(valueTemplateStr)) {
			return new JsFieldVariable("valueTemplate", valueTemplateStr, "valueTemplateStr");
		}
		return null;
	}

	private JsField resolveTemplate() {
		if(StringUtils.isNotEmpty(templateFn)) {
			return new JsFieldVariable("template", templateFn, "templateFn");
		} else if(StringUtils.isNotEmpty(templateStr)) {
			return new JsFieldVariable("template", templateStr, "templateStr");
		}
		return null;
	}

	private JsField resolveHeaderTemplate() {
		if(StringUtils.isNotEmpty(headerTemplateFn)) {
			return new JsFieldVariable("headerTemplate", headerTemplateFn, "headerTemplateFn");
		} else if(StringUtils.isNotEmpty(headerTemplateStr)) {
			return new JsFieldVariable("headerTemplate", headerTemplateStr, "headerTemplateStr");
		}
		return null;
	}

	private JsField resolveDataSource() {
		if(dataSourceArray != null && dataSourceArray.length != 0) {
			return new JsFieldArray("dataSource", dataSourceArray, "dataSourceArray");
		}
		return new JsFieldObject("dataSource", dataSourceObj, "dataSourceObj");
	}

	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, String> getRawKeyValues(String fieldName) {
		if(fieldName.equals("events")) {
			return getEventsObject();
		}
		return null;
	}

	private Map<String, String> getEventsObject() {
		if(isNotEmpty(events)) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for(EventVo event : events) {
				map.put(event.getName(), event.getHandler());
			}
			return map;
		}
		return null;
	}


	public FieldResolver getFieldResolver() {
		return fieldResolver;
	}


	public void setFieldResolver(FieldResolver fieldResolver) {
		this.fieldResolver = fieldResolver;
	}

}
