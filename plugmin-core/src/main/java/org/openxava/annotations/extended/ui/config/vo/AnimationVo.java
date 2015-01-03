package org.openxava.annotations.extended.ui.config.vo;

import org.openxava.annotations.extended.Animation;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class AnimationVo {

	@DefaultValue("true")
	private Boolean animation;

	private AnimationConfigVo config;

	
	public static AnimationVo instance() {
//		if(!calledFrom(TabConfigVo.class)) {
//			throw new InstantiationRestrictedException("No permission to instantiate");
//		}
		return new AnimationVo();
	}

	public Boolean isAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		if(animation != null)
			this.animation = animation;
	}

	public AnimationConfigVo getConfig_() {
		if(config == null)
			try {
				config = AnimationConfigVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return config;
	}
	
	public AnimationConfigVo getConfig() {
		return config;
	}

	public void setConfig(AnimationConfigVo config) {
		if(config != null)
			this.config = config;
	}

	public AnimationVo copyFrom(Animation animation) {
		setAnimation(animation.animation().getBool());
		getConfig_().copyFrom(animation.config());
		return this;
	}
	
}
