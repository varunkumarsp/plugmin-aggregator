package org.openxava.annotations.extended;
import static org.openxava.annotations.extended.Boolean.NULL;

public @interface Mandatory {

	Boolean value() default NULL;

}
