package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;
import org.openxava.annotations.extended.ui.config.enums.Boolean;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnMenu {

	Boolean isEnabled() default NULL;

	Boolean columns() default NULL;

	Boolean filterable() default NULL;

	Boolean sortable() default NULL;

}
