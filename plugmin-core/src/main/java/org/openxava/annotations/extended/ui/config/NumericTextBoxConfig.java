package org.openxava.annotations.extended.ui.config;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.Event;
import org.openxava.annotations.extended.ui.config.enums.Boolean;

/**
 * Define the behavior for <b>NumericTextBox</b>. <p>
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
public @interface NumericTextBoxConfig {
	
	String culture() default "";
	
	int decimals() default -1;
	
	String downArrowText() default "";

	String format() default "";
	
	int max() default -1;
	
	int min() default -1;
	
	String placeholder() default "";
	
	Boolean spinners() default NULL;
	
	int step() default -1;
	
	String upArrowText() default "";
	
	int value() default -1;
	
	Event[] events() default {};
	
	String name() default "";

}
