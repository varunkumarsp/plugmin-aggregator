package org.openxava.web.servlets;

import static org.apache.commons.lang3.SystemUtils.IS_OS_UNIX;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.util.Is;
import org.openxava.util.XavaResources;
import org.openxava.web.editors.AttachedFile;
import org.openxava.web.editors.FilePersistorFactory;

import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil;
/**
 * 
 * @author Jeromy Altuna
 */
@SuppressWarnings("serial")
public class FilesServlet extends HttpServlet {
	
	private static final String MIME_UNKNOWN = "application/octet-stream";
	
	private static Log log = LogFactory.getLog(FilesServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String fileId = (String) request.getParameter("fileId");
			
			if (!Is.emptyString(fileId)) {
				AttachedFile file = FilePersistorFactory.getInstance().find(fileId);
				
				registerMimeDetector();
				
				MimeType mimeType = MimeUtil.getMostSpecificMimeType(MimeUtil.getMimeTypes(file.getName()));
				String mime = mimeType.getMediaType() + "/" + mimeType.getSubType();
								
				if(MIME_UNKNOWN.equals(mime)) {
					mimeType = MimeUtil.getMostSpecificMimeType(MimeUtil.getMimeTypes(file.getData()));
					mime = mimeType.getMediaType() + "/" + mimeType.getSubType();
				}
				response.setContentType(mime);
				response.setHeader("Content-Disposition", "inline; filename=\""	+ file.getName() + "\"");
				response.getOutputStream().write(file.getData());
				
				unregisterMimeDetector();
			}

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new ServletException(XavaResources.getString("attached_file_error"));
		}
	}

	private static void registerMimeDetector() {
		if(IS_OS_UNIX)
			MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.OpendesktopMimeDetector");
		else if (IS_OS_WINDOWS)
			MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.WindowsRegistryMimeDetector");
		else 
			MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector");
	}
	
	private static void unregisterMimeDetector() {
		if(IS_OS_UNIX)
			MimeUtil.unregisterMimeDetector("eu.medsea.mimeutil.detector.OpendesktopMimeDetector");
		else if (IS_OS_WINDOWS)
			MimeUtil.unregisterMimeDetector("eu.medsea.mimeutil.detector.WindowsRegistryMimeDetector");
		else 
			MimeUtil.unregisterMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector");
	}
}
