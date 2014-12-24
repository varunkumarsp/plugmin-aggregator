package org.plugmin.core.service;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.plugmin.core.util.PlugminConfigurationUtils.PLUGMIN_PARSER_MODE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.openxava.annotations.parse.AnnotatedClassParser;
import org.openxava.component.MetaComponent;
import org.openxava.dropdown.MetaDropDown;
import org.openxava.model.meta.MetaProperty;
import org.openxava.tab.meta.MetaTab;
import org.plugmin.core.dao.MetaComponentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

@Service
public class MetaComponentServiceEagerImpl implements MetaComponentService, ServletContextAware {

	private static final Logger logger = LoggerFactory.getLogger(MetaComponentServiceEagerImpl.class);

	@Autowired
	MetaComponentDao metaComponentDao;
	
	ServletContext servletContext;
	
	List<MetaComponent> metaComponents = new ArrayList<MetaComponent>();
	

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void postConstruct() throws Exception {
		String parserMode = servletContext.getInitParameter(PLUGMIN_PARSER_MODE);
		if(isNotEmpty(parserMode) && parserMode.toLowerCase().equals("eager")) {
			logger.info("Entering postConstruct()");
			
			long startTime = System.currentTimeMillis();
			
			Collection<String> managedClasses = metaComponentDao.init();
			logger.info("Plugmin Managed Classes: " + managedClasses);
	        
	        AnnotatedClassParser parser = new AnnotatedClassParser();
	        for (String clazz : managedClasses) {
	       		MetaComponent metaComponent = parser.parse(Class.forName(clazz).getSimpleName());
	       		metaComponents.add(metaComponent);
	           	logger.debug("Found component " + metaComponent.getName());
			}
	        
	        for (MetaComponent metaComponent : metaComponents) {
				parser.parse2ndPhase(metaComponent, metaComponents, true);
			}
	        
	        for (MetaComponent metaComponent : metaComponents) {
	        	Collection<MetaTab> metaTabs = metaComponent.getMetaTabs();
	    		for (MetaTab tab : metaTabs) {
	    			parser.configureSections(tab, metaComponents, true);
				}
			}
	        
	        long stopTime = System.currentTimeMillis();
	        long elapsedTime = stopTime - startTime;
	        logger.info("Execution time of postConstruct(): " + MILLISECONDS.toSeconds(elapsedTime) + " seconds");
	        
	        logger.info("Leaving postConstruct()");
		} else {
			logger.info("Skipping postConstruct()");
		}
	}
	
	@Override
	public MetaComponent find(String entity) {
		for(MetaComponent component : metaComponents) {
			if(component.getName().toLowerCase().equals(entity.toLowerCase()))
				return component;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaProperty> metaPropertiesTab(String entity, String view) {
		MetaComponent component = find(entity);
		MetaTab metaTab = component.getMetaTab(view);
		if(view == null) {
			metaTab = component.getMetaTab();
		}
		List<MetaProperty> metaProperties = metaTab.namesToMetaProperties(metaTab.getPropertiesNames());
		return metaProperties;
	}

	@Override
	public List<MetaProperty> metaPropertiesDropDown(String entity, String view) throws Exception {
		MetaComponent component = find(entity);
		MetaDropDown metaDropDown = component.getMetaDropDown(view);
		if(view == null) {
			metaDropDown = component.getMetaDropDown();
		}
		List<MetaProperty> metaProperties = metaDropDown.namesToMetaProperties(metaDropDown.getPropertiesNames());
		return metaProperties;
	}

	@Override
	public List<MetaProperty> metaPropertiesView(String entity, String view) {
		MetaComponent component = find(entity);
		MetaTab metaTab = component.getMetaTab(view);
		if(view == null) {
			metaTab = component.getMetaTab();
		}
		List<MetaProperty> metaProperties = metaTab.namesToMetaProperties(metaTab.getPropertiesNames());
		return metaProperties;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
}
