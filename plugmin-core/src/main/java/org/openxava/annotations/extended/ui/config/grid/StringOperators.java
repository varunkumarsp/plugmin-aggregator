package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface StringOperators {

	String eq() default "";

	String neq() default "";

	String startsWith() default "";

	String contains() default "";

	String doesNotContain() default "";

	String endsWith() default "";

}
