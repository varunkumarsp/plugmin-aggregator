package org.openxava.annotations.extended.ui.config.defaults;

import static org.openxava.annotations.extended.ui.config.enums.AutocompleteFilter.CONTAINS;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.ui.config.AutocompleteConfig;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@AutocompleteConfig(
	filter = CONTAINS,
	minLength = 2	
)
public @interface DefaultAutocompleteConfig {

}
