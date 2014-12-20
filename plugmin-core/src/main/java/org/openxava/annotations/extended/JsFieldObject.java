package org.openxava.annotations.extended;

public class JsFieldObject implements JsField {

private String fieldName;
	
	private Object fieldValue;
	
	private String compositeField;
	
	public JsFieldObject(String fieldName, Object fieldValue, String compositeField) {
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
