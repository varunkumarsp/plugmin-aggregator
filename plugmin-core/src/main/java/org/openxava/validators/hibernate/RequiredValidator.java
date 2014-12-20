package org.openxava.validators.hibernate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.openxava.annotations.Required;
import org.openxava.validators.IPropertyValidator;
import org.openxava.validators.meta.MetaValidators;

/**
 * Implements Required annotation of OpenXava as a Hibernate validator. <p>
 *  
 * @author Javier Paniza
 */

public class RequiredValidator implements Validator {
	

	
	public void initialize(Required required) {
	}

	public boolean isValid(Object value) {
		if (HibernateValidatorInhibitor.isInhibited()) return true; // Usually when saving from MapFacade, MapFacade already has done the validation
		if (value == null) return false;		
		try {
			IPropertyValidator validator = 
				MetaValidators.getMetaValidatorRequiredFor(value.getClass().getName()).
					getPropertyValidator();			
			validator.validate(FailingMessages.getInstance(), value, "", "");
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
