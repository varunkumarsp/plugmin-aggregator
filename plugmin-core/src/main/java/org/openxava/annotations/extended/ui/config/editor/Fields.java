package org.openxava.annotations.extended.ui.config.editor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface Fields {

	String nameStr() default "";
	
	Name nameObj() default @Name;
	
	String typeStr() default "";
	
	Type typeObj() default @Type;
	
	String sizeStr() default "";
	
	Size sizeObj() default @Size;
}
