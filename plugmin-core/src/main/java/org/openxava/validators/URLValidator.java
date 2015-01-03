package org.openxava.validators;

import org.apache.commons.validator.UrlValidator;
import org.openxava.util.Messages;



/**
 * 
 * @author Janesh Kodikara
 */

public class URLValidator implements IPropertyValidator {

    private UrlValidator urlValidator = new UrlValidator();

    public void validate(Messages errors, Object value, String propertyName, String modelName) throws Exception {

        if (value == null || value.toString().length() ==0 ) return;

        if (! urlValidator.isValid(value.toString())) {
           errors.add("url_validation_error", propertyName);
        }
    }

}



