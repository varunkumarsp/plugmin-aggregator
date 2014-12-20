package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.Excel;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Excel.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class ExcelVo {
	
	@DefaultValue("false")
	private Boolean allPages;
	
	@DefaultValue("Export.xslx")
	private String fileName;
	
	@DefaultValue("false")
	private Boolean filterable;
	
	private String proxyURL;

	private ExcelVo() {
	}
	
	public static ExcelVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new ExcelVo();
	}
	
	public Boolean isAllPages() {
		return allPages;
	}

	public void setAllPages(Boolean allPages) {
		if(allPages != null)
			this.allPages = allPages;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if(isNotEmpty(fileName) && !fileName.equals("Export.xslx"))
			this.fileName = fileName;
	}

	public Boolean isFilterable() {
		return filterable;
	}

	public void setFilterable(Boolean filterable) {
		if(filterable != null)
			this.filterable = filterable;
	}

	public String getProxyURL() {
		return proxyURL;
	}

	public void setProxyURL(String proxyURL) {
		if(isNotEmpty(proxyURL))
			this.proxyURL = proxyURL;
	}

	public ExcelVo copyFrom(Excel excel) {
		setAllPages(excel.allPages().getBool());
		setFileName(excel.fileName());
		setFilterable(excel.filterable().getBool());
		setProxyURL(excel.proxyURL());
		return this;
	}

}
