package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.extended.ui.config.vo.FieldType.BOOLEAN;
import static org.openxava.annotations.extended.ui.config.vo.FieldType.DATE;
import static org.openxava.annotations.extended.ui.config.vo.FieldType.NUMBER;
import static org.openxava.annotations.extended.ui.config.vo.FieldType.STRING;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldObject;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;
import org.openxava.tab.meta.MetaTab;
import org.openxava.util.meta.MetaElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class FieldVo implements JsFieldValueResolver {

	@CompositeField("editable")
	@DefaultValue("true")
	private Boolean editable;
	
	@DefaultValue("false")
	private Boolean nullable;
	
	@DefaultValue("string")
	private FieldType type;
	
	private ValidationVo validation;
	
	@CompositeField("defaultValue")
	private Object defaultValue;
	
	@CompositeField("defaultValue")
	private String defaultValueJsVar;
	
	@JsonIgnore
	private MetaElement metaElement; //for internal use only
	

	public FieldVo(ColumnVo column) {
		if(!column.isCommandColumn()) {
			metaElement = column.getMetaElement();
			init(column);
			if(column.isIdField()) {
				editable = false;
				nullable = true;
			}
			
			if(!column.isEditable()) { //Required for associated entities more than one column.
				editable = false;
				nullable = true;
			}
			
			if(column.isForeignEntity()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(column.getForeignEntityIdField(), "");
				map.put(column.getForeignEntityNameField(), "");
				this.defaultValue = map;
			}
		}
	}

	/**
	 * Used by model-only fields
	 * @param fieldType
	 * @param defaultValue2
	 */
	public FieldVo(Class<?> fieldType, String defaultValue) {
		setType(fieldType);
		setDefaultValueJsVar(defaultValue);
	}

	private void init(ColumnVo column) {
		Field metaField = column.getMetaField();
		DefaultValue defaultValueAnn = metaField.getAnnotation(DefaultValue.class);
		if(defaultValueAnn != null)
			this.defaultValue = defaultValueAnn.value();
		
		if(!column.isForeignEntity()) {
			setType(metaField);
		}
	}

	private void setType(Field metaField) {
		Class<?> type = metaField.getType();
		setType(type);
	}

	private void setType(Class<?> type) {
		if(type.equals(java.lang.Integer.class)) {
			setType(NUMBER);
		} else if(type.equals(java.lang.Boolean.class)) {
			setType(BOOLEAN);
		} else if(type.equals(java.lang.Long.class)) {
			setType(NUMBER);
		} else if(type.equals(java.lang.Double.class)) {
			setType(NUMBER);
		} else if(type.equals(java.lang.Float.class)) {
			setType(NUMBER);
		} else if(type.equals(String.class)) {
			setType(STRING);
		} else if(type.equals(Calendar.class)) {
			setType(DATE);
		} else if(type.equals(Date.class)) {
			setType(DATE);
		} else if(type.equals(BigDecimal.class)) {
			setType(NUMBER);
		} else if(type.equals(BigInteger.class)) {
			setType(NUMBER);
		}
	}

	public Boolean isEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean isNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public ValidationVo getValidation() {
		return validation;
	}

	public void setValidation(ValidationVo validation) {
		this.validation = validation;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public JsField resolve(String field) {
		if(field.equals("defaultValue")) {
			return resolveDefaultValue();
		} else if(field.equals("editable")) {
			return resolveEditable();
		}
		return null;
	}
	
	private JsField resolveEditable() {
		if(this.editable != null) {
			boolean gridEditable = false;
			
			if(metaElement instanceof MetaTab) {
				MetaTab metaTab = (MetaTab) metaElement;
				EditableVo editable = metaTab.getConfig().getEditable();
				if(editable.getConfig() != null)
					gridEditable = true;
				else if(editable.isEditable() != null && editable.isEditable()) 
					gridEditable = true;
			}
			
			if(gridEditable) {
				if(!this.editable) {
					return new JsFieldObject("editable", editable, "editable");
				}
			}
		}
		return null;
	}

	private JsField resolveDefaultValue() {
		if(isNotEmpty(defaultValueJsVar)) {
			return new JsFieldVariable("defaultValue", defaultValueJsVar, "defaultValueJsVar");
		} else if(defaultValue != null) {
			return new JsFieldObject("defaultValue", defaultValue, "defaultValue");
		}
		return null;
	}

	public String getDefaultValueJsVar() {
		return defaultValueJsVar;
	}

	public void setDefaultValueJsVar(String defaultValueJsVar) {
		this.defaultValueJsVar = defaultValueJsVar;
	}
	
}
