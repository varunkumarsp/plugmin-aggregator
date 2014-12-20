package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface DateOperators {

	String eq() default "";

	String neq() default "";

	String gte() default "";

	String gt() default "";

	String lte() default "";

	String lt() default "";

}
