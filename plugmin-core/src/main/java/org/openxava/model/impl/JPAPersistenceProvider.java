package org.openxava.model.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.RemoveException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.jpa.XPersistence;
import org.openxava.model.meta.MetaModel;
import org.openxava.tab.impl.ITabProvider;
import org.openxava.tab.impl.JPATabProvider;
import org.openxava.util.XavaException;
import org.openxava.util.XavaResources;

/**
 * Persistence Manager for EJB3 JPA.
 * 
 * @author Javier Paniza
 */
public class JPAPersistenceProvider extends POJOPersistenceProviderBase {
	
	private static Log log = LogFactory.getLog(JPAPersistenceProvider.class);
	
	protected Object find(Class pojoClass, Serializable key) {
		try {
			flush(); 
			Object result = XPersistence.getManager().find(pojoClass, key);  
			if (result != null) refreshIfManaged(result); 			
			return result;
		}
		catch (EntityNotFoundException ex) {
			// As in JPA specification find does not throw EntityNotFoundException
			// but Hibernate (at least 3.2RC2) throw it (maybe a bug?)
			return null;
		}
	}
	
	protected void persist(Object object) {
		try { 
			XPersistence.getManager().persist(object);
		}
		catch (org.openxava.validators.ValidationException ex) {
			rollback(); 
			throw ex;
		}
		catch (javax.validation.ConstraintViolationException ex) {
			rollback();
			throw ex;
		}
		catch (javax.validation.ValidationException ex) { 
			rollback();
			throw new org.openxava.validators.ValidationException(ex.getMessage());
		}		
	}
	 
	public void remove(MetaModel metaModel, Map keyValues)
			throws RemoveException, XavaException {  
		try {
			Object model = find(metaModel, keyValues, false);  
			XPersistence.getManager().remove(model);
		}
		catch (org.openxava.validators.ValidationException ex) {
			rollback();
			throw new org.openxava.validators.ValidationException(XavaResources.getString("remove_error",
					metaModel.getName(), XavaResources.getString(ex.getMessage())));
		}
		catch (javax.validation.ValidationException ex) {
			rollback();
			throw new org.openxava.validators.ValidationException(XavaResources.getString("remove_error",
					metaModel.getName(), XavaResources.getString(ex.getMessage())));
		}		
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new RemoveException(XavaResources.getString("remove_error",
					metaModel.getName(), ex.getMessage()));
		}
	}
	
	public void begin() {		 
	}
		
	public void commit() {
		flush(); 
		XPersistence.commit();		 				
	}

	public void rollback() {	
		XPersistence.rollback();		 
	}
	
	public void reassociate(Object entity) {
		XPersistence.getManager().lock(entity, LockModeType.WRITE);  		
	}

	
	public void flush() {		
		XPersistence.getManager().flush();				
	}

	protected Object createQuery(String query) { 		
		return XPersistence.getManager().createQuery(query);
	}

	protected void setParameterToQuery(Object query, String name, Object value) {
		((javax.persistence.Query) query).setParameter(name, value);
	}

	protected Object getUniqueResult(Object query) {
		Iterator it = ((javax.persistence.Query) query).getResultList().iterator();
		if (!it.hasNext()) return null;
		return it.next();
	}

	public void refreshIfManaged(Object object) {
		if (isManaged(object)) {
			XPersistence.getManager().refresh(object);
		}
	}
	
	private boolean isManaged(Object object) {
		try {
			return XPersistence.getManager().contains(object);
		}
		catch (IllegalArgumentException ex) {
			return false; // Surely it's not annotated with @Entity
		}		
	}

	public ITabProvider createTabProvider() {
		return new JPATabProvider();												
	}

}
