package org.openxava.annotations;

import java.lang.annotation.*;

/**
 * A group of <code>@{@link DropDown}</code> associated to the same entity. <p>
 * 
 * Applies to entities.<p>
 * 
 * Example:
 * <pre>
 * </pre>
 * 
 * @author Varun Kumar
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface DropDowns {
	
	DropDown [] value();
	
}
