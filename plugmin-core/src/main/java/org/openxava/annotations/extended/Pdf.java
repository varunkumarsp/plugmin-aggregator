package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
public @interface Pdf {

	String author() default "";

	String creator() default "";

	String fileName() default "Export.pdf";

	String keywords() default "";

	Boolean landscape() default NULL;

	Margin margin() default @Margin;

	String paperSizeStr() default "";

	String[] paperSizeArray() default {};

	String proxyURL() default "";

	String subject() default "";

	String title() default "";

}
