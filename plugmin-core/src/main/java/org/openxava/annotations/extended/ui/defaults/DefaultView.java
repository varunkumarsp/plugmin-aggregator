package org.openxava.annotations.extended.ui.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.View;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@View(name = "default")
public @interface DefaultView {

}
