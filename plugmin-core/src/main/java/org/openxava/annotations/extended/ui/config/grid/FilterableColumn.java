package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FilterableColumn {

	Cell cell() default @Cell(dataTextField = "");
	
	String uiStr() default "";

	String uiFn() default "";
	
}
