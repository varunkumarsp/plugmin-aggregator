package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.editor.FileTransport;
import org.openxava.annotations.extended.ui.config.vo.InstantiationRestrictedException;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldObject;
import org.openxava.annotations.parse.JsFieldString;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class FileTransportVo implements JsFieldValueResolver {

	
	@CompositeField("read")
	private String readStr;
	
	@CompositeField("read")
	private TransportXVo readObj;
	
	private String uploadUrl;
	
	@CompositeField("fileUrl")
	private String fileUrlStr;
	
	@CompositeField("fileUrl")
	private String fileUrlFn;
	
	@CompositeField("destroy")
	private String destroyStr;
	
	@CompositeField("destroy")
	private TransportXVo destroyObj;
	
	@CompositeField("create")
	private String createStr;
	
	@CompositeField("create")
	private TransportXVo createObj;
	

	public static FileTransportVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(ImageBrowserVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new FileTransportVo();
	}
	
	
	public String getReadStr() {
		return readStr;
	}

	public void setReadStr(String readStr) {
		if(isNotEmpty(readStr))
			this.readStr = readStr;
	}

	public TransportXVo getReadObj_() {
		if(readObj == null)
			try {
				readObj = TransportXVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return readObj;
	}
	
	public TransportXVo getReadObj() {
		return readObj;
	}

	public void setReadObj(TransportXVo readObj) {
		if(readObj != null)
			this.readObj = readObj;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		if(isNotEmpty(uploadUrl))
			this.uploadUrl = uploadUrl;
	}

	public String getFileUrlStr() {
		return fileUrlStr;
	}

	public void setFileUrlStr(String fileUrlStr) {
		if(isNotEmpty(fileUrlStr))
			this.fileUrlStr = fileUrlStr;
	}

	public String getFileUrlFn() {
		return fileUrlFn;
	}

	public void setFileUrlFn(String fileUrlFn) {
		if(isNotEmpty(fileUrlFn))
			this.fileUrlFn = fileUrlFn;
	}

	public String getDestroyStr() {
		return destroyStr;
	}

	public void setDestroyStr(String destroyStr) {
		if(isNotEmpty(destroyStr))
			this.destroyStr = destroyStr;
	}

	public TransportXVo getDestroyObj_() {
		if(destroyObj == null)
			try {
				destroyObj = TransportXVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return destroyObj;
	}
	
	public TransportXVo getDestroyObj() {
		return destroyObj;
	}

	public void setDestroyObj(TransportXVo destroyObj) {
		if(destroyObj != null)
			this.destroyObj = destroyObj;
	}

	public String getCreateStr() {
		return createStr;
	}

	public void setCreateStr(String createStr) {
		if(isNotEmpty(createStr))
			this.createStr = createStr;
	}

	public TransportXVo getCreateObj_() {
		if(createObj == null)
			try {
				createObj = TransportXVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return createObj;
	}
	
	public TransportXVo getCreateObj() {
		return createObj;
	}

	public void setCreateObj(TransportXVo createObj) {
		if(createObj != null)
			this.createObj = createObj;
	}

	public void copyFrom(FileTransport config) {
		getCreateObj_().copyFrom(config.createObj());
		setCreateStr(config.createStr());
		getDestroyObj_().copyFrom(config.destroyObj());
		setDestroyStr(config.destroyStr());
		setFileUrlFn(config.fileUrlFn());
		setFileUrlStr(config.fileUrlStr());
		getReadObj_().copyFrom(config.readObj());
		setReadStr(config.readStr());
		setUploadUrl(config.uploadUrl());
	}


	@Override
	public JsField resolve(String field) {
		if(field.equals("read")) {
			return resolveRead();
		} else if(field.equals("fileUrl")) {
			return resolveFileUrl();
		} else if(field.equals("destroy")) {
			return resolveDestroy();
		} else if(field.equals("create")) {
			return resolveCreate();
		}
		return null;
	}

	private JsField resolveCreate() {
		if (createObj != null) {
			return new JsFieldObject("create", createObj, "createObj");
		} else if (isNotEmpty(createStr)) {
			return new JsFieldString("create", createStr, "createStr");
		}
		return null;
	}

	private JsField resolveDestroy() {
		if (destroyObj != null) {
			return new JsFieldObject("destroy", destroyObj, "destroyObj");
		} else if (isNotEmpty(destroyStr)) {
			return new JsFieldString("destroy", destroyStr, "destroyStr");
		}
		return null;
	}

	private JsField resolveRead() {
		if (readObj != null) {
			return new JsFieldObject("read", readObj, "readObj");
		} else if (isNotEmpty(readStr)) {
			return new JsFieldString("read", readStr, "readStr");
		}
		return null;
	}
	
	private JsField resolveFileUrl() {
		if (isNotEmpty(fileUrlFn)) {
			return new JsFieldVariable("fileUrl", fileUrlFn, "fileUrlFn");
		} else if (isNotEmpty(fileUrlStr)) {
			return new JsFieldString("fileUrl", fileUrlStr, "fileUrlStr");
		}
		return null;
	}

}
