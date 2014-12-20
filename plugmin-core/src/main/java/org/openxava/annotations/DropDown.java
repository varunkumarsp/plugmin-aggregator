package org.openxava.annotations;

import java.lang.annotation.*;
import org.openxava.filters.*;

/**
 * Define the behavior for <b>drop down box</b>. <p>
 * 
 * Applies to entities. <p> 
 * 
 * Example:
 * <pre>
 * </pre>
 * 
 * @author Varun Kumar
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface DropDown {
	
	/**
	 * You can define several dropdowns in a entity, and set a name for each one. <p>
	 *  
	 * This name is used to indicate the tab that you want to use.
	 */
	String name() default "";
	
	/**
	 * A simple way to specify a different visual style for some rows. <p>
	 * 
	 * Normally to emphasize rows that fulfill certain condition.
	 */
	RowStyle [] rowStyles() default {};
	
	String keyProperty() default "";
	
	String valueProperty() default "";
	
	/**
	 * Allows to define programmatically some logic to apply to the values
	 * entered by user when he filters the list data.
	 */
	Class filter() default VoidFilter.class;
	
	/**
	 * Condition to be fulfilled by the displayed data. <p>
	 * 
	 * It's added to the user condition if needed. 
	 */
	String baseCondition() default "";
	
	/**
	 * To specify the initial order for data.
	 */
	String defaultOrder() default "";
	
}
