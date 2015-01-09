package org.openxava.annotations.parse;

import java.util.List;

import org.openxava.annotations.extended.ui.config.vo.ElementVo;

public class FieldResolverMetaViewImpl extends FieldResolverNoOpImpl {

	
	private List<ElementVo> elements;
	

	public FieldResolverMetaViewImpl(List<ElementVo> elements) {
		this.elements = elements;
	}

	@Override
	public String resolve(String projectionField) {
		for (ElementVo element : elements) {
			if(element.getField().equals(projectionField)) {
				if(element.isForeignEntity())
					return projectionField + "." + element.getForeignEntityIdField();
				break;
			}
		}
		return projectionField;
	}

	@Override
	public String associatedEntityName(String field) {
		if(field.contains("_")) {
			field = field.substring(field.indexOf("_") + 1).replaceAll("_", ".");
	    	for (ElementVo element : elements) {
	    		if(element.isForeignEntity()) {
	    			if(element.getForeignEntityIdField().equals(field))
	    				return element.getField();
	    		}
			}
		}
		return null;
	}
	
	@Override
    public boolean isAssociatedEntity(String field) {
		if(field.contains("_")) {
			field = field.substring(field.indexOf("_") + 1).replaceAll("_", ".");
	    	for (ElementVo element : elements) {
	    		if(element.isForeignEntity()) {
	    			if(element.getForeignEntityIdField().equals(field))
	    				return true;
	    		}
			}
		}
		return false;
	}
	
	public List<ElementVo> getElements() {
		return elements;
	}

}
