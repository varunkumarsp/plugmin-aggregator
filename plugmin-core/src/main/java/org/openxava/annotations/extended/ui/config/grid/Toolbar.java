package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Toolbar {

	String name();

	String text();

	String templateStr() default "";

	String templateFn() default "";

}
