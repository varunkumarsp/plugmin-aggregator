package org.openxava.actions;

import java.util.Map;

import javax.ejb.ObjectNotFoundException;

import org.openxava.model.MapFacade;
import org.openxava.validators.ValidationException;

/**
 * @author Javier Paniza
 */

public class UpdateAction extends UpdateReferenceBaseAction  {
	
	
	
	public void execute() throws Exception {		
		try {					
			// Update
			Map key = getView().getKeyValues();			
			MapFacade.setValues(getView().getModelName(), key, getValuesToSave());
			returnsToPreviousViewUpdatingReferenceView(key);
		}
		catch (ValidationException ex) {			
			addErrors(ex.getErrors());
		}
		catch (ObjectNotFoundException ex) {
			addError("no_modify_no_exists");
		}
	}
	
}
