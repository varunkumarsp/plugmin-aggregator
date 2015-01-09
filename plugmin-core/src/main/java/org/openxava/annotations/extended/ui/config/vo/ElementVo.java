package org.openxava.annotations.extended.ui.config.vo;

import static org.istage.util.AdminUtil.titleFromFieldName;
import static org.openxava.annotations.parse.ComponentUtil.autocompleteConfig;
import static org.openxava.annotations.parse.ComponentUtil.createAutocomplete;
import static org.openxava.annotations.parse.ComponentUtil.createDropDown;
import static org.openxava.annotations.parse.ComponentUtil.createEnumComboBox;
import static org.openxava.annotations.parse.ComponentUtil.createEnumDropDown;
import static org.openxava.annotations.parse.ComponentUtil.createEnumMultiSelect;
import static org.openxava.annotations.parse.ComponentUtil.datePickerConfig;
import static org.openxava.annotations.parse.ComponentUtil.dateTimePickerConfig;
import static org.openxava.annotations.parse.ComponentUtil.dropDownConfig;
import static org.openxava.annotations.parse.ComponentUtil.editorConfig;
import static org.openxava.annotations.parse.ComponentUtil.foreignComponent;
import static org.openxava.annotations.parse.ComponentUtil.jsFunctions;
import static org.openxava.annotations.parse.ComponentUtil.numericTextBoxConfig;
import static org.openxava.annotations.parse.ComponentUtil.registerAutocomplete;
import static org.openxava.annotations.parse.ComponentUtil.registerDropDown;
import static org.openxava.annotations.parse.ComponentUtil.timePickerConfig;
import static org.openxava.annotations.parse.ComponentUtil.uploadConfig;
import static org.openxava.annotations.parse.EntityUtil._isIdField;
import static org.openxava.annotations.parse.EntityUtil.hasCompositePrimaryKey;
import static org.openxava.annotations.parse.EntityUtil.idField;
import static org.openxava.annotations.parse.EntityUtil.isForeignEntity_;
import static org.openxava.annotations.parse.EntityUtil.isManyToOne;
import static org.openxava.annotations.parse.EntityUtil.isOneToOne;
import static org.openxava.annotations.parse.EntityUtil.nameField;
import static org.openxava.annotations.parse.EntityUtil.randomInt;
import static org.openxava.annotations.parse.ReflectionUtil.fieldReflection;
import static org.openxava.annotations.parse.ReflectionUtil.findAnnotation;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.openxava.FeatureNotImplementedException;
import org.openxava.annotations.extended.CascadeDropdown;
import org.openxava.annotations.extended.Label;
import org.openxava.annotations.extended.ui.config.ComboBoxConfig;
import org.openxava.annotations.extended.ui.config.DropDownConfig;
import org.openxava.annotations.extended.ui.config.MultiSelectConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultComboBoxConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultDropDownConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultMultiSelectConfig;
import org.openxava.annotations.extended.ui.config.grid.UseEnumComboBox;
import org.openxava.annotations.extended.ui.config.grid.UseEnumDropDown;
import org.openxava.annotations.extended.ui.config.grid.UseEnumMultiSelect;
import org.openxava.annotations.extended.ui.config.vo.editor.EditorConfigVo;
import org.openxava.annotations.extended.ui.use.UseAutocomplete;
import org.openxava.annotations.extended.ui.use.UseDatePicker;
import org.openxava.annotations.extended.ui.use.UseDateTimePicker;
import org.openxava.annotations.extended.ui.use.UseEditor;
import org.openxava.annotations.extended.ui.use.UseNumericTextBox;
import org.openxava.annotations.extended.ui.use.UseTimePicker;
import org.openxava.annotations.extended.ui.use.UseUpload;
import org.openxava.annotations.parse.JsonUtil;
import org.openxava.autocomplete.MetaAutocomplete;
import org.openxava.component.MetaComponent;
import org.openxava.dropdown.MetaComboBox;
import org.openxava.dropdown.MetaDropDown;
import org.openxava.dropdown.MetaMultiSelect;
import org.openxava.model.meta.MetaEntity;
import org.openxava.view.meta.MetaView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementVo {

	private static Logger logger = LoggerFactory.getLogger(ElementVo.class);
	
	private Boolean editable = true;
	private Boolean idField = false;
	private MetaView metaView;
	private boolean serializeConfig = false;
	private WidgetConfig widgetConfig;
	private AngularScope angularScope_core;
	private List<AngularScope> angularScopes = new ArrayList<AngularScope>();
	private Element element;
	private Element element_core;
	private String ngModel;
	private String field_;
	private String configName;
	private String dataField;
	
	private boolean foreignEntity;
	private String foreignEntityIdField;
	private String field;
	

	public ElementVo(String field, Field metaField, MetaView metaView, List<MetaComponent> metaComponents, boolean serializeConfig) throws Exception {
		try {
			setSerializeConfig(serializeConfig);
			setMetaView(metaView);
			MetaEntity metaEntity = metaView.getMetaComponent().getMetaEntity();
			Class<?> entity = metaEntity.getPOJOClass();
		
			this.field = field;
			
			field_ = field.replaceAll("\\.", "_");
			dataField = field_;
			ngModel = entity.getSimpleName() + "." + field_;
			configName = field_ + "_" + metaView.getName() + "_config";
			
			if(isForeignEntity_(field, metaEntity)) {
				if(isManyToOne(field, entity)) {
					buildElement(field, entity);
					if(field.contains(".")) {
						ui(field, entity, metaComponents);
					} else {
						foreignEntity = true;
						configureManyToOneColumn(field, metaView, metaComponents);
					}
				} else if(isOneToOne(field, entity)){
					buildElement(field, entity);
					if(field.contains(".")) {
						configureOneToOneColumn(field, metaView, metaComponents, true);
					} else {
						String nameField = nameField(entity.getDeclaredField(field).getType());
						field = field + "." + nameField;
						this.field = field;
						configureOneToOneColumn(field, metaView, metaComponents, false);
					}
				}
			} else {
				buildElement(field, entity);
				ui(field, entity, metaComponents);
				if(_isIdField(metaField, metaEntity))
					configureIdColumn();
			}
			
			if(angularScope_core != null)
				angularScopes.add(angularScope_core);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void buildElement(String field, Class<?> entity) throws Exception {
		String title;
		Field metaField = fieldReflection(field, entity);
		
		Label label = findAnnotation(Label.class, metaField, metaView);
		if(label != null)
			title = label.value();
		else
			title = titleFromFieldName(field);
		
		element = new Element(Tag.valueOf("div"), "");
		element.attr("class", "plugmin-field");
		element.append("<div class='plugmin-field-name'>" + title + "</div>");
		element.append("<div class='plugmin-seperator'>:</div>");
		
		element_core = new Element(Tag.valueOf("div"), "");
		element_core.attr("class", "plugmin-field-value");
		
		element.appendChild(element_core);
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
	private void configureManyToOneColumn(String field,	MetaView metaView, List<MetaComponent> metaComponents) throws Exception {
		MetaEntity metaEntity = metaView.getMetaComponent().getMetaEntity();
		Class<?> pojo = metaEntity.getPOJOClass();
		Field metaField = pojo.getDeclaredField(field);
		metaField.setAccessible(true);
		
		Class<?> foreignEntity = metaField.getType();

		if(hasCompositePrimaryKey(foreignEntity)) {
			String msg = "Composite keys not supported: " + metaEntity.getPOJOClassName();
			logger.error(msg, new FeatureNotImplementedException(msg));
		}
		
		foreignEntityIdField = idField(foreignEntity);
		String foreignEntityNameField = nameField(foreignEntity);

		StringBuffer cascadeFrom = new StringBuffer(); //StringBuffer is used to mutate the variable in another method
		StringBuffer cascadeFromField = new StringBuffer();
		
		CascadeDropdown cascade = findAnnotation(CascadeDropdown.class, metaField, metaView);
		if(cascade != null) {
			configureCascadingOptions(metaView, foreignEntity, cascade, cascadeFrom, cascadeFromField);
		}
		
		MetaComponent foreignComponent = foreignComponent(foreignEntity, metaComponents, serializeConfig);

		String dropdownName = foreignComponent.getMetaEntity().getName().toLowerCase() + "_" + foreignEntityNameField + "_" + "_dropdown_" + randomInt();
		MetaDropDown metaDropDown = createDropDown(dropDownConfig(metaView, metaField), dropdownName, foreignEntityIdField, foreignEntityNameField, cascadeFrom.toString(), cascadeFromField.toString(), foreignComponent, serializeConfig);
		registerDropDown(metaDropDown, foreignComponent, metaComponents);
		
		ngModel = ngModel + "." + foreignEntityIdField;
		element_core.append("<select id='" + field_ + "' kendo-drop-down-list class='' ng-model='" + ngModel + "' k-options='" + field_ + "'></select>");

		String json = null;
		if(serializeConfig)
			json = JsonUtil.toJson(metaDropDown.getConfig());
		
		widgetConfig = new WidgetConfig(configName, metaDropDown.getConfig(), json);
		angularScope_core = new AngularScope("$scope." + field_, configName);
		
		List<String> jsFunctions = metaView.getJsFunctions();
		jsFunctions.addAll(jsFunctions(dropdownName, metaDropDown));
	}

	private void configureCascadingOptions(MetaView metaView, 
			Class<?> foreignEntity, CascadeDropdown cascade,
			StringBuffer cascadeFrom, StringBuffer cascadeFromField) throws Exception {
		MetaEntity metaEntity = metaView.getMetaComponent().getMetaEntity();
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
			}
		}
	}

	private Field findFieldByType(Class<?> entity, Class<?> typeToFind) {
		Field[] declaredFields = entity.getDeclaredFields();
		for (Field field : declaredFields) {
			if(field.getType().equals(typeToFind))
				return field;
		}
		return null;
	}

	/**
	 * 
	 * @param field
	 * @param metaField
	 * @param metaEntity
	 * @param metaComponents
	 * @param containsDot 
	 * @param jsFunctions
	 * @throws Exception
	 */
	private void configureOneToOneColumn(String field,	MetaView metaView, List<MetaComponent> metaComponents, boolean containsDot) throws Exception {
		MetaEntity metaEntity = metaView.getMetaComponent().getMetaEntity();
		Class<?> entity = metaEntity.getPOJOClass();
		
		Field metaField = fieldReflection(field, entity);
		metaField.setAccessible(true);
		Class<?> foreignEntity = metaField.getDeclaringClass();
		
		if(!containsDot) {
			field_ = field.replaceAll("\\.", "_");
			dataField = field_;
			ngModel = entity.getSimpleName() + "." + field_;
			configName = field_ + "_" + metaView.getName() + "_config";
		}
		
		UseAutocomplete useAutocomplete = findAnnotation(UseAutocomplete.class, metaField, metaView);
		if(useAutocomplete != null) {
			if(hasCompositePrimaryKey(foreignEntity)) {
				String msg = "Composite keys not supported: " + foreignEntity;
				logger.error(msg, new FeatureNotImplementedException(msg));
			}
			
			String foreignEntityNameField = nameField(foreignEntity);
			
			MetaComponent foreignComponent = foreignComponent(foreignEntity, metaComponents, serializeConfig);
			
			String html = "<input type='text' kendo-auto-complete class='' ng-model='" + ngModel + "' k-options='" + field_ + "' />";
			
			String autocompleteName = foreignComponent.getMetaEntity().getName().toLowerCase() + "_" + foreignEntityNameField + "_" + "_autocomplete_" + randomInt();
			MetaAutocomplete metaAutocomplete = createAutocomplete(autocompleteConfig(metaView, metaField), autocompleteName, foreignEntityNameField, foreignComponent, serializeConfig);
			registerAutocomplete(metaAutocomplete, foreignComponent, metaComponents);
			
			String json = null;
			if(serializeConfig)
				json = JsonUtil.toJson(metaAutocomplete.getConfig());
			
			angularScope_core = new AngularScope("$scope." + field_, configName);
			widgetConfig = new WidgetConfig(configName, metaAutocomplete.getConfig(), json);
			
			List<String> jsFunctions = metaView.getJsFunctions();
			jsFunctions.addAll(jsFunctions(autocompleteName, metaAutocomplete));
			
			element_core.append(html);
		} else {
			ui(field, entity, metaComponents);
		}
	}
	
	private void configureIdColumn() {
		this.idField = true;
		this.editable = false;
	}

	private void ui(String field, Class<?> entity, List<MetaComponent> metaComponents) throws Exception {
		Field metaField = fieldReflection(field, entity);
		String html = null;
		Object config = null;

		Class<?> type = metaField.getType();
		
		UseEnumDropDown useEnumDropDown = metaField.getAnnotation(UseEnumDropDown.class);
		UseEnumMultiSelect useEnumMultiSelect = metaField.getAnnotation(UseEnumMultiSelect.class);
		UseEnumComboBox useEnumComboBox = metaField.getAnnotation(UseEnumComboBox.class);
		UseEditor useEditor = findAnnotation(UseEditor.class, metaField, metaView);
		UseUpload useUpload = findAnnotation(UseUpload.class, metaField, metaView);
		UseDateTimePicker useDateTimePicker = findAnnotation(UseDateTimePicker.class, metaField, metaView);
		UseDatePicker useDatePicker = findAnnotation(UseDatePicker.class, metaField, metaView);
		UseTimePicker useTimePicker = findAnnotation(UseTimePicker.class, metaField, metaView);
		UseNumericTextBox useNumericTextBox = findAnnotation(UseNumericTextBox.class, metaField, metaView);
		UseAutocomplete useAutocomplete = findAnnotation(UseAutocomplete.class, metaField, metaView);
		
		if(useEnumDropDown != null) {
			MetaDropDown enumDropDown = configureEnumDropdown(field, useEnumDropDown);
			html = "<select id='" + field_ + "' kendo-drop-down-list class='' ng-model='" + ngModel + "' k-options='" + field_ + "'></select>";
			config = enumDropDown.getConfig();
		} else if(useEnumMultiSelect != null) {
			MetaMultiSelect enumMultiSelect = configureEnumMultiSelect(field, useEnumMultiSelect);
			html = "<select id='" + field_ + "' kendo-multi-select class='' ng-model='" + ngModel + "' k-options='" + field_ + "'></select>";
			config = enumMultiSelect.getConfig();
		} else if(useEnumComboBox != null) {
			MetaComboBox enumComboBox = configureEnumComboBox(field, useEnumComboBox);
			html = "<select id='" + field_ + "' kendo-combo-box class='' ng-model='" + ngModel + "' k-options='" + field_ + "'></select>";
			config = enumComboBox.getConfig();
		} else if(useEditor != null) {
			html = "<textarea style='height: 5em' kendo-editor ng-model='" + getNgModel() + "' k-options='" + field_ + "'></textarea>";
			config = new EditorConfigVo(editorConfig(metaView, metaField));
			element.getElementsByClass("plugmin-field-value").addClass("width-x2");
		} else if(useUpload != null) {
			html = "<input type='file' name='files' kendo-upload k-select='" + field_ + "_changed' k-remove='" + field_ + "_removed' k-options='" + field_ + "' />";
			
			String fnChanged = "function(e) { \n" +
                    		"\t var fileNames = $.map(e.files, function(file) { return file.name; }).join(', '); \n" +
                    		"\t $scope." + entity.getSimpleName() + "." + field_ + " = fileNames; \n" +
                    	"}";
			
			AngularScope fileNameChangedScope = new AngularScope("$scope." + field_ + "_changed", fnChanged);
			angularScopes.add(fileNameChangedScope);
			
			String fnRemoved = "function(e) { \n" +
            		"\t $scope." + entity.getSimpleName() + "." + field_ + " = null; \n" +
            	"}";
			
			AngularScope fileNameRemovedScope = new AngularScope("$scope." + field_ + "_removed", fnRemoved);
			angularScopes.add(fileNameRemovedScope);
			
			config = new UploadConfigVo(uploadConfig(metaView, metaField));
		} else if(useDateTimePicker != null) {
			html = "<input kendo-date-time-picker ng-model='" + getNgModel() + "' k-options='" + field_ + "' />"; 
			config = new DateTimePickerConfigVo(dateTimePickerConfig(metaView, metaField));
		} else if(useDatePicker != null) {
			html = "<input kendo-date-time-picker ng-model='" + getNgModel() + "' k-options='" + field_ + "' />";
			config = new DatePickerConfigVo(datePickerConfig(metaView, metaField));
		} else if(useTimePicker != null) {
			html = "<input kendo-date-time-picker ng-model='" + getNgModel() + "' k-options='" + field_ + "' />"; 
			config = new TimePickerConfigVo(timePickerConfig(metaView, metaField));
		} else if(useNumericTextBox != null) {
			html = "<input kendo-numeric-text-box ng-model='" + getNgModel() + "' k-options='" + field_ + "' />"; 
			config = new NumericTextBoxConfigVo(numericTextBoxConfig(metaView, metaField));
		} else if(useAutocomplete != null) {
			html = "<input type='text' kendo-auto-complete class='' ng-model='" + ngModel + "' k-options='" + field_ + "' />";
			config = new AutocompleteConfigVo(autocompleteConfig(metaView, metaField));
		} else if(type.equals(java.lang.Integer.class)) {
			html = "<input kendo-numeric-text-box ng-model='" + getNgModel() + "' k-options='" + field_ + "' />";
			config = new NumericTextBoxConfigVo(numericTextBoxConfig((UseNumericTextBox)null, null));
		} else if(type.equals(java.lang.Boolean.class)) {
			html = "<input type='radio' ng-model='" + getNgModel() + "' />";
		} else if(type.equals(java.lang.Long.class)) {
			html = "<input kendo-numeric-text-box ng-model='" + getNgModel() + "' k-options='" + field_ + "' />";
			config = new NumericTextBoxConfigVo(numericTextBoxConfig((UseNumericTextBox)null, null));
		} else if(type.equals(java.lang.Double.class)) {
			html = "<input kendo-numeric-text-box ng-model='" + getNgModel() + "' k-options='" + field_ + "' />";
			config = new NumericTextBoxConfigVo(numericTextBoxConfig((UseNumericTextBox)null, null));
		} else if(type.equals(java.lang.Float.class)) {
			html = "<input kendo-numeric-text-box ng-model='" + getNgModel() + "' k-options='" + field_ + "' />";
			config = new NumericTextBoxConfigVo(numericTextBoxConfig((UseNumericTextBox)null, null));
		}  else if(type.equals(BigDecimal.class)) {
			html = "<input kendo-numeric-text-box ng-model='" + getNgModel() + "' k-options='" + field_ + "' />";
			config = new NumericTextBoxConfigVo(numericTextBoxConfig((UseNumericTextBox)null, null));
		} else if(type.equals(String.class)) {
			html = "<input type='text' class='k-textbox ' ng-model='" + getNgModel() + "' />";
		} else if(type.equals(Calendar.class)) {
			html = "<input kendo-date-time-picker ng-model='" + getNgModel() + "' k-options='" + field_ + "' />";
			config = new DateTimePickerConfigVo(dateTimePickerConfig((UseDateTimePicker)null, null));
		} else if(type.equals(Date.class)) {
			html = "<input kendo-date-time-picker ng-model='" + getNgModel() + "' k-options='" + field_ + "' />";
			config = new DateTimePickerConfigVo(dateTimePickerConfig((UseDateTimePicker)null, null));
		}
			
		
		if(config != null) {
			String json = null;
			if(serializeConfig)
				json = JsonUtil.toJson(config);
			
			angularScope_core = new AngularScope("$scope." + field_, configName);
			widgetConfig = new WidgetConfig(configName, config, json);
		}
		
		element_core.append(html);
	}

	private MetaDropDown configureEnumDropdown(String field, UseEnumDropDown enumValues) throws Exception {
		String dropdownName = field + "_enum_" + "_dropdown_" + randomInt();
		DropDownConfig config = DefaultDropDownConfig.class.getAnnotation(DropDownConfig.class);
		MetaDropDown metaDropDown = createEnumDropDown(config, dropdownName, field, enumValues.value(), serializeConfig);
		return metaDropDown;
	}
	
	private MetaMultiSelect configureEnumMultiSelect(String field, UseEnumMultiSelect enumValues) throws Exception {
		String dropdownName = field + "_enum_" + "_dropdown_" + randomInt();
		MultiSelectConfig config = DefaultMultiSelectConfig.class.getAnnotation(MultiSelectConfig.class);
		MetaMultiSelect metaMultiSelect = createEnumMultiSelect(config, dropdownName, field, enumValues.value(), serializeConfig);
		return metaMultiSelect;
	}
	
	private MetaComboBox configureEnumComboBox(String field, UseEnumComboBox enumValues) throws Exception {
		String dropdownName = field + "_enum_" + "_dropdown_" + randomInt();
		ComboBoxConfig config = DefaultComboBoxConfig.class.getAnnotation(ComboBoxConfig.class);
		MetaComboBox metaComboBox = createEnumComboBox(config, dropdownName, field, enumValues.value(), serializeConfig);
		return metaComboBox;
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

	public MetaView getMetaView() {
		return metaView;
	}

	public void setMetaView(MetaView metaElement) {
		this.metaView = metaElement;
	}

	public boolean isSerializeConfig() {
		return serializeConfig;
	}

	public void setSerializeConfig(boolean serializeConfig) {
		this.serializeConfig = serializeConfig;
	}

	public WidgetConfig getWidgetConfig() {
		return widgetConfig;
	}

	public void setWidgetConfig(WidgetConfig widgetConfig) {
		this.widgetConfig = widgetConfig;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public String getNgModel() {
		return ngModel;
	}

	public void setNgModel(String ngModel) {
		this.ngModel = ngModel;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}

	public List<AngularScope> getAngularScopes() {
		return angularScopes;
	}

	public void setAngularScopes(List<AngularScope> angularScopes) {
		this.angularScopes = angularScopes;
	}

	public boolean isForeignEntity() {
		return foreignEntity;
	}

	public void setForeignEntity(boolean foreignEntity) {
		this.foreignEntity = foreignEntity;
	}

	public String getForeignEntityIdField() {
		return foreignEntityIdField;
	}

	public void setForeignEntityIdField(String foreignEntityIdField) {
		this.foreignEntityIdField = foreignEntityIdField;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
}
