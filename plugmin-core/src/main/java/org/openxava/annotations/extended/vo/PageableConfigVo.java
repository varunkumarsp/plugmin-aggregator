package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.PageableConfig;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(PageableConfig.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class PageableConfigVo {
	
	private Integer pageSize;
	
	@DefaultValue("true")
	private Boolean previousNext;
	
	@DefaultValue("true")
	private Boolean numeric;
	
	private Integer buttonCount;
	
	@DefaultValue("false")
	private Boolean input;
	
	@DefaultValue("false")
	private Boolean pageSizesBool;
	
	private int[] pageSizesArray;
	
	@DefaultValue("false")
	private Boolean refresh;
	
	@DefaultValue("true")
	private Boolean info;
	
	
	private PageableConfigVo() {
	}
	
	public static PageableConfigVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(PageableVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new PageableConfigVo();
	}

	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize != null)
			this.pageSize = pageSize;
	}

	public Boolean isPreviousNext() {
		return previousNext;
	}

	public void setPreviousNext(Boolean previousNext) {
		if(previousNext != null && previousNext!= true)
			this.previousNext = previousNext;
	}

	public Boolean isNumeric() {
		return numeric;
	}

	public void setNumeric(Boolean numeric) {
		if(numeric != null && numeric!= true)
			this.numeric = numeric;
	}

	public Integer getButtonCount() {
		return buttonCount;
	}

	public void setButtonCount(Integer buttonCount) {
		if(buttonCount != null)
			this.buttonCount = buttonCount;
	}

	public Boolean isInput() {
		return input;
	}

	public void setInput(Boolean input) {
		if(input != null && input!= false)
			this.input = input;
	}

	public Boolean isPageSizesBool() {
		return pageSizesBool;
	}

	public void setPageSizesBool(Boolean pageSizesBool) {
		if(pageSizesBool != null && pageSizesBool!= false)
			this.pageSizesBool = pageSizesBool;
	}

	public int[] getPageSizesArray() {
		return pageSizesArray;
	}

	public void setPageSizesArray(int[] pageSizesArray) {
		if(pageSizesArray != null)
			this.pageSizesArray = pageSizesArray;
	}

	public Boolean isRefresh() {
		return refresh;
	}

	public void setRefresh(Boolean refresh) {
		if(refresh != null && refresh!= false)
			this.refresh = refresh;
	}

	public Boolean isInfo() {
		return info;
	}

	public void setInfo(Boolean info) {
		if(info != null && info!= true)
			this.info = info;
	}
	

	public PageableConfigVo copyFrom(PageableConfig config) {
		setButtonCount(config.buttonCount());
		setInfo(config.info().getBool());
		setInput(config.input().getBool());
		setNumeric(config.numeric().getBool());
		setPageSize(config.pageSize());
		setPageSizesArray(config.pageSizesArray());
		setPageSizesBool(config.pageSizesBool().getBool());
		setPreviousNext(config.previousNext().getBool());
		setRefresh(config.refresh().getBool());
		return this;
	}

}
