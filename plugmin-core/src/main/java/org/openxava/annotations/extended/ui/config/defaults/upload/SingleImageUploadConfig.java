package org.openxava.annotations.extended.ui.config.defaults.upload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.UploadConfig;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@UploadConfig(
		
)
public @interface SingleImageUploadConfig {

}
