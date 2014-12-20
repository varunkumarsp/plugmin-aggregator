package org.openxava.annotations.parse;


public class FieldResolverNoOpImpl implements FieldResolver {


	@Override
	public String resolve(String field) {
		return field;
	}

	@Override
	public String associatedEntityName(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAssociatedEntity(String field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAssociatedEntityField(String field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNameField(String field, String associatedEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIdField(String field, String associatedEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
