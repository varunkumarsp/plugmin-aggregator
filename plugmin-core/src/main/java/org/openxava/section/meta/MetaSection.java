package org.openxava.section.meta;

import static org.openxava.annotations.extended.JsonUtil.toJson;

import org.openxava.annotations.extended.vo.TabConfigVo;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MetaSection {

	private String title;

	private String id;

	private String init;

	private String content;
	
	private String forEntity;
	
	private String forView;
	
	private TabConfigVo tabConfig;

	private String json;
	
	private String sectionId; //for internal use only


	
	public MetaSection() {
	}
	
	public MetaSection(String title, String id, String sectionId, String json, String forEntity, String forView) throws JsonProcessingException {
		super();
		this.title = title;
		this.id = id;
		this.sectionId = sectionId;
		this.json = json;
		this.forEntity = forEntity;
		this.forView = forView;
		
		this.content = "<div class=\"" + sectionId + "\"></div>";
		if(json != null)
			this.init = getInit();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getForEntity() {
		return forEntity;
	}

	public void setForEntity(String forEntity) {
		this.forEntity = forEntity;
	}

	public String getForView() {
		return forView;
	}

	public void setForView(String forView) {
		this.forView = forView;
	}

	@Override
	public String toString() {
		return "MetaSection [title=" + title + ", id=" + id + ", init=" + init
				+ ", content=" + content + ", forEntity=" + forEntity
				+ ", forView=" + forView + "]";
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

	
}
