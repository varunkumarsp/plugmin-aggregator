package org.openxava.annotations.extended.ui.config;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.enums.Boolean;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface Async {

	Boolean autoUpload() default NULL;
	
	Boolean batch() default NULL;
	
	String removeField() default "";
	
	String removeUrl() default "";
	
	String removeVerb() default "";
	
	String saveField() default "";
	
	String saveUrl() default "";
	
	Boolean withCredentials() default NULL;
	
}
