package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.DateTimePickerConfig;
import org.openxava.annotations.extended.ui.config.enums.Depth;
import org.openxava.annotations.extended.ui.config.enums.Start;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;
import org.openxava.annotations.parse.JsObject;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;
import org.openxava.annotations.parse.JsonValueProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class DateTimePickerConfigVo implements JsFieldValueResolver,
		JsonKeyValuesProviderI, Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	@JsonValueProvider
	private AnimationVo animation;
	
	@DefaultValue("Current focused date is #=kendo.toString(data.current, 'G')#")
	private String ARIATemplate;
	
	@DefaultValue("en-US")
	private String culture;
	
	private String[] dates;
	
	private Depth depth;
	
	@CompositeField("footer")
	private String footerStr;
	
	@CompositeField("footer")
	private String footerFn;
	
	@DefaultValue("MM/dd/yyyy h:mm tt")
	private String format;
	
	@DefaultValue("30")
	private Integer interval;
	
	@DefaultValue("new Date(2099, 11, 31)")
	@JsObject
	private String max;
	
	@DefaultValue("new Date(1900, 0, 1)")
	@JsObject
	private String min;
	
	private MonthVo month;

	@JsObject
	private String parseFormats;
	
	@DefaultValue("month")
	private Start start;
	
	@DefaultValue("h:mm tt")
	private String timeFormat;
	
	@JsObject
	private String value;
	
	@JsonRawKeyValueProvider
	private List<EventVo> events;



	
	public DateTimePickerConfigVo(DateTimePickerConfig config) {
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

	public String getARIATemplate() {
		return ARIATemplate;
	}

	public void setARIATemplate(String aRIATemplate) {
		if(isNotEmpty(aRIATemplate))
			ARIATemplate = aRIATemplate;
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

	public Depth getDepth() {
		return depth;
	}

	public void setDepth(Depth depth) {
		if(depth != null && depth.getDepth() != null)
			this.depth = depth;
	}

	public String getFooterStr() {
		return footerStr;
	}

	public void setFooterStr(String footerStr) {
		if(isNotEmpty(footerStr))
			this.footerStr = footerStr;
	}

	public String getFooterFn() {
		return footerFn;
	}

	public void setFooterFn(String footerFn) {
		if(isNotEmpty(footerFn))
			this.footerFn = footerFn;
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

	public MonthVo getMonth_() {
		if(month == null)
			try {
				month = MonthVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return month;
	}
	
	public MonthVo getMonth() {
		return month;
	}

	public void setMonth(MonthVo month) {
		if(month != null)
			this.month = month;
	}

	public String getParseFormats() {
		return parseFormats;
	}

	public void setParseFormats(String parseFormats) {
		if(isNotEmpty(parseFormats))
			this.parseFormats = parseFormats;
	}

	public Start getStart() {
		return start;
	}

	public void setStart(Start start) {
		if(start != null && start.getStart() != null)
			this.start = start;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		if(isNotEmpty(timeFormat))
			this.timeFormat = timeFormat;
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

	public void copyFrom(DateTimePickerConfig config) {
		getAnimation_().copyFrom(config.animation());
		setARIATemplate(config.ARIATemplate());
		setCulture(config.culture());
		setDates(config.dates());
		setDepth(config.depth());
		setFooterFn(config.footerFn());
		setFooterStr(config.footerStr());
		setFormat(config.format());
		setInterval(config.interval());
		setMax(config.max());
		setMin(config.min());
		getMonth_().copyFrom(config.month());
		setParseFormats(config.parseFormats());
		setStart(config.start());
		setTimeFormat(config.timeFormat());
		setValue(config.value());
	}
	
	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsField resolve(String field) {
		if (field.equals("footer")) {
			return resolveFooter();
		}
		return null;
	}
	
	private JsField resolveFooter() {
		if (StringUtils.isNotEmpty(footerFn)) {
			return new JsFieldVariable("footer", footerFn, "footerFn");
		} else if (StringUtils.isNotEmpty(footerStr)) {
			return new JsFieldVariable("footer", footerStr, "footerStr");
		}
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
