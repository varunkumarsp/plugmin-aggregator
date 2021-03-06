package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * A group of <code>@{@link LabelFormat}</code> associated to the same member. <p>
 * 
 * Applies to properties and references with descriptions list.<p>
 * 
 * It allows to define a value different for <code>@{@link LabelFormat}</code> in each view.<br>
 * Example:
 * <pre>
 * &nbsp;@LabelFormats({
 * &nbsp;&nbsp;&nbsp;@LabelFormat(forViews="DEFAULT", value= ... ),
 * &nbsp;&nbsp;&nbsp;@LabelFormat(forViews="Simple, VerySimple", value= ... ),
 * &nbsp;&nbsp;&nbsp;@LabelFormat(forViews="Complete", value= ... )
 * &nbsp;})
 * </pre>
 *
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface LabelFormats {
	
	LabelFormat [] value();
	
}
