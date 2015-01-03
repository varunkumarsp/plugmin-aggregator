package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A group of <code>@{@link ReferenceView}</code> associated to the same reference. <p>
 * 
 * Applies to references.<p>
 * 
 * It allows to define a value different for <code>@{@link ReferenceView}</code> 
 * in each view.<br>
 * Example:
 * <pre>
 * &nbsp;@ReferenceViews({
 * &nbsp;&nbsp;&nbsp;@ReferenceView(forViews="DEFAULT", value= ... ),
 * &nbsp;&nbsp;&nbsp;@ReferenceView(forViews="Simple, VerySimple", value= ... ),
 * &nbsp;&nbsp;&nbsp;@ReferenceView(forViews="Complete", value= ... )
 * &nbsp;})
 * </pre>
 *
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface ReferenceViews {
	
	ReferenceView [] value();
	
}
