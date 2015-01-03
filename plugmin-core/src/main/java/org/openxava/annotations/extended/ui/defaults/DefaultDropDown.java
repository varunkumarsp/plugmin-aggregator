package org.openxava.annotations.extended.ui.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.DropDown;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@DropDown(name = "default")
public @interface DefaultDropDown {

}
