package org.openxava.annotations.extended;

public class JsFieldString implements JsField {

	private String fieldName;
	
	private String fieldValue;
	
	private String compositeField;
	
	public JsFieldString(String fieldName, String fieldValue, String compositeField) {
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
