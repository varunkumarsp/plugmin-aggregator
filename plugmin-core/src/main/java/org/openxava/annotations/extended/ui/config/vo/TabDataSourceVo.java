package org.openxava.annotations.extended.ui.config.vo;

public class TabDataSourceVo {

	private String dataSource = "";
	private String readUrl = ""; 
	private String createUrl = ""; 
	private String updateUrl = ""; 
	private String deleteUrl = "";
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getReadUrl() {
		return readUrl;
	}
	public void setReadUrl(String readUrl) {
		this.readUrl = readUrl;
	}
	public String getCreateUrl() {
		return createUrl;
	}
	public void setCreateUrl(String createUrl) {
		this.createUrl = createUrl;
	}
	public String getUpdateUrl() {
		return updateUrl;
	}
	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}
	public String getDeleteUrl() {
		return deleteUrl;
	}
	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}
	
	
}
