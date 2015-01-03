package org.openxava.section.meta;

import static org.openxava.annotations.parse.JsonUtil.toJson;
import org.openxava.annotations.extended.ui.config.vo.TabConfigVo;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MetaSectionForGrid extends MetaSection {


	private String init;

	private TabConfigVo tabConfig;

	private String json;
	
	private String sectionId; //for internal use only


	
	public MetaSectionForGrid() {
	}
	
	public MetaSectionForGrid(String title, String id, String sectionId, String json, String forEntity, String forView) throws JsonProcessingException {
		super(title, sectionId, forEntity, forView);
		this.sectionId = sectionId;
		this.json = json;
		if(json != null)
			this.init = getInit();
	}

	public String getInit() throws JsonProcessingException {
		if(init == null) {
			if(json == null)
				json = toJson(tabConfig);
			init = "detailRow.find('." + sectionId + "').kendoGrid(" + json + ");";
		}
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public TabConfigVo getTabConfig() {
		return tabConfig;
	}

	public void setTabConfig(TabConfigVo tabConfig) {
		this.tabConfig = tabConfig;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public String toString() {
		return "MetaSectionForGrid [init=" + init + ", tabConfig=" + tabConfig
				+ ", json=" + json + ", sectionId=" + sectionId + "]";
	}

}
