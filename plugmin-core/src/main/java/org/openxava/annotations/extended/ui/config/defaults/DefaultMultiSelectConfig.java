package org.openxava.annotations.extended.ui.config.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.MultiSelectConfig;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@MultiSelectConfig(
		
)
public @interface DefaultMultiSelectConfig {

}
