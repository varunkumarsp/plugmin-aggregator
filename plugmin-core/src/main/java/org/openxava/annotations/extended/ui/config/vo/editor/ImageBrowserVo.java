package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.ui.config.editor.ImageBrowser;
import org.openxava.annotations.extended.ui.config.vo.InstantiationRestrictedException;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class ImageBrowserVo {

	@DefaultValue(".png,.gif,.jpg,.jpeg")
	private String fileTypes;
	
	@DefaultValue("/")
	private String path;
	
	private ImageTransportVo transport;
	
	private SchemaVo schema;

	
	public static ImageBrowserVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(EditorConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new ImageBrowserVo();
	}
	
	
	public String getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String fileTypes) {
		if(isNotEmpty(fileTypes))
			this.fileTypes = fileTypes;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if(isNotEmpty(path))
			this.path = path;
	}

	public ImageTransportVo getTransport_() {
		if(transport == null)
			try {
				transport = ImageTransportVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return transport;
	}
	
	public ImageTransportVo getTransport() {
		return transport;
	}

	public void setTransport(ImageTransportVo transport) {
		if(transport != null)
			this.transport = transport;
	}

	public SchemaVo getSchema_() {
		if(schema == null)
			try {
				schema = SchemaVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return schema;
	}
	
	public SchemaVo getSchema() {
		return schema;
	}

	public void setSchema(SchemaVo schema) {
		if(schema != null)
			this.schema = schema;
	}

	public void copyFrom(ImageBrowser config) {
		setFileTypes(config.fileTypes());
		setPath(config.path());
		getSchema_().copyFrom(config.schema());
		getTransport_().copyFrom(config.transport());
	}

}
