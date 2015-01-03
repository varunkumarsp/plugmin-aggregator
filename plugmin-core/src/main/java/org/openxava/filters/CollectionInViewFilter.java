package org.openxava.filters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.openxava.util.Maps;
import org.openxava.util.XavaException;

/**
 * Used for implementing collections using a {@link org.openxava.tab.Tab} from a
 * {@link org.openxava.view.View}. <p>
 * 
 * 
 * @author Javier Paniza
 */

public class CollectionInViewFilter extends CollectionInViewBaseFilter {
	
	protected Collection getKeyValues() throws XavaException {	
		Collection keyNames = getView().getMetaModel().getAllKeyPropertiesNames();
		Collection values = new ArrayList();		
		Map viewValues = getView().getKeyValues();
		for (Iterator it = keyNames.iterator(); it.hasNext();) {
			String keyName = (String) it.next();
			values.add(Maps.getValueFromQualifiedName(viewValues, keyName));				
		}
		return values;
	}

}
