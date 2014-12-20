package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SortableConfig {

	Boolean allowUnsort() default NULL;

	SortableMode mode() default SortableMode.NULL;

}
