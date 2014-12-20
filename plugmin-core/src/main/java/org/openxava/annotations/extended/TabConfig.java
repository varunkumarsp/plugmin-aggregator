package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.Boolean.NULL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface TabConfig {

	String altRowTemplateStr() default "";
	
	String altRowTemplateFn() default "";

	Boolean autoBind() default NULL;

	int columnResizeHandleWidth() default -1;

	ColumnMenu columnMenu() default @ColumnMenu;
	
	DataSource dataSource() default @DataSource;

	String detailTemplateStr() default "";

	String detailTemplateFn() default "";
	
	String detailInitStr() default "";
	
	String detailInitFn() default "";

	Editable editable() default @Editable;

	Excel excel() default @Excel;

	Filterable filterable() default @Filterable;

	Groupable groupable() default @Groupable;

	String height() default "";

	Boolean mobileBool() default NULL;
	
	String mobileStr() default "";

	Boolean navigatable() default NULL;

	Pageable pageable() default @Pageable;

	Pdf pdf() default @Pdf;

	Boolean reorderable() default NULL;

	Boolean resizable() default NULL;

	String rowTemplateStr() default "";
	
	String rowTemplateFn() default "";

	Scrollable scrollable() default @Scrollable;

	Boolean selectableBool() default NULL;

	Selectable selectableStr() default Selectable.NULL;

	Sortable sortable() default @Sortable;

	String toolbarStr() default "";

	String toolbarFn() default "";

	Toolbar[] toolbarArray() default {};

	Event[] events() default {};
	
	String[] forTabs();

}
