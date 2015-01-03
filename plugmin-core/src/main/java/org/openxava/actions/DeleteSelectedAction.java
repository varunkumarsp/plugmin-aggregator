package org.openxava.actions;

import java.util.Map;

import org.openxava.model.MapFacade;
import org.openxava.validators.ValidationException;

/**
 * 
 * @author Javier Paniza
 */

public class DeleteSelectedAction extends TabBaseAction implements IModelAction {
		
	private String model;
		
	public void execute() throws Exception {				
		Map [] selectedOnes = getSelectedKeys(); 
		if (selectedOnes != null) {						
			for (int i = 0; i < selectedOnes.length; i++) {				
				Map key = selectedOnes[i];
				try {									
					MapFacade.remove(model, key);				
				}
				catch (ValidationException ex) {
					addError("no_delete_row", new Integer(i), key);
					addErrors(ex.getErrors());
				}								
				catch (Exception ex) { 
					addError("no_delete_row", new Integer(i), key);
				}						
			}
			getTab().deselectAll();
			resetDescriptionsCache();
		}
	}

	public void setModel(String modelName) {
		this.model = modelName;		
	}
	
}
