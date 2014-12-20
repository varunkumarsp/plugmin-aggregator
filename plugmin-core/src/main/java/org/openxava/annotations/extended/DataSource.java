package org.openxava.annotations.extended;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.openxava.annotations.extended.Boolean.*; 

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
