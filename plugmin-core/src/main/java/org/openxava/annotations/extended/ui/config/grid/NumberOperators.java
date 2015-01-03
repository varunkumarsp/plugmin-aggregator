package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NumberOperators {

	String eq() default "";

	String neq() default "";

	String gte() default "";

	String gt() default "";

	String lte() default "";

	String lt() default "";

}
