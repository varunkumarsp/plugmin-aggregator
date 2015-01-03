package org.openxava.annotations.extended.ui.config.editor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.openxava.annotations.extended.Event;

/**
 * Define the behavior for <b>Editor</b>. <p>
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
@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
public @interface EditorConfig {
	
	String domain() default "";
	
	String encoded() default "";
	
	Serialization serialization() default @Serialization;
	
	String[] stylesheets() default {};
	
	String[] toolsStrArray() default {};
	
	Tool[] toolsObjArray() default {};
	
	ImageBrowser imageBrowser() default @ImageBrowser;
	
	FileBrowser fileBrowser() default @FileBrowser;
			
	Event[] events() default {};
	
	String name() default "";

}
