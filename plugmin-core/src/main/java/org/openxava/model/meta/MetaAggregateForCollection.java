package org.openxava.model.meta;




import org.openxava.component.MetaComponent;
import org.openxava.mapping.ModelMapping;
import org.openxava.util.Strings;
import org.openxava.util.XavaException;

/**
 * 
 * @author Javier Paniza
 */

public class MetaAggregateForCollection extends MetaAggregate {
		
	public ModelMapping getMapping() throws XavaException {
		return getMetaComponent().getAggregateMapping(getName());
	}
		
	public void setMetaComponent(MetaComponent metaComponent) {
		super.setMetaComponent(metaComponent);
	}
			
	public Class getBeanClass() throws XavaException {
		throw new UnsupportedOperationException ("Still not supported");
		
	}
	
	public String getContainerReference() {
		if (super.getContainerReference() == null) setContainerReference(Strings.firstLower(getContainerModelName()));  
		return super.getContainerReference();
	}
	
}

