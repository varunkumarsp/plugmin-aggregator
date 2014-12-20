package org.openxava.annotations.parse;


public interface FieldResolver {

	public String resolve(String field);

	String associatedEntityName(String field);

	boolean isAssociatedEntity(String field);

	public boolean isAssociatedEntityField(String field);

	public boolean isNameField(String field, String associatedEntity);

	boolean isIdField(String field, String associatedEntity);
}
