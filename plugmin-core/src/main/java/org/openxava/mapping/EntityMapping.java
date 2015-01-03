package org.openxava.mapping;



import org.openxava.model.meta.MetaModel;
import org.openxava.util.XavaException;


/** 
 * @author Javier Paniza
 */

public class EntityMapping extends ModelMapping {
	
	
	
	public String getModelName() throws XavaException {
		return getMetaModel().getName();
	}

	public MetaModel getMetaModel() throws XavaException {		
		return getMetaComponent().getMetaEntity();
	}
	
	

}


