package org.openxava.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A group of <code>@{@link RemoveSelectedAction}</code> associated to the same 
 * collection. <p>
 * 
 * Applies to @OneToMany/@ManyToMany collections.<p>
 * 
 * It allows to define a value different for @RemoveSelectedAction in each view.<br>
 * Example:
 * <pre>
 * &nbsp;@RemoveSelectedActions({
 * &nbsp;&nbsp;&nbsp;@RemoveSelectedAction(forViews="DEFAULT", value= ... ),
 * &nbsp;&nbsp;&nbsp;@RemoveSelectedAction(forViews="Simple, VerySimple", value= ... ),
 * &nbsp;&nbsp;&nbsp;@RemoveSelectedAction(forViews="Complete", value= ... )
 * &nbsp;})
 * </pre>
 * 
 * @author Javier Paniza
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface RemoveSelectedActions {
	
	RemoveSelectedAction [] value();
	
}
