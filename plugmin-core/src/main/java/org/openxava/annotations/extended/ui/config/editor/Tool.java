package org.openxava.annotations.extended.ui.config.editor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface Tool {
	
	String name() default "";
	
	String tooltip() default "";
	
	String exec() default "";
	
	Item[] items() default {};
	
	String templateStr() default "";
	
	String templateFn() default "";

}
