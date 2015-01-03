package org.openxava.web.taglib;

import javax.servlet.jsp.tagext.IterationTag;

/**
 * @author Javier Paniza
 */
public interface IActionTag extends IterationTag {

	void setAction(String string);
	void setArgv(String string);	

}
