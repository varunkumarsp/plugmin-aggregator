package org.openxava.annotations.parse;

import java.util.Map;

public class FieldResolverMapImpl implements FieldResolver {

	private Map<String, String> map;

	public FieldResolverMapImpl(Map<String, String> map) {
		this.map = map;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	@Override
	public String resolve(String field) {
		return map.get(field);
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
