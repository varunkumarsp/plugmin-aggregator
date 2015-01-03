package org.openxava.annotations.extended.ui.config.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AnimationEffect {

	ZOOM_IN("zoom:in"),
	ZOOM_OUT("zoom:out"),
	NULL(null);

	private final String effect;

	private AnimationEffect(String effect) {
		this.effect = effect;
	}

	public String getEffect() {
		return effect;
	}
	
	public String getValue() {
		return effect;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.effect;
	}
}
