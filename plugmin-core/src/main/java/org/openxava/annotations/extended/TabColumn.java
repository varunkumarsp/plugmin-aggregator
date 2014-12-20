package org.openxava.annotations.extended;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface TabColumn {

	Aggregates[] aggregates() default {};

	Attribute[] attributes() default {};

	Command[] commandObjs() default {};
	
	String[] commandArray() default {};

	String editor() default "";

	Boolean encoded() default NULL;
	
	Boolean filterableBool() default NULL;
	
	String filterableJsObj() default "";

	FilterableColumn filterable() default @FilterableColumn;

	String footerTemplateStr() default "";

	String format() default "";

	Boolean groupable() default NULL;

	String groupHeaderTemplate() default "";

	String groupFooterTemplate() default "";

	Attribute[] headerAttributes() default {};

	String headerTemplateStr() default "";

	Boolean hidden() default NULL;

	Boolean locked() default NULL;

	Boolean lockable() default NULL;

	Boolean sortable() default NULL;

	String template() default "";

	String title() default "";

	String width() default "";

	Boolean menu() default NULL;

	String headerTemplateFn() default "";

	String footerTemplateFn() default "";
	
	String[] forTabs() default {};
}
