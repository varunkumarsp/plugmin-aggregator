package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.editor.Item;
import org.openxava.annotations.extended.ui.config.editor.Tool;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;
import org.openxava.annotations.parse.JsObject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class ToolVo implements JsFieldValueResolver {

	private String name;

	private String tooltip;

	@JsObject
	private String exec;

	private List<ItemVo> items;

	@CompositeField("template")
	private String templateStr;

	@CompositeField("template")
	private String templateFn;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(isNotEmpty(name))
			this.name = name;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		if(isNotEmpty(tooltip))
			this.tooltip = tooltip;
	}

	public String getExec() {
		return exec;
	}

	public void setExec(String exec) {
		if(isNotEmpty(exec))
			this.exec = exec;
	}

	public List<ItemVo> getItems() {
		return items;
	}

	public void setItems(List<ItemVo> items) {
		if(CollectionUtils.isNotEmpty(items))
			this.items = items;
	}

	public String getTemplateStr() {
		return templateStr;
	}

	public void setTemplateStr(String templateStr) {
		if(isNotEmpty(templateStr))
			this.templateStr = templateStr;
	}

	public String getTemplateFn() {
		return templateFn;
	}

	public void setTemplateFn(String templateFn) {
		if(isNotEmpty(templateFn))
			this.templateFn = templateFn;
	}

	public ToolVo copyFrom(Tool config) {
		setExec(config.exec());
		
		List<ItemVo> items_ = new ArrayList<ItemVo>();
		Item[] items = config.items();
		for (Item item : items) {
			items_.add(new ItemVo().copyFrom(item));
		}
		setItems(items_);
		
		setName(config.name());
		setTemplateFn(config.templateFn());
		setTemplateStr(config.templateStr());
		setTooltip(config.tooltip());
		return this;
	}

	@Override
	public JsField resolve(String field) {
		if(field.equals("template")) {
			return resolveTemplate();
		}
		return null;
	}

	private JsField resolveTemplate() {
		if (StringUtils.isNotEmpty(templateFn)) {
			return new JsFieldVariable("template", templateFn,
					"templateFn");
		} else if (StringUtils.isNotEmpty(templateStr)) {
			return new JsFieldVariable("template", templateStr,
					"templateStr");
		}
		return null;
	}

}
