package org.openxava.actions;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.fileupload.FileItem;
import org.openxava.util.Is;
import org.openxava.web.editors.AttachedFile;
import org.openxava.web.editors.FilePersistorFactory;

/**
 * Logic of UploadFile.uploadFile action in default-controllers.xml. <p>
 * 
 * Instance {@link org.openxava.web.editors.AttachedFile} object and stores it 
 * in a file container. <p>
 * 
 * @author Jeromy Altuna
 */
public class UploadFileAction extends ViewBaseAction implements INavigationAction, 
																IProcessLoadedFileAction 
{
	
	@SuppressWarnings("rawtypes")
	private List fileItems;
	
	@Inject
	private String newFileProperty;
		
	public void execute() throws Exception {
		Iterator<?> it = getFileItems().iterator();
		while (it.hasNext()) {
			FileItem fi = (FileItem) it.next();					
			if (!Is.emptyString(fi.getName())) {
				AttachedFile file = new AttachedFile();
				file.setName(fi.getName());
				file.setData(fi.get());
				FilePersistorFactory.getInstance().save(file);
				getPreviousView().setValue(getNewFileProperty(), file.getId());
				break;
			}			
		}		
		closeDialog(); 
	}
	
	@SuppressWarnings("rawtypes")
	public List getFileItems() {
		return fileItems;
	}

	@SuppressWarnings("rawtypes")
	public void setFileItems(List fileItems) {
		this.fileItems = fileItems; 
	}

	public String[] getNextControllers() throws Exception {
		return PREVIOUS_CONTROLLERS;
	}

	public String getCustomView() throws Exception {
		return PREVIOUS_VIEW;
	}

	public String getNewFileProperty() {
		return newFileProperty;
	}

	public void setNewFileProperty(String newFileProperty) {
		this.newFileProperty = newFileProperty;
	}	
}
