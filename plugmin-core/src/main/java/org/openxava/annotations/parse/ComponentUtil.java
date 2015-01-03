package org.openxava.annotations.parse;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.istage.util.AdminUtil.PLUGMIN_REST_BASE_URL;
import static org.openxava.annotations.parse.ReflectionUtil.fieldReflection;
import static org.openxava.annotations.parse.ReflectionUtil.findAnnotation;
import static org.openxava.annotations.parse.ReflectionUtil.findWidget;
import static org.openxava.annotations.parse.ReflectionUtil.findWidgetConfig;
import static org.openxava.annotations.parse.ReflectionUtil.isCollection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.openxava.annotations.extended.ui.DropDown;
import org.openxava.annotations.extended.ui.DropDowns;
import org.openxava.annotations.extended.ui.Tab;
import org.openxava.annotations.extended.ui.Tabs;
import org.openxava.annotations.extended.ui.config.AutocompleteConfig;
import org.openxava.annotations.extended.ui.config.AutocompleteConfigs;
import org.openxava.annotations.extended.ui.config.DatePickerConfig;
import org.openxava.annotations.extended.ui.config.DatePickerConfigs;
import org.openxava.annotations.extended.ui.config.DateTimePickerConfig;
import org.openxava.annotations.extended.ui.config.DateTimePickerConfigs;
import org.openxava.annotations.extended.ui.config.DropDownConfig;
import org.openxava.annotations.extended.ui.config.DropDownConfigs;
import org.openxava.annotations.extended.ui.config.EditorConfigs;
import org.openxava.annotations.extended.ui.config.NumericTextBoxConfig;
import org.openxava.annotations.extended.ui.config.NumericTextBoxConfigs;
import org.openxava.annotations.extended.ui.config.TabConfig;
import org.openxava.annotations.extended.ui.config.TabConfigs;
import org.openxava.annotations.extended.ui.config.TimePickerConfig;
import org.openxava.annotations.extended.ui.config.TimePickerConfigs;
import org.openxava.annotations.extended.ui.config.UploadConfig;
import org.openxava.annotations.extended.ui.config.UploadConfigs;
import org.openxava.annotations.extended.ui.config.defaults.DefaultAutocompleteConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultDatePickerConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultDateTimePickerConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultDropDownConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultEditorConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultNumericTextBoxConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultTabConfig;
import org.openxava.annotations.extended.ui.config.defaults.DefaultTimePickerConfig;
import org.openxava.annotations.extended.ui.config.editor.EditorConfig;
import org.openxava.annotations.extended.ui.config.vo.AngularScope;
import org.openxava.annotations.extended.ui.config.vo.AutocompleteConfigVo;
import org.openxava.annotations.extended.ui.config.vo.ColumnVo;
import org.openxava.annotations.extended.ui.config.vo.DataConfig;
import org.openxava.annotations.extended.ui.config.vo.DataSourceVo;
import org.openxava.annotations.extended.ui.config.vo.DropDownConfigVo;
import org.openxava.annotations.extended.ui.config.vo.ElementVo;
import org.openxava.annotations.extended.ui.config.vo.EventVo;
import org.openxava.annotations.extended.ui.config.vo.WidgetConfig;
import org.openxava.annotations.extended.ui.use.UseAutocomplete;
import org.openxava.annotations.extended.ui.use.UseDatePicker;
import org.openxava.annotations.extended.ui.use.UseDateTimePicker;
import org.openxava.annotations.extended.ui.use.UseDropdown;
import org.openxava.annotations.extended.ui.use.UseEditor;
import org.openxava.annotations.extended.ui.use.UseNumericTextBox;
import org.openxava.annotations.extended.ui.use.UseTab;
import org.openxava.annotations.extended.ui.use.UseTimePicker;
import org.openxava.annotations.extended.ui.use.UseUpload;
import org.openxava.autocomplete.MetaAutocomplete;
import org.openxava.component.MetaComponent;
import org.openxava.dropdown.MetaDropDown;
import org.openxava.tab.meta.MetaTab;
import org.openxava.util.meta.MetaElement;
import org.openxava.view.meta.MetaView;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ComponentUtil {

	public static void registerAutocomplete(MetaAutocomplete metaAutocomplete,
			MetaComponent metaComponent, List<MetaComponent> metaComponents) throws Exception {
		for (MetaComponent comp : metaComponents) {
			if(comp.getMetaEntity().getName().equals(metaComponent.getMetaEntity().getName())) {
				comp.addMetaAutocomplete(metaAutocomplete);
			}
		}
	}
	
	public static void registerDropDown(MetaDropDown metaDropDown,
			MetaComponent metaComponent, List<MetaComponent> metaComponents) throws Exception {
		for (MetaComponent comp : metaComponents) {
			if(comp.getMetaEntity().getName().equals(metaComponent.getMetaEntity().getName())) {
				comp.addMetaDropDown(metaDropDown);
			}
		}
	}
	
	public static MetaDropDown createDropDown(DropDownConfig config, String dropdownName, String dataValueField, String dataTextField, String cascadeFrom, String cascadeFromField, MetaComponent metaComponent, boolean isSerializeConfig) throws Exception {
		DropDownConfigVo dropDownConfig = new DropDownConfigVo(dataValueField, dataTextField);
		dropDownConfig.copyFrom(config);
		
		MetaDropDown metaDropDown = new MetaDropDown(metaComponent);
		metaDropDown.setName(dropdownName);
		metaDropDown.setKeyProperty(dataValueField);
		metaDropDown.setValueProperty(dataTextField);

		Class<?> pojo = metaComponent.getMetaEntity().getPOJOClass();
		List<ColumnVo> columns = columns(Arrays.asList(new String[]{ dataValueField, dataTextField }), pojo, isSerializeConfig);
		
		DataSourceVo dataSource = dataSource(metaComponent.getMetaEntity().getName(), dropdownName, "dropdown", columns);
		dropDownConfig.setDataSourceObj(dataSource);
		
		if(isNotEmpty(cascadeFrom))
			dropDownConfig.setCascadeFrom(cascadeFrom);
		if(isNotEmpty(cascadeFromField))
			dropDownConfig.setCascadeFromField(cascadeFromField);
		
		List<EventVo> events = dropDownConfig.getEvents();
		if(events == null) {
			events = new ArrayList<EventVo>();
			dropDownConfig.setEvents(events);
		}
		EventVo changeEvent = new EventVo();
		changeEvent.setName("change");
		String handler = "function(e) { try { var value = this.value(); console.log('selected value: ' + value);" + dropdownName + "_on_change(this, container, options);" + " } catch(e) { console.log(e.toString()); } }";
		changeEvent.setHandler(handler);
		events.add(changeEvent);
		
		metaDropDown.setConfig(dropDownConfig);
		if(isSerializeConfig) {
			metaDropDown.setJson(JsonUtil.toJson(dropDownConfig));
		}
		return metaDropDown;
	}
	
	public static MetaDropDown createEnumDropDown(DropDownConfig config, String dropdownName, String field, String[] enumValues, boolean isSerializeConfig) throws Exception {
		DropDownConfigVo dropDownConfig = new DropDownConfigVo(enumValues);
		dropDownConfig.copyFrom(config);
		
		MetaDropDown metaDropDown = new MetaDropDown();
		metaDropDown.setName(dropdownName);
		
		dropDownConfig.setDataSourceArray(enumValues);
		metaDropDown.setConfig(dropDownConfig);

		if(isSerializeConfig) {
			metaDropDown.setJson(JsonUtil.toJson(dropDownConfig));
		}
		return metaDropDown;
	}
	
	public static MetaAutocomplete createAutocomplete(AutocompleteConfig config, String autocompleteName, String dataTextField, MetaComponent metaComponent, boolean isSerializeConfig) throws Exception {
		AutocompleteConfigVo autocompleteConfig = new AutocompleteConfigVo(dataTextField);
		autocompleteConfig.copyFrom(config);
		
		MetaAutocomplete metaAutocomplete = new MetaAutocomplete(metaComponent);
		metaAutocomplete.setName(autocompleteName);
		metaAutocomplete.setValueProperty(dataTextField);

		Class<?> pojo = metaComponent.getMetaEntity().getPOJOClass();
		List<ColumnVo> columns = columns(Arrays.asList(new String[]{ dataTextField }), pojo, isSerializeConfig);
		
		DataSourceVo dataSource = dataSource(metaComponent.getMetaEntity().getName(), autocompleteName, "autocomplete", columns);
		autocompleteConfig.setDataSourceObj(dataSource);
		
		metaAutocomplete.setConfig(autocompleteConfig);
		if(isSerializeConfig) {
			metaAutocomplete.setJson(JsonUtil.toJson(autocompleteConfig));
		}
		return metaAutocomplete;
	}
	
	public static DataSourceVo dataSource(String modelName, String viewName, String widget, List<ColumnVo> columns) throws Exception {
		DataSourceVo ds = new DataSourceVo(columns);
		String readUrl = "ctx + '" + PLUGMIN_REST_BASE_URL + "/rest/" + widget + "/read/" + modelName + "?view=" + viewName + "'";
		ds.getTransport_(null, readUrl, null, null);
		return ds;
	}
	
	public static List<ColumnVo> columns(List<String> properties, Class<?> pojoClass, boolean serializeConfig) throws Exception {
		List<ColumnVo> columns = new ArrayList<ColumnVo>();
		for (String property : properties) {
			ColumnVo column = new ColumnVo(property, pojoClass.getDeclaredField(property), serializeConfig);
			columns.add(column);
		}
		return columns;
	}
	
	public static MetaComponent foreignComponent(Class<?> foreignEntity,
			List<MetaComponent> metaComponents, boolean serializeConfig) throws Exception {
		for (MetaComponent metaComponent : metaComponents) {
			if(metaComponent.getMetaEntity().getPOJOClass().equals(foreignEntity)) {
				return metaComponent;
			}
		}
		
		AnnotatedClassParser parser = new AnnotatedClassParser();
		MetaComponent metaComponent = parser.parse(foreignEntity.getSimpleName());
    	metaComponents.add(metaComponent);
    	parser.parse2ndPhase(metaComponent, metaComponents, serializeConfig);
		return metaComponent;
	}
	
	public static List<String> jsFunctions(String fnName, MetaAutocomplete metaAutocomplete) throws JsonProcessingException {
		String fn = "function " + fnName + "(container, options) {" +
						"var input = $('<input data-bind=\"value:' + options.field.split('\\.')[0] + '\"/>');" +
						"input.attr('name', options.field.split('\\.')[0]);" +
						"input.attr('id', options.field.split('\\.')[0]);" +
						"input.appendTo(container);" +
						"input.kendoAutoComplete(" + metaAutocomplete.getJson() + ")" +
					"}";

		List<String> fns = new ArrayList<String>();
		fns.add(fn);
		return fns;
	}
	
	public static List<String> jsFunctions(String fnName, MetaDropDown metaDropDown) throws JsonProcessingException {
		String fn = "function " + fnName + "(container, options) {" +
						"var input = $('<input data-bind=\"value:' + options.field.split('\\.')[0] + '\"/>');" +
						"input.attr('name', options.field.split('\\.')[0]);" +
						"input.attr('id', options.field.split('\\.')[0]);" +
						"input.appendTo(container);" +
						"input.kendoDropDownList(" + metaDropDown.getJson() + ")" +
    				"}";
		
		String onChangeFn = "function " + fnName + "_on_change($this, container, options) {" +
								"try {" +
								"var arg2 = arguments[2];" +
								"console.log(arg2);" +
								"} catch(e) {console.log(e.toString());}" +	
							"}";	
		
		List<String> fns = new ArrayList<String>();
		fns.add(fn);
		fns.add(onChangeFn);
		return fns;
	}

	@SuppressWarnings("unchecked")
	public static void configureViewSection(MetaView view, List<MetaComponent> metaComponents, boolean serializeConfig) throws Exception {
		Class<?> pojoClass = view.getMetaComponent().getMetaEntity().getPOJOClass();

		List<AngularScope> angularScopes = view.getAngularScopes();
		Element angularView = view.getAngularView();
		List<WidgetConfig> widgetConfigs = view.getWidgetConfigs();
		DataConfig dataConfig = view.getDataConfig();
		
		angularScopes.add(new AngularScope("$scope." + pojoClass.getSimpleName(), pojoClass.getSimpleName()));
		dataConfig.setName(pojoClass.getSimpleName());
		
		Collection<String> membersNames = view.getMembersNames();
		
		Element row = new Element(Tag.valueOf("div"), "");
		row.attr("class", "plugmin-row");
		
		for (String field : membersNames) {
			if(field.startsWith("__GROUP__")) {
				continue;
			} else if(field.equals("\n")) {
				angularView.appendChild(row);
				row = new Element(Tag.valueOf("div"), "");
				row.attr("class", "plugmin-row");
				continue;
			}
			
			Field metaField = fieldReflection(field, pojoClass);
			if(isCollection(metaField)) {
				continue;
			}
			
			ElementVo element = new ElementVo(field, metaField, view, metaComponents, serializeConfig);
			
			row.appendChild(element.getElement());
			
			if(element.getAngularScope() != null)
				angularScopes.add(element.getAngularScope());
			
			if(element.getWidgetConfig() != null)
				widgetConfigs.add(element.getWidgetConfig());
			
			if(isNotEmpty(element.getDataField()))
				dataConfig.getFields().add(element.getDataField());
		}
		angularView.appendChild(row);
	}

	public static DateTimePickerConfig dateTimePickerConfig(MetaElement metaElement, Field metaField) throws Exception {
		DateTimePickerConfig dateTimePickerConfig = null;

		UseDateTimePicker useDatePicker = null;
		
		if(metaElement instanceof MetaTab)
			useDatePicker = findAnnotation(UseDateTimePicker.class, metaField, (MetaTab)metaElement);
		else if(metaElement instanceof MetaView)
			useDatePicker = findAnnotation(UseDateTimePicker.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("DateTimePicker config cannot be obtained for elements other than MetaTab & MetaView");
			
		if(useDatePicker != null) {
			String config = useDatePicker.config();
			if(StringUtils.isNotEmpty(config)) {
				dateTimePickerConfig = findWidgetConfig(DateTimePickerConfigs.class, DateTimePickerConfig.class, config, metaField.getDeclaringClass());
			}
		}
		
		if(dateTimePickerConfig == null) {
			dateTimePickerConfig = DefaultDateTimePickerConfig.class.getAnnotation(DateTimePickerConfig.class);
		}
		
		return dateTimePickerConfig;
	}
	
	public static DatePickerConfig datePickerConfig(MetaElement metaElement, Field metaField) throws Exception {
		DatePickerConfig datePickerConfig = null;

		UseDatePicker useDatePicker = null;
		
		if(metaElement instanceof MetaTab)
			useDatePicker = findAnnotation(UseDatePicker.class, metaField, (MetaTab)metaElement);
		else if(metaElement instanceof MetaView)
			useDatePicker = findAnnotation(UseDatePicker.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("DatePicker config cannot be obtained for elements other than MetaTab & MetaView");
			
		if(useDatePicker != null) {
			String config = useDatePicker.config();
			if(StringUtils.isNotEmpty(config)) {
				datePickerConfig = findWidgetConfig(DatePickerConfigs.class, DatePickerConfig.class, config, metaField.getDeclaringClass());
			}
		}
		
		if(datePickerConfig == null) {
			datePickerConfig = DefaultDatePickerConfig.class.getAnnotation(DatePickerConfig.class);
		}
		
		return datePickerConfig;
	}
	
	public static TimePickerConfig timePickerConfig(MetaElement metaElement, Field metaField) throws Exception {
		TimePickerConfig timePickerConfig = null;

		UseTimePicker useDatePicker = null;
		
		if(metaElement instanceof MetaTab)
			useDatePicker = findAnnotation(UseTimePicker.class, metaField, (MetaTab)metaElement);
		else if(metaElement instanceof MetaView)
			useDatePicker = findAnnotation(UseTimePicker.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("TimePicker config cannot be obtained for elements other than MetaTab & MetaView");
			
		if(useDatePicker != null) {
			String config = useDatePicker.config();
			if(StringUtils.isNotEmpty(config)) {
				timePickerConfig = findWidgetConfig(TimePickerConfigs.class, TimePickerConfig.class, config, metaField.getDeclaringClass());
			}
		}
		
		if(timePickerConfig == null) {
			timePickerConfig = DefaultTimePickerConfig.class.getAnnotation(TimePickerConfig.class);
		}
		
		return timePickerConfig;
	}
	
	public static NumericTextBoxConfig numericTextBoxConfig(MetaElement metaElement, Field metaField) throws Exception {
		NumericTextBoxConfig numericTextBoxConfig = null;

		UseNumericTextBox useNumericTextBox = null;
		
		if(metaElement instanceof MetaTab)
			useNumericTextBox = findAnnotation(UseNumericTextBox.class, metaField, (MetaTab)metaElement);
		else if(metaElement instanceof MetaView)
			useNumericTextBox = findAnnotation(UseNumericTextBox.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("DatePicker config cannot be obtained for elements other than MetaTab & MetaView");
			
		if(useNumericTextBox != null) {
			String config = useNumericTextBox.config();
			if(StringUtils.isNotEmpty(config)) {
				numericTextBoxConfig = findWidgetConfig(NumericTextBoxConfigs.class, NumericTextBoxConfig.class, config, metaField.getDeclaringClass());
			}
		}
		
		if(numericTextBoxConfig == null) {
			numericTextBoxConfig = DefaultNumericTextBoxConfig.class.getAnnotation(NumericTextBoxConfig.class);
		}
		
		return numericTextBoxConfig;
	}

	public static EditorConfig editorConfig(MetaElement metaElement, Field metaField) throws Exception {
		EditorConfig editorConfig = null;

		UseEditor useEditor = null;
		
		if(metaElement instanceof MetaTab)
			useEditor = findAnnotation(UseEditor.class, metaField, (MetaTab)metaElement);
		else if(metaElement instanceof MetaView)
			useEditor = findAnnotation(UseEditor.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("Editor config cannot be obtained for elements other than MetaTab & MetaView");
			
		if(useEditor != null) {
			String config = useEditor.config();
			if(StringUtils.isNotEmpty(config)) {
				editorConfig = findWidgetConfig(EditorConfigs.class, EditorConfig.class, config, metaField.getDeclaringClass());
			}
		}
		
		if(editorConfig == null) {
			editorConfig = DefaultEditorConfig.class.getAnnotation(EditorConfig.class);
		}
		
		return editorConfig;
	}
	
	public static UploadConfig uploadConfig(MetaElement metaElement, Field metaField, Class<? extends Annotation> defaultConfigClass) throws Exception {
		UploadConfig uploadConfig = null;

		UseUpload useUpload = null;
		
		if(metaElement instanceof MetaTab)
			useUpload = findAnnotation(UseUpload.class, metaField, (MetaTab)metaElement);
		else if(metaElement instanceof MetaView)
			useUpload = findAnnotation(UseUpload.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("Upload config cannot be obtained for elements other than MetaTab & MetaView");
			
		if(useUpload != null) {
			String config = useUpload.config();
			if(StringUtils.isNotEmpty(config)) {
				uploadConfig = findWidgetConfig(UploadConfigs.class, UploadConfig.class, config, metaField.getDeclaringClass());
			}
		}
		
		if(uploadConfig == null) {
			uploadConfig = defaultConfigClass.getAnnotation(UploadConfig.class);
		}
		
		return uploadConfig;
	}

	
	public static AutocompleteConfig autocompleteConfig(MetaElement metaElement, Field metaField) throws Exception {
		AutocompleteConfig autocompleteConfig = null;

		UseAutocomplete useAutocomplete = null;
		
		if(metaElement instanceof MetaTab)
			useAutocomplete = findAnnotation(UseAutocomplete.class, metaField, (MetaTab)metaElement);
		else if(metaElement instanceof MetaView)
			useAutocomplete = findAnnotation(UseAutocomplete.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("AutoComplete config cannot be obtained for elements other than MetaTab & MetaView");
			
		if(useAutocomplete != null) {
			String config = useAutocomplete.config();
			if(StringUtils.isNotEmpty(config)) {
				autocompleteConfig = findWidgetConfig(AutocompleteConfigs.class, AutocompleteConfig.class, config, metaField.getDeclaringClass());
			}
		}
		
		if(autocompleteConfig == null) {
			autocompleteConfig = DefaultAutocompleteConfig.class.getAnnotation(AutocompleteConfig.class);
		}
		
		return autocompleteConfig;
	}
	
	public static DropDownConfig dropDownConfig(MetaElement metaElement, Field metaField) throws Exception {
		DropDownConfig dropDownConfig = null;

		UseDropdown useDropDown = null;
		
		if(metaElement instanceof MetaTab)
			useDropDown = findAnnotation(UseDropdown.class, metaField, (MetaTab)metaElement);
		else if(metaElement instanceof MetaView)
			useDropDown = findAnnotation(UseDropdown.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("DropDown config cannot be obtained for elements other than MetaTab & MetaView");
			
		if(useDropDown != null) {
			String name = useDropDown.name();
			if(StringUtils.isNotEmpty(name)) {
				DropDown dropDown = findWidget(DropDowns.class, DropDown.class, name, metaField.getDeclaringClass());
				if(dropDown != null) {
					String config = dropDown.config();
					if(StringUtils.isNotEmpty(config)) {
						dropDownConfig = findWidgetConfig(DropDownConfigs.class, DropDownConfig.class, config, metaField.getDeclaringClass());
					}
				}
			}
		}
		
		if(dropDownConfig == null) {
			dropDownConfig = DefaultDropDownConfig.class.getAnnotation(DropDownConfig.class);
		}
		
		return dropDownConfig;
	}
	
	public static TabConfig tabConfig(MetaElement metaElement, Field metaField) throws Exception {
		TabConfig tabConfig = null;

		UseTab useTab = null;
		
		if(metaElement instanceof MetaTab)
			useTab = findAnnotation(UseTab.class, metaField, (MetaTab)metaElement);
		
		else if(metaElement instanceof MetaView)
			useTab = findAnnotation(UseTab.class, metaField, (MetaView)metaElement);
		else
			throw new Exception("Tab config cannot be obtained for elements other than MetaTab & MetaView");
		
		if(useTab != null) {
			String name = useTab.name();
			if(StringUtils.isNotEmpty(name)) {
				Tab tab = findWidget(Tabs.class, Tab.class, name, metaField.getDeclaringClass());
				if(tab != null) {
					String config = tab.config();
					if(StringUtils.isNotEmpty(config)) {
						tabConfig = findWidgetConfig(TabConfigs.class, TabConfig.class, config, metaField.getDeclaringClass());
					}
				}
			}
		}
		
		if(tabConfig == null) {
			tabConfig = DefaultTabConfig.class.getAnnotation(TabConfig.class);
		}
		
		return tabConfig;
	}
	
}
