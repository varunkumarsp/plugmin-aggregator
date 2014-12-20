package org.openxava.annotations.extended;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.View;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@View(name = "default")
public @interface DefaultView {

}
