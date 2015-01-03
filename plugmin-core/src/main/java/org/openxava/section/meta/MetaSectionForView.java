package org.openxava.section.meta;

import java.util.List;

import org.openxava.annotations.extended.ui.config.vo.AngularScope;
import org.openxava.view.meta.MetaView;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MetaSectionForView extends MetaSection {


	private String angularView;
	
	private List<AngularScope> angularScopes;

	private MetaView metaView;
	

	public MetaSectionForView(String title, MetaView metaView) throws JsonProcessingException {
		super();
		setTitle(title);
		this.metaView = metaView;
		angularView = metaView.getAngularView().outerHtml();
		angularScopes = metaView.getAngularScopes();
		
	}

	public String getAngularView() {
		return angularView;
	}

	public void setAngularView(String angularView) {
		this.angularView = angularView;
	}

	public List<AngularScope> getAngularScopes() {
		return angularScopes;
	}

	public void setAngularScopes(List<AngularScope> angularScopes) {
		this.angularScopes = angularScopes;
	}

	public MetaView getMetaView() {
		return metaView;
	}

	public void setMetaView(MetaView metaView) {
		this.metaView = metaView;
	}

}
