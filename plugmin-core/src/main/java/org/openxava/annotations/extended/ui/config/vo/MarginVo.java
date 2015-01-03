package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.Margin;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Margin.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class MarginVo {
	
	@DefaultValue("0")
	private String left;
	
	@DefaultValue("0")
	private String right;
	
	@DefaultValue("0")
	private String top;
	
	@DefaultValue("0")
	private String bottom;
	
	
	private MarginVo() {
	}
	
	public static MarginVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(PdfVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new MarginVo();
	}
	

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		if(isNotEmpty(left))
			this.left = left;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		if(isNotEmpty(right))
			this.right = right;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		if(isNotEmpty(top))
			this.top = top;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		if(isNotEmpty(bottom))
			this.bottom = bottom;
	}

	
	public MarginVo copyFrom(Margin margin) {
		setBottom(margin.bottom());
		setLeft(margin.left());
		setRight(margin.right());
		setTop(margin.top());
		return this;
	}

}
