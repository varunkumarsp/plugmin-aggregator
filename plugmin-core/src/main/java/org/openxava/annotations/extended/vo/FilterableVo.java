package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.Filterable;
import org.openxava.annotations.extended.FilterableConfig;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Filterable.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class FilterableVo {
	
	@DefaultValue("false")
	private Boolean filterable;
	
	private FilterableConfigVo config;

	
	private FilterableVo() {
	}
	
	public static FilterableVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new FilterableVo();
	}
	
	
	
	public Boolean isFilterable() {
		return filterable;
	}

	public void setFilterable(Boolean filterable) {
		if(filterable != null)
			this.filterable = filterable;
	}

	public FilterableConfigVo getConfig_() {
		if(config == null)
			try {
				config = FilterableConfigVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return config;
	}
	
	public FilterableConfigVo getConfig() {
		return config;
	}

	public void setConfig(FilterableConfigVo config) {
		if(config != null)
			this.config = config;
	}

	public FilterableVo copyFrom(Filterable filterable) {
		setFilterable(filterable.filterable().getBool());
		
		FilterableConfig config = filterable.config();
		Object defaultConfig = AnnotationUtils.getDefaultValue(Filterable.class, "config");
		if(!config.equals(defaultConfig)) {
			getConfig_().copyFrom(config);
		}
		return this;
	}

}
