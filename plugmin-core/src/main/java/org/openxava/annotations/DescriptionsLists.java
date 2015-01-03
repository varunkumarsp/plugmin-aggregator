package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A group of <code>@{@link DescriptionsList}</code> associated to the same member. <p>
 * 
 * Applies to references.<p>
 * 
 * It allows to define a value different for <code>@{@link DescriptionsList}</code> in each view.<br>
 * Example:
 * <pre>
 * &nbsp;@DescriptionsLists({
 * &nbsp;&nbsp;&nbsp;@DescriptionsList(forViews="DEFAULT", ... ),
 * &nbsp;&nbsp;&nbsp;@DescriptionsList(forViews="Simple, VerySimple", ... ),
 * &nbsp;&nbsp;&nbsp;@DescriptionsList(forViews="Complete", ... )
 * &nbsp;})
 * </pre>
 *  
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface DescriptionsLists {
	
	DescriptionsList [] value();
	
}
