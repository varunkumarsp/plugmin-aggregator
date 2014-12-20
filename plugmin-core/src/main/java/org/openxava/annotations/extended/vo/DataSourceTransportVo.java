package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.JsObject;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class DataSourceTransportVo {

	private TransportCreate create;
	private TransportRead read;
	private TransportUpdate update;
	private TransportDestroy destroy;
	private String pushFn;
	@JsObject
	private String parameterMap = "parameterMap";


	private DataSourceTransportVo() {
	}
	
	public static DataSourceTransportVo instance(String createUrl, String readUrl, String updateUrl, String destroyUrl) throws InstantiationRestrictedException {
		if(!calledFrom(DataSourceVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new DataSourceTransportVo(createUrl, readUrl, updateUrl, destroyUrl);
	}
	
	private DataSourceTransportVo(String createUrl, String readUrl,
			String updateUrl, String destroyUrl) {
		if(isNotEmpty(createUrl))
			this.create = new TransportCreate(createUrl);
		if(isNotEmpty(readUrl))
			this.read = new TransportRead(readUrl);
		if(isNotEmpty(updateUrl))
			this.update = new TransportUpdate(updateUrl);
		if(isNotEmpty(destroyUrl))
			this.destroy = new TransportDestroy(destroyUrl);
	}

	public TransportCreate getCreate() {
		return create;
	}

	public void setCreate(TransportCreate create) {
		if(create != null)
			this.create = create;
	}

	public TransportRead getRead() {
		return read;
	}

	public void setRead(TransportRead read) {
		if(read != null)
			this.read = read;
	}

	public TransportUpdate getUpdate() {
		return update;
	}

	public void setUpdate(TransportUpdate update) {
		if(update != null)
			this.update = update;
	}

	public TransportDestroy getDestroy() {
		return destroy;
	}

	public void setDestroy(TransportDestroy destroy) {
		if(destroy != null)
			this.destroy = destroy;
	}

	public String getPushFn() {
		return pushFn;
	}

	public void setPushFn(String pushFn) {
		if(isNotEmpty(pushFn))
			this.pushFn = pushFn;
	}

	public String getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(String parameterMap) {
		if(isNotEmpty(parameterMap))
			this.parameterMap = parameterMap;
	}

}
