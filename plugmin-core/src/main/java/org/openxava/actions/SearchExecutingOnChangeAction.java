package org.openxava.actions;

import java.util.Map;

/**
 * 
 * @author Javier Paniza
 */
public class SearchExecutingOnChangeAction extends SearchByViewKeyAction {
	
	protected void setValuesToView(Map values) throws Exception {		
		getView().setValuesExecutingOnChangeActions(values);		
	}

}
