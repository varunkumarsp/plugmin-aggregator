package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.ui.config.enums.AnimationEffect.NULL;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.openxava.annotations.extended.ui.config.enums.AnimationEffect;

@Retention(RetentionPolicy.RUNTIME)
public @interface Open {

	AnimationEffect effects() default NULL;
	
	int duration() default -1;
}
