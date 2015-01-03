package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Operators {

	StringOperators stringOperators() default @StringOperators;

	NumberOperators numberOperators() default @NumberOperators;

	DateOperators dateOperators() default @DateOperators;

	EnumOperators enumOperators() default @EnumOperators;

}
