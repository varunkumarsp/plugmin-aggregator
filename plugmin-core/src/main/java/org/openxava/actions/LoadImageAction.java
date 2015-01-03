package org.openxava.actions;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.fileupload.FileItem;
import org.openxava.util.Is;
import org.openxava.util.Strings;
import org.openxava.view.View;

/**
 * @author Javier Paniza
 */

public class LoadImageAction extends ViewBaseAction implements INavigationAction, IProcessLoadedFileAction {
	
	private List fileItems;
	@Inject
	private String newImageProperty;
	

	public void execute() throws Exception {
		Iterator i = getFileItems().iterator();
		while (i.hasNext()) {
			FileItem fi = (FileItem)i.next();
			String fileName = fi.getName();			
			if (!Is.emptyString(fileName)) {
				String propertyName = Strings.lastToken(getNewImageProperty(), ".");
				findView().setValue(propertyName, fi.get());
			}			
		}		
		closeDialog(); 
	}
	
	private View findView() { 
		String property = getNewImageProperty(); 
		String []m = property.split( "\\." );   
		View result = getPreviousView(); 
		for( int i = 0; i < m.length-1; i++ ) { 
			result = result.getSubview(m[i]); 
		} 		
		return result; 
	} 
		
	public String[] getNextControllers() {				
		return PREVIOUS_CONTROLLERS;
	}

	public String getCustomView() {		
		return PREVIOUS_VIEW;
	}

	public String getNewImageProperty() {
		return newImageProperty;
	}

	public void setNewImageProperty(String string) {
		newImageProperty = string;	
	}

	public List getFileItems() {
		return fileItems;
	}

	public void setFileItems(List fileItems) {
		this.fileItems = fileItems;
	}

}
