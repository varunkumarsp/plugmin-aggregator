package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.Event;
import org.openxava.annotations.extended.Mandatory;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.TabConfig;
import org.openxava.annotations.extended.ui.config.enums.SelectableMode;
import org.openxava.annotations.extended.ui.config.grid.Toolbar;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldArray;
import org.openxava.annotations.parse.JsFieldBoolean;
import org.openxava.annotations.parse.JsFieldString;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;
import org.openxava.annotations.parse.JsonKeyValuesProviderI;
import org.openxava.annotations.parse.JsonRawKeyValueProvider;
import org.openxava.annotations.parse.JsonValueProvider;
import org.openxava.tab.meta.MetaTab;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(TabConfig.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class TabConfigVo implements JsFieldValueResolver, JsonKeyValuesProviderI {

	private String name;
	
	private String baseCondition;
	
	@CompositeField("altRowTemplate")
	private String altRowTemplateStr;
	
	@CompositeField("altRowTemplate")
	private String altRowTemplateFn;
	
	@DefaultValue("true")
	private Boolean autoBind;
	
	@DefaultValue("3")
	private Integer columnResizeHandleWidth;
	
	private ColumnMenuVo columnMenu;
	
	@CompositeField("detailTemplate")
	private String detailTemplateStr;
	
	@CompositeField("detailTemplate")
	private String detailTemplateFn;
	
	@CompositeField("detailInit")
	private String detailInitFn;

	@JsonValueProvider
	private EditableVo editable;
	
	private ExcelVo excel;
	
	@JsonValueProvider
	private FilterableVo filterable;
	
	@JsonValueProvider
	private GroupableVo groupable;
	
	private String height;
	
	@CompositeField("mobile")
	@DefaultValue("false")
	private Boolean mobileBool;
	
	@CompositeField("mobile")
	private String mobileStr;
	
	@DefaultValue("false")
	private Boolean navigatable;
	
	@JsonValueProvider
	private PageableVo pageable;
	
	private PdfVo pdf;
	private Boolean reorderable;
	private Boolean resizable;
	
	@CompositeField("rowTemplate")
	private String rowTemplateStr;
	
	@CompositeField("rowTemplate")
	private String rowTemplateFn;
	
	@JsonValueProvider
	private ScrollableVo scrollable;
	
	@CompositeField("selectable")
	@DefaultValue("false")
	private Boolean selectableBool;
	
	@CompositeField("selectable")
	@DefaultValue("multiple, row")
	private SelectableMode selectableStr;
	
	@JsonValueProvider
	private SortableVo sortable;
	
	@CompositeField("toolbar")
	private String toolbarStr;
	
	@CompositeField("toolbar")
	private String toolbarFn;
	
	@CompositeField("toolbar")
	private List<ToolbarVo> toolbarArray;
	
	@JsonRawKeyValueProvider
	private List<EventVo> events;
	
	@Mandatory
	private DataSourceVo dataSource;
	
	@Mandatory
	private List<ColumnVo> columns;

	private TabConfigVo() {
	}
	
	public static TabConfigVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(MetaTab.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new TabConfigVo();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(isNotEmpty(name))
			this.name = name;
	}

	public String getBaseCondition() {
		return baseCondition;
	}

	public void setBaseCondition(String baseCondition) {
		if(isNotEmpty(baseCondition))
			this.baseCondition = baseCondition;
	}

	public String getAltRowTemplateStr() {
		return altRowTemplateStr;
	}

	public void setAltRowTemplateStr(String altRowTemplateStr) {
		if(isNotEmpty(altRowTemplateStr))
			this.altRowTemplateStr = altRowTemplateStr;
	}

	public String getAltRowTemplateFn() {
		return altRowTemplateFn;
	}

	public void setAltRowTemplateFn(String altRowTemplateFn) {
		if(isNotEmpty(altRowTemplateFn))
			this.altRowTemplateFn = altRowTemplateFn;
	}

	public Boolean isAutoBind() {
		return autoBind;
	}

	public void setAutoBind(Boolean autoBind) {
		if(autoBind != null)
			this.autoBind = autoBind;
	}

	public Integer getColumnResizeHandleWidth() {
		return columnResizeHandleWidth;
	}

	public void setColumnResizeHandleWidth(Integer columnResizeHandleWidth) {
		if(columnResizeHandleWidth != null && columnResizeHandleWidth != -1)
			this.columnResizeHandleWidth = columnResizeHandleWidth;
	}

	public ColumnMenuVo getColumnMenu_() {
		if(columnMenu == null)
			try {
				columnMenu = ColumnMenuVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return columnMenu;
	}
	
	public ColumnMenuVo getColumnMenu() {
		return columnMenu;
	}

	public void setColumnMenu(ColumnMenuVo columnMenu) {
		if(columnMenu != null)
			this.columnMenu = columnMenu;
	}

	public String getDetailTemplateStr() {
		return detailTemplateStr;
	}

	public void setDetailTemplateStr(String detailTemplateStr) {
		if(isNotEmpty(detailTemplateStr))
			this.detailTemplateStr = detailTemplateStr;
	}

	public String getDetailTemplateFn() {
		return detailTemplateFn;
	}

	public void setDetailTemplateFn(String detailTemplateFn) {
		if(isNotEmpty(detailTemplateFn))
			this.detailTemplateFn = detailTemplateFn;
	}

	public String getDetailInitFn() {
		return detailInitFn;
	}

	public void setDetailInitFn(String detailInitFn) {
		if(isNotEmpty(detailInitFn))
			this.detailInitFn = detailInitFn;
	}

	public EditableVo getEditable_() {
		if(editable == null)
			try {
				editable = EditableVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return editable;
	}
	
	public EditableVo getEditable() {
		return editable;
	}
	
	public void setEditable(EditableVo editable) {
		if(editable != null)
			this.editable = editable;
	}

	public ExcelVo getExcel_() {
		if(excel == null)
			try {
				excel = ExcelVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return excel;
	}
	
	public ExcelVo getExcel() {
		return excel;
	}

	public void setExcel(ExcelVo excel) {
		if(excel != null)
			this.excel = excel;
	}

	public FilterableVo getFilterable_() {
		if(filterable == null)
			try {
				filterable = FilterableVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return filterable;
	}
	
	public FilterableVo getFilterable() {
		return filterable;
	}

	public void setFilterable(FilterableVo filterable) {
		if(filterable != null)
			this.filterable = filterable;
	}

	public GroupableVo getGroupable_() {
		if(groupable == null)
			try {
				groupable = GroupableVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return groupable;
	}
	
	public GroupableVo getGroupable() {
		return groupable;
	}

	public void setGroupable(GroupableVo groupable) {
		if(groupable != null)
			this.groupable = groupable;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		if(isNotEmpty(height))
			this.height = height;
	}

	public Boolean isMobileBool() {
		return mobileBool;
	}

	public void setMobileBool(Boolean mobileBool) {
		if(mobileBool != null)
			this.mobileBool = mobileBool;
	}

	public String getMobileStr() {
		return mobileStr;
	}

	public void setMobileStr(String mobileStr) {
		if(isNotEmpty(mobileStr))
			this.mobileStr = mobileStr;
	}

	public Boolean isNavigatable() {
		return navigatable;
	}

	public void setNavigatable(Boolean navigatable) {
		if(navigatable != null)
			this.navigatable = navigatable;
	}

	public PageableVo getPageable_() {
		if(pageable == null)
			try {
				pageable = PageableVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return pageable;
	}
	
	public PageableVo getPageable() {
		return pageable;
	}

	public void setPageable(PageableVo pageable) {
		if(pageable != null)
			this.pageable = pageable;
	}

	public PdfVo getPdf_() {
		if(pdf == null)
			try {
				pdf = PdfVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return pdf;
	}
	
	public PdfVo getPdf() {
		return pdf;
	}

	public void setPdf(PdfVo pdf) {
		if(pdf != null)
			this.pdf = pdf;
	}

	public Boolean isReorderable() {
		return reorderable;
	}

	public void setReorderable(Boolean reorderable) {
		if(reorderable != null)
			this.reorderable = reorderable;
	}

	public Boolean isResizable() {
		return resizable;
	}

	public void setResizable(Boolean resizable) {
		if(resizable != null)
			this.resizable = resizable;
	}

	public String getRowTemplateStr() {
		return rowTemplateStr;
	}

	public void setRowTemplateStr(String rowTemplateStr) {
		if(isNotEmpty(rowTemplateStr))
			this.rowTemplateStr = rowTemplateStr;
	}

	public String getRowTemplateFn() {
		return rowTemplateFn;
	}

	public void setRowTemplateFn(String rowTemplateFn) {
		if(isNotEmpty(rowTemplateFn))
			this.rowTemplateFn = rowTemplateFn;
	}

	public ScrollableVo getScrollable_() {
		if(scrollable == null)
			try {
				scrollable = ScrollableVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return scrollable;
	}
	
	public ScrollableVo getScrollable() {
		return scrollable;
	}

	public void setScrollable(ScrollableVo scrollable) {
		if(scrollable != null)
			this.scrollable = scrollable;
	}

	public Boolean isSelectableBool() {
		return selectableBool;
	}

	public void setSelectableBool(Boolean selectableBool) {
		if(selectableBool != null)
			this.selectableBool = selectableBool;
	}

	public SelectableMode getSelectableStr() {
		return selectableStr;
	}

	public void setSelectableStr(SelectableMode selectableStr) {
		if(selectableStr != null && selectableStr.getValue() != null && !selectableStr.getValue().equals("multiple, row"))
			this.selectableStr = selectableStr;
	}

	public SortableVo getSortable_() {
		if(sortable == null)
			try {
				sortable = SortableVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return sortable;
	}
	
	public SortableVo getSortable() {
		return sortable;
	}

	public void setSortable(SortableVo sortable) {
		if(sortable != null)
			this.sortable = sortable;
	}

	public String getToolbarStr() {
		return toolbarStr;
	}

	public void setToolbarStr(String toolbarStr) {
		if(isNotEmpty(toolbarStr))
			this.toolbarStr = toolbarStr;
	}
	
	public TabConfigVo resetToolbarStr() {
		this.toolbarStr = null;
		return this;
	}

	public String getToolbarFn() {
		return toolbarFn;
	}

	public void setToolbarFn(String toolbarFn) {
		if(isNotEmpty(toolbarFn))
			this.toolbarFn = toolbarFn;
	}
	
	public TabConfigVo resetToolbarFn() {
		this.toolbarFn = null;
		return this;
	}

	public List<ToolbarVo> getToolbarArray() {
		return toolbarArray;
	}

	public void addToolbarArray(List<ToolbarVo> toolbarArray) {
		if(toolbarArray != null){
			if(this.toolbarArray == null)
				this.toolbarArray = new ArrayList<ToolbarVo>();
			this.toolbarArray.addAll(toolbarArray);
		}
	}
	
	public void addToolbar(ToolbarVo toolbar) {
		if(toolbar != null){
			if(this.toolbarArray == null)
				this.toolbarArray = new ArrayList<ToolbarVo>();
			this.toolbarArray.add(toolbar);
		}
	}
	
	public TabConfigVo resetToolbarArray() {
		this.toolbarArray = null;
		return this;
	}

	public List<EventVo> getEvents() {
		return events;
	}

	public void addEvents(List<EventVo> events) {
		if(CollectionUtils.isNotEmpty(events)) {
			if(this.events == null)
				this.events = new ArrayList<EventVo>();
			this.events.addAll(events);
		}
	}
	
	public void addEvent(EventVo event) {
		if(event != null) {
			if(this.events == null)
				this.events = new ArrayList<EventVo>();
			this.events.add(event);
		}
	}

	public DataSourceVo getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSourceVo dataSource) {
		if(dataSource != null)
			this.dataSource = dataSource;
	}

	public List<ColumnVo> getColumns() {
		return columns;
	}
	
	public void addColumns(List<ColumnVo> columns) {
		if(CollectionUtils.isNotEmpty(columns)) {
			if(this.columns == null)
				this.columns = new ArrayList<ColumnVo>();
			this.columns .addAll(columns);
		}
	}
	
	public void addColumn(ColumnVo column) {
		if(column != null) {
			if(this.columns == null)
				this.columns = new ArrayList<ColumnVo>();
			this.columns .add(column);
		}
	}

	public TabConfigVo copyFrom(TabConfig config) {
		setAltRowTemplateStr(config.altRowTemplateStr());
		setAltRowTemplateFn(config.altRowTemplateFn());
		setAutoBind(config.autoBind().getBool());
		getColumnMenu_().copyFrom(config.columnMenu());
		setColumnResizeHandleWidth(config.columnResizeHandleWidth());
		setDetailTemplateFn(config.detailTemplateFn());
		setDetailTemplateStr(config.detailTemplateStr());
		getEditable_().copyFrom(config.editable());
		addEvents(eventList(config.events()));
		getExcel_().copyFrom(config.excel());
		getFilterable_().copyFrom(config.filterable());
		getGroupable_().copyFrom(config.groupable());
		setHeight(config.height());
		setMobileBool(config.mobileBool().getBool());
		setMobileStr(config.mobileStr());
		setName(null);
		setNavigatable(config.navigatable().getBool());
		getPageable_().copyFrom(config.pageable());
		getPdf_().copyFrom(config.pdf());
		setReorderable(config.reorderable().getBool());
		setResizable(config.resizable().getBool());
		setRowTemplateFn(config.rowTemplateFn());
		setRowTemplateStr(config.rowTemplateStr());
		getScrollable_().copyFrom(config.scrollable());
		setSelectableBool(config.selectableBool().getBool());
		setSelectableStr(config.selectableStr());
		getSortable_().copyFrom(config.sortable());
		addToolbarArray(toolBarList(config.toolbarArray()));
		setToolbarFn(config.toolbarFn());
		setToolbarStr(config.toolbarStr());
		return this;
	}

	private List<EventVo> eventList(Event[] events) {
		List<EventVo> list = new ArrayList<EventVo>();
		for(Event event : events) {
			list.add(new EventVo().copyFrom(event));
		}
		return list;
	}

	private List<ToolbarVo> toolBarList(Toolbar[] toolbarArray) {
		List<ToolbarVo> list = new ArrayList<ToolbarVo>();
		for(Toolbar toolBar : toolbarArray) {
			list.add(new ToolbarVo().copyFrom(toolBar));
		}
		return list;
	}

	@Override
	public JsField resolve(String field) {
		if(field.equals("altRowTemplate")) {
			return resolveAltRowTemplate();
		} else if(field.equals("detailTemplate")) {
			return resolveDetailTemplate();
		} else if(field.equals("detailInit")) {
			return resolveDetailInit();
		} else if(field.equals("rowTemplate")) {
			return resolveRowTemplate();
		} else if(field.equals("toolbar")) {
			return resolveToolbar();
		} else if(field.equals("mobile")) {
			return resolveMobile();
		} else if(field.equals("selectable")) {
			return resolveSelectable();
		}
		return null;
	}

	private JsField resolveDetailInit() {
		if(StringUtils.isNotEmpty(getDetailInitFn())) {
			return new JsFieldVariable("detailInit", getDetailInitFn(), "detailInitFn");
		}
		return null;
	}

	private JsField resolveAltRowTemplate() {
		if(StringUtils.isNotEmpty(getAltRowTemplateFn())) {
			return new JsFieldVariable("altRowTemplate", getAltRowTemplateFn(), "altRowTemplateFn");
		} else if(StringUtils.isNotEmpty(getAltRowTemplateStr())) {
			return new JsFieldString("altRowTemplate", getAltRowTemplateStr(), "altRowTemplateStr");
		}
		return null;
	}

	private JsField resolveSelectable() {
		if(selectableStr != null && StringUtils.isNotEmpty(selectableStr.getSelectable())) {
			return new JsFieldString("selectable", selectableStr.getSelectable(), "selectableStr");
		} else if(selectableBool != null) {
			return new JsFieldBoolean("selectable", selectableBool, "selectableBool");
		}
		return null;
	}

	private JsField resolveMobile() {
		if(StringUtils.isNotEmpty(mobileStr)) {
			return new JsFieldString("mobile", mobileStr, "mobileStr");
		} else if(mobileBool != null) {
			return new JsFieldBoolean("mobile", mobileBool, "mobileBool");
		}
		return null;
	}

	private JsField resolveToolbar() {
		if(StringUtils.isNotEmpty(toolbarFn)) {
			return new JsFieldVariable("toolbar", toolbarFn, "toolbarFn");
		} else if(StringUtils.isNotEmpty(toolbarStr)) {
			return new JsFieldString("toolbar", toolbarStr, "toolbarStr");
		} else if(CollectionUtils.isNotEmpty(toolbarArray)) {
			return new JsFieldArray("toolbar", toolbarArray.toArray(new ToolbarVo[toolbarArray.size()]), "toolbarArray");
		}
		return null;
	}

	private JsField resolveRowTemplate() {
		if(StringUtils.isNotEmpty(rowTemplateFn)) {
			return new JsFieldVariable("rowTemplate", rowTemplateFn, "rowTemplateFn");
		} else if(StringUtils.isNotEmpty(rowTemplateStr)) {
			return new JsFieldString("rowTemplate", rowTemplateStr, "rowTemplateStr");
		}
		return null;
	}

	private JsField resolveDetailTemplate() {
		if(StringUtils.isNotEmpty(detailTemplateFn)) {
			return new JsFieldVariable("detailTemplate", detailTemplateFn, "detailTemplateFn");
		} else if(StringUtils.isNotEmpty(detailTemplateStr)) {
			return new JsFieldString("detailTemplate", detailTemplateStr, "detailTemplateStr");
		}
		return null;
	}

	@Override
	public Object getValue(String fieldName) {
		if(fieldName.equals("editable") && editable != null) {
			if(editable.getConfig() != null)
				return editable.getConfig();
			else if(editable.isEditable() != null && editable.isEditable()) 
				return new Boolean(true);
		} else if(fieldName.equals("filterable") && filterable != null) {
			if(filterable.getConfig() != null)
				return filterable.getConfig();
			else if(filterable.isFilterable() != null && filterable.isFilterable())
				return new Boolean(true);
		} else if(fieldName.equals("groupable") && groupable != null) {
			if(groupable.getConfig() != null)
				return groupable.getConfig();
			else if(groupable.isGroupable() != null && groupable.isGroupable())
				return new Boolean(true);
		} else if(fieldName.equals("pageable") && pageable != null) {
			if(pageable.getConfig() != null)
				return pageable.getConfig();
			else if(pageable.isPageable() != null && pageable.isPageable())
				return new Boolean(true);
		} else if(fieldName.equals("scrollable") && scrollable != null) {
			if(scrollable.getConfig() != null)
				return scrollable.getConfig();
			else if(scrollable.isScrollable() != null && scrollable.isScrollable())
				return new Boolean(true);
		} else if(fieldName.equals("sortable") && sortable != null) {
			if(sortable.getConfig() != null)
				return sortable.getConfig();
			if(sortable.isSortable() != null && sortable.isSortable())
				return new Boolean(true);
		} else if(fieldName.equals("events") && events != null) {
				return getEventsObject();
		}
		return null;
	}

	@Override
	public Map<String, Object> getKeyValues(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, String> getRawKeyValues(String fieldName) {
		if(fieldName.equals("events")) {
			return getEventsObject();
		}
		return null;
	}

	private Map<String, String> getEventsObject() {
		if(isNotEmpty(events)) {
			Map<String, String> map = new LinkedHashMap<String, String>();
			for(EventVo event : events) {
				map.put(event.getName(), event.getHandler());
			}
			return map;
		}
		return null;
	}

}
