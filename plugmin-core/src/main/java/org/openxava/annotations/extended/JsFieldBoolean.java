package org.openxava.annotations.extended;

public class JsFieldBoolean implements JsField {

	private String fieldName;
	
	private java.lang.Boolean fieldValue;
	
	private String compositeField;
	
	public JsFieldBoolean(String fieldName, java.lang.Boolean fieldValue, String compositeField) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.compositeField = compositeField;
	}

	@Override
	public String getFieldName() {
		return this.fieldName;
	}
	
	@Override
	public Object getFieldValue() {
		return this.fieldValue;
	}
	
	@Override
	public String getCompositeFieldName() {
		return this.compositeField;
	}

}
