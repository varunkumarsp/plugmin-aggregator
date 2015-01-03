package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute {

	String key();
	
	String value();

}
