package org.openxava.annotations.extended.ui.config.editor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface ImageBrowser {

	String fileTypes() default "";
	
	String path() default "";
	
	ImageTransport transport() default @ImageTransport;
	
	Schema schema() default @Schema;
}
