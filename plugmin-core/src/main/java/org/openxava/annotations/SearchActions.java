package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A group of <code>@{@link SearchAction}</code> associated to the same reference. <p>
 * 
 * Applies to references.<p>
 * 
 * It allows to define a value different for <code>@{@link SearchAction}</code> 
 * in each view.<br>
 * Example:
 * <pre>
 * &nbsp;@SearchActions({
 * &nbsp;&nbsp;&nbsp;@SearchAction(forViews="DEFAULT", value= ... ),
 * &nbsp;&nbsp;&nbsp;@SearchAction(forViews="Simple, VerySimple", value= ... ),
 * &nbsp;&nbsp;&nbsp;@SearchAction(forViews="Complete", value= ... )
 * &nbsp;})
 * </pre>
 * 
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface SearchActions {
	
	SearchAction [] value();
	
}
