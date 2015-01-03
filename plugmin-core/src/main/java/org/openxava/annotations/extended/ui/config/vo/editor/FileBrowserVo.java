package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.ui.config.editor.FileBrowser;
import org.openxava.annotations.extended.ui.config.vo.InstantiationRestrictedException;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class FileBrowserVo {

	String fileTypes;
	
	String path;
	
	FileTransportVo transport;
	
	SchemaVo schema;

	public static FileBrowserVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(EditorConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new FileBrowserVo();
	}
	
	public void copyFrom(FileBrowser config) {
		// TODO Auto-generated method stub
		
	}

}
