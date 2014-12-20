package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.Boolean;
import org.openxava.annotations.extended.DataSource;
import org.openxava.annotations.extended.Operator;
import org.openxava.annotations.extended.Event;

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
@Target({ ElementType.TYPE })
public @interface DropDownConfig {
	
	Boolean autoBind() default Boolean.NULL;

	String cascadeFrom() default "";

	String cascadeFromField() default "";

	DataSource dataSourceObj() default @DataSource;

	String[] dataSourceArray() default {};

//	String dataTextField() default "";
//	
//	String dataValueField() default "";

	int delay() default 500;

	Operator filter() default Operator.NULL;

	int height() default 200;

	Boolean ignoreCase() default Boolean.NULL;

	int index() default 0;

	int minLength() default 1;

	String optionLabel() default "";

	String headerTemplateStr() default "";

	String headerTemplateFn() default "";

	String templateStr() default "";

	String templateFn() default "";

	String valueTemplateStr() default "";

	String valueTemplateFn() default "";

	String text() default "";

	String value() default "";

	Boolean valuePrimitive() default Boolean.NULL;
	
	Event[] events() default {};
	
	String[] forDropDowns();
	
}
