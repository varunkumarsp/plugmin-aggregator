package org.openxava.annotations.extended.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.ColumnMenu;
import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(ColumnMenu.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class ColumnMenuVo {

	@DefaultValue("false")
	private Boolean isEnabled;
	
	@DefaultValue("true")
	private Boolean columns;
	
	@DefaultValue("true")
	private Boolean filterable;
	
	@DefaultValue("true")
	private Boolean sortable;

	private ColumnMenuVo() {
	}
	
	public static ColumnMenuVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new ColumnMenuVo();
	}
	
	public Boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(Boolean isEnabled) {
		if(isEnabled != null)
			this.isEnabled = isEnabled;
	}

	public Boolean isColumns() {
		return columns;
	}

	public void setColumns(Boolean columns) {
		if(columns != null)
			this.columns = columns;
	}

	public Boolean isFilterable() {
		return filterable;
	}

	public void setFilterable(Boolean filterable) {
		if(filterable != null)
			this.filterable = filterable;
	}

	public Boolean isSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		if(sortable != null)
			this.sortable = sortable;
	}

	public ColumnMenuVo copyFrom(ColumnMenu columnMenu) {
		setColumns(columnMenu.columns().getBool());
		setEnabled(columnMenu.isEnabled().getBool());
		setFilterable(columnMenu.filterable().getBool());
		setSortable(columnMenu.sortable().getBool());
		return this;
	}

}
