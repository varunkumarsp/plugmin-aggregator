package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A group of <code>@{@link ListProperties}</code> associated to the same collection. <p>
 * 
 * Applies to collections.<p>
 * 
 * It allows to define a value different for <code>@{@link ListProperties}</code> in each view.<br>
 * Example:
 * <pre>
 * &nbsp;@ListsProperties({
 * &nbsp;&nbsp;&nbsp;@ListProperties(forViews="DEFAULT", value= ... ),
 * &nbsp;&nbsp;&nbsp;@ListProperties(forViews="Simple, VerySimple", value= ... ),
 * &nbsp;&nbsp;&nbsp;@ListProperties(forViews="Complete", value= ... )
 * &nbsp;})
 * </pre>
 * 
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface ListsProperties {
	
	ListProperties [] value();
	
}
