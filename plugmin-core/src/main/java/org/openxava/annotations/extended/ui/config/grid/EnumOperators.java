package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EnumOperators {

	String eq() default "";

	String neq() default "";

}
