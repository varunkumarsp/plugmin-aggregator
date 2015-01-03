package org.openxava.annotations.extended.ui.use;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UseDatePicker {

	String config() default "";

	String[] forTabs() default {};

	String[] forViews() default {};
	
}
