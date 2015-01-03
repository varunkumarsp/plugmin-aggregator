package org.openxava.annotations.extended.ui.config;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ColumnMenu;
import org.openxava.annotations.extended.DataSource;
import org.openxava.annotations.extended.Event;
import org.openxava.annotations.extended.ui.config.enums.Boolean;
import org.openxava.annotations.extended.ui.config.enums.SelectableMode;
import org.openxava.annotations.extended.ui.config.grid.Editable;
import org.openxava.annotations.extended.ui.config.grid.Excel;
import org.openxava.annotations.extended.ui.config.grid.Filterable;
import org.openxava.annotations.extended.ui.config.grid.Groupable;
import org.openxava.annotations.extended.ui.config.grid.Pageable;
import org.openxava.annotations.extended.ui.config.grid.Pdf;
import org.openxava.annotations.extended.ui.config.grid.Scrollable;
import org.openxava.annotations.extended.ui.config.grid.Sortable;
import org.openxava.annotations.extended.ui.config.grid.Toolbar;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
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

	SelectableMode selectableStr() default SelectableMode.NULL;

	Sortable sortable() default @Sortable;

	String toolbarStr() default "";

	String toolbarFn() default "";

	Toolbar[] toolbarArray() default {};

	Event[] events() default {};
	
	String name() default "";

}
