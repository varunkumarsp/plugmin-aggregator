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
public @interface UploadConfig {
	
	Async async() default @Async;
	
	Boolean enabled() default NULL;
	
	String files() default "";
	
	Boolean multiple() default NULL;
	
	Boolean showFileList() default NULL;
	
	String templateStr() default "";
	
	String templateFn() default "";
		
	Event[] events() default {};
	
	String name() default "";

}
