package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.ScrollableConfig;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(ScrollableConfig.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class ScrollableConfigVo {

	@DefaultValue("false")
	private Boolean virtual;

	
	private ScrollableConfigVo() {
	}
	
	public static ScrollableConfigVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(ScrollableVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new ScrollableConfigVo();
	}
	
	
	public Boolean isVirtual() {
		return virtual;
	}

	public void setVirtual(Boolean virtual) {
		if(virtual != null)
			this.virtual = virtual;
	}

	public ScrollableConfigVo copyFrom(ScrollableConfig config) {
		setVirtual(config.virtual().getBool());
		return this;
	}
}
