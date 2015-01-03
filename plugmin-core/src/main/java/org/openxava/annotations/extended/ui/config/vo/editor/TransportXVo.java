package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.editor.TransportX;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class TransportXVo implements JsFieldValueResolver {

	@DefaultValue("application/x-www-form-urlencoded")
	private String contentType;
	
	private String data;
	
	private String dataType;
	
	@DefaultValue("GET")
	private String type;
	
	@CompositeField("url")
	private String urlStr;
	
	@CompositeField("url")
	private String urlFn;
	

	public static TransportXVo instance() {
		return new TransportXVo();
	}
	
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		if(isNotEmpty(contentType))
			this.contentType = contentType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		if(isNotEmpty(data))
			this.data = data;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		if(isNotEmpty(dataType))
			this.dataType = dataType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(isNotEmpty(type))
			this.type = type;
	}

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		if(isNotEmpty(urlStr))
			this.urlStr = urlStr;
	}

	public String getUrlFn() {
		return urlFn;
	}

	public void setUrlFn(String urlFn) {
		if(isNotEmpty(urlFn))
			this.urlFn = urlFn;
	}

	public void copyFrom(TransportX config) {
		setContentType(config.contentType());
		setData(config.data());
		setDataType(config.dataType());
		setType(config.type());
		setUrlFn(config.urlFn());
		setUrlStr(config.urlStr());
	}


	@Override
	public JsField resolve(String field) {
		if(field.equals("url")) {
			return resolveUrl();
		}
		return null;
	}


	private JsField resolveUrl() {
		if (StringUtils.isNotEmpty(urlFn)) {
			return new JsFieldVariable("url", urlFn, "urlFn");
		} else if (StringUtils.isNotEmpty(urlStr)) {
			return new JsFieldVariable("url", urlStr, "urlStr");
		}
		return null;
	}

}
