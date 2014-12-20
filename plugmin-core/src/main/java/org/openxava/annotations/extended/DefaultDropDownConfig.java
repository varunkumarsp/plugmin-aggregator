package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.Operator.CONTAINS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.DropDownConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@DropDownConfig(
	filter = CONTAINS,
	minLength = 2,	
	forDropDowns = { "" }
)
public @interface DefaultDropDownConfig {

	String[] forDropDowns();

}
