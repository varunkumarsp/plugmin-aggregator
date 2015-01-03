package org.openxava.util;

import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.component.MetaComponent;

/**
 * Global utilities about the system. <p>
 * 
 * @author Javier Paniza
 */

public class XSystem {

	private static Log log = LogFactory.getLog(XSystem.class);
	
	private static boolean onServer = false;
	private static String encoding = null; 
	
	/**
	 * Does that {@link #onServer} returns <tt>true</tt>.
	 *
	 * Must to be called from a static block in a common base class of EJB, or
	 * in all EJB is there aren't base class.<br>
	 */
	public static void _setOnServer() {
		onServer = true;
	}
	
	/**
	 * If we are in a client: java application, applet, servlet, jsp, etc. <p> 
	 */
	public static boolean onClient() {
		return !onServer();
	}
	
	/**
	 * If we are in a EJB server. <p>
	 */
	public static boolean onServer() {
		return onServer;
	}

	
	public static void _setLogLevelFromJavaLoggingLevelOfXavaPreferences() {		
		Logger rootLogger = Logger.getLogger("");
		Handler [] rootHandler = rootLogger.getHandlers();		
		for (int i=0; i<rootHandler.length; i++) {
			if (rootHandler[i] instanceof ConsoleHandler)
				rootHandler[i].setLevel(Level.ALL);
		}		
		Logger.getLogger("org.openxava").setLevel(XavaPreferences.getInstance().getJavaLoggingLevel());
		try {
			for (Iterator it = MetaComponent.getAllPackageNames().iterator(); it.hasNext(); ) {
				String packageName = (String) it.next();
				Logger.getLogger(packageName).setLevel(XavaPreferences.getInstance().getJavaLoggingLevel());
			}			
		}
		catch (Exception ex) {
			log.warn(XavaResources.getString("logging_level_not_set"));
		}
		Logger.getLogger("org.hibernate").setLevel(XavaPreferences.getInstance().getHibernateJavaLoggingLevel());
	}

	/**
	 * To use for XML encoding and for web requests and responses encoding. <p>
	 */
	public static String getEncoding() { 
		if (encoding == null) {		
			if ("OS/400".equalsIgnoreCase(System.getProperty("os.name")) && 
				System.getProperties().containsKey("was.install.root")) encoding = "ISO-8859-1";
			else {
				encoding = System.getProperty("ibm.system.encoding");
				if (encoding == null) encoding = System.getProperty("file.encoding");
				if (encoding == null) encoding = "UTF-8"; 
				else if ("Cp1252".equalsIgnoreCase(encoding)) encoding = "ISO-8859-1";
				else if ("utf8".equalsIgnoreCase(encoding)) encoding = "UTF-8";
				else if ("iso8859-1".equalsIgnoreCase(encoding)) encoding = "ISO-8859-1";
				else if ("MS874".equalsIgnoreCase(encoding)) encoding = "ISO-8859-11";
			}
		}		
		return encoding;				
	}
	
}
