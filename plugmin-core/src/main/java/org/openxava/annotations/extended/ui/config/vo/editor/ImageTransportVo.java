package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.editor.ImageTransport;
import org.openxava.annotations.extended.ui.config.vo.InstantiationRestrictedException;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldObject;
import org.openxava.annotations.parse.JsFieldString;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class ImageTransportVo implements JsFieldValueResolver {

	
	@CompositeField("read")
	private String readStr;
	
	@CompositeField("read")
	private TransportXVo readObj;
	
	@CompositeField("thumbnailUrl")
	private String thumbnailUrlStr;
	
	@CompositeField("thumbnailUrl")
	private String thumbnailUrlFn;
	
	private String uploadUrl;
	
	@CompositeField("imageUrl")
	private String imageUrlStr;
	
	@CompositeField("imageUrl")
	private String imageUrlFn;
	
	@CompositeField("destroy")
	private String destroyStr;
	
	@CompositeField("destroy")
	private TransportXVo destroyObj;
	
	@CompositeField("create")
	private String createStr;
	
	@CompositeField("create")
	private TransportXVo createObj;
	

	public static ImageTransportVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(ImageBrowserVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new ImageTransportVo();
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

	public String getThumbnailUrlStr() {
		return thumbnailUrlStr;
	}

	public void setThumbnailUrlStr(String thumbnailUrlStr) {
		if(isNotEmpty(thumbnailUrlStr))
			this.thumbnailUrlStr = thumbnailUrlStr;
	}

	public String getThumbnailUrlFn() {
		return thumbnailUrlFn;
	}

	public void setThumbnailUrlFn(String thumbnailUrlFn) {
		if(isNotEmpty(thumbnailUrlFn))
			this.thumbnailUrlFn = thumbnailUrlFn;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		if(isNotEmpty(uploadUrl))
			this.uploadUrl = uploadUrl;
	}

	public String getImageUrlStr() {
		return imageUrlStr;
	}

	public void setImageUrlStr(String imageUrlStr) {
		if(isNotEmpty(imageUrlStr))
			this.imageUrlStr = imageUrlStr;
	}

	public String getImageUrlFn() {
		return imageUrlFn;
	}

	public void setImageUrlFn(String imageUrlFn) {
		if(isNotEmpty(imageUrlFn))
			this.imageUrlFn = imageUrlFn;
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

	public void copyFrom(ImageTransport config) {
		getCreateObj_().copyFrom(config.createObj());
		setCreateStr(config.createStr());
		getDestroyObj_().copyFrom(config.destroyObj());
		setDestroyStr(config.destroyStr());
		setImageUrlFn(config.imageUrlFn());
		setImageUrlStr(config.imageUrlStr());
		getReadObj_().copyFrom(config.readObj());
		setReadStr(config.readStr());
		setThumbnailUrlFn(config.thumbnailUrlFn());
		setThumbnailUrlStr(config.thumbnailUrlStr());
		setUploadUrl(config.uploadUrl());
	}


	@Override
	public JsField resolve(String field) {
		if(field.equals("read")) {
			return resolveRead();
		} else if(field.equals("thumbnailUrl")) {
			return resolveThumbnailUrl();
		} else if(field.equals("imageUrl")) {
			return resolveImageUrl();
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
	
	private JsField resolveImageUrl() {
		if (isNotEmpty(imageUrlFn)) {
			return new JsFieldVariable("imageUrl", imageUrlFn, "imageUrlFn");
		} else if (isNotEmpty(imageUrlStr)) {
			return new JsFieldString("imageUrl", imageUrlStr, "imageUrlStr");
		}
		return null;
	}


	private JsField resolveThumbnailUrl() {
		if (isNotEmpty(thumbnailUrlFn)) {
			return new JsFieldVariable("thumbnailUrl", thumbnailUrlFn, "thumbnailUrlFn");
		} else if (isNotEmpty(imageUrlStr)) {
			return new JsFieldString("thumbnailUrl", thumbnailUrlStr, "thumbnailUrlStr");
		}
		return null;
	}

}
