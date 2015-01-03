package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.openxava.annotations.extended.ui.config.TimePickerConfig;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsObject;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;
import org.openxava.annotations.parse.JsonValueProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class TimePickerConfigVo implements JsFieldValueResolver,
		JsonKeyValuesProviderI, Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	@JsonValueProvider
	private AnimationVo animation;
	
	@DefaultValue("en-US")
	private String culture;
	
	private String[] dates;
	
	@DefaultValue("h:mm tt")
	private String format;
	
	@DefaultValue("30")
	private Integer interval;
	
	@DefaultValue("new Date(2099, 11, 31, 0, 0)") //date part is ignored
	@JsObject
	private String max;
	
	@DefaultValue("new Date(1900, 0, 1, 0, 0)") //date part is ignored
	@JsObject
	private String min;
	
	@JsObject
	private String parseFormats;
	
	@JsObject
	private String value;
	
	@JsonRawKeyValueProvider
	private List<EventVo> events;

	
	public TimePickerConfigVo(TimePickerConfig config) {
		this.copyFrom(config);
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

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		if(isNotEmpty(culture))
			this.culture = culture;
	}

	public String[] getDates() {
		return dates;
	}

	public void setDates(String[] dates) {
		if(dates != null && dates.length != 0)
			this.dates = dates;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		if(isNotEmpty(format))
			this.format = format;
	}
	
	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		if(interval != null && interval != -1)
			this.interval = interval;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(isNotEmpty(max))
			this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(isNotEmpty(min))
			this.min = min;
	}

	public String getParseFormats() {
		return parseFormats;
	}

	public void setParseFormats(String parseFormats) {
		if(isNotEmpty(parseFormats))
			this.parseFormats = parseFormats;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if(isNotEmpty(value))
			this.value = value;
	}
	
	public List<EventVo> getEvents() {
		return events;
	}

	public void setEvents(List<EventVo> events) {
		if(CollectionUtils.isNotEmpty(events))
			this.events = events;
	}

	public void copyFrom(TimePickerConfig config) {
		getAnimation_().copyFrom(config.animation());
		setCulture(config.culture());
		setDates(config.dates());
		setFormat(config.format());
		setInterval(config.interval());
		setMax(config.max());
		setMin(config.min());
		setParseFormats(config.parseFormats());
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

}
