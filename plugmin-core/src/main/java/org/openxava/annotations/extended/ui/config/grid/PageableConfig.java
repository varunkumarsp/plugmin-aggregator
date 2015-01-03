package org.openxava.annotations.extended.ui.config.grid;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.openxava.annotations.extended.ui.config.enums.Boolean;

@Retention(RetentionPolicy.RUNTIME)
public @interface PageableConfig {

	int pageSize() default -1;

	Boolean previousNext() default NULL;

	Boolean numeric() default NULL;

	int buttonCount() default -1;

	Boolean input() default NULL;

	Boolean pageSizesBool() default NULL;

	int[] pageSizesArray() default {};

	Boolean refresh() default NULL;

	Boolean info() default NULL;

}
