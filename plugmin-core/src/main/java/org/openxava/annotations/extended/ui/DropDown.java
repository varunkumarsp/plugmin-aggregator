package org.openxava.annotations.extended.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define the behavior for <b>drop down box</b>. <p>
 * 
 * Applies to entities. <p> 
 * 
 * Example:
 * <pre>
 * </pre>
 * 
 * @author Varun Kumar
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
public @interface DropDown {

	String name();
	
	String keyProperty() default "";
	
	String valueProperty() default "";
	
	String config() default "";
	
}
