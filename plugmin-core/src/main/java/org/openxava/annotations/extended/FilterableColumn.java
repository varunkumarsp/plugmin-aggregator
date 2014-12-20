package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface FilterableColumn {

	Cell cell() default @Cell(dataTextField = "");
	
	String uiStr() default "";

	String uiFn() default "";
	
}
