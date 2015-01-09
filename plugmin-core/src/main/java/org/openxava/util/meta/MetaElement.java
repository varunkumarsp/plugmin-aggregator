package org.openxava.util.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.ui.config.vo.WidgetConfig;
import org.openxava.component.MetaComponent;
import org.openxava.util.Is;
import org.openxava.util.Labels;
import org.openxava.util.Locales;
import org.openxava.util.Strings;

import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * @author Javier Paniza
 */
abstract public class MetaElement implements java.io.Serializable {

	private String description;
	public String name;
	private String label;
	
	public MetaComponent metaComponent;
	public String json;
	public List<String> jsFunctions = new ArrayList<String>();
	public List<String> onDocReadyjsFunctions = new ArrayList<String>();
	public List<WidgetConfig> widgetConfigs = new ArrayList<WidgetConfig>();
	
	

	public boolean has18nLabel() {		
		return Labels.exists(getId());
	}
	
	public boolean hasName() {
		String n = getName();
		return n != null && !n.trim().equals("");
	}
	
	public String getLabel(ServletRequest request) {		
		return getLabel(getLocale(request));
	}
	
	
	public Locale getLocale(ServletRequest request) {
		return Locales.getCurrent();
	}
	
	
	/**
	 * For refine the label calculation
	 */
	public String getLabel(Locale locale) {
		return getLabel(locale, getId());
	}
	
	/**
	 * Implementation of label obtaining. <p>  
	 */
	public String getLabel(Locale locale, String id) {		
		if (id == null) return "";
		if (Is.emptyString(label)) label = Strings.javaIdentifierToNaturalLabel(getName());			
		return Labels.get(id, locale, label).trim();		 
	}
	
		

	/**
	 * Unique id of element, normally used to search the label in the resources files.	 
	 */
	public abstract String getId();
	
	
	/**	 
	 * @return java.lang.String Not null
	 */
	public java.lang.String getName() {
		return name == null ? "" : name;
	}
	
	public void setName(java.lang.String newName) {
		name = newName;
	}
	
	public String getDescription() { 
		return getDescription(Locales.getCurrent()); 
	}
	
	public String getDescription(Locale locale) {
		return getDescription(locale, getId());
	}
	
	public String getDescription(Locale locale, String id) {		
		if (id == null) return "";
		String descriptionId = id + "[description]";
		String result = "";
		if (Labels.exists(descriptionId)) {
			result = Labels.get(descriptionId, locale);
		}
		else {
			if (has18nLabel()) result = getLabel(locale);
			else result = Is.emptyString(description)?getLabel(locale):description;
		}				
		/* The next lines fail in some charsets
		result = Strings.change(result, "''", "'");
		result = Strings.change(result, "'", "'");
		*/
		result = Strings.change(result, "'", ""); 
		return result;
	}
	
	public String getDescription(ServletRequest request) {
		return getDescription(getLocale(request));
	}	
	public void setDescription(String newDescription) {
		description = newDescription;
	}

	public MetaComponent getMetaComponent() {
		return metaComponent;
	}

	public void setMetaComponent(MetaComponent metaComponent) {
		this.metaComponent = metaComponent;
	}

	public String getJson() throws JsonProcessingException {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public List<String> getJsFunctions() {
		return jsFunctions;
	}

	public void addJsFunctions(List<String> jsFunctions) {
		this.jsFunctions.addAll(jsFunctions);
	}
	
	public void addJsFunction(String jsFunction) {
		this.jsFunctions.add(jsFunction);
	}

	public List<WidgetConfig> getWidgetConfigs() {
		return widgetConfigs;
	}

	public void addWidgetConfigs(List<WidgetConfig> widgetConfigs) {
		this.widgetConfigs.addAll(widgetConfigs);
	}
	
	public void addWidgetConfig(WidgetConfig widgetConfig) {
		this.widgetConfigs.add(widgetConfig);
	}

	public List<String> getOnDocReadyjsFunctions() {
		return onDocReadyjsFunctions;
	}

	public void addOnDocReadyjsFunctions(List<String> onDocReadyjsFunctions) {
		this.onDocReadyjsFunctions.addAll(onDocReadyjsFunctions);
	}
	
	public void addOnDocReadyjsFunction(String onDocReadyjsFunction) {
		this.onDocReadyjsFunctions.add(onDocReadyjsFunction);
	}
	
	public String getLabel() {
		if(StringUtils.isEmpty(label))
			return getName();
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}