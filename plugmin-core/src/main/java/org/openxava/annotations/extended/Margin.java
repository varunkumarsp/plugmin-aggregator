package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface Margin {

	String left() default "";
	
	String right() default "";
	
	String top() default "";
	
	String bottom() default "";

}
