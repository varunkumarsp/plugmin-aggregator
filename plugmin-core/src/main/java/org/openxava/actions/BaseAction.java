package org.openxava.actions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.controller.Environment;
import org.openxava.controller.ModuleContext;
import org.openxava.controller.ModuleManager;
import org.openxava.controller.meta.MetaAction;
import org.openxava.controller.meta.MetaControllers;
import org.openxava.hibernate.XHibernate;
import org.openxava.jpa.XPersistence;
import org.openxava.util.Is;
import org.openxava.util.Locales;
import org.openxava.util.Messages;
import org.openxava.util.XavaException;
import org.openxava.util.XavaResources;
import org.openxava.web.DescriptionsLists;

/**
 * 
 * @author Javier Paniza
 */

abstract public class BaseAction implements IAction, IRequestAction, IModuleContextAction, IChangeModeAction {
	
	private static Log log = LogFactory.getLog(BaseAction.class); 
	
	private Messages errors;
	private Messages messages;
	private Environment environment;
	private transient HttpServletRequest request;
	private ModuleContext context; 
	private String nextMode;
	
	public Messages getErrors() {
		return errors;
	}

	public void setErrors(Messages errors) { 
		this.errors = errors;
	}
	
	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
	
	protected void addErrors(Messages errors) {
		this.errors.add(errors);
	}
	
	protected void addMessages(Messages messages) {
		this.messages.add(messages);
	}
	
	/**
	 * If some id is a String between quotes (') it will be displayed literally, untranslated. 
	 */	
	protected void addError(String messageId, Object ... ids) {
		errors.add(Messages.Type.ERROR, messageId, ids); 
	}
	
	/**
	 * If some id is a String between quotes (') it will be displayed literally, untranslated. 
	 */	
	protected void addMessage(String messageId, Object ... ids) {
		messages.add(Messages.Type.MESSAGE, messageId, ids);  
	}
	
	/**
	 * If some id is a String between quotes (') it will be displayed literally, untranslated.
	 * 
	 * @since 4.3 
	 */	
	protected void addInfo(String messageId, Object ... ids) {
		messages.add(Messages.Type.INFO, messageId, ids);
	}
	
	/**
	 * If some id is a String between quotes (') it will be displayed literally, untranslated.
	 * 
	 * @since 4.3
	 */	
	protected void addWarning(String messageId, Object ... ids) {
		messages.add(Messages.Type.WARNING, messageId, ids);
	}
			
	public void executeBefore() throws Exception {	
	}
	
	public void executeAfter() throws Exception {	
	}
	
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	public Environment getEnvironment() {
		return environment;
	}
	
	/**
	 * Reset the cache of all descriptions-list and 
	 * others uses of descriptionsEditors.	 
	 */
	protected void resetDescriptionsCache() {
		DescriptionsLists.resetDescriptionsCache(request.getSession());		
	}
	
	public void setRequest(HttpServletRequest request) { 
		this.request= request;
	}
	
	/**
	 * With this method you can access directly to the
	 * web application resources, but it ties you to 
	 * implementation technology (servlets), hence it's 
	 * better to avoid it if you have alternative and 
	 * are thinking in migrating to another tecnology. 
	 * 
	 * @since 4m1
	 */
	protected HttpServletRequest getRequest() { 
		return this.request;
	}
	
	/**
	 * The Locale of the current request. <p> 
	 */
    protected Locale getLocale() {
        return Locales.getCurrent();
    }

	/**
	 * @since 4m1
	 */    
	protected ModuleContext getContext() {  
		if (context == null) throw new XavaException("context_null_in_action");
		return context;
	}

	public void setContext(ModuleContext context) {
		this.context = context;
	}

	/**
	 * @since 4m1
	 */
	public String getNextMode() {
		return nextMode;
	}

	/**
	 * @since 4m1
	 */	
	protected void setNextMode(String nextMode) {
		this.nextMode = nextMode;
	}

	/**
	 * @since 4m1
	 */	
	protected ModuleManager getManager() { 
		return (ModuleManager) getContext().get(request, "manager");
	}	
	
	/**
	 * Actions add added even if they are hidden. <p>
	 * 
	 * @since 4m2
	 */		
	protected void addActions(String ... qualifiedActions) { 
		for (String qualifiedAction: qualifiedActions) {
			if (Is.emptyString(qualifiedAction)) continue; 
			MetaAction action = MetaControllers.getMetaAction(qualifiedAction);
			if (action.isHidden()) { 
				action = action.cloneMetaAction();
				action.setHidden(false);
			}
			if (getManager().isShowDialog()) getManager().addMetaAction(action);
			else getManager().addSimpleMetaAction(action);
		}
	}
	
	/**
	 * @since 4m2
	 */		
	protected void removeActions(String ... qualifiedActions) { 
		for (String qualifiedAction: qualifiedActions) {
			if(getManager().isShowDialog()){
				getManager().removeMetaAction(MetaControllers.getMetaAction(qualifiedAction));
			}
			else{
				getManager().removeSimpleMetaAction(MetaControllers.getMetaAction(qualifiedAction));	
			}
		}
	}

	/**
	 * @since 4m2
	 */		
	protected void clearActions() {		
		getManager().memorizeControllers();										
		getManager().setControllersNames(IChangeControllersAction.EMPTY_CONTROLLER);
	}
	
	/**
	 * @since 4m2
	 */	
	protected void setControllers(String ... controllers) {  
		if (!(this instanceof IChangeControllersAction)) {
			getManager().setControllers(controllers); 
		}
		else {
			log.warn(XavaResources.getString("change_controllers_action_over_set_contoller", getClass())); 
		}
	}

	/**
	 * @since 4m2
	 */	
	protected void returnToPreviousControllers() { 
		setControllers(IChangeControllersAction.PREVIOUS_CONTROLLERS); 
	}
	
	/**
	 * Set the default controllers of the current module. <p>
	 * 
	 * The defaults controllers are those declared in application.xml for the
	 * module, so the initial controllers when the module starts. <br>
	 * Also, this method clear the stack of controllers navigation, that is, if after
	 * calling to this method you call to {@link returnToPreviousController}() it will 
	 * not have effect.<br>
	 * 
	 * @since 4m2
	 */
	protected void setDefaultControllers() { 
		setControllers(IChangeControllersAction.DEFAULT_CONTROLLERS); 
	}
	
	/**
	 * Commit the current transaction.
	 * 
	 * @since 5.0 
	 */
	protected void commit() {  		
		XPersistence.commit();
		XHibernate.commit();		
	}

	/**
	 * Rollback the current transaction.
	 * 
	 * @since 5.0
	 */	
	protected void rollback() { 		
		XPersistence.rollback();
		XHibernate.rollback();		
	}	
	       
}
