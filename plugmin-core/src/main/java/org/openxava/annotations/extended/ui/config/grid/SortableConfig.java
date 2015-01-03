package org.openxava.annotations.extended.ui.config.grid;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.openxava.annotations.extended.ui.config.enums.Boolean;
import org.openxava.annotations.extended.ui.config.enums.SortableMode;

@Retention(RetentionPolicy.RUNTIME)
public @interface SortableConfig {

	Boolean allowUnsort() default NULL;

	SortableMode mode() default SortableMode.NULL;

}
