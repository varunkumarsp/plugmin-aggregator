package org.openxava.annotations.extended.ui.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.Animation;
import org.openxava.annotations.extended.DataSource;
import org.openxava.annotations.extended.Event;
import org.openxava.annotations.extended.ui.config.enums.Boolean;
import org.openxava.annotations.extended.ui.config.enums.Operator;

/**
 * Define the behavior for <b>multi select box</b>. <p>
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
public @interface MultiSelectConfig {
	
	Animation animation() default @Animation;
	
	Boolean autoBind() default Boolean.NULL;
	
	Boolean autoClose() default Boolean.NULL;

	DataSource dataSourceObj() default @DataSource;

	String[] dataSourceArray() default {};

//	String dataTextField() default "";
//	
//	String dataValueField() default "";

	int delay() default -1;
	
	Boolean enable() default Boolean.NULL;

	Operator filter() default Operator.NULL;

	int height() default -1;
	
	Boolean highlightFirst() default Boolean.NULL;

	Boolean ignoreCase() default Boolean.NULL;

	int minLength() default -1;
	
	int maxSelectedItems() default -1;

	String placeholder() default "";

	String headerTemplateStr() default "";

	String headerTemplateFn() default "";
	
	String itemTemplateStr() default "";

	String itemTemplateFn() default "";

	String tagTemplateStr() default "";

	String tagTemplateFn() default "";

	String[] value() default {};

	Boolean valuePrimitive() default Boolean.NULL;
	
	Event[] events() default {};

	String name() default "";
}
