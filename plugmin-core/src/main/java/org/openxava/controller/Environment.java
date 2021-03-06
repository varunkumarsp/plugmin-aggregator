package org.openxava.controller;

import java.util.Map;

import org.openxava.controller.meta.MetaControllers;
import org.openxava.util.XavaException;

/**
 * It read first the variables sent on create (it extracted from module),
 * and then all variable defined in controllers. <p>
 * 
 * @author Javier Paniza
 */

public class Environment implements java.io.Serializable {
	
	private Map localVariables;
	
	
	public Environment(Map localVariables) {
		this.localVariables = localVariables;
	}

	/**
	 * @return Null if does not exists.
	 */
	public String getValue(String name) throws XavaException {
		String value = null;
		if (localVariables != null) {
			value = (String) localVariables.get(name);
		}
		if (value != null) return value;
		return MetaControllers.getEnvironmentVariable(name);
	}

}
