package org.openxava.tab.impl;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.openxava.ejbx.SessionBase;

/**
 * 
 * @author Javier Paniza
 */
public class EntityTabBean extends SessionBase implements IEntityTabDataProvider {
	
	private EntityTabDataProvider dataProvider = new EntityTabDataProvider();
	
	
	
	public void ejbCreate() {		
	}
	
	public DataChunk nextChunk(ITabProvider tabProvider, String modelName, List propertiesNames, Collection tabCalculators, Map keyIndexs) throws RemoteException {	
		return dataProvider.nextChunk(tabProvider, modelName, propertiesNames, tabCalculators, keyIndexs);
	}

	public int getResultSize(ITabProvider tabProvider) {	
		return dataProvider.getResultSize(tabProvider);		
	}
	
	public Number getSum(ITabProvider tabProvider, String property) { 	
		return dataProvider.getSum(tabProvider, property);				
	}
		
}
