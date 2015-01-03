package org.openxava.annotations.extended.ui.config.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.GroupableConfig;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(GroupableConfig.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class GroupableConfigVo {
	
	@DefaultValue("true")
	private Boolean enabled;
	
	@DefaultValue("false")
	private Boolean showFooter;
	
	
	private GroupableConfigVo() {
	}
	
	public static GroupableConfigVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(GroupableVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new GroupableConfigVo();
	}
	

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		if(enabled != null)
			this.enabled = enabled;
	}

	public Boolean isShowFooter() {
		return showFooter;
	}

	public void setShowFooter(Boolean showFooter) {
		if(showFooter != null)
			this.showFooter = showFooter;
	}

	public GroupableConfigVo copyFrom(GroupableConfig config) {
		setEnabled(config.enabled().getBool());
		setShowFooter(config.showFooter().getBool());
		return this;
	}

}
