package org.openxava.annotations.extended.ui.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.Animation;
import org.openxava.annotations.extended.Event;

/**
 * Define the behavior for <b>datepicker</b>. <p>
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
public @interface TimePickerConfig {
	
	Animation animation() default @Animation;
	
	String culture() default "";
	
	String[] dates() default {};
	
	String format() default "";
	
	int interval() default -1;
	
	String max() default "";
	
	String min() default "";
	
	String parseFormats() default "";
	
	String value() default "";
	
	Event[] events() default {};
	
	String name() default "";

}
