package org.openxava.validators.hibernate;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.openxava.annotations.EntityValidator;
import org.openxava.annotations.parse.AnnotatedClassParser;
import org.openxava.util.PropertiesManager;
import org.openxava.util.meta.MetaSet;
import org.openxava.validators.IValidator;
import org.openxava.validators.meta.MetaValidator;

/**
 * Implements a EntityValidator of OpenXava as a Hibernate validator. <p>
 *  
 * @author Javier Paniza
 */

public class EntityValidatorValidator implements Validator {
	
	private MetaValidator metaValidator;

	public void initialize(EntityValidator entityValidator) {
		metaValidator = AnnotatedClassParser.createEntityValidator(entityValidator);
	}

	public boolean isValid(Object entity) {	
		if (HibernateValidatorInhibitor.isInhibited()) return true;  // Usually when saving from MapFacade, MapFacade already has done the validation
		if (metaValidator.isOnlyOnCreate()) return true;
		try {			
			Iterator itSets =  metaValidator.getMetaSetsWithoutValue().iterator();			 
			IValidator v = metaValidator.createValidator();
			PropertiesManager validatorProperties = new PropertiesManager(v);
			PropertiesManager entityProperties = new PropertiesManager(entity);
			while (itSets.hasNext()) {
				MetaSet set = (MetaSet) itSets.next();					
				Object value = entityProperties.executeGet(set.getPropertyNameFrom());								
				validatorProperties.executeSet(set.getPropertyName(), value);									
			}							
			v.validate(FailingMessages.getInstance());
			return true;
		}
		catch (IllegalStateException ex) {
			if (FailingMessages.EXCEPTION_MESSAGE.equals(ex.getMessage())) return false;
			throw ex;
		}
		catch (RuntimeException ex) {
			throw ex;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage(), ex);
		}		
	}

	@Override
	public BeanDescriptor getConstraintsForClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validate(T arg0, Class<?>... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateProperty(T arg0,
			String arg1, Class<?>... arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateValue(Class<T> arg0,
			String arg1, Object arg2, Class<?>... arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExecutableValidator forExecutables() {
		// TODO Auto-generated method stub
		return null;
	}

}
