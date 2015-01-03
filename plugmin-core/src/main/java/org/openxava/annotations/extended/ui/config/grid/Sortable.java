package org.openxava.annotations.extended.ui.config.grid;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.openxava.annotations.extended.ui.config.enums.Boolean;

@Retention(RetentionPolicy.RUNTIME)
public @interface Sortable {

	Boolean sortable() default NULL;

	SortableConfig config() default @SortableConfig;

}
