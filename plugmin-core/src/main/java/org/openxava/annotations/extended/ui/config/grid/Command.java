package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

	String name();
	
	String text();
	
	String className() default "";
	
	String click() default "";
}
