package org.openxava.annotations.extended.ui.config.grid;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.openxava.annotations.extended.ui.config.enums.Boolean;

@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

	Boolean allPages() default NULL;

	String fileName() default "Export.xslx";

	Boolean filterable() default NULL;

	String proxyURL() default "";

}
