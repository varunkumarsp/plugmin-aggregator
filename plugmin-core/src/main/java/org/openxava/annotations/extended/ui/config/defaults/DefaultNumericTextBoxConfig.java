package org.openxava.annotations.extended.ui.config.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.NumericTextBoxConfig;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@NumericTextBoxConfig(
		decimals = 0,
		downArrowText = "Decrement",
		upArrowText = "Increment",
		min = 1
)
public @interface DefaultNumericTextBoxConfig {

}
