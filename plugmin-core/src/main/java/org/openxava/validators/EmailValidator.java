package org.openxava.validators;

import org.apache.commons.validator.GenericValidator;
import org.openxava.util.Messages;

/**
 * 
 * @author Janesh Kodikara
 */

public class EmailValidator implements IPropertyValidator {

    public void validate(Messages errors, Object value, String propertyName, String modelName) throws Exception {
    	if (value == null || value.toString().length() == 0) return;     	 
    	if (! GenericValidator.isEmail(value.toString())) { 
    		errors.add("email_validation_error", propertyName); 
    		return; 
    	}     
    }

}