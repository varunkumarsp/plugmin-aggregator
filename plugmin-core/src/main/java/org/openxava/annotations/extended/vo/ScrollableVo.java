package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.Scrollable;
import org.openxava.annotations.extended.ScrollableConfig;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Scrollable.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class ScrollableVo {
	
	@DefaultValue("true")
	private Boolean scrollable;
	
	private ScrollableConfigVo config;

	
	private ScrollableVo() {
	}
	
	public static ScrollableVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new ScrollableVo();
	}
	
	public Boolean isScrollable() {
		return scrollable;
	}

	public void setScrollable(Boolean scrollable) {
		if(scrollable != null)
			this.scrollable = scrollable;
	}

	public ScrollableConfigVo getConfig_() {
		if(config == null)
			try {
				config = ScrollableConfigVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return config;
	}
	
	public ScrollableConfigVo getConfig() {
		return config;
	}

	public void setConfig(ScrollableConfigVo config) {
		if(config != null)
			this.config = config;
	}

	
	public ScrollableVo copyFrom(Scrollable scrollable) {
		setScrollable(scrollable.scrollable().getBool());
		
		ScrollableConfig config = scrollable.config();
		Object defaultConfig = AnnotationUtils.getDefaultValue(Scrollable.class, "config");
		if(!config.equals(defaultConfig)) {
			getConfig_().copyFrom(config);
		}
		return this;
	}

}
