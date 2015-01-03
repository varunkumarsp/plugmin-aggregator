package org.openxava.actions;



import javax.inject.Inject;

import org.openxava.session.Gallery;

/**
 * 
 * @author Javier Paniza
 */

public class RemoveImageFromGalleryAction extends BaseAction {
	
	@Inject
	private Gallery gallery;
	private String oid;
	
	
	public void execute() throws Exception {
		gallery.removeImage(oid);
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

}
