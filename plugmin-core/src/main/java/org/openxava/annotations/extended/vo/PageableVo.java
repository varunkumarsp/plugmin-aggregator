package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.Pageable;
import org.openxava.annotations.extended.PageableConfig;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Pageable.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class PageableVo {
	
	@DefaultValue("false")
	private Boolean pageable;
	
	private PageableConfigVo config;

	
	private PageableVo() {
	}
	
	public static PageableVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new PageableVo();
	}
	
	public Boolean isPageable() {
		return pageable;
	}

	public void setPageable(Boolean pageable) {
		if(pageable != null)
			this.pageable = pageable;
	}

	public PageableConfigVo getConfig_() {
		if(config == null)
			try {
				config = PageableConfigVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return config;
	}
	
	public PageableConfigVo getConfig() {
		return config;
	}

	public void setConfig(PageableConfigVo config) {
		if(config != null)
			this.config = config;
	}
	

	public PageableVo copyFrom(Pageable pageable) {
		setPageable(pageable.pageable().getBool());
		
		PageableConfig config = pageable.config();
		Object defaultConfig = AnnotationUtils.getDefaultValue(Pageable.class, "config");
		if(!config.equals(defaultConfig)) {
			getConfig_().copyFrom(config);
		}
		return this;
	}

}
