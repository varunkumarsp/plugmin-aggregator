package org.openxava.generators;

import org.openxava.model.meta.MetaProperty;

/**
 * To customize the code generation of a concrete property. <p>
 * 
 * @author Javier Paniza
 */
public interface IPropertyCodeGenerator {
	
	void setMetaProperty(MetaProperty metaProperty);
	
	String generate() throws Exception;

}
