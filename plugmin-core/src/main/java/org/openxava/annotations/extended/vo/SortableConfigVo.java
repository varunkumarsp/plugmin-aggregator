package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.SortableConfig;
import org.openxava.annotations.extended.SortableMode;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(SortableConfig.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class SortableConfigVo {
	
	@DefaultValue("true")
	private Boolean allowUnsort;
	
	@DefaultValue("single")
	private SortableMode mode;
	
	
	private SortableConfigVo() {
	}
	
	public static SortableConfigVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(SortableVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new SortableConfigVo();
	}
	

	public Boolean isAllowUnsort() {
		return allowUnsort;
	}

	public void setAllowUnsort(Boolean allowUnsort) {
		if(allowUnsort != null)
			this.allowUnsort = allowUnsort;
	}

	public SortableMode getMode() {
		return mode;
	}

	public void setMode(SortableMode mode) {
		if(mode != null && mode.getValue() != null)
			this.mode = mode;
	}

	public SortableConfigVo copyFrom(SortableConfig config) {
		setAllowUnsort(config.allowUnsort().getBool());
		setMode(config.mode());
		return this;
	}

}
