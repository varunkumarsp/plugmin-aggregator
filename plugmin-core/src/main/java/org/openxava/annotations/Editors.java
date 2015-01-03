package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A group of <code>@{@link Editor}</code> associated to the same member. <p>
 * 
 * Applies to properties, references and collections. <p>
 * 
 * It allows to define a value different for <code>@{@link Editor}</code> in each view.<br>
 * Example:
 * <pre>
 * &nbsp;@Editors({
 * &nbsp;&nbsp;&nbsp;@Editor(forViews="DEFAULT", value= ... ),
 * &nbsp;&nbsp;&nbsp;@Editor(forViews="Simple, VerySimple", value= ... ),
 * &nbsp;&nbsp;&nbsp;@Editor(forViews="Complete", value= ... )
 * &nbsp;})
 * </pre>
 *
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Editors {
	
	Editor [] value();
	
}
