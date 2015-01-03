package org.openxava.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.util.XavaResources;
import org.openxava.web.Ids;

/**
 * Logic used by OX for generated ids for the HTML parts. <p>
 * 
 * @author Javier Paniza
 */

public class IdTag extends TagSupport {
	
	private static Log log = LogFactory.getLog(IdTag.class);
	
	private String name;
	
	public int doStartTag() throws JspException {		
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			pageContext.getOut().print(
				Ids.decorate( request, name	)
			);		
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new JspException(XavaResources.getString("id_tag_error", name));				
		}

		return SKIP_BODY;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}