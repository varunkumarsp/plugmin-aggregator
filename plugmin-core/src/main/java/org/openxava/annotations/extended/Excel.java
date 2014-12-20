package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

	Boolean allPages() default NULL;

	String fileName() default "Export.xslx";

	Boolean filterable() default NULL;

	String proxyURL() default "";

}
