package org.openxava.dropdown;

import static org.openxava.annotations.parse.JsonUtil.toJson;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.FeatureNotImplementedException;
import org.openxava.annotations.extended.ui.config.vo.DropDownConfigVo;
import org.openxava.component.MetaComponent;
import org.openxava.filters.IFilter;
import org.openxava.filters.meta.MetaFilter;
import org.openxava.model.meta.MetaEntity;
import org.openxava.model.meta.MetaModel;
import org.openxava.model.meta.MetaProperty;
import org.openxava.tab.meta.MetaRowStyle;
import org.openxava.util.ElementNotFoundException;
import org.openxava.util.Labels;
import org.openxava.util.XavaException;
import org.openxava.util.meta.MetaElement;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MetaDropDown extends MetaElement implements java.io.Serializable, Cloneable {

	private static Log log = LogFactory.getLog(MetaDropDown.class);

	private String defaultOrder;
	private String sQLBaseCondition;
	private String keyProperty = null;
	private String valueProperty = null;
	private List<MetaProperty> metaProperties = null;
	private List<String> propertiesNames = null;
	private String properties; // separated by commas
	private Map<String, MetaProperty> metaPropertiesDropDown;
	private String select;
	private String modelName;
	private MetaModel metaModel;
	private boolean excludeAll = false;
	private boolean excludeByKey = false;
	private MetaFilter metaFilter;
	private IFilter filter;
	private String baseCondition;
	private Collection rowStyles;
	private String id;
	private DropDownConfigVo config;
	
	public MetaDropDown() {
	}
	
	public MetaDropDown(MetaComponent component) {
		setMetaComponent(component);
	}

	public static MetaDropDown createDefault(MetaComponent component) throws Exception {
		MetaDropDown dd = new MetaDropDown();
		dd.setMetaComponent(component);
		dd.setKeyProperty(defaultKeyProperty(component.getMetaEntity()));
		dd.setValueProperty(defaultValueProperty(component.getMetaEntity()));
		return dd;
	}
	
	public static MetaDropDown createDefault(MetaModel metaModel) throws Exception { 
		MetaDropDown dd = new MetaDropDown();
		dd.setMetaComponent(metaModel.getMetaComponent());
		dd.setKeyProperty(defaultKeyProperty(metaModel.getMetaComponent().getMetaEntity()));
		dd.setValueProperty(defaultValueProperty(metaModel.getMetaComponent().getMetaEntity()));
		return dd;
	}

	public static String defaultKeyProperty(MetaEntity metaEntity) throws FeatureNotImplementedException {
		Collection<String> allKeyPropertiesNames = metaEntity.getAllKeyPropertiesNames();
		if(allKeyPropertiesNames.size() > 1) {
			throw new FeatureNotImplementedException("Default dropdown for entity [\"" + metaEntity.getName() + "\"] with composite key has not been implemented. Try using single key or write the sql in 'baseCondition' attribute");
		}
		String idProperty = allKeyPropertiesNames.toArray(new String[allKeyPropertiesNames.size()])[0];
		return idProperty;
	}
	
	public static String defaultValueProperty(MetaEntity metaEntity) throws Exception {
		return metaEntity.propertyForDefaultLabel();
	}
	
	public void setDefaultValues() {
		// TODO Auto-generated method stub
		
	}
	
	public void addMetaRowStyle(MetaRowStyle style) {
		if (rowStyles == null)
			rowStyles = new ArrayList();
		rowStyles.add(style);
	}
	
	public List<MetaProperty> namesToMetaProperties(Collection<String> names) throws XavaException {
		List<MetaProperty> metaProperties = new ArrayList<MetaProperty>();
		MetaProperty metaPropertyDd = null;
		try {
			for (String name : names) {
				MetaProperty metaProperty = getMetaModel()
						.getMetaProperty(name).cloneMetaProperty();
				metaProperty.setQualifiedName(name);
				String labelId = null;
				if (representCollection()) {
					labelId = getId() + "." + name;
					// If there is no specific translation for the collection,
					// we take the translation from the default tab.
					if (!Labels.existsExact(labelId)) {
						labelId = getIdForDefaultDropDown() + ".properties." + name;
					}
				} else {
					labelId = getId() + ".properties." + name;
				}
				if (Labels.exists(labelId)) {
					metaProperty.setLabelId(labelId);
				} else if (metaPropertiesDropDown != null) {
					// By now only the label overwritten from the property of
					// tab
					metaPropertyDd = (MetaProperty) metaPropertiesDropDown.get(name);
					if (metaPropertyDd != null) {
						metaProperty = metaProperty.cloneMetaProperty();
						metaProperty.setLabel(metaPropertyDd.getLabel());
					}
				}
				metaProperties.add(metaProperty);
			}
		} catch (ElementNotFoundException ex) {
			MetaProperty notInEntity = new MetaProperty();
			notInEntity.setName(getName());
			notInEntity.setTypeName("java.lang.Object");
			if (metaPropertyDd != null) {
				notInEntity.setLabel(metaPropertyDd.getLabel());
			}
			metaProperties.add(notInEntity);
		}
		return metaProperties;
	}

	private String getIdForDefaultDropDown() {
		return getMetaComponent().getName() + ".dropdown";
	}
	
	private boolean representCollection() {
		return false;
	}

	public String getDefaultOrder() {
		return defaultOrder;
	}

	public void setDefaultOrder(String defaultOrder) {
		this.defaultOrder = defaultOrder;
	}

	public String getsQLBaseCondition() {
		return sQLBaseCondition;
	}

	public void setsQLBaseCondition(String sQLBaseCondition) {
		this.sQLBaseCondition = sQLBaseCondition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MetaComponent getMetaComponent() {
		return metaComponent;
	}

	public void setMetaComponent(MetaComponent metaComponent) {
		this.metaComponent = metaComponent;
		this.metaModel = this.metaComponent.getMetaEntity();
		this.modelName = this.metaComponent.getName();
	}

	public String getKeyProperty() {
		return keyProperty;
	}

	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
		if(propertiesNames == null)
			propertiesNames = new ArrayList<String>();
		propertiesNames.add(keyProperty);
	}

	public String getValueProperty() {
		return valueProperty;
	}

	public void setValueProperty(String valueProperty) {
		this.valueProperty = valueProperty;
		if(propertiesNames == null)
			propertiesNames = new ArrayList<String>();
		propertiesNames.add(valueProperty);
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public MetaModel getMetaModel() {
		return metaModel;
	}

	public void setMetaModel(MetaModel metaModel) {
		this.metaModel = metaModel;
	}

	public boolean isExcludeAll() {
		return excludeAll;
	}

	public void setExcludeAll(boolean excludeAll) {
		this.excludeAll = excludeAll;
	}

	public boolean isExcludeByKey() {
		return excludeByKey;
	}

	public void setExcludeByKey(boolean excludeByKey) {
		this.excludeByKey = excludeByKey;
	}

	public MetaFilter getMetaFilter() {
		return metaFilter;
	}

	public void setMetaFilter(MetaFilter metaFilter) {
		this.metaFilter = metaFilter;
	}

	public IFilter getFilter() {
		return filter;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public String getBaseCondition() {
		return baseCondition;
	}

	public void setBaseCondition(String baseCondition) {
		this.baseCondition = baseCondition;
	}

	public Collection getRowStyles() {
		return rowStyles;
	}

	public void setRowStyles(Collection rowStyles) {
		this.rowStyles = rowStyles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MetaProperty> getMetaProperties() {
		if (metaProperties == null) {
			metaProperties = namesToMetaProperties(getPropertiesNames());
		}
		return metaProperties;
	}

	public void setMetaProperties(List<MetaProperty> metaProperties) {
		this.metaProperties = metaProperties;
	}
	
	public List getPropertiesNames() throws XavaException {
		if (propertiesNames == null) {
			if (!areAllProperties()) {
				propertiesNames = createPropertiesNames();
			} else {
				// for dropdown box, no chance for * properties
			}
		}
		return propertiesNames;
	}
	
	private boolean areAllProperties() {
		return false;
	}

	private List<String> createPropertiesNames() {
		StringTokenizer st = new StringTokenizer(
				removeTotalProperties(properties), ",;");
		List<String> result = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String name = st.nextToken().trim();
			result.add(name);
		}
		return result;
	}
	
	private String removeTotalProperties(String properties) {
		if (!properties.contains("["))
			return properties;
		return properties.replaceAll("\\[[^\\]]*\\]", "");
	}
	
	@Override
	public String toString() {
		return "MetaDropDown [defaultOrder=" + defaultOrder
				+ ", sQLBaseCondition=" + sQLBaseCondition + ", name=" + getName()
				+ ", metaComponent=" + getMetaComponent() + ", keyProperty="
				+ keyProperty + ", valueProperty=" + valueProperty
				+ ", select=" + select + ", modelName=" + modelName
				+ ", metaModel=" + metaModel + ", excludeAll=" + excludeAll
				+ ", excludeByKey=" + excludeByKey + ", metaFilter="
				+ metaFilter + ", filter=" + filter + ", baseCondition="
				+ baseCondition + ", rowStyles=" + rowStyles + ", id=" + id + "]";
	}

	public DropDownConfigVo getConfig() {
		return config;
	}

	public void setConfig(DropDownConfigVo config) {
		if(config != null)
			this.config = config;
	}

	public String getJson() throws JsonProcessingException {
		if(json == null)
			json = toJson(config);
		return json;
	}

	public void setJson(String json) {
		if(isNotEmpty(json))
			this.json = json;
	}

	public List<String> getJsFunctions() {
		return jsFunctions;
	}

	public void addJsFunctions(List<String> jsFunctions) {
		if(CollectionUtils.isNotEmpty(jsFunctions)) {
			if(this.jsFunctions == null)
				this.jsFunctions = new ArrayList<String>();
			this.jsFunctions.addAll(jsFunctions);
		}
	}

}
