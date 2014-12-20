package org.openxava.annotations.parse;

import static org.openxava.annotations.parse.FieldDefaultValueResolver.isNotDefault;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.Selectable;
import org.openxava.annotations.extended.vo.ColumnMenuVo;
import org.openxava.annotations.extended.vo.EditableVo;
import org.openxava.annotations.extended.vo.ExcelVo;
import org.openxava.annotations.extended.vo.FilterableVo;
import org.openxava.annotations.extended.vo.GroupableVo;
import org.openxava.annotations.extended.vo.PageableVo;
import org.openxava.annotations.extended.vo.PdfVo;
import org.openxava.annotations.extended.vo.ScrollableVo;
import org.openxava.annotations.extended.vo.SortableVo;
import org.openxava.annotations.extended.vo.TabConfigVo;
import org.openxava.annotations.extended.vo.ToolbarVo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class TabConfigSerializer extends JsonSerializer<TabConfigVo> {

	@Override
	public void serialize(TabConfigVo tabConfig, JsonGenerator gen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		try {
			gen.writeStartObject();
			
			serializeAltRowTemplate(gen, tabConfig);
			serializeColumnMenu(gen, tabConfig);
			serializeColumnResizeHandleWidth(gen, tabConfig);
			serializeDetailTemplate(gen, tabConfig);
			serializeEditable(gen, tabConfig);
			serializeExcel(gen, tabConfig);
			serializeFilterable(gen, tabConfig);
			serializeGroupable(gen, tabConfig);
			serializeHeight(gen, tabConfig);
			serializeMobile(gen, tabConfig);
			serializePageable(gen, tabConfig);
			serializePdf(gen, tabConfig);
			serializeRowTemplate(gen, tabConfig);
			serializeScrollable(gen, tabConfig);
			serializeSelectable(gen, tabConfig);
			serializeSortable(gen, tabConfig);
			serializeToolbar(gen, tabConfig);
			
			gen.writeObjectField("dataSource", tabConfig.getDataSource());
			gen.writeObjectField("columns", tabConfig.getColumns());
			
			gen.writeEndObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void serializeToolbar(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		List<ToolbarVo> toolbarArray = tabConfig.getToolbarArray();
		String toolbarFn = tabConfig.getToolbarFn();
		String toolbarStr = tabConfig.getToolbarStr();
		
		if(StringUtils.isNotEmpty(toolbarFn)) {
			gen.writeFieldName("toolbar");
			gen.writeRawValue(toolbarFn);
		} else if(CollectionUtils.isNotEmpty(toolbarArray)) {
			gen.writeObjectField("toolbar", toolbarArray);
		} else if(isNotDefault(toolbarStr, "toolbarStr", TabConfigVo.class)) {
			gen.writeStringField("toolbar", toolbarStr);
		}
	}

	private void serializeSortable(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		SortableVo sortable = tabConfig.getSortable();
		if(sortable != null ) {
			boolean sortable2 = sortable.isSortable();
			if(isNotDefault(sortable2, "sortable", SortableVo.class))
				gen.writeObjectField("sortable", sortable.getConfig());
		}
	}

	private void serializeSelectable(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		Selectable selectable = tabConfig.getSelectableStr();
		if(selectable != null) {
			String selectableStr = selectable.toString();
			if(isNotDefault(selectableStr, "selectableStr", TabConfigVo.class))
				gen.writeStringField("selectableStr", selectableStr);
		}
	}

	private void serializeScrollable(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		ScrollableVo scrollable = tabConfig.getScrollable();
		if(scrollable != null ) {
			boolean scrollable2 = scrollable.isScrollable();
			if(isNotDefault(scrollable2, "scrollable", ScrollableVo.class))
				gen.writeObjectField("scrollable", scrollable.getConfig());
		}
	}

	private void serializeRowTemplate(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		String rowTemplateFn = tabConfig.getRowTemplateFn();
		String rowTemplateStr = tabConfig.getRowTemplateStr();
		
		if(StringUtils.isNotEmpty(rowTemplateFn)) {
			gen.writeFieldName("rowTemplate");
			gen.writeRawValue(rowTemplateFn);
		} else if(StringUtils.isNotEmpty(rowTemplateStr)) {
			gen.writeStringField("rowTemplate", rowTemplateStr);
		}
	}

	private void serializePdf(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		PdfVo pdf = tabConfig.getPdf();
		if(pdf != null)
			gen.writeObjectField("pdf", pdf);
	}

	private void serializePageable(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		PageableVo pageable = tabConfig.getPageable();
		if(pageable != null ) {
			boolean pageable2 = pageable.isPageable();
			if(isNotDefault(pageable2, "pageable", PageableVo.class))
				gen.writeObjectField("pageable", pageable.getConfig());
		}
	}

	private void serializeMobile(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		String mobileStr = tabConfig.getMobileStr();
		if(isNotDefault(mobileStr, "mobileStr", TabConfigVo.class))
			gen.writeStringField("mobileStr", mobileStr);
	}

	private void serializeHeight(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		String height = tabConfig.getHeight();
		if(isNotDefault(height, "height", TabConfigVo.class))
			gen.writeStringField("height", height);
	}

	private void serializeGroupable(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		GroupableVo groupable = tabConfig.getGroupable();
		if(groupable != null ) {
			boolean groupable2 = groupable.isGroupable();
			if(isNotDefault(groupable2, "groupable", GroupableVo.class))
				gen.writeObjectField("groupable", groupable.getConfig());
		}
	}

	private void serializeFilterable(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		FilterableVo filterable = tabConfig.getFilterable();
		if(filterable != null ) {
			boolean filterable2 = filterable.isFilterable();
			if(isNotDefault(filterable2, "filterable", FilterableVo.class))
				gen.writeObjectField("filterable", filterable.getConfig());
		}
	}

	private void serializeExcel(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		ExcelVo excel = tabConfig.getExcel();
		if(excel != null)
			gen.writeObjectField("excel", excel);
	}

	private void serializeEditable(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		EditableVo editable = tabConfig.getEditable();
		if(editable != null ) {
			boolean editable2 = editable.isEditable();
			if(isNotDefault(editable2, "editable", EditableVo.class))
				gen.writeObjectField("editable", editable.getConfig());
		}
	}

	private void serializeColumnResizeHandleWidth(JsonGenerator gen,
			TabConfigVo tabConfig) throws Exception {
		Integer columnResizeHandleWidth = tabConfig.getColumnResizeHandleWidth();
		if(isNotDefault(columnResizeHandleWidth, "columnResizeHandleWidth", TabConfigVo.class))
			gen.writeNumberField("columnResizeHandleWidth", columnResizeHandleWidth);
	}

	private void serializeColumnMenu(JsonGenerator gen, TabConfigVo tabConfig) throws Exception {
		ColumnMenuVo columnMenu = tabConfig.getColumnMenu();
		if(columnMenu != null) {
			boolean enabled = columnMenu.isEnabled();
			if(isNotDefault(enabled, "isEnabled", ColumnMenuVo.class)) {
				gen.writeObjectField("columnMenu", columnMenu);
			}
		}
	}

	private void serializeAltRowTemplate(JsonGenerator gen,
			TabConfigVo tabConfig) throws Exception {
		String altRowTemplateStr = tabConfig.getAltRowTemplateStr();
		String altRowTemplateFn = tabConfig.getAltRowTemplateFn();
		if(StringUtils.isNotEmpty(altRowTemplateFn)) {
			gen.writeFieldName("altRowTemplate");
			gen.writeRawValue(altRowTemplateFn);
		} if(isNotDefault(altRowTemplateStr, "altRowTemplate", TabConfigVo.class))
			gen.writeStringField("altRowTemplate", altRowTemplateStr);
	}

	private void serializeDetailTemplate(JsonGenerator gen,
			TabConfigVo tabConfig) throws Exception {
		String detailTemplateFn = tabConfig.getDetailTemplateFn();
		String detailTemplateStr = tabConfig.getDetailTemplateStr();
		
		if(StringUtils.isNotEmpty(detailTemplateFn)) {
			gen.writeFieldName("detailTemplate");
			gen.writeRawValue(detailTemplateFn);
		} else if(StringUtils.isNotEmpty(detailTemplateStr)) {
			gen.writeStringField("detailTemplate", detailTemplateStr);
		}
	}

}
