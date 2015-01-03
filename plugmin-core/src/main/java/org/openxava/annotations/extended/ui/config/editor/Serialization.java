package org.openxava.annotations.extended.ui.config.editor;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.enums.Boolean;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface Serialization {

	Boolean entities() default NULL;
	
	Boolean scripts() default NULL;
	
}
