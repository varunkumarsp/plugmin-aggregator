package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EditableConfig {

	Boolean confirmationBool() default NULL;
	
	String confirmationStr() default "";
	
	String confirmationFn() default "";

	String cancelDelete() default "";

	String confirmDelete() default "";

	String createAt() default "";

	Boolean destroy() default NULL;

	EditableMode mode() default EditableMode.NULL;

	String templateStr() default "";
	
	String templateFn() default "";

	Boolean update() default NULL;

	Window window() default @Window;

}
