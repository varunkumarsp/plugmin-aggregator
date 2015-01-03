package org.openxava.annotations.extended.ui.config.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.AnimationConfig;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class AnimationConfigVo {

	private CloseVo close;
	
	private OpenVo open;

	public static AnimationConfigVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(AnimationVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new AnimationConfigVo();
	}

	public CloseVo getClose_() {
		if(close == null)
			try {
				close = CloseVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return close;
	}
	
	public CloseVo getClose() {
		return close;
	}

	public void setClose(CloseVo close) {
		if(close != null)
			this.close = close;
	}

	public OpenVo getOpen_() {
		if(open == null)
			try {
				open = OpenVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return open;
	}
	
	public OpenVo getOpen() {
		return open;
	}

	public void setOpen(OpenVo open) {
		if(open != null)
			this.open = open;
	}

	public void copyFrom(AnimationConfig config) {
		getOpen_().copyFrom(config.open());
		getClose_().copyFrom(config.close());
	}
	
}
