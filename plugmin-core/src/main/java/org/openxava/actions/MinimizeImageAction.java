package org.openxava.actions;



import javax.inject.Inject;

import org.openxava.session.Gallery;

/**
 * 
 * @author Javier Paniza
 */

public class MinimizeImageAction extends BaseAction {
	
	@Inject
	private Gallery gallery;
	

	public void execute() throws Exception {
		gallery.setMaximized(false);
	}

}
