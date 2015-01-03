package org.openxava.annotations.extended.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define the behavior for <b>tab</b>ular data presentation (List mode). <p>
 * 
 * Applies to entities. <p> 
 * 
 * Example:
 * <pre>
 * &nbsp;@Tab(name="ActiveYear",
 * &nbsp;&nbsp;&nbsp;filter=ActiveYearFilter.class,		
 * &nbsp;&nbsp;&nbsp;properties="year, number, customer.number, customer.name, amountsSum, vat, detailsCount, paid, importance",
 * &nbsp;&nbsp;&nbsp;baseCondition="${year} = ?"
 * &nbsp;)
 * </pre>
 * 
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
public @interface Tab {
	
	String name();
	
	String properties() default "";
	
	String config() default "";
	
}
