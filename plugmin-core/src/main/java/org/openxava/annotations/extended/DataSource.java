package org.openxava.annotations.extended;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;
import org.openxava.annotations.extended.ui.config.enums.Boolean;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

	String dataSource() default "";

	String readUrl() default "";

	String createUrl() default "";

	String updateUrl() default "";

	String destroyUrl() default "";

	Boolean autoSync() default NULL;
	
	Boolean batch() default NULL;

	int pageSize() default -1;

}
