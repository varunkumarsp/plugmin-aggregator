package org.openxava.annotations.extended.ui.config.vo;

import static org.openxava.annotations.extended.ui.config.enums.Operator.CONTAINS;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.FilterableColumn;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldString;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(FilterableColumn.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class FilterableColumnVo implements JsFieldValueResolver {

	private CellVo cell;
	
	@CompositeField("ui")
	private String uiStr;
	
	@CompositeField("ui")
	private String uiFn;

	@JsonIgnore
	private ColumnVo column; //for internal use
	
	
	private FilterableColumnVo() {
	}
	
	private FilterableColumnVo(ColumnVo column) {
		this.column = column;
		if(!column.isCommandColumn()) {
			init(column.getMetaField());
		}
	}
	
	public static FilterableColumnVo instance(ColumnVo column) throws InstantiationRestrictedException {
		if(!calledFrom(ColumnVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new FilterableColumnVo(column);
	}
	

	private void init(Field metaField) {
		Class<?> type = metaField.getType();
		if(type.equals(java.lang.Integer.class)) {
			
		} else if(type.equals(java.lang.Boolean.class)) {
			
		} else if(type.equals(java.lang.Long.class)) {
			
		} else if(type.equals(java.lang.Double.class)) {
			
		} else if(type.equals(java.lang.Float.class)) {
			
		} else if(type.equals(String.class)) {
			CellVo cell = getCell_();
			cell.setSuggestionOperator(CONTAINS);
			cell.setOperator(CONTAINS);
			cell.setMinLength(2);
		} else if(type.equals(Calendar.class)) {
			getCell_().setTemplate("dateTimeEditorForFilterSuggestion");
		} else if(type.equals(Date.class)) {
			getCell_().setTemplate("dateTimeEditorForFilterSuggestion");
		}
	}


	public CellVo getCell_() {
		if(cell == null)
			try {
				cell = CellVo.instance(column);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return cell;
	}
	
	public CellVo getCell() {
		return cell;
	}

	public void setCell(CellVo cell) {
		if(cell != null)
			this.cell = cell;
	}

	public String getUiStr() {
		return uiStr;
	}

	public void setUiStr(String uiStr) {
		if(isNotEmpty(uiStr))
			this.uiStr = uiStr;
	}

	public String getUiFn() {
		return uiFn;
	}

	public void setUiFn(String uiFn) {
		if(isNotEmpty(uiFn))
			this.uiFn = uiFn;
	}

	public FilterableColumnVo copyFrom(FilterableColumn filterable) {
		if(!column.isCommandColumn()) {
			getCell_().copyFrom(filterable.cell());
			setUiFn(filterable.uiFn());
			setUiStr(filterable.uiStr());
			return this;
		}
		return null;
	}

	@Override
	public JsField resolve(String field) {
		if(field.equals("ui")) {
			return resolveUi();
		} else if(field.equals("some other")) {
			return resolveSomeOther();
		}
		
		return null;
	}

	private JsField resolveUi() {
		if(StringUtils.isNotEmpty(uiFn))
			return new JsFieldVariable("ui", uiFn, "uiFn");
		else
			return new JsFieldString("ui", uiStr, "uiStr");
	}

	private JsField resolveSomeOther() {
		// TODO Auto-generated method stub
		return null;
	}

	public FilterableColumnVo resetCell() {
		this.cell = null;
		return this;
	}

}
