package org.openxava.annotations.parse;

import java.util.ArrayList;
import java.util.List;

import org.openxava.annotations.extended.ui.config.vo.ColumnVo;

public class FieldResolverMetaTabImpl implements FieldResolver {

	
	private List<ColumnVo> columns;
	

	public FieldResolverMetaTabImpl(List<ColumnVo> columns) {
		this.columns = new ArrayList<ColumnVo>();
		for (ColumnVo column : columns) {
			if(!column.isCommandColumn()) {
				this.columns.add(column);
			}
		}
	}

	@Override
	public String resolve(String projectionField) {
		for (ColumnVo column : columns) {
			if(column.getField().equals(projectionField)) {
				if(column.isForeignEntity())
					return projectionField + "." + column.getForeignEntityNameField();
				break;
			}
		}
		return projectionField;
	}

	@Override
	public String associatedEntityName(String field) {
		if(field.contains("_")) {
			field = field.substring(field.indexOf("_") + 1).replaceAll("_", ".");
	    	for (ColumnVo column : columns) {
	    		if(column.isForeignEntity()) {
	    			if(column.getForeignEntityIdField().equals(field) || column.getForeignEntityNameField().equals(field))
	    				return column.getField();
	    		}
			}
		}
		return null;
	}

	@Override
    public boolean isAssociatedEntity(String field) {
		if(field.contains("_")) {
			field = field.substring(field.indexOf("_") + 1).replaceAll("_", ".");
	    	for (ColumnVo column : columns) {
	    		if(column.isForeignEntity()) {
	    			if(column.getForeignEntityIdField().equals(field) || column.getForeignEntityNameField().equals(field))
	    				return true;
	    		}
			}
		}
		return false;
	}
	
	@Override
    public boolean isAssociatedEntityField(String field) {
		field = field.replaceAll("_", ".");
    	for (ColumnVo column : columns) {
    		if(column.getField().equals(field)) {
    			if(!column.isForeignEntity())
    				return true;
    			break;
    		}
		}
		return false;
	}
	
	@Override
	public boolean isNameField(String field, String associatedEntity) {
		field = field.substring(field.indexOf("_") + 1).replaceAll("_", ".");
		for (ColumnVo column : columns) {
    		if(column.isForeignEntity() && column.getField().equals(associatedEntity)) {
    			if(column.getForeignEntityNameField().equals(field))
    				return true;
    			break;
    		}
		}
		return false;
	}
	
	@Override
	public boolean isIdField(String field, String associatedEntity) {
		field = field.substring(field.indexOf("_") + 1).replaceAll("_", ".");
		for (ColumnVo column : columns) {
    		if(column.isForeignEntity() && column.getField().equals(associatedEntity)) {
    			if(column.getForeignEntityIdField().equals(field))
    				return true;
    			break;
    		}
		}
		return false;
	}
    
	public List<ColumnVo> getColumns() {
		return columns;
	}

}
