package org.openxava.annotations.extended.ui.config.editor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface ImageTransport {

	String readStr() default "";
	
	TransportX readObj() default @TransportX;
	
	String thumbnailUrlStr() default "";
	
	String thumbnailUrlFn() default "";
	
	String uploadUrl() default "";
	
	String imageUrlStr() default "";
	
	String imageUrlFn() default "";
	
	String destroyStr() default "";
	
	TransportX destroyObj() default @TransportX;
	
	String createStr() default "";
	
	TransportX createObj() default @TransportX;
	
}
