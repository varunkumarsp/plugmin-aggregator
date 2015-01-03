package org.openxava.annotations.extended.ui.config.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SelectableMode  {
	ROW("row"),
	CELL("cell"),
	MULTIPLE_ROW("multiple, row"),
	MULTIPLE_CELL("multiple, cell"),
	NULL(null);
	
	public static final class BootstrapSingleton {
        public static final Map<String, SelectableMode> lookup = new HashMap<String, SelectableMode>();
   }
    
	private final String selectable;

	private SelectableMode(String selectable) {
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

	public static SelectableMode get(String str){
		return BootstrapSingleton.lookup.get(str);
	}
	
	public static SelectableMode getEnum(String str){
	    for(SelectableMode sel : values()){
	        if( sel.getSelectable().equals(str)){
	            return sel;
	        }
	    }
	    return null;
	}
	
}
