package org.openxava.annotations.extended.ui.config.grid;

import static org.openxava.annotations.extended.ui.config.enums.Boolean.NULL;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.openxava.annotations.extended.ui.config.enums.Boolean;
import org.openxava.annotations.extended.ui.config.enums.Operator;

@Retention(RetentionPolicy.RUNTIME)
public @interface Cell {

	String dataSource() default "";

	String dataTextField();

	int delay() default -1;

	int inputWidth() default -1;

	Operator suggestionOperator() default Operator.NULL;

	int minLength() default -1;

	Boolean enabled() default NULL;

	Operator operator() default Operator.NULL;

	Boolean showOperators() default NULL;

	String template() default "";

	
}
