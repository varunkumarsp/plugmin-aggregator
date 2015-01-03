package org.openxava.annotations.extended.ui.config.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.Groupable;
import org.openxava.annotations.extended.ui.config.grid.GroupableConfig;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Groupable.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class GroupableVo {
	
	@DefaultValue("false")
	private Boolean groupable;
	
	private GroupableConfigVo config;

	
	private GroupableVo() {
	}
	
	public static GroupableVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new GroupableVo();
	}
	
	
	public Boolean isGroupable() {
		return groupable;
	}

	public void setGroupable(Boolean groupable) {
		if(groupable != null)
			this.groupable = groupable;
	}

	public GroupableConfigVo getConfig_() {
		if(config == null)
			try {
				config = GroupableConfigVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return config;
	}
	
	public GroupableConfigVo getConfig() {
		return config;
	}

	public void setConfig(GroupableConfigVo config) {
		if(config != null)
			this.config = config;
	}

	public GroupableVo copyFrom(Groupable groupable) {
		setGroupable(groupable.groupable().getBool());
		
		GroupableConfig config = groupable.config();
		Object defaultConfig = AnnotationUtils.getDefaultValue(Groupable.class, "config");
		if(!config.equals(defaultConfig)) {
			getConfig_().copyFrom(config);
		}
		return this;
	}

}
