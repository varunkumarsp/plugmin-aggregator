package org.openxava.annotations.extended.ui.config.editor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface FileTransport {

	String readStr() default "";
	
	TransportX readObj() default @TransportX;
	
	String uploadUrl() default "";
	
	String fileUrlStr() default "";
	
	String fileUrlFn() default "";
	
	String destroyStr() default "";
	
	TransportX destroyObj() default @TransportX;
	
	String createStr() default "";
	
	TransportX createObj() default @TransportX;
	
}
