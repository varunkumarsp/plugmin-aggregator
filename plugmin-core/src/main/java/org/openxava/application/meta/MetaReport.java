package org.openxava.application.meta;

import java.io.Serializable;




/**
 * @author Javier Paniza
 */
public class MetaReport implements Serializable {
	
	private String modelName;
	private String tabName;
	
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

}
