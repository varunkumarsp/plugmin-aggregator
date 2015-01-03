package org.openxava.annotations.extended.ui.config.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.Open;
import org.openxava.annotations.extended.ui.config.enums.AnimationEffect;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class OpenVo {

	private AnimationEffect effects;
	
	private Integer duration;

	public static OpenVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(AnimationConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new OpenVo();
	}

	public AnimationEffect getEffects() {
		return effects;
	}

	public void setEffects(AnimationEffect effects) {
		if(effects != null && effects.getEffect() != null)
			this.effects = effects;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		if(duration != null && duration != -1)
			this.duration = duration;
	}
	
	public void copyFrom(Open config) {
		setDuration(config.duration());
		setEffects(config.effects());
	}
	
}
