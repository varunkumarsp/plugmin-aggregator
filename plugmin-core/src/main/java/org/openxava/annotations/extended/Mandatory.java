package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;
import org.openxava.annotations.extended.ui.config.enums.Boolean;
public @interface Mandatory {

	Boolean value() default NULL;

}
