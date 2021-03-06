package org.plugmin.core.service;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.EntityUtil.idField;
import static org.plugmin.core.util.FilterUtil.cast;
import static org.plugmin.core.util.PlugminConfigurationUtils.PLUGMIN_PARSER_MODE;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.commons.collections.CollectionUtils;
import org.openxava.annotations.extended.ui.config.vo.ColumnVo;
import org.openxava.annotations.extended.ui.config.vo.FilterItem;
import org.openxava.annotations.extended.ui.config.vo.TabConfigVo;
import org.openxava.annotations.parse.FieldResolver;
import org.openxava.annotations.parse.FieldResolverMetaTabImpl;
import org.openxava.annotations.parse.ResultTransformerMetaTabImpl;
import org.openxava.component.MetaComponent;
import org.openxava.model.meta.MetaProperty;
import org.openxava.tab.meta.MetaTab;
import org.plugmin.core.dao.GridDao;
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
public class GridServiceImpl implements GridService, ServletContextAware, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(GridServiceImpl.class);

	MetaComponentService metaComponentService;
	
	@Autowired
	GridDao gridDao;

	@Autowired(required = false)
	DataSourceFilterSetter dataSourceFilterSetter;

	private ServletContext servletContext;

	private ApplicationContext applicationContext;
	
	private static GridService INSTANCE;
	
	
	@PostConstruct
	public void postConstruct() {
		String parserMode = servletContext.getInitParameter(PLUGMIN_PARSER_MODE);
		
		Class<?> metaComponentServiceType = MetaComponentServiceLazyImpl.class;
		if(isNotEmpty(parserMode) && parserMode.toLowerCase().equals("eager"))
			metaComponentServiceType = MetaComponentServiceEagerImpl.class;
		
		metaComponentService = (MetaComponentService) applicationContext.getBean(metaComponentServiceType);
		
		INSTANCE = this;
	}
	
	@Override
	public MetaTab metaTab(String entity, String view) throws Exception {
		MetaComponent component = metaComponentService.find(entity);
		
		MetaTab metaTab = component.getMetaTab(view);
		if(view == null) {
			metaTab = component.getMetaTab();
		}
		return metaTab;
	}

	@Override
	public DataSourceResult read(DataSourceRequest req, MetaTab metaTab) throws Exception {
		MetaComponent component = metaTab.getMetaComponent();
		Class<?> pojoClass = component.getMetaEntity().getPOJOClass();
		
		List<ProjectionDescriptor> projections = new ArrayList<ProjectionDescriptor>();
		
		TabConfigVo config = metaTab.getConfig();
		List<ColumnVo> columns = config.getColumns();
		Set<String> projectionFields = new LinkedHashSet<String>();
		
		for (ColumnVo column : columns) {
			projectionFields.addAll(getFields(column));
		}
		
		String idField = idField(pojoClass);
		includeIdFieldIfNot(projectionFields, idField); //id field needed for selecting distinct rows on left outer join.
		
		moveIdFieldToTop(projectionFields, idField); //id field should be in the first position so that the "DISTINCT" keyword is placed correctly
		
		for (String projectionField : projectionFields) {
			ProjectionDescriptor projection = new ProjectionDescriptor();
			projection.setField(projectionField);
			projections.add(projection);
		}
		
		req.setProjection(projections);

		if(dataSourceFilterSetter != null) {
			List<FilterItem> filters = dataSourceFilterSetter.filters(metaTab);
			
			if(CollectionUtils.isNotEmpty(filters)) {
				FilterDescriptor filter = req.getFilter();
				if(filter.getLogic() == null) {
					filter.setLogic("and");
					filter.setIgnoreCase(true);
				}
				
				for (FilterItem filterItem : filters) {
					filter.getFilters().add(cast(filterItem));
				}
			}
		}
		
		
		FieldResolver fieldResolver = new FieldResolverMetaTabImpl(columns);
		req.setFieldResolver(fieldResolver);
		req.setResultTransformer(new ResultTransformerMetaTabImpl(fieldResolver));
		
		return gridDao.getList(req, pojoClass);
	}
	
	private void moveIdFieldToTop(Set<String> projectionFields, String idField) {
		Set<String> set = new LinkedHashSet<String>(projectionFields);
		
		projectionFields.clear();
		projectionFields.add(idField);
		
		for (String field : set) {
			if(!field.equals(idField))
				projectionFields.add(field);
		}
	}

	private void includeIdFieldIfNot(Set<String> projectionFields, String idField) {
		if(!projectionFields.contains(idField)) {
			projectionFields.add(idField);
		}
	}

	private List<String> getFields(ColumnVo column) {
		List<String> fields = new ArrayList<String>();
		
		if(!column.isCommandColumn()) {
			String field = column.getField().replaceAll("_", ".");
			
			if(column.isForeignEntity()) {
				fields.add(field + "." + column.getForeignEntityIdField());
				fields.add(field + "." + column.getForeignEntityNameField());
			} else if(field.contains(".")) { //one-to-one
				fields.add(field);
			} else {
				fields.add(field);
			}
		}
		
		return fields;
	}
	
	@Override
	public List<MetaProperty> metaProperties(String entity, String view) throws Exception {
		List<MetaProperty> metaProperties = metaComponentService.metaPropertiesTab(entity, view);
		return metaProperties;
	}

	@Override
	@Transactional
	public DataSourceResult create(List<Map<String, Object>> models, MetaTab metaTab) throws Exception {
		DataSourceResult result = new DataSourceResult();
		List<Object> data = new ArrayList<Object>(models.size());
		result.setData(data);
		
		MetaComponent component = metaTab.getMetaComponent();
		Class<?> pojoClass = component.getMetaEntity().getPOJOClass();
		for (Map<String, Object> model : models) {
			Object generatedId = gridDao.create(model, pojoClass, metaTab.getName());

			String idField = idField(pojoClass);
			model.put(idField, generatedId);
			
			data.add(model);
		}
		return result;
	}

	@Override
	@Transactional
	public DataSourceResult update(List<Map<String, Object>> models, MetaTab metaTab) throws Exception {
		DataSourceResult result = new DataSourceResult();
		List<Object> data = new ArrayList<Object>(models.size());
		result.setData(data);
		
		MetaComponent component = metaTab.getMetaComponent();
		Class<?> pojoClass = component.getMetaEntity().getPOJOClass();
		for (Map<String, Object> model : models) {
			gridDao.update(model, pojoClass, metaTab.getName());
			
			data.add(model);
		}
		return result;
	}

	@Override
	@Transactional
	public DataSourceResult delete(List<Map<String, Object>> models, MetaTab metaTab) throws Exception {
		DataSourceResult result = new DataSourceResult();
		List<Object> data = new ArrayList<Object>(models.size());
		result.setData(data);
		
		MetaComponent component = metaTab.getMetaComponent();
		Class<?> pojoClass = component.getMetaEntity().getPOJOClass();
		for (Map<String, Object> model : models) {
			gridDao.delete(model, pojoClass, metaTab.getName());
			
			data.add(model);
		}
		return result;
	}

	public static GridService gridService() {
		return INSTANCE;
	}

	@Override
	public void clear() throws Exception {
		metaComponentService.clear();
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
