package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;
import org.openxava.annotations.extended.ui.config.enums.Boolean;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Animation {

	Boolean animation() default NULL;

	AnimationConfig config() default @AnimationConfig;
}
