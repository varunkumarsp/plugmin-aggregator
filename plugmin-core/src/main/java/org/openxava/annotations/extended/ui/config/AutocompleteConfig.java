package org.openxava.annotations.extended.ui.config;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import org.openxava.annotations.extended.ui.config.enums.Boolean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.Animation;
import org.openxava.annotations.extended.DataSource;
import org.openxava.annotations.extended.Event;
import org.openxava.annotations.extended.ui.config.enums.AutocompleteFilter;

/**
 * Define the behavior for <b>autocomplete</b>. <p>
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
public @interface AutocompleteConfig {
	
	Animation animation() default @Animation;
	
	DataSource dataSourceObj() default @DataSource;

	String[] dataSourceArray() default {};
	
//	String dataTextField() default "";
	
	int delay() default -1;
	
	Boolean enable() default NULL;
	
	AutocompleteFilter filter() default AutocompleteFilter.NULL;
	
	int height() default -1;
	
	Boolean highlightFirst() default NULL;
	
	Boolean ignoreCase() default NULL;
	
	int minLength() default -1;

	String placeholder() default "";
	
	String separator() default "";
	
	Boolean suggest() default NULL;
	
	String headerTemplateStr() default "";

	String headerTemplateFn() default "";
	
	String templateStr() default "";

	String templateFn() default "";
	
	Boolean valuePrimitive() default NULL;

	Event[] events() default {};
	
	String name() default "";
}
