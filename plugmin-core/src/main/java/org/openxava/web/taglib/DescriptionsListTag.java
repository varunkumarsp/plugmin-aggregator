package org.openxava.web.taglib;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.controller.ModuleContext;
import org.openxava.model.meta.MetaReference;
import org.openxava.util.XavaResources;
import org.openxava.view.View;
import org.openxava.web.Ids;
import org.openxava.web.dwr.Module;


/**
 * 
 * @author Javier Paniza
 */

public class DescriptionsListTag extends TagSupport {
	
	private static Log log = LogFactory.getLog(DescriptionsListTag.class);
	private String reference;
	
	public int doStartTag() throws JspException {
		try {			
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			ModuleContext context = (ModuleContext) request.getSession().getAttribute("context");			
									
			String viewObject = request.getParameter("viewObject");
			viewObject = (viewObject == null || viewObject.equals(""))?"xava_view":viewObject;
			View view = (View) context.get(request, viewObject);

			MetaReference metaReference = view.getMetaReference(reference).cloneMetaReference();
			metaReference.setName(reference);
			String prefix = request.getParameter("propertyPrefix");
			prefix = prefix == null?"":prefix;
			String application = request.getParameter("application");
			String module = request.getParameter("module");
			String referenceKey = Ids.decorate(application, module, prefix + reference); 
			request.setAttribute(referenceKey, metaReference);
			String editorURL = "reference.jsp?referenceKey=" + referenceKey + "&onlyEditor=true&frame=false&composite=false&descriptionsList=true";
			String editorPrefix = Module.isPortlet()?"/WEB-INF/jsp/xava/":"/xava/";  
			try {
				pageContext.include(editorPrefix + editorURL); 
			}
			catch (ServletException ex) { 
				Throwable cause = ex.getRootCause() == null?ex:ex.getRootCause(); 
				log.error(cause.getMessage(), cause);
				pageContext.include(editorPrefix + "editors/notAvailableEditor.jsp"); 
			}
			catch (Exception ex) {	
				log.error(ex.getMessage(), ex);
				pageContext.include(editorPrefix + "editors/notAvailableEditor.jsp"); 
			}											
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new JspException(XavaResources.getString("descriptionsList_tag_error", reference));
		}	
		return SKIP_BODY;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String property) {
		this.reference = property;		
	}
	
}