package org.openxava.annotations.extended.ui.config.editor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface FileBrowser {

	String fileTypes() default "";
	
	String path() default "";
	
	FileTransport transport() default @FileTransport;
	
	Schema schema() default @Schema;
}
