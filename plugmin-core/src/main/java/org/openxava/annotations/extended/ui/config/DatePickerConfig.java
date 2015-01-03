package org.openxava.annotations.extended.ui.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.Animation;
import org.openxava.annotations.extended.Event;
import org.openxava.annotations.extended.ui.config.enums.Depth;
import org.openxava.annotations.extended.ui.config.enums.Start;

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
public @interface DatePickerConfig {
	
	Animation animation() default @Animation;
	
	String ARIATemplate() default "";
	
	String culture() default "";
	
	String[] dates() default {};
	
	Depth depth() default Depth.NULL;
	
	String footerStr() default "";
	
	String footerFn() default "";
	
	String format() default "";
	
	String max() default "";
	
	String min() default "";
	
	Month month() default @Month;
	
	String parseFormats() default "";
	
	Start start() default Start.NULL;
	
	String value() default "";
	
	Event[] events() default {};
	
	String name() default "";

}
