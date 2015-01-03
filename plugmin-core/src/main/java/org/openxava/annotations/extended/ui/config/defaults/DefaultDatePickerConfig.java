package org.openxava.annotations.extended.ui.config.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.DatePickerConfig;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@DatePickerConfig(
		format = "dd-MM-yyyy"
)
public @interface DefaultDatePickerConfig {

}
