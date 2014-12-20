package org.openxava.annotations.extended;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.openxava.annotations.extended.Boolean.*; 

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface DefaultValue {

	String value();

}