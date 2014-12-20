package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.Window;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Window.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class WindowVo {
	
	private String title;
	
	@DefaultValue("true")
	private Boolean animation;
	
	private String open;

	
	private WindowVo() {
	}
	
	public static WindowVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(EditableConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new WindowVo();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(isNotEmpty(title))
			this.title = title;
	}

	public Boolean isAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		if(animation != null)
			this.animation = animation;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		if(isNotEmpty(open))
			this.open = open;
	}

	public WindowVo copyFrom(Window window) {
		setAnimation(window.animation().getBool());
		setOpen(window.open());
		setTitle(window.title());
		return this;
	}

}
