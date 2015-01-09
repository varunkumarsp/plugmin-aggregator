package org.openxava.section.meta;

import org.openxava.view.meta.MetaView;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ViewSection extends MetaSection {


	private MetaView metaView;
	

	public ViewSection(String title, MetaView metaView) throws JsonProcessingException {
		super();
		setTitle(title);
		this.metaView = metaView;
		
	}

	public MetaView getMetaView() {
		return metaView;
	}

	public void setMetaView(MetaView metaView) {
		this.metaView = metaView;
	}

}
