package org.openxava.annotations.extended.ui.config.grid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.Event;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface TabEvents {

	Event[] value();

}
