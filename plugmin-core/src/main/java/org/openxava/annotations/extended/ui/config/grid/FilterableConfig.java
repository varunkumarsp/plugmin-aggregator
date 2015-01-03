package org.openxava.annotations.extended.ui.config.grid;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.openxava.annotations.extended.ui.config.enums.Boolean;
import org.openxava.annotations.extended.ui.config.enums.FilterableMode;

@Retention(RetentionPolicy.RUNTIME)
public @interface FilterableConfig {

	Boolean extra() default NULL;

	Operators operators() default @Operators;

	FilterableMode mode() default FilterableMode.NULL;

}
