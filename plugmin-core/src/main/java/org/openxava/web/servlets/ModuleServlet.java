package org.openxava.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openxava.util.XavaResources;

/**
 * 
 * @author Javier Paniza
 */
public class ModuleServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] uri = request.getRequestURI().split("/");
		if (uri.length < 4) {
			response.getWriter().print(XavaResources.getString(request, "module_name_missing"));
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(
			"/xava/module.jsp?application=" + uri[1] + "&module=" + uri[3] + "&friendlyURL=true");			
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
