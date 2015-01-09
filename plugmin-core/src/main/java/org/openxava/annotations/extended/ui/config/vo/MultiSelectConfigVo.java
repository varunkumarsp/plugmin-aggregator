package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.MultiSelectConfig;
import org.openxava.annotations.extended.ui.config.enums.Operator;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldArray;
import org.openxava.annotations.parse.JsFieldObject;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;
import org.openxava.annotations.parse.JsonValueProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class MultiSelectConfigVo implements JsFieldValueResolver, JsonKeyValuesProviderI, Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	
	@JsonValueProvider
	private AnimationVo animation;
	
	@DefaultValue("true")
	private Boolean autoBind;
	
	@DefaultValue("true")
	private Boolean autoClose;
	
	@CompositeField("dataSource")
	private DataSourceVo dataSourceObj;
	
	@CompositeField("dataSource")
	private String[] dataSourceArray;
	
	@DefaultValue("")
	private String dataTextField;
	
	@DefaultValue("")
	private String dataValueField;
	
	@DefaultValue("200")
	private Integer delay;
	
	@DefaultValue("true")
	private Boolean enable;
	
	@DefaultValue("startswith")
	private Operator filter;
	
	@DefaultValue("200")
	private Integer height;
	
	@DefaultValue("true")
	private Boolean highlightFirst;
	
	@DefaultValue("true")
	private Boolean ignoreCase;
	
	@DefaultValue("0")
	private Integer minLength;
	
	private Integer maxSelectedItems;
	
	@DefaultValue("")
	private String placeholder;
	
	@CompositeField("headerTemplate")
	private String headerTemplateStr;

	@CompositeField("headerTemplate")
	private String headerTemplateFn;

	@CompositeField("itemTemplate")
	private String itemTemplateStr;

	@CompositeField("itemTemplate")
	private String itemTemplateFn;
	
	@CompositeField("tagTemplate")
	private String tagTemplateStr;

	@CompositeField("tagTemplate")
	private String tagTemplateFn;
	
	private String[] value;
	
	@DefaultValue("false")
	private Boolean valuePrimitive;

	@JsonRawKeyValueProvider
	private List<EventVo> events;

	
	
	public MultiSelectConfigVo(String dataValueField, String dataTextField) {
		this.dataValueField = dataValueField;
		this.dataTextField = dataTextField;
	}

	/**
	 * Used by enum dropdowns
	 * @param enumValues
	 */
	public MultiSelectConfigVo(String[] enumValues) {
		this.dataValueField = null;
		this.dataTextField = null;
	}


	public AnimationVo getAnimation_() {
		if(animation == null)
			try {
				animation = AnimationVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return animation;
	}
	
	public AnimationVo getAnimation() {
		return animation;
	}

	public void setAnimation(AnimationVo animation) {
		if(animation != null)
			this.animation = animation;
	}
	
	public Boolean getAutoBind() {
		return autoBind;
	}

	public void setAutoBind(Boolean autoBind) {
		if(autoBind != null)
			this.autoBind = autoBind;
	}

	public Boolean getAutoClose() {
		return autoClose;
	}

	public void setAutoClose(Boolean autoClose) {
		if(autoClose != null)
			this.autoClose = autoClose;
	}

	public DataSourceVo getDataSourceObj() {
		return dataSourceObj;
	}

	public void setDataSourceObj(DataSourceVo dataSourceObj) {
		if(dataSourceObj != null)
			this.dataSourceObj = dataSourceObj;
	}

	public String[] getDataSourceArray() {
		return dataSourceArray;
	}

	public void setDataSourceArray(String[] dataSourceArray) {
		if(dataSourceArray != null && dataSourceArray.length != 0)
			this.dataSourceArray = dataSourceArray;
	}

	public String getDataTextField() {
		return dataTextField;
	}

	public void setDataTextField(String dataTextField) {
		if(isNotEmpty(dataTextField))
			this.dataTextField = dataTextField;
	}

	public String getDataValueField() {
		return dataValueField;
	}

	public void setDataValueField(String dataValueField) {
		if(isNotEmpty(dataValueField))
			this.dataValueField = dataValueField;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		if(delay != null && delay != -1)
			this.delay = delay;
	}

	public Operator getFilter() {
		return filter;
	}

	public void setFilter(Operator filter) {
		if(filter != null && filter.getValue() != null)
			this.filter = filter;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		if(height != null && height != -1)
			this.height = height;
	}

	public Boolean getIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		if(ignoreCase != null)
			this.ignoreCase = ignoreCase;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		if(minLength != null && minLength != -1)
		this.minLength = minLength;
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

	public String[] getValue() {
		return value;
	}

	public void setValue(String[] value) {
		if(ArrayUtils.isNotEmpty(value))
			this.value = value;
	}

	public Boolean getValuePrimitive() {
		return valuePrimitive;
	}

	public void setValuePrimitive(Boolean valuePrimitive) {
		if(valuePrimitive != null)
			this.valuePrimitive = valuePrimitive;
	}
	
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		if(enable != null)
			this.enable = enable;
	}

	public Boolean getHighlightFirst() {
		return highlightFirst;
	}

	public void setHighlightFirst(Boolean highlightFirst) {
		if(highlightFirst != null)
			this.highlightFirst = highlightFirst;
	}

	public Integer getMaxSelectedItems() {
		return maxSelectedItems;
	}

	public void setMaxSelectedItems(Integer maxSelectedItems) {
		if(maxSelectedItems != null && maxSelectedItems != -1)
			this.maxSelectedItems = maxSelectedItems;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		if(isNotEmpty(placeholder))
			this.placeholder = placeholder;
	}

	public String getItemTemplateStr() {
		return itemTemplateStr;
	}

	public void setItemTemplateStr(String itemTemplateStr) {
		if(isNotEmpty(itemTemplateStr))
			this.itemTemplateStr = itemTemplateStr;
	}

	public String getItemTemplateFn() {
		return itemTemplateFn;
	}

	public void setItemTemplateFn(String itemTemplateFn) {
		if(isNotEmpty(itemTemplateFn))
			this.itemTemplateFn = itemTemplateFn;
	}

	public String getTagTemplateStr() {
		return tagTemplateStr;
	}

	public void setTagTemplateStr(String tagTemplateStr) {
		if(isNotEmpty(tagTemplateStr))
			this.tagTemplateStr = tagTemplateStr;
	}

	public String getTagTemplateFn() {
		return tagTemplateFn;
	}

	public void setTagTemplateFn(String tagTemplateFn) {
		if(isNotEmpty(tagTemplateFn))
			this.tagTemplateFn = tagTemplateFn;
	}

	public List<EventVo> getEvents() {
		return events;
	}

	public void setEvents(List<EventVo> events) {
		if(CollectionUtils.isNotEmpty(events))
			this.events = events;
	}

	public void copyFrom(MultiSelectConfig config) {
		getAnimation_().copyFrom(config.animation());
		setAutoBind(config.autoBind().getBool());
		setAutoClose(config.autoClose().getBool());
		setDataSourceArray(null);
		setDataSourceObj(null);
		setDelay(config.delay());
		setEnable(config.enable().getBool());
		setFilter(config.filter());
		setHeight(config.height());
		setHighlightFirst(config.highlightFirst().getBool());
		setIgnoreCase(config.ignoreCase().getBool());
		setMinLength(config.minLength());
		setMaxSelectedItems(config.maxSelectedItems());
		setPlaceholder(config.placeholder());
		setHeaderTemplateFn(config.headerTemplateFn());
		setHeaderTemplateStr(config.headerTemplateStr());
		setItemTemplateFn(config.itemTemplateFn());
		setItemTemplateStr(config.itemTemplateStr());
		setTagTemplateFn(config.tagTemplateFn());
		setTagTemplateStr(config.tagTemplateStr());
		setValue(config.value());
		setValuePrimitive(config.valuePrimitive().getBool());
	}
	
	@Override
	public JsField resolve(String field) {
		if(field.equals("dataSource")) {
			return resolveDataSource();
		} else if(field.equals("headerTemplate")) {
			return resolveHeaderTemplate();
		} else if(field.equals("itemTemplate")) {
			return resolveItemTemplate();
		} else if(field.equals("tagTemplate")) {
			return resolveTagTemplate();
		}
		return null;
	}

	private JsField resolveTagTemplate() {
		if(StringUtils.isNotEmpty(tagTemplateFn)) {
			return new JsFieldVariable("tagTemplate", tagTemplateFn, "tagTemplateFn");
		} else if(StringUtils.isNotEmpty(tagTemplateStr)) {
			return new JsFieldVariable("tagTemplate", tagTemplateStr, "tagTemplateStr");
		}
		return null;
	}

	private JsField resolveItemTemplate() {
		if(StringUtils.isNotEmpty(itemTemplateFn)) {
			return new JsFieldVariable("itemTemplate", itemTemplateFn, "itemTemplateFn");
		} else if(StringUtils.isNotEmpty(itemTemplateStr)) {
			return new JsFieldVariable("itemTemplate", itemTemplateStr, "itemTemplateStr");
		}
		return null;
	}

	private JsField resolveHeaderTemplate() {
		if(StringUtils.isNotEmpty(headerTemplateFn)) {
			return new JsFieldVariable("headerTemplate", headerTemplateFn, "headerTemplateFn");
		} else if(StringUtils.isNotEmpty(headerTemplateStr)) {
			return new JsFieldVariable("headerTemplate", headerTemplateStr, "headerTemplateStr");
		}
		return null;
	}

	private JsField resolveDataSource() {
		if(dataSourceArray != null && dataSourceArray.length != 0) {
			return new JsFieldArray("dataSource", dataSourceArray, "dataSourceArray");
		}
		return new JsFieldObject("dataSource", dataSourceObj, "dataSourceObj");
	}

	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String fieldName) {
		if(fieldName.equals("animation") && animation != null) {
			if(animation.getConfig() != null)
				return animation.getConfig();
			else if(animation.isAnimation() != null && animation.isAnimation()) 
				return new Boolean(true);
		}
		return null;
	}
	
	@Override
	public Map<String, String> getRawKeyValues(String fieldName) {
		if(fieldName.equals("events")) {
			return getEventsObject();
		}
		return null;
	}

	private Map<String, String> getEventsObject() {
		if(isNotEmpty(events)) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for(EventVo event : events) {
				map.put(event.getName(), event.getHandler());
			}
			return map;
		}
		return null;
	}


}
