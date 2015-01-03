package org.openxava.annotations.extended.ui.config.editor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface TransportX {
	
	String contentType() default "";
	
	String data() default "";
	
	String dataType() default "";
	
	String type() default "";
	
	String urlStr() default "";
	
	String urlFn() default "";

}
