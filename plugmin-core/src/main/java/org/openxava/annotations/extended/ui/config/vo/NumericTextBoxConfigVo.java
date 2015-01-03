package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.openxava.annotations.extended.ui.config.NumericTextBoxConfig;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class NumericTextBoxConfigVo implements JsFieldValueResolver,
		JsonKeyValuesProviderI, Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	@DefaultValue("en-US")
	private String culture;
	
	private Integer decimals;
	
	@DefaultValue("Decrease value")
	private String downArrowText;

	@DefaultValue("n")
	private String format;
	
	private Integer max;
	
	private Integer min;
	
	@DefaultValue("")
	private String placeholder;
	
	@DefaultValue("true")
	private Boolean spinners;
	
	@DefaultValue("1")
	private Integer step;
	
	@DefaultValue("Increase value")
	private String upArrowText;
	
	private Integer value;
		
	@JsonRawKeyValueProvider
	private List<EventVo> events;
	
	
	public NumericTextBoxConfigVo(NumericTextBoxConfig config) {
		this.copyFrom(config);
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		if(isNotEmpty(culture))
			this.culture = culture;
	}

	public Integer getDecimals() {
		return decimals;
	}

	public void setDecimals(Integer decimals) {
		if(decimals != null && decimals != -1)
			this.decimals = decimals;
	}

	public String getDownArrowText() {
		return downArrowText;
	}

	public void setDownArrowText(String downArrowText) {
		if(isNotEmpty(downArrowText))
			this.downArrowText = downArrowText;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		if(isNotEmpty(format))
			this.format = format;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		if(max != null && max != -1)
			this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		if(min != null && min != -1)
			this.min = min;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		if(isNotEmpty(placeholder))
			this.placeholder = placeholder;
	}

	public Boolean getSpinners() {
		return spinners;
	}

	public void setSpinners(Boolean spinners) {
		if(spinners != null)
			this.spinners = spinners;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		if(step != null && step != -1)
			this.step = step;
	}

	public String getUpArrowText() {
		return upArrowText;
	}

	public void setUpArrowText(String upArrowText) {
		if(isNotEmpty(upArrowText))
			this.upArrowText = upArrowText;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		if(value != null && value != -1)
			this.value = value;
	}

	public List<EventVo> getEvents() {
		return events;
	}

	public void setEvents(List<EventVo> events) {
		if(CollectionUtils.isNotEmpty(events))
			this.events = events;
	}

	public void copyFrom(NumericTextBoxConfig config) {
		setCulture(config.culture());
		setDecimals(config.decimals());
		setDownArrowText(config.downArrowText());
		setFormat(config.format());
		setMax(config.max());
		setMin(config.min());
		setPlaceholder(config.placeholder());
		setSpinners(config.spinners().getBool());
		setStep(config.step());
		setUpArrowText(config.upArrowText());
		setValue(config.value());
	}
	
	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsField resolve(String field) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public Map<String, String> getRawKeyValues(String fieldName) {
		if (fieldName.equals("events")) {
			return getEventsObject();
		}
		return null;
	}

	private Map<String, String> getEventsObject() {
		if (isNotEmpty(events)) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (EventVo event : events) {
				map.put(event.getName(), event.getHandler());
			}
			return map;
		}
		return null;
	}

	@Override
	public Object getValue(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

}
