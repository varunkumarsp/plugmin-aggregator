package org.plugmin.core.service;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.EntityUtil.idField;
import static org.plugmin.core.util.FilterUtil.cast;
import static org.plugmin.core.util.PlugminConfigurationUtils.PLUGMIN_PARSER_MODE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.openxava.annotations.extended.ui.config.vo.AngularScope;
import org.openxava.annotations.extended.ui.config.vo.ElementVo;
import org.openxava.annotations.extended.ui.config.vo.FilterItem;
import org.openxava.annotations.parse.FieldResolver;
import org.openxava.annotations.parse.FieldResolverMetaViewImpl;
import org.openxava.annotations.parse.ResultTransformerMetaViewImpl;
import org.openxava.component.MetaComponent;
import org.openxava.model.meta.MetaProperty;
import org.openxava.view.meta.MetaView;
import org.plugmin.core.dao.ViewDao;
import org.plugmin.core.service.DataSourceRequest.FilterDescriptor;
import org.plugmin.core.service.DataSourceRequest.ProjectionDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;

@Service
public class ViewServiceImpl implements ViewService, ServletContextAware, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(ViewServiceImpl.class);

	MetaComponentService metaComponentService;
	
	@Autowired
	ViewDao viewDao;

	@Autowired(required = false)
	DataSourceFilterSetter dataSourceFilterSetter;

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
	public MetaView metaView(String entity, String view) throws Exception {
		MetaComponent component = metaComponentService.find(entity);
		
		MetaView metaView = component.getMetaEntity().getMetaView(view);
		if(view == null) {
			metaView = component.getMetaEntity().getMetaView("default");
		}
		return metaView;
	}

	@Override
	public List<MetaProperty> metaProperties(String entity, String view)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> read(String entityId, MetaView metaView)
			throws Exception {
		Class<?> entity = metaView.getMetaComponent().getMetaEntity().getPOJOClass();
		removeDuplicates(metaView.getAngularScopes());
		DataSourceRequest req = new DataSourceRequest();
		
		List<ProjectionDescriptor> projections = new ArrayList<ProjectionDescriptor>();
		Set<String> projectionFields = new LinkedHashSet<String>();

		for (ElementVo element : metaView.getElements()) {
			projectionFields.addAll(getFields(element));
		}
		
		for (String projectionField : projectionFields) {
			ProjectionDescriptor projection = new ProjectionDescriptor();
			projection.setField(projectionField);
			projections.add(projection);
		}
		req.setProjection(projections);
		
		FilterDescriptor filter = req.getFilter();
		if(filter.getLogic() == null) {
			filter.setLogic("and");
			filter.setIgnoreCase(true);
		}
		FilterItem filterItem = new FilterItem(idField(entity), entityId, "eq");
		filter.getFilters().add(cast(filterItem));
		
		FieldResolver fieldResolver = new FieldResolverMetaViewImpl(metaView.getElements());
		req.setFieldResolver(fieldResolver);
		req.setResultTransformer(new ResultTransformerMetaViewImpl(fieldResolver));
		
		return (Map<String, Object>) viewDao.read(req, entity);
	}

	private void removeDuplicates(List<AngularScope> angularScopes) {
		Set<AngularScope> angularScopes_ = new HashSet<AngularScope>(angularScopes);
		angularScopes.clear();
		angularScopes.addAll(angularScopes_);
	}

	@Override
	@Transactional
	public Map<String, Object> create(Map<String, Object> model, MetaView metaView) throws Exception {
		MetaComponent component = metaView.getMetaComponent();
		Class<?> pojoClass = component.getMetaEntity().getPOJOClass();
		
		Object generatedId = viewDao.create(model, pojoClass, metaView.getName());

		String idField = idField(pojoClass);
		model.put(idField, generatedId);
		
		return model;
	}

	@Override
	@Transactional
	public Map<String, Object> update(String entityId, Map<String, Object> model, MetaView metaView) throws Exception {
		MetaComponent component = metaView.getMetaComponent();
		Class<?> pojoClass = component.getMetaEntity().getPOJOClass();
		
		viewDao.update(entityId, model, pojoClass, metaView.getName());
		
		return model;
	}
	
	private List<String> getFields(ElementVo element) {
		List<String> fields = new ArrayList<String>();
		
		String field = element.getField().replaceAll("_", ".");
		
		if(element.isForeignEntity()) {
			fields.add(field + "." + element.getForeignEntityIdField());
		} else if(field.contains(".")) { //one-to-one
			fields.add(field);
		} else {
			fields.add(field);
		}
		
		return fields;
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
