package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface Toolbar {

	String name();

	String text();

	String templateStr() default "";

	String templateFn() default "";

}