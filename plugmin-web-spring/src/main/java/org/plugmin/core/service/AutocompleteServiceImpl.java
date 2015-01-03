package org.plugmin.core.service;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.plugmin.core.util.PlugminConfigurationUtils.PLUGMIN_PARSER_MODE;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.hibernate.criterion.CriteriaSpecification;
import org.openxava.annotations.extended.ui.config.vo.ColumnVo;
import org.openxava.annotations.parse.FieldResolver;
import org.openxava.annotations.parse.FieldResolverImpl;
import org.openxava.autocomplete.MetaAutocomplete;
import org.openxava.component.MetaComponent;
import org.plugmin.core.dao.AutocompleteDao;
import org.plugmin.core.service.DataSourceRequest.ProjectionDescriptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

@Service
public class AutocompleteServiceImpl implements AutocompleteService, ServletContextAware, ApplicationContextAware {

	@Autowired
	AutocompleteDao autocompleteDao;
	
	MetaComponentService metaComponentService;

	private ServletContext servletContext;

	private ApplicationContext applicationContext;

	
	@PostConstruct
	public void postConstruct() {
		String parserMode = servletContext.getInitParameter(PLUGMIN_PARSER_MODE);
		
		Class<?> metaComponentServiceType = MetaComponentServiceLazyImpl.class;
		if(isNotEmpty(parserMode) && parserMode.toLowerCase().equals("eager"))
			metaComponentServiceType = MetaComponentServiceEagerImpl.class;
		
		metaComponentService = (MetaComponentService) applicationContext.getBean(metaComponentServiceType);
	}

	@Override
	public DataSourceResult getList(DataSourceRequest req,
			MetaAutocomplete metaAutocomplete) {
		MetaComponent component = metaAutocomplete.getMetaComponent();
		Class<?> clz = component.getMetaEntity().getPOJOClass();
		
		List<ProjectionDescriptor> projections = new ArrayList<ProjectionDescriptor>();
		
		ProjectionDescriptor projection1 = new ProjectionDescriptor();
		projection1.setField(metaAutocomplete.getValueProperty());
		projections.add(projection1);

		req.setProjection(projections);
		
		FieldResolver fieldResolver = metaAutocomplete.getConfig().getFieldResolver();
		if(fieldResolver == null)
			fieldResolver = new FieldResolverImpl(new ArrayList<ColumnVo>());

		req.setFieldResolver(fieldResolver);
		req.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		
		return autocompleteDao.getList(req, clz);
	}


	@Override
	public MetaAutocomplete metaAutocomplete(String entity, String view) throws Exception {
		MetaComponent component = metaComponentService.find(entity);
		
		MetaAutocomplete metaAutocomplete = component.getMetaAutocomplete(view);
		if(view == null) {
			metaAutocomplete = component.getMetaAutocomplete();
		}
		return metaAutocomplete;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
