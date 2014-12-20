package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnMenu {

	Boolean isEnabled() default NULL;

	Boolean columns() default NULL;

	Boolean filterable() default NULL;

	Boolean sortable() default NULL;

}
