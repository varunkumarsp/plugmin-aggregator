package org.openxava.annotations.extended;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Selectable  {
	ROW("row"),
	CELL("cell"),
	MULTIPLE_ROW("multiple, row"),
	MULTIPLE_CELL("multiple, cell"),
	NULL(null);
	
	public static final class BootstrapSingleton {
        public static final Map<String, Selectable> lookup = new HashMap<String, Selectable>();
   }
    
	private final String selectable;

	private Selectable(String selectable) {
		this.selectable = selectable;
		BootstrapSingleton.lookup.put(selectable, this);
	}

	public String getSelectable() {
		return selectable;
	}
	
	public String getValue() {
		return selectable;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.selectable;
	}

	public static Selectable get(String str){
		return BootstrapSingleton.lookup.get(str);
	}
	
	public static Selectable getEnum(String str){
	    for(Selectable sel : values()){
	        if( sel.getSelectable().equals(str)){
	            return sel;
	        }
	    }
	    return null;
	}
	
}
