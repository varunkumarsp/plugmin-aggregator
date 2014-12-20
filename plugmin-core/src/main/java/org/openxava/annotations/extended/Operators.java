package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface Operators {

	StringOperators stringOperators() default @StringOperators;

	NumberOperators numberOperators() default @NumberOperators;

	DateOperators dateOperators() default @DateOperators;

	EnumOperators enumOperators() default @EnumOperators;

}
