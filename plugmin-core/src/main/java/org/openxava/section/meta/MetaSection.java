package org.openxava.section.meta;

import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class MetaSection {

	private String title;

	private String id;

	private String content;
	
	private String forEntity;
	
	private String forView;
	
	
	public MetaSection() {
	}
	
	public MetaSection(String title, String sectionId, String forEntity, String forView) throws JsonProcessingException {
		super();
		this.title = title;
		this.id = sectionId;
		this.forEntity = forEntity;
		this.forView = forView;
		this.content = "<div class=\"" + sectionId + "\"></div>";
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
		return "MetaSection [title=" + title + ", id=" + id + ", content="
				+ content + ", forEntity=" + forEntity + ", forView=" + forView
				+ "]";
	}

}
