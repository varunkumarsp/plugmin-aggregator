package org.openxava.actions;

import javax.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;
import org.openxava.model.MapFacade;
import org.openxava.util.Is;

/**
 * Logic of AttachedFiles.add action in default-controllers.xml. <p>
 *  
 * @author Jeromy Altuna
 */
public class AddFileToFilesetAction extends ViewBaseAction implements 
														   ILoadFileAction 
{
	@Inject
	private String newFilesetProperty;
	
	@Override
	public void execute() throws Exception {
		String libraryId = getView().getValueString(getNewFilesetProperty());
		if(Is.emptyString(libraryId)) {
			libraryId = (String) new org.openxava.calculators.UUIDCalculator().calculate();
			getView().setValue(getNewFilesetProperty(), libraryId);
			if(!getView().isKeyEditable()) {
				MapFacade.setValues(getView().getModelName(), 
									getView().getKeyValues(), 
									ArrayUtils.toMap(new String[][] { 
										{ getNewFilesetProperty(), libraryId }  
									}));
			}
		}
		showDialog();
	}
	
	@Override
	public String[] getNextControllers() throws Exception {
		return new String[]{ "UploadFileIntoFileset" };
	}
	
	@Override
	public String getCustomView() throws Exception {
		return "xava/editors/addFiles";
	}

	@Override
	public boolean isLoadFile() {
		return true;
	}

	public String getNewFilesetProperty() {
		return newFilesetProperty;
	}

	public void setNewFilesetProperty(String newFilesetProperty) {
		this.newFilesetProperty = newFilesetProperty;
	}	
}
