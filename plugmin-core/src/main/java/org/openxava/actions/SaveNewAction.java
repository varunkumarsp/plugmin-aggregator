package org.openxava.actions;

import java.util.Map;

import javax.ejb.DuplicateKeyException;

import org.openxava.model.MapFacade;
import org.openxava.validators.ValidationException;

/**
 * @author Javier Paniza
 */

public class SaveNewAction extends UpdateReferenceBaseAction {

	public void execute() throws Exception {
		try {
			Map key = null;
			if (getView().isKeyEditable()) {
				// Create
				key = MapFacade.createReturningKey(getView().getModelName(),
						getValuesToSave());
			} else {
				// Modify, usually when collection exists, because 
				// saving an element collection saves automatically the main entity
				key = getView().getKeyValues();
				MapFacade.setValues(getView().getModelName(), key,
						getValuesToSave());
			}
			returnsToPreviousViewUpdatingReferenceView(key);
		} 
		catch (ValidationException ex) {
			addErrors(ex.getErrors());
		} 
		catch (DuplicateKeyException ex) {
			addError("no_create_exists");
		}

	}

}
