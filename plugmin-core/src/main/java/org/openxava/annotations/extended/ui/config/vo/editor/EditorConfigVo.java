package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.editor.EditorConfig;
import org.openxava.annotations.extended.ui.config.editor.Tool;
import org.openxava.annotations.extended.ui.config.vo.EventVo;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldArray;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class EditorConfigVo implements JsFieldValueResolver,
		JsonKeyValuesProviderI, Serializable {

	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	private String domain;
	
	@DefaultValue("true")
	private String encoded;
	
	private SerializationVo serialization;
	
	private String[] stylesheets;
	
	@CompositeField("tools")
	private String[] toolsStrArray;
	
	@CompositeField("tools")
	private List<ToolVo> toolsObjArray;
	
	private ImageBrowserVo imageBrowser;
	
	private FileBrowserVo fileBrowser;
	
	@JsonRawKeyValueProvider
	private List<EventVo> events;


	public EditorConfigVo(EditorConfig editorConfig) {
		this.copyFrom(editorConfig);
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		if(isNotEmpty(domain))
			this.domain = domain;
	}

	public String getEncoded() {
		return encoded;
	}

	public void setEncoded(String encoded) {
		if(isNotEmpty(encoded))
			this.encoded = encoded;
	}

	public SerializationVo getSerialization_() {
		if(serialization == null)
			try {
				serialization = SerializationVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return serialization;
	}
	
	public SerializationVo getSerialization() {
		return serialization;
	}

	public void setSerialization(SerializationVo serialization) {
		if(serialization != null)
			this.serialization = serialization;
	}

	public String[] getStylesheets() {
		return stylesheets;
	}

	public void setStylesheets(String[] stylesheets) {
		if(stylesheets != null && stylesheets.length != 0)
			this.stylesheets = stylesheets;
	}

	public String[] getToolsStrArray() {
		return toolsStrArray;
	}

	public void setToolsStrArray(String[] toolsStrArray) {
		if(toolsStrArray != null && toolsStrArray.length != 0)
			this.toolsStrArray = toolsStrArray;
	}

	public List<ToolVo> getToolsObjArray() {
		return toolsObjArray;
	}

	public void setToolsObjArray(List<ToolVo> toolsObjArray) {
		if(CollectionUtils.isNotEmpty(toolsObjArray))
			this.toolsObjArray = toolsObjArray;
	}

	public ImageBrowserVo getImageBrowser_() {
		if(imageBrowser == null)
			try {
				imageBrowser = ImageBrowserVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return imageBrowser;
	}
	
	public ImageBrowserVo getImageBrowser() {
		return imageBrowser;
	}

	public void setImageBrowser(ImageBrowserVo imageBrowser) {
		if(imageBrowser != null)
			this.imageBrowser = imageBrowser;
	}

	public FileBrowserVo getFileBrowser_() {
		if(fileBrowser == null)
			try {
				fileBrowser = FileBrowserVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return fileBrowser;
	}
	
	public FileBrowserVo getFileBrowser() {
		return fileBrowser;
	}

	public void setFileBrowser(FileBrowserVo fileBrowser) {
		if(fileBrowser != null)
			this.fileBrowser = fileBrowser;
	}

	public List<EventVo> getEvents() {
		return events;
	}

	public void setEvents(List<EventVo> events) {
		if(CollectionUtils.isNotEmpty(events))
			this.events = events;
	}

	public void copyFrom(EditorConfig config) {
		setDomain(config.domain());
		setEncoded(config.encoded());
		getFileBrowser_().copyFrom(config.fileBrowser());
		getImageBrowser_().copyFrom(config.imageBrowser());
		getSerialization_().copyFrom(config.serialization());
		setStylesheets(config.stylesheets());
		
		Tool[] toolsObjArray = config.toolsObjArray();
		List<ToolVo> toolsObjArray_ = new ArrayList<ToolVo>();
		for (Tool tool : toolsObjArray) {
			toolsObjArray_.add(new ToolVo().copyFrom(tool));
		}
		
		setToolsObjArray(toolsObjArray_);
		setToolsStrArray(config.toolsStrArray());
	}

	@Override
	public JsField resolve(String field) {
		if(field.equals("tools")) {
			return resolveTools();
		}
		return null;
	}

	private JsField resolveTools() {
		if (CollectionUtils.isNotEmpty(toolsObjArray)) {
			return new JsFieldArray("tools", toolsObjArray.toArray(), "toolsObjArray");
		} else if(ArrayUtils.isNotEmpty(toolsStrArray)) {
			return new JsFieldArray("tools", toolsStrArray, "toolsStrArray");
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
