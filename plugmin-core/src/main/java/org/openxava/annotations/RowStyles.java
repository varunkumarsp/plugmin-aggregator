package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * A group of <code>@{@link RowStyle}</code> associated to the same collection. <p>
 * 
 * It allows to define a value different for <code>@{@link RowStyle}</code> in each view.<br>
 * Example:
 * <pre>
 * &nbsp;@RowStyles({
 * &nbsp;&nbsp;&nbsp;@RowStyle(forViews="Specials", style="highlight", property="type", value="special"),
 * &nbsp;&nbsp;&nbsp;@RowStyle(forViews="Complete", style="highlight", property="type", value="steady") 	
 * &nbsp;})
 * </pre>
 * It does not work for @ElementCollection.
 *
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface RowStyles {
	
	RowStyle [] value();
	
}
