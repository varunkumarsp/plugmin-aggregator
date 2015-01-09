package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.ui.config.Async;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsObject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class AsyncVo {

	@DefaultValue("true")
	private Boolean autoUpload;

	@DefaultValue("false")
	private Boolean batch;

	@DefaultValue("fileNames")
	private String removeField;

	@JsObject
	private String removeUrl;

	@DefaultValue("POST")
	private String removeVerb;

	private String saveField;

	@JsObject
	private String saveUrl;

	@DefaultValue("true")
	private Boolean withCredentials;

	
	public static AsyncVo instance() throws InstantiationRestrictedException {
		if (!calledFrom(UploadConfigVo.class)) {
			throw new InstantiationRestrictedException(
					"No permission to instantiate");
		}
		return new AsyncVo();
	}

	
	public Boolean getAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(Boolean autoUpload) {
		if (autoUpload != null)
			this.autoUpload = autoUpload;
	}

	public Boolean getBatch() {
		return batch;
	}

	public void setBatch(Boolean batch) {
		if(batch != null)
			this.batch = batch;
	}

	public String getRemoveField() {
		return removeField;
	}

	public void setRemoveField(String removeField) {
		if(isNotEmpty(removeField))
			this.removeField = removeField;
	}

	public String getRemoveUrl() {
		return removeUrl;
	}

	public void setRemoveUrl(String removeUrl) {
		if(isNotEmpty(removeUrl))
			this.removeUrl = removeUrl;
	}

	public String getRemoveVerb() {
		return removeVerb;
	}

	public void setRemoveVerb(String removeVerb) {
		if(isNotEmpty(removeVerb))
			this.removeVerb = removeVerb;
	}

	public String getSaveField() {
		return saveField;
	}

	public void setSaveField(String saveField) {
		if(isNotEmpty(saveField))
			this.saveField = saveField;
	}

	public String getSaveUrl() {
		return saveUrl;
	}

	public void setSaveUrl(String saveUrl) {
		if(isNotEmpty(saveUrl))
			this.saveUrl = saveUrl;
	}

	public Boolean getWithCredentials() {
		return withCredentials;
	}

	public void setWithCredentials(Boolean withCredentials) {
		if(withCredentials != null)
			this.withCredentials = withCredentials;
	}

	public void copyFrom(Async config) {
		setAutoUpload(config.autoUpload().getBool());
		setBatch(config.batch().getBool());
		setRemoveField(config.removeField());
		setRemoveUrl(config.removeUrl());
		setRemoveVerb(config.removeVerb());
		setSaveField(config.saveField());
		setSaveUrl(config.saveUrl());
		setWithCredentials(config.withCredentials().getBool());
	}

}
