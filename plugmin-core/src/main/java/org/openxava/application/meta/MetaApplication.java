package org.openxava.application.meta;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.annotations.parse.AnnotatedClassParser;
import org.openxava.component.MetaComponent;
import org.openxava.controller.meta.MetaControllers;
import org.openxava.util.ElementNotFoundException;
import org.openxava.util.Is;
import org.openxava.util.Labels;
import org.openxava.util.Strings;
import org.openxava.util.XavaException;
import org.openxava.util.XavaPreferences;
import org.openxava.util.meta.MetaElement;

/**
 * @author Javier Paniza
 */
public class MetaApplication extends MetaElement implements java.io.Serializable {

	private static Log log = LogFactory.getLog(MetaApplication.class);

	private Map metaModules = new HashMap();
	private Collection modulesNames = new ArrayList(); // to preserve the order
	private Collection folders;
	private Collection controllersForDefaultModule;
	private boolean defaultModulesGenerated = false;
	
	
	/**
	 * 
	 * @param newModule Not null
	 */
	public void addMetaModule(MetaModule newModule) {
		metaModules.put(newModule.getName(), newModule);
		newModule.setMetaApplication(this);
		modulesNames.add(newModule.getName()); // to preserve the order
	}
	
	public String getFolderLabel(Locale locale, String folder) {
		folder = Strings.change(folder, "/", ".");					
		return Labels.get(folder, locale);
	}
	
	public String getFolderLabel(String folder) {
		return getFolderLabel(Locale.getDefault(), folder);
	}
	
	
	public Collection getFolders() throws XavaException { 
		if (folders == null) {
			folders = new HashSet();
			for (Iterator it = getMetaModules().iterator(); it.hasNext(); ) {
				MetaModule metaModule = (MetaModule) it.next(); 
				folders.add(metaModule.getFolder());
			}
		}
		return folders;
	}

	
	
	/**
	 * 
	 * @exception XavaException  Any problem 
	 * @return of <tt>MetaModule</tt>. Not null.
	 */
	public Collection getMetaModules() throws XavaException {
		generateDefaultModules();
		return metaModules.values();
	}

	private void generateDefaultModules() throws XavaException { 
		if (defaultModulesGenerated) return;
		generateDefaultModulesFromJPAEntities();
		generateDefaultModulesFromXMLComponents();
		defaultModulesGenerated = true;
	}

	private void generateDefaultModulesFromXMLComponents() throws XavaException {
		boolean generateDefaultModules = XavaPreferences.getInstance().isGenerateDefaultModules();
		for (Iterator it=MetaComponent.getAll().iterator(); it.hasNext(); ) {
			String modelName = ((MetaComponent) it.next()).getName();
			if (!metaModules.containsKey(modelName) && generateDefaultModules) {
				createDefaultModule(modelName);
			}			
		}		
	}

	private void generateDefaultModulesFromJPAEntities() throws XavaException {
		boolean generateDefaultModules = XavaPreferences.getInstance().isGenerateDefaultModules();
		try {
			Collection classNames = AnnotatedClassParser.friendMetaApplicationGetManagedClassNames();
			for (Iterator it=classNames.iterator(); it.hasNext(); ) {
				String className = (String) it.next();
				String modelName = Strings.lastToken(className, ".");
				if (!metaModules.containsKey(modelName) && generateDefaultModules) {
					createDefaultModule(modelName);
				}			
			}							
		}
		catch (Exception ex) {
			log.error(ex);
			throw new XavaException("default_modules_from_jpa_error");
		}
	}			

	/**
	 * In the same order that they are found in application.xml/aplicacion.xml. <p>
	 *  
	 * @return of <tt>String</tt>. Not null.
	 */	
	public Collection getModulesNames() {
		return modulesNames;
	}
	
	/**
	 * The modules in the indicated folder 
	 * in the same order that they are found in application.xml/aplicacion.xml. <p>
	 *  
	 * @return of <tt>String</tt>. Not null.
	 * @throws XavaException 
	 */	
	public Collection getModulesNamesByFolder(String folder) throws XavaException {
		if (Is.emptyString(folder) || folder.trim().equals("/")) folder = ""; 
		Collection result = new ArrayList();
		for (Iterator it=getModulesNames().iterator(); it.hasNext();) {
			String moduleName = (String) it.next();
			String moduleFolder = getMetaModule(moduleName).getFolder();
			if (Is.equal(folder, moduleFolder)) {
				result.add(moduleName);
			}
		}
		return result;
	}
		
	/**
     * @exception ElementNotFoundException
	 */
	public MetaModule getMetaModule(String name) throws ElementNotFoundException, XavaException {
		MetaModule result = (MetaModule) metaModules.get(name);		
		if (result == null) {
			if (existsModel(name)) {				
				result = createDefaultModule(name);		
			}
		}
		if (result == null) {
			throw new ElementNotFoundException("module_not_found", name);
		}
		return result;
	}

	private boolean existsModel(String name) throws XavaException {
		return MetaComponent.exists(name);
	}
	
	private MetaModule createDefaultModule(String modelName) throws XavaException { 				
		MetaModule module = new MetaModule();
		module.setMetaApplication(this);
		module.setName(modelName);			
		module.setModelName(modelName);
		if (MetaControllers.contains(modelName)) {
			module.addControllerName(modelName);
		}
		else {
			for (Iterator it = getControllersForDefaultModule().iterator(); it.hasNext();) {
				module.addControllerName((String) it.next()); 
			}
		}
		metaModules.put(modelName, module);
		return module;		
	}
	
	public void addControllerForDefaultModule(String controllerName) { 
		if (controllersForDefaultModule == null) controllersForDefaultModule = new ArrayList();
		controllersForDefaultModule.add(controllerName);
	}

	private Collection getControllersForDefaultModule() { 
		if (controllersForDefaultModule == null) return Collections.EMPTY_LIST;
		return controllersForDefaultModule;
	}

	public String toString() {
		return "Application: " + getName(); 
	}

	public String getId() {
		return getName();
	}
	
}


