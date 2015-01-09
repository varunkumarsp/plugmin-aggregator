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
import org.openxava.annotations.parse.FieldResolverMetaTabImpl;
import org.openxava.component.MetaComponent;
import org.openxava.dropdown.MetaDropDown;
import org.plugmin.core.dao.DropDownBoxDao;
import org.plugmin.core.service.DataSourceRequest.ProjectionDescriptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

@Service
public class DropDownBoxServiceImpl implements DropDownBoxService, ServletContextAware, ApplicationContextAware {

	@Autowired
	DropDownBoxDao dropDownBoxDao;
	
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
			MetaDropDown metaDropDown) {
		MetaComponent component = metaDropDown.getMetaComponent();
		Class<?> clz = component.getMetaEntity().getPOJOClass();
		
		List<ProjectionDescriptor> projections = new ArrayList<ProjectionDescriptor>();
		
		ProjectionDescriptor projection1 = new ProjectionDescriptor();
		projection1.setField(metaDropDown.getKeyProperty());
		projections.add(projection1);

		/**If pojo class doesn't have field with "name", then the id field will be taken as name.
		 *Placing DISTINCT on two id fields results in mysql error. 
		 */
		if(!metaDropDown.getKeyProperty().equals(metaDropDown.getValueProperty())) { 
			ProjectionDescriptor projection2 = new ProjectionDescriptor();
			projection2.setField(metaDropDown.getValueProperty());
			projections.add(projection2);
		}

		req.setProjection(projections);
		
		FieldResolver fieldResolver = metaDropDown.getConfig().getFieldResolver();
		if(fieldResolver == null)
			fieldResolver = new FieldResolverMetaTabImpl(new ArrayList<ColumnVo>());

		req.setFieldResolver(fieldResolver);
		req.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		
		return dropDownBoxDao.getList(req, clz);
	}


	@Override
	public MetaDropDown metaDropDown(String entity, String view) throws Exception {
		MetaComponent component = metaComponentService.find(entity);
		
		MetaDropDown metaDropDown = component.getMetaDropDown(view);
		if(view == null) {
			metaDropDown = component.getMetaDropDown();
		}
		return metaDropDown;
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
