package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Margin {

	String left() default "";
	
	String right() default "";
	
	String top() default "";
	
	String bottom() default "";

}
