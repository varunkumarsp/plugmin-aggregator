package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.UploadConfig;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;
import org.openxava.annotations.parse.JsObject;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class UploadConfigVo implements JsFieldValueResolver,
		JsonKeyValuesProviderI, Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;

	private AsyncVo async;
	
	@DefaultValue("true")
	private Boolean enabled;

	@JsObject
	private String files;
	
	@DefaultValue("true")
	private Boolean multiple;
	
	@DefaultValue("true")
	private Boolean showFileList;
	
	@CompositeField("template")
	private String templateStr;
	
	@CompositeField("template")
	private String templateFn;

	@JsonRawKeyValueProvider
	private List<EventVo> events;


	public UploadConfigVo(UploadConfig uploadConfig) {
		this.copyFrom(uploadConfig);
	}

	public AsyncVo getAsync_() {
		if(async == null)
			try {
				async = AsyncVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return async;
	}
	
	public AsyncVo getAsync() {
		return async;
	}

	public void setAsync(AsyncVo async) {
		if(async != null)
			this.async = async;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		if(enabled != null)
			this.enabled = enabled;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		if(isNotEmpty(files))
			this.files = files;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		if(multiple != null)
			this.multiple = multiple;
	}

	public Boolean getShowFileList() {
		return showFileList;
	}

	public void setShowFileList(Boolean showFileList) {
		if(showFileList != null)
			this.showFileList = showFileList;
	}
	
	public String getTemplateStr() {
		return templateStr;
	}

	public void setTemplateStr(String templateStr) {
		if(isNotEmpty(templateStr))
			this.templateStr = templateStr;
	}

	public String getTemplateFn() {
		return templateFn;
	}

	public void setTemplateFn(String templateFn) {
		if(isNotEmpty(templateFn))
			this.templateFn = templateFn;
	}

	public List<EventVo> getEvents() {
		return events;
	}

	public void setEvents(List<EventVo> events) {
		if(CollectionUtils.isNotEmpty(events))
			this.events = events;
	}

	public void copyFrom(UploadConfig config) {
		getAsync_().copyFrom(config.async());
		setEnabled(config.enabled().getBool());
		setFiles(config.files());
		setMultiple(config.multiple().getBool());
		setShowFileList(config.showFileList().getBool());
		setTemplateFn(config.templateFn());
		setTemplateStr(config.templateStr());
	}

	@Override
	public JsField resolve(String field) {
		if (field.equals("template")) {
			return resolveTemplate();
		}
		return null;
	}

	private JsField resolveTemplate() {
		if (StringUtils.isNotEmpty(templateFn)) {
			return new JsFieldVariable("template", templateFn, "templateFn");
		} else if (StringUtils.isNotEmpty(templateStr)) {
			return new JsFieldVariable("template", templateStr, "templateStr");
		}
		return null;
	}

	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String fieldName) {
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
		if (CollectionUtils.isNotEmpty(events)) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for (EventVo event : events) {
				map.put(event.getName(), event.getHandler());
			}
			return map;
		}
		return null;
	}

}
