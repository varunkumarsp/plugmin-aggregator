package org.openxava.annotations.extended.ui.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.editor.EditorConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface EditorConfigs {

	EditorConfig[] value();

}
