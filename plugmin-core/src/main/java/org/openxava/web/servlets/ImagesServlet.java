package org.openxava.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.controller.ModuleContext;
import org.openxava.util.Strings;
import org.openxava.util.XavaResources;
import org.openxava.view.View;
import org.openxava.web.Ids;


/**
 * @author Javier Paniza
 */

public class ImagesServlet extends HttpServlet {
	
	private static Log log = LogFactory.getLog(ImagesServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String propertyKey = Ids.undecorate(request.getParameter( "property" ));
			View view = extractCurrentView( request, propertyKey );
			String property = Strings.lastToken(propertyKey, ".");
			byte [] image = (byte []) view.getValue(property); 
			if (image != null) {
				response.setContentType("image");
				response.getOutputStream().write(image);
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new ServletException(XavaResources.getString("image_error"));
		}		
	}
	
	private View extractCurrentView( HttpServletRequest request, String propertyKey) { 		 
		ModuleContext context = (ModuleContext) request.getSession().getAttribute("context"); 
		View view = (View)context.get(request, "xava_view" ); 		 		
		String []m = propertyKey.split( "\\." );  
		for( int i = 0; i < m.length-1; i++ ) { 
			view = view.getSubview(m[i]); 
		} 		 
		return view;
	}
		
}
