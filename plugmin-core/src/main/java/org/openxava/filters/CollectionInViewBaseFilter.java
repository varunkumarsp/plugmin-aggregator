package org.openxava.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.util.XavaException;
import org.openxava.util.XavaResources;
import org.openxava.view.View;

/**
 * Used for implementing collections using a {@link org.openxava.tab.Tab} 
 * from a {@link org.openxava.view.View}. <p>
 * 
 * @author Javier Paniza
 */

abstract public class CollectionInViewBaseFilter implements IFilter {
	
	private static Log log = LogFactory.getLog(CollectionInViewBaseFilter.class);
	private View view;	

	public Object filter(Object o) throws FilterException {
		try {
			Collection keyValues = getKeyValues();
			if (o instanceof Object []) {			
				List c = new ArrayList(Arrays.asList((Object []) o));
				keyValues.addAll(c);						
			} 
			else if (o != null){
				keyValues.add(o);
				
			}
			return keyValues.toArray();
		}
		catch (XavaException ex) {
			log.error(ex);
			throw new FilterException(XavaResources.getString("value_from_view_for_filtering_collection_error")); 
		}

	}

	/**
	 * Values to add as key at the begin of arguments for filtering. <p>
	 */
	abstract protected Collection getKeyValues() throws XavaException; 

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}	

}
