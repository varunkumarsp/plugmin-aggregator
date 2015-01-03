package org.openxava.annotations.extended.ui.config.defaults;

import static org.openxava.annotations.extended.ui.config.enums.Operator.CONTAINS;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.DropDownConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@DropDownConfig(
	filter = CONTAINS,
	minLength = 2	
)
public @interface DefaultDropDownConfig {

}
