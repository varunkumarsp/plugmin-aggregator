package org.openxava.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.util.XavaResources;

/**
 * To generate custom Jasper Reports from that extends <code>JasperReportBaseAction</code>.
 * 
 * @author Javier Paniza
 * @author Daniel García Salas
 */

public class GenerateCustomReportServlet extends HttpServlet { 	

	private static Log log = LogFactory.getLog(GenerateCustomReportServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = (String) request.getSession().getAttribute("xava.report.filename"); 
		String format = (String) request.getSession().getAttribute("xava.report.format");
		JasperPrint jprint = (JasperPrint) request.getSession().getAttribute("xava.report.jprint");
		request.getSession().removeAttribute("xava.report.filename");
		request.getSession().removeAttribute("xava.report.format"); 
		request.getSession().removeAttribute("xava.report.jprint");
		JasperPrint[] jprints = (JasperPrint[]) request.getSession().getAttribute("xava.report.jprints");
		int i = 0;
		if (jprints!=null) {
			i = Integer.parseInt(request.getParameter("index"));
			jprint = jprints[i];
			if (i==jprints.length-1) {
				request.getSession().removeAttribute("xava.report.jprints");
			}
		}		
		try {
			if (format == null) {
				format = JasperReportBaseAction.PDF;
			}
			
			JRExporter exporter;
			if (format.equals(JasperReportBaseAction.EXCEL)) {
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + ".xls\""); 
				exporter = new JRXlsExporter();
			} 
			else if (format.equalsIgnoreCase(JasperReportBaseAction.RTF)) { 				
				response.setContentType("application/rtf");  
				response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + ".rtf\""); 
				exporter = new JRRtfExporter() ;//
			} 			
			else if (format.equalsIgnoreCase(JasperReportBaseAction.ODT)) {  				
				response.setContentType("application/vnd.oasis.opendocument.text"); 
				response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + ".odt\""); 
				exporter = new JROdtExporter();
			}
			else {
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + ".pdf\""); 
				exporter = new JRPdfExporter();				
			}
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
			exporter.exportReport();
		} 
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new ServletException(XavaResources.getString("report_error"));
		}		
	}

}