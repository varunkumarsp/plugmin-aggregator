package org.openxava.actions;

import java.util.Map;

import org.openxava.model.MapFacade;

/**
 * 
 * @author Genaro Tur
 */

public class SaveElementInManyToManyCollectionAction extends CollectionElementViewBaseAction {
	
	public void execute() throws Exception {
		String collectionModel = getCollectionElementView().getModelName();
	    String collection = getCollectionElementView().getMemberName();
	    String viewModel = getPreviousView().getModelName();
	    Map viewValues = getPreviousView().getKeyValues();
	    
		Map mapAux = getCollectionElementView().getValues(); 
		Map created = MapFacade.createReturningKey(collectionModel, mapAux);

		MapFacade.addCollectionElement(viewModel, viewValues, collection, created);
		getPreviousView().refreshCollections();
        closeDialog();
   }
	
}
