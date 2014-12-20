package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.Sortable;
import org.openxava.annotations.extended.SortableConfig;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Sortable.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class SortableVo {
	
	@DefaultValue("false")
	private Boolean sortable;
	
	private SortableConfigVo config;

	
	private SortableVo() {
	}
	
	public static SortableVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new SortableVo();
	}
	
	public Boolean isSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		if(sortable != null)
			this.sortable = sortable;
	}

	public SortableConfigVo getConfig_() {
		if(config == null)
			try {
				config = SortableConfigVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return config;
	}
	
	public SortableConfigVo getConfig() {
		return config;
	}

	public void setConfig(SortableConfigVo config) {
		if(config != null)
			this.config = config;
	}

	public SortableVo copyFrom(Sortable sortable) {
		setSortable(sortable.sortable().getBool());
		
		SortableConfig config = sortable.config();
		Object defaultConfig = AnnotationUtils.getDefaultValue(Sortable.class, "config");
		if(!config.equals(defaultConfig)) {
			getConfig_().copyFrom(config);
		}
		return this;
	}

}
