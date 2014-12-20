package org.openxava.annotations.extended;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface TabColumnCascade {

	/**
	 * ManyToOne field names in the same entity should be given
	 * @return
	 */
	String from();
	
	String forTab();
	
}
