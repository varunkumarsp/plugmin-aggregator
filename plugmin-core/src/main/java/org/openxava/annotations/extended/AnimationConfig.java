package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnimationConfig {

	Close close() default @Close;
	
	Open open() default @Open;
}
