package org.plugmin.core.service;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.plugmin.core.util.PlugminConfigurationUtils.PLUGMIN_PARSER_MODE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
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
public class MetaComponentServiceLazyImpl implements MetaComponentService, ServletContextAware {

	private static final Logger logger = LoggerFactory.getLogger(MetaComponentServiceLazyImpl.class);

	@Autowired
	MetaComponentDao metaComponentDao;
	
	ServletContext servletContext;
	
	List<MetaComponent> metaComponents = new ArrayList<MetaComponent>();
	
	AnnotatedClassParser parser = new AnnotatedClassParser();
	

	@PostConstruct
	public void postConstruct() throws Exception {
		String parserMode = servletContext.getInitParameter(PLUGMIN_PARSER_MODE);
		if(isEmpty(parserMode) || parserMode.toLowerCase().equals("lazy")) {
			logger.info("Entering postConstruct()");
			
			Collection<String> managedClasses = metaComponentDao.init();
			logger.info("Plugmin Managed Classes: " + managedClasses);
			
			logger.info("Leaving postConstruct()");
		} else {
			logger.info("Skipping postConstruct()");
		}
	}
	
	@Override
	public void clear() throws Exception {
		metaComponents.clear();
	}
	
	@Override
	public MetaComponent find(String entity) throws Exception {
		MetaComponent component = null;
		
		for(MetaComponent component_ : metaComponents) {
			String compName = component_.getName().toLowerCase().substring(component_.getName().lastIndexOf('.') + 1);
			if(compName.equals(entity.toLowerCase())) {
				component = component_;
				break;
			}
		}
		
		if(component == null) {
			synchronized (parser) {
				MetaComponent component_ = parser.parse(StringUtils.capitalize(entity));
		   		metaComponents.add(component_);
		   		parser.parse2ndPhase(component_, metaComponents, false);

		   		logger.info("MetaComponents Size: " + metaComponents.size());
		   		
		   		component = component_;
			}
		}

		if(!component.isDoneParsing3rdPhase())
			parser.parse3rdPhase(component, metaComponents, false);
		return component;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaProperty> metaPropertiesTab(String entity, String view) throws Exception {
		MetaComponent component = find(entity);
		MetaTab metaTab = component.getMetaTab(view);
		
		if(!metaTab.isSectionsConfigured()) {
			synchronized (parser) {
				parser.configureSections(metaTab, metaComponents, false);
			}
		}
		
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
	public List<MetaProperty> metaPropertiesView(String entity, String view) throws Exception {
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
