package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.istage.util.AdminUtil.titleFromFieldName;
import static org.istage.util.AdminUtil.titleFromLabelAnnotation;
import static org.openxava.annotations.parse.ComponentUtil.autocompleteConfig;
import static org.openxava.annotations.parse.ComponentUtil.createAutocomplete;
import static org.openxava.annotations.parse.ComponentUtil.createDropDown;
import static org.openxava.annotations.parse.ComponentUtil.createEnumDropDown;
import static org.openxava.annotations.parse.ComponentUtil.dropDownConfig;
import static org.openxava.annotations.parse.ComponentUtil.foreignComponent;
import static org.openxava.annotations.parse.ComponentUtil.jsFunctions;
import static org.openxava.annotations.parse.ComponentUtil.registerAutocomplete;
import static org.openxava.annotations.parse.ComponentUtil.registerDropDown;
import static org.openxava.annotations.parse.EntityUtil._isIdField;
import static org.openxava.annotations.parse.EntityUtil.hasCompositePrimaryKey;
import static org.openxava.annotations.parse.EntityUtil.idField;
import static org.openxava.annotations.parse.EntityUtil.isForeignEntity_;
import static org.openxava.annotations.parse.EntityUtil.isManyToMany;
import static org.openxava.annotations.parse.EntityUtil.isManyToOne;
import static org.openxava.annotations.parse.EntityUtil.isOneToMany;
import static org.openxava.annotations.parse.EntityUtil.isOneToOne;
import static org.openxava.annotations.parse.EntityUtil.nameField;
import static org.openxava.annotations.parse.EntityUtil.randomInt;
import static org.openxava.annotations.parse.ReflectionUtil.fieldReflection;
import static org.openxava.annotations.parse.ReflectionUtil.findAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.openxava.FeatureNotImplementedException;
import org.openxava.annotations.extended.Attribute;
import org.openxava.annotations.extended.CascadeDropdown;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.DropDownConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultDropDownConfig;
import org.openxava.annotations.extended.ui.config.enums.Aggregates;
import org.openxava.annotations.extended.ui.config.enums.EditableMode;
import org.openxava.annotations.extended.ui.config.enums.Operator;
import org.openxava.annotations.extended.ui.config.grid.Command;
import org.openxava.annotations.extended.ui.config.grid.EnumValues;
import org.openxava.annotations.extended.ui.config.grid.TabColumn;
import org.openxava.annotations.extended.ui.use.UseAutocomplete;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldArray;
import org.openxava.annotations.parse.JsFieldString;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;
import org.openxava.autocomplete.MetaAutocomplete;
import org.openxava.component.MetaComponent;
import org.openxava.dropdown.MetaDropDown;
import org.openxava.model.meta.MetaEntity;
import org.openxava.tab.meta.MetaTab;
import org.openxava.util.meta.MetaElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(TabColumn.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class ColumnVo implements JsFieldValueResolver {

	@JsonIgnore
	private static Logger logger = LoggerFactory.getLogger(ColumnVo.class);
	
	private List<Aggregates> aggregates;
	private Map<String, String> attributes;
	
	@CompositeField("command")
	private List<CommandVo> commandObjs;
	
	@CompositeField("command")
	private String[] commandArray;
	
	@CompositeField("editor")
	private String editor;
	
	@DefaultValue("true")
	private Boolean encoded;
	
	@DefaultValue("true")
	private Boolean filterableBool;
	
	private FilterableColumnVo filterable;
	
	@CompositeField("footerTemplate")
	private String footerTemplateStr;
	
	@CompositeField("footerTemplate")
	private String footerTemplateFn;
	
	private String format;
	
	@DefaultValue("true")
	private Boolean groupable;
	
	private String groupHeaderTemplate;
	private String groupFooterTemplate;
	private Map<String, String> headerAttributes;
	
	@CompositeField("headerTemplate")
	private String headerTemplateStr;
	
	@CompositeField("headerTemplate")
	private String headerTemplateFn;
	
	@DefaultValue("false")
	private Boolean hidden;
	
	@DefaultValue("false")
	private Boolean locked;
	
	@DefaultValue("true")
	private Boolean lockable;
	
	@DefaultValue("true")
	private Boolean sortable;
	
	private String template;
	private String title;
	private String width;
	
	@DefaultValue("true")
	private Boolean menu;
	
	private String field;
	
	private List<Map<String, String>> values;
	
	@JsonIgnore
	private Field metaField; //for internal use only
	
	@JsonIgnore
	private Boolean editable = true; //for internal use only
	
	@JsonIgnore
	private Boolean idField = false; //for internal use only

	@JsonIgnore
	private Boolean foreignEntity = false; //for internal use only

	@JsonIgnore
	private String foreignEntityIdField; //for internal use only

	@JsonIgnore
	private String foreignEntityNameField; //for internal use only

	@JsonIgnore
	private MetaElement metaElement; //for internal use only
	
	@JsonIgnore
	private boolean commandColumn = false; //for internal use only

	@JsonIgnore
	private boolean serializeConfig = false; //for internal use only

	@JsonIgnore
	private String[] enumValues; //for internal use only
	

	/**
	 * A command column constructor
	 * @param strings
	 */
	public ColumnVo(String[] commands) {
		setCommandArray(commands);
		setCommandColumn(true);
	}
	
	public ColumnVo(List<CommandVo> commands) {
		addCommandObjs(commands);
		setCommandColumn(true);
	}
	
	/**
	 * Used by dropdowns & autocomplete
	 * @param field
	 * @param metaField
	 * @param serializeConfig 
	 * @throws Exception
	 */
	public ColumnVo(String field, Field metaField, boolean serializeConfig) throws Exception {
		setTitle(field);
		setField(field);
		setMetaField(metaField);
		setSerializeConfig(serializeConfig);
	}

	/**
	 * Used by grids where some columns in the grid needs dropdown editor
	 * @param field
	 * @param metaField
	 * @param metaElement
	 * @param metaComponents
	 * @throws Exception
	 */
	public ColumnVo(String field, Field metaField, MetaElement metaElement, List<MetaComponent> metaComponents, boolean serializeConfig) throws Exception {
		try {
			setSerializeConfig(serializeConfig);
			setMetaElement(metaElement);
			MetaEntity metaEntity = metaElement.getMetaComponent().getMetaEntity();
			
			if(isForeignEntity_(field, metaEntity)) {
				if(isManyToOne(field, metaEntity.getPOJOClass())) {
					setTitle(field);
					setField(field);
					if(field.contains(".")) {
						metaField = fieldReflection(field, metaEntity.getPOJOClass());
						init(metaField, metaComponents);
						editable = false;
					} else {
						String nameField = nameField(metaEntity.getPOJOClass().getDeclaredField(field).getType());
						metaField = fieldReflection(field + "." + nameField, metaEntity.getPOJOClass());
						setMetaField(metaField);
						foreignEntity = true;
						configureManyToOneColumn(field, metaElement, metaComponents);
					}
				} else if(isOneToOne(field, metaEntity.getPOJOClass())){
					if(field.contains(".")) {
						setTitle(field);
						setField(field);
						init(metaField, metaComponents);
						configureOneToOneColumn(field, metaElement, metaComponents);
					} else {
						String nameField = nameField(metaEntity.getPOJOClass().getDeclaredField(field).getType());
						metaField = fieldReflection(field + "." + nameField, metaEntity.getPOJOClass());
						setTitle(field);
						field = field + "." + nameField;
						setField(field);
						init(metaField, metaComponents);
						configureOneToOneColumn(field, metaElement, metaComponents);
					}
				} else if(isOneToMany(field, metaEntity.getPOJOClass())){
//					configureOneToManyColumn(field, metaElement, metaComponents);
				} else if(isManyToMany(field, metaEntity.getPOJOClass())){
//					configureManyToManyColumn(field, metaElement, metaComponents);
				}
			} else {
				setTitle(field);
				setField(field);
				init(metaField, metaComponents);
				if(_isIdField(metaField, metaEntity))
					configureIdColumn();
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * NOTE-1: Foreign entities with @OneToOne mapping don't need a dropdown. Eg: ProductsDescription.productName
	 * NOTE-2: Foreign entities having composite keys couldn't be given a dropdown. Instead throws FeatureNotImplementedException.
	 * @param field
	 * @param metaField
	 * @param metaEntity
	 * @param metaComponents
	 * @param jsFunctions
	 * @throws Exception
	 */
	private void configureManyToOneColumn(String field,	MetaElement metaElement, List<MetaComponent> metaComponents) throws Exception {
		MetaEntity metaEntity = metaElement.getMetaComponent().getMetaEntity();
		Class<?> pojo = metaEntity.getPOJOClass();
		Field metaField = pojo.getDeclaredField(field);
		metaField.setAccessible(true);
		
		Class<?> foreignEntity = metaField.getType();

		if(hasCompositePrimaryKey(foreignEntity)) {
			String msg = "Composite keys not supported: " + metaEntity.getPOJOClassName();
			logger.error(msg, new FeatureNotImplementedException(msg));
		}
		
		foreignEntityIdField = idField(foreignEntity);
		foreignEntityNameField = nameField(foreignEntity);

		StringBuffer cascadeFrom = new StringBuffer(); //StringBuffer is used to mutate the variable in another method
		StringBuffer cascadeFromField = new StringBuffer();
		
		if(metaElement instanceof MetaTab) {
			MetaTab metaTab = (MetaTab) metaElement;
			
			CascadeDropdown cascade = findAnnotation(CascadeDropdown.class, metaField, metaTab);
			if(cascade != null) {
				configureCascadingOptions(metaTab, foreignEntity, cascade, cascadeFrom, cascadeFromField);
			}
			
			MetaComponent foreignComponent = foreignComponent(foreignEntity, metaComponents, serializeConfig);
			
			String dropdownName = foreignComponent.getMetaEntity().getName().toLowerCase() + "_" + foreignEntityNameField + "_" + "_dropdown_" + randomInt();
			MetaDropDown metaDropDown = createDropDown(dropDownConfig(metaTab, metaField), dropdownName, foreignEntityIdField, foreignEntityNameField, cascadeFrom.toString(), cascadeFromField.toString(), foreignComponent, serializeConfig);
			registerDropDown(metaDropDown, foreignComponent, metaComponents);
			
			setEditor(dropdownName);
			configureFilterCell(foreignEntityNameField, metaDropDown.getConfig().getDataSourceObj());
			setTemplate("#=" + field + "." + getForeignEntityNameField() + "#");
			
			List<String> jsFunctions = getMetaElement().getJsFunctions();
			jsFunctions.addAll(jsFunctions(dropdownName, metaDropDown));
		}
	}

	private void configureCascadingOptions(MetaTab metaTab, 
			Class<?> foreignEntity, CascadeDropdown cascade,
			StringBuffer cascadeFrom, StringBuffer cascadeFromField) throws Exception {
		MetaEntity metaEntity = metaTab.getMetaComponent().getMetaEntity();
		Class<?> pojo = metaEntity.getPOJOClass();
		
		Field _cascadeFromField = pojo.getDeclaredField(cascade.from());
		if(_cascadeFromField != null) {
			Class<?> cascadeFromType = _cascadeFromField.getType();
			
			Field f = findFieldByType(foreignEntity, cascadeFromType);
			if(f != null) {
				String targetEntity = f.getName();
				String targetEntityId = idField(cascadeFromType);

				/**
				 * Assign the values in the end, so that in case of any exception, the cascade values are null. 
				 * Thus turning off the cascading option.
				 */
				cascadeFromField.append(targetEntity + "." + targetEntityId);
				cascadeFrom.append(cascade.from());
				
				configureTabForCascadingColumns(metaTab);
			}
		}
	}

	private void configureTabForCascadingColumns(MetaTab metaTab) {
		TabConfigVo config = metaTab.getConfig();
		EditableVo editable = config.getEditable_();
		EditableConfigVo editableConfig = editable.getConfig_();
		editableConfig.setMode(EditableMode.INLINE);// use inline mode so both dropdownlists are visible (required for cascading)
		
		ColumnVo command = new ColumnVo(new String[]{ "edit", "delete" }); //required for inline mode
		config.addColumn(command);
	}

	private Field findFieldByType(Class<?> entity, Class<?> typeToFind) {
		Field[] declaredFields = entity.getDeclaredFields();
		for (Field field : declaredFields) {
			if(field.getType().equals(typeToFind))
				return field;
		}
		return null;
	}

	private void configureFilterCell(String field, DataSourceVo dataSource) {
		FilterableColumnVo filterable = getFilterable_();
		CellVo cell = filterable.getCell_();
		cell.setDataTextField(field);
		cell.setDataSourceObj(dataSource);
		cell.setSuggestionOperator(Operator.CONTAINS);
		cell.setOperator(Operator.CONTAINS);
		cell.setMinLength(2);
	}
	
	private void configureFilterCell(String field, List<Map<String, String>> dataSource) {
		FilterableColumnVo filterable = getFilterable_();
		CellVo cell = filterable.getCell_();
		cell.setDataTextField(field);
		cell.setDataSourceArray(dataSource);
		cell.setSuggestionOperator(Operator.STARTS_WITH);
		cell.setOperator(Operator.STARTS_WITH);
		cell.setMinLength(1);
	}

	/**
	 * 
	 * @param field
	 * @param metaField
	 * @param metaEntity
	 * @param metaComponents
	 * @param jsFunctions
	 * @throws Exception
	 */
	private void configureOneToOneColumn(String field,	MetaElement metaElement, List<MetaComponent> metaComponents) throws Exception {
		MetaEntity metaEntity = metaElement.getMetaComponent().getMetaEntity();
		Class<?> entity = metaEntity.getPOJOClass();
		
		Field metaField = fieldReflection(field, entity);
		metaField.setAccessible(true);
		Class<?> foreignEntity = metaField.getDeclaringClass();
		
		if(hasCompositePrimaryKey(foreignEntity)) {
			String msg = "Composite keys not supported: " + foreignEntity;
			logger.error(msg, new FeatureNotImplementedException(msg));
		}
		
		foreignEntityNameField = nameField(foreignEntity);
		
		MetaComponent foreignComponent = foreignComponent(foreignEntity, metaComponents, serializeConfig);
		
		if(metaElement instanceof MetaTab) {
			MetaTab metaTab = (MetaTab) metaElement;
			
			UseAutocomplete useAutocomplete = findAnnotation(UseAutocomplete.class, metaField, metaTab);
			if(useAutocomplete != null) {
				String autocompleteName = foreignComponent.getMetaEntity().getName().toLowerCase() + "_" + foreignEntityNameField + "_" + "_autocomplete_" + randomInt();
				MetaAutocomplete metaAutocomplete = createAutocomplete(autocompleteConfig(metaTab, metaField), autocompleteName, foreignEntityNameField, foreignComponent, serializeConfig);
				registerAutocomplete(metaAutocomplete, foreignComponent, metaComponents);
				
				setEditor(autocompleteName);
				
				List<String> jsFunctions = getMetaElement().getJsFunctions();
				jsFunctions.addAll(jsFunctions(autocompleteName, metaAutocomplete));
			}
		}
	}
	
	/**
	 * 
	 * @param field
	 * @param metaField
	 * @param metaEntity
	 * @param metaComponents
	 * @param jsFunctions
	 * @throws Exception
	 */
	private void configureOneToManyColumn(String field,	MetaElement metaElement, List<MetaComponent> metaComponents) throws Exception {
		editable = false;
		groupable = false;
		filterableBool = false;
		sortable = false;
		setTitle(field);
		setField(field);
	}
	
	/**
	 * 
	 * @param field
	 * @param metaField
	 * @param metaEntity
	 * @param metaComponents
	 * @param jsFunctions
	 * @throws Exception
	 */
	private void configureManyToManyColumn(String field,	MetaElement metaElement, List<MetaComponent> metaComponents) throws Exception {
		editable = false;
		groupable = false;
		filterableBool = false;
		sortable = false;
		setTitle(field);
		setField(field);
	}
	
	private void configureIdColumn() {
		this.idField = true;
		this.groupable = false;
		this.editable = false;
	}

	private void init(Field metaField, List<MetaComponent> metaComponents) throws Exception {
		setMetaField(metaField);
		
		EnumValues enumValues = metaField.getAnnotation(EnumValues.class);
		if(enumValues != null) {
			configureEnumColumn(enumValues);
			return; 
		}
		
		Class<?> type = metaField.getType();
		if(type.equals(java.lang.Integer.class)) {
			
		} else if(type.equals(java.lang.Boolean.class)) {
			
		} else if(type.equals(java.lang.Long.class)) {
			
		} else if(type.equals(java.lang.Double.class)) {
			
		} else if(type.equals(java.lang.Float.class)) {
			
		} else if(type.equals(String.class)) {
			Class<?> pojo = metaField.getDeclaringClass();
			MetaComponent foreignComponent = foreignComponent(pojo, metaComponents, serializeConfig);
			String dropdownName = foreignComponent.getMetaEntity().getName().toLowerCase() + "_" + metaField.getName() + "_" + "_dropdown_" + randomInt();
			
			MetaDropDown metaDropDown = createDropDown(dropDownConfig(metaElement, metaField), dropdownName, idField(pojo), metaField.getName(), null, null, foreignComponent, serializeConfig);
			registerDropDown(metaDropDown, foreignComponent, metaComponents);
			
			configureFilterCell(metaField.getName(), metaDropDown.getConfig().getDataSourceObj());
		} else if(type.equals(Calendar.class)) {
			setFormat("{0:dd-MM-yyyy hh:mm tt}");
			
			if(editable)
				setEditor("dateTimeEditor");
			
			getFilterable_().setUiFn("dateTimeEditorForFilter");
		} else if(type.equals(Date.class)) {
			setFormat("{0:dd-MM-yyyy hh:mm tt}");
			
			if(editable)
				setEditor("dateTimeEditor");
			
			getFilterable_().setUiFn("dateTimeEditorForFilter");
		}
	}

	private void configureEnumColumn(EnumValues enumValues) throws Exception {
		this.enumValues = enumValues.value();
		
		List<Map<String, String>> values = getValues_();
		for (String enumValue : this.enumValues) {
			Map<String, String> map = new HashMap<String, String>(2);
			map.put("text", enumValue);
			map.put("value", enumValue);
			values.add(map);
		}
		
		String dropdownName = metaField.getName() + "_enum_" + "_dropdown_" + randomInt();
		DropDownConfig config = DefaultDropDownConfig.class.getAnnotation(DropDownConfig.class);
		MetaDropDown metaDropDown = createEnumDropDown(config, dropdownName, metaField.getName(), this.enumValues, serializeConfig);
		
		setEditor(dropdownName);
		
		String[] dataSourceArray = metaDropDown.getConfig().getDataSourceArray();
		List<Map<String, String>> dataSource = new ArrayList<Map<String,String>>(dataSourceArray.length);
		for (String item : dataSourceArray) {
			Map<String, String> map = new HashMap<String, String>(1);
			map.put(metaField.getName(), item);
			dataSource.add(map);
		}
		configureFilterCell(metaField.getName(), dataSource);
		
		List<String> jsFunctions = getMetaElement().getJsFunctions();
		jsFunctions.addAll(jsFunctions(dropdownName, metaDropDown));
	}

	public List<Aggregates> getAggregates() {
		return aggregates;
	}

	public void addAggregates(List<Aggregates> aggregates) {
		if(aggregates != null) {
			if(this.aggregates == null)
				this.aggregates = new ArrayList<Aggregates>();
			this.aggregates.addAll(aggregates);
		}
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void addAttributes(Map<String, String> attributes) {
		if(attributes != null) {
			if(this.attributes == null)
				this.attributes = new HashMap<String, String>();
			this.attributes.putAll(attributes);
		}
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		if(isNotEmpty(editor))
			this.editor = editor;
	}

	public Boolean isEncoded() {
		return encoded;
	}

	public void setEncoded(Boolean encoded) {
		if(encoded != null)
			this.encoded = encoded;
	}

	public Boolean isFilterableBool() {
		return filterableBool;
	}

	public void setFilterableBool(Boolean filterableBool) {
		if(filterableBool != null)
			this.filterableBool = filterableBool;
	}

	public FilterableColumnVo getFilterable_() {
		if(filterable == null)
			try {
				filterable = FilterableColumnVo.instance(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return filterable;
	}
	
	public FilterableColumnVo getFilterable() {
		return filterable;
	}

	public void setFilterable(FilterableColumnVo filterable) {
		if(filterable != null)
			this.filterable = filterable;
	}

	public String getFooterTemplateStr() {
		return footerTemplateStr;
	}

	public void setFooterTemplateStr(String footerTemplateStr) {
		if(isNotEmpty(footerTemplateStr))
			this.footerTemplateStr = footerTemplateStr;
	}

	public String getFooterTemplateFn() {
		return footerTemplateFn;
	}

	public void setFooterTemplateFn(String footerTemplateFn) {
		if(isNotEmpty(footerTemplateFn))
			this.footerTemplateFn = footerTemplateFn;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		if(isNotEmpty(format))
			this.format = format;
	}

	public Boolean isGroupable() {
		return groupable;
	}

	public void setGroupable(Boolean groupable) {
		if(groupable != null)
			this.groupable = groupable;
	}

	public String getGroupHeaderTemplate() {
		return groupHeaderTemplate;
	}

	public void setGroupHeaderTemplate(String groupHeaderTemplate) {
		if(isNotEmpty(groupHeaderTemplate))
			this.groupHeaderTemplate = groupHeaderTemplate;
	}

	public String getGroupFooterTemplate() {
		return groupFooterTemplate;
	}

	public void setGroupFooterTemplate(String groupFooterTemplate) {
		if(isNotEmpty(groupFooterTemplate))
			this.groupFooterTemplate = groupFooterTemplate;
	}

	public Map<String, String> getHeaderAttributes() {
		return headerAttributes;
	}

	public void addHeaderAttributes(Map<String, String> headerAttributes) {
		if(headerAttributes != null) {
			if(this.headerAttributes == null)
				this.headerAttributes = new HashMap<String, String>();
			this.headerAttributes.putAll(headerAttributes);
		}
	}

	public String getHeaderTemplateStr() {
		return headerTemplateStr;
	}

	public void setHeaderTemplateStr(String headerTemplateStr) {
		if(isNotEmpty(headerTemplateStr))
			this.headerTemplateStr = headerTemplateStr;
	}

	public String getHeaderTemplateFn() {
		return headerTemplateFn;
	}

	public void setHeaderTemplateFn(String headerTemplateFn) {
		if(isNotEmpty(headerTemplateFn))
			this.headerTemplateFn = headerTemplateFn;
	}

	public Boolean isHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		if(hidden != null)
			this.hidden = hidden;
	}

	public Boolean isLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		if(locked != null)
			this.locked = locked;
	}

	public Boolean isLockable() {
		return lockable;
	}

	public void setLockable(Boolean lockable) {
		if(lockable != null)
			this.lockable = lockable;
	}

	public Boolean isSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		if(sortable != null)
			this.sortable = sortable;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		if(isNotEmpty(template))
			this.template = template;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(isNotEmpty(title)) {
			this.title = titleFromFieldName(title);
		}
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		if(isNotEmpty(width))
			this.width = width;
	}

	public Boolean isMenu() {
		return menu;
	}

	public void setMenu(Boolean menu) {
		if(menu != null)
			this.menu = menu;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		if(field != null)
			this.field = field.replaceAll("\\.", "_");
	}
	
	public void setMetaField(Field field) {
		if(field != null) {
			this.metaField = field;
			if(this.metaElement != null) {
				String title = titleFromLabelAnnotation(field, metaElement);
				if(title != null)
					this.title = title;
			}
		}
	}

	public Field getMetaField() {
		return this.metaField;
	}
	
	public ColumnVo copyFrom(TabColumn tabColumn) {
		addAggregates(aggregates(tabColumn.aggregates()));
		addAttributes(attributes(tabColumn.attributes()));
		addCommandObjs(commandObjs(tabColumn.commandObjs()));
		setCommandArray(commandArray(tabColumn.commandArray()));
		setEditor(tabColumn.editor());
		setEncoded(tabColumn.encoded().getBool());
		getFilterable_().copyFrom(tabColumn.filterable());
		setFilterableBool(tabColumn.filterableBool().getBool());
		setFooterTemplateFn(tabColumn.footerTemplateFn());
		setFooterTemplateStr(tabColumn.footerTemplateStr());
		setFormat(tabColumn.format());
		setGroupable(tabColumn.groupable().getBool());
		setGroupFooterTemplate(tabColumn.groupFooterTemplate());
		setGroupHeaderTemplate(tabColumn.groupHeaderTemplate());
		addHeaderAttributes(headerAttributes(tabColumn.headerAttributes()));
		setHeaderTemplateFn(tabColumn.headerTemplateFn());
		setHeaderTemplateStr(tabColumn.headerTemplateStr());
		setHidden(tabColumn.hidden().getBool());
		setLockable(tabColumn.lockable().getBool());
		setLocked(tabColumn.locked().getBool());
		setMenu(tabColumn.menu().getBool());
		setSortable(tabColumn.sortable().getBool());
		setTemplate(tabColumn.template());
		setTitle(tabColumn.title());
		setWidth(tabColumn.width());
		return this;
	}

	private Map<String, String> headerAttributes(Attribute[] headerAttributes) {
		if(headerAttributes.length > 0) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (Attribute attribute : headerAttributes) {
				map.put(attribute.key(), attribute.value());
			}
			return map;
		}
		return null;
	}

	private List<CommandVo> commandObjs(Command[] commands) {
		if(commands.length > 0) {
			List<CommandVo> list = new ArrayList<CommandVo>();
			for (Command command : commands) {
				CommandVo com = new CommandVo().copyFrom(command);
				list.add(com);
			}
			return list;
		}
		return null;
	}
	
	private String[] commandArray(String[] commands) {
		if(commands.length > 0) {
			return commands;
		}
		return null;
	}

	private Map<String, String> attributes(Attribute[] attributes) {
		if(attributes.length > 0) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (Attribute attribute : attributes) {
				map.put(attribute.key(), attribute.value());
			}
			return map;
		}
		return null;
	}

	private List<Aggregates> aggregates(Aggregates[] aggregates) {
		if(aggregates.length > 0) {
			List<Aggregates> list = new ArrayList<Aggregates>();
			for (Aggregates aggregate : aggregates) {
				list.add(aggregate);
			}
			return list;
		}
		return null;
	}

	@Override
	public JsField resolve(String field) {
		if(field.equals("footerTemplate")) {
			return resolveFooterTemplate();
		} else if(field.equals("headerTemplate")) {
			return resolveHeaderTemplate();
		} else if(field.equals("editor")) {
			return resolveEditor();
		} else if(field.equals("command")) {
			return resolveCommand();
		}
		return null;
	}

	private JsField resolveEditor() {
		if(isNotEmpty(editor)) {
			return new JsFieldVariable("editor", editor, "editor");
		}
		return null;
	}
	
	private JsField resolveCommand() {
		if(CollectionUtils.isNotEmpty(commandObjs)) {
			return new JsFieldArray("command", commandObjs.toArray(new CommandVo[commandObjs.size()]), "commandObjs");
		} else if(commandArray != null && commandArray.length > 0) {
			return new JsFieldArray("command", commandArray, "commandArray");
		}
		return null;
	}

	private JsField resolveHeaderTemplate() {
		if(isNotEmpty(headerTemplateFn)) {
			return new JsFieldVariable("headerTemplate", headerTemplateFn, "headerTemplateFn");
		} else if(isNotEmpty(headerTemplateStr)) {
			return new JsFieldString("headerTemplate", headerTemplateStr, "headerTemplateStr");
		}
		return null;
	}
	
	private JsField resolveFooterTemplate() {
		if(isNotEmpty(footerTemplateFn)) {
			return new JsFieldVariable("footerTemplate", footerTemplateFn, "footerTemplateFn");
		} else if(isNotEmpty(footerTemplateStr)) {
			return new JsFieldString("footerTemplate", footerTemplateStr, "footerTemplateStr");
		}
		return null;
	}

	public Boolean isEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public Boolean isIdField() {
		return idField;
	}

	public void setIdField(Boolean idField) {
		this.idField = idField;
	}

	public Boolean isForeignEntity() {
		return foreignEntity;
	}

	public void setForeignEntity(Boolean foreignEntity) {
		this.foreignEntity = foreignEntity;
	}

	public String getForeignEntityIdField() {
		return foreignEntityIdField;
	}

	public void setForeignEntityIdField(String foreignEntityIdField) {
		this.foreignEntityIdField = foreignEntityIdField;
	}

	public String getForeignEntityNameField() {
		return foreignEntityNameField;
	}

	public void setForeignEntityNameField(String foreignEntityNameField) {
		this.foreignEntityNameField = foreignEntityNameField;
	}

	public MetaElement getMetaElement() {
		return metaElement;
	}

	public void setMetaElement(MetaElement metaElement) {
		this.metaElement = metaElement;
	}

	public List<CommandVo> getCommandObjs() {
		return commandObjs;
	}

	public void addCommandObjs(List<CommandVo> commandObjs) {
		if(commandObjs != null) {
			if(this.commandObjs == null)
				this.commandObjs = new ArrayList<CommandVo>();
			this.commandObjs.addAll(commandObjs);
		}
	}

	public String[] getCommandArray() {
		return commandArray;
	}

	public void setCommandArray(String[] commandArray) {
		if(commandArray != null && commandArray.length > 0) {
			this.commandArray = commandArray;
		}
	}

	public boolean isCommandColumn() {
		return commandColumn;
	}

	public void setCommandColumn(boolean commandColumn) {
		this.commandColumn = commandColumn;
	}

	public boolean isSerializeConfig() {
		return serializeConfig;
	}

	public void setSerializeConfig(boolean serializeConfig) {
		this.serializeConfig = serializeConfig;
	}

	public String[] getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(String[] enumValues) {
		this.enumValues = enumValues;
	}

	public List<Map<String, String>> getValues_() {
		if(values == null) {
			values = new ArrayList<Map<String,String>>();
		}
		return values;
	}
	
	public List<Map<String, String>> getValues() {
		return values;
	}

	public void setValues(List<Map<String, String>> values) {
		this.values = values;
	}
	
}
