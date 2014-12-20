package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FilterableConfig {

	Boolean extra() default NULL;

	Operators operators() default @Operators;

	FilterableMode mode() default FilterableMode.NULL;

}
