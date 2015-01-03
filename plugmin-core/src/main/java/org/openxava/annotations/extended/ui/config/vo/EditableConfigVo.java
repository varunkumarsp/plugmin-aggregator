package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.enums.EditableMode;
import org.openxava.annotations.extended.ui.config.grid.EditableConfig;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldBoolean;
import org.openxava.annotations.parse.JsFieldString;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(EditableConfig.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class EditableConfigVo implements JsFieldValueResolver {
	
	@CompositeField("confirmation")
	@DefaultValue("true")
	private Boolean confirmationBool;
	
	@CompositeField("confirmation")
	private String confirmationStr;
	
	@CompositeField("confirmation")
	private String confirmationFn;
	
	@DefaultValue("Cancel")
	private String cancelDelete;
	
	@DefaultValue("Delete")
	private String confirmDelete;
	
	@DefaultValue("top")
	private String createAt;
	
	@DefaultValue("true")
	private Boolean destroy;
	
	@DefaultValue("incell")
	private EditableMode mode;
	
	@CompositeField("template")
	private String templateStr;
	
	@CompositeField("template")
	private String templateFn;
	
	@DefaultValue("true")
	private Boolean update;
	
	private WindowVo window;

	
	private EditableConfigVo() {
	}
	
	public static EditableConfigVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(EditableVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new EditableConfigVo();
	}
	
	
	public Boolean isConfirmationBool() {
		return confirmationBool;
	}

	public void setConfirmationBool(Boolean confirmationBool) {
		if(confirmationBool != null)
			this.confirmationBool = confirmationBool;
	}

	public String getConfirmationStr() {
		return confirmationStr;
	}

	public void setConfirmationStr(String confirmationStr) {
		if(isNotEmpty(confirmationStr))
			this.confirmationStr = confirmationStr;
	}

	public String getConfirmationFn() {
		return confirmationFn;
	}

	public void setConfirmationFn(String confirmationFn) {
		if(isNotEmpty(confirmationFn))
			this.confirmationFn = confirmationFn;
	}

	public String getCancelDelete() {
		return cancelDelete;
	}

	public void setCancelDelete(String cancelDelete) {
		if(isNotEmpty(cancelDelete))
			this.cancelDelete = cancelDelete;
	}

	public String getConfirmDelete() {
		return confirmDelete;
	}

	public void setConfirmDelete(String confirmDelete) {
		if(isNotEmpty(confirmDelete))
			this.confirmDelete = confirmDelete;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		if(isNotEmpty(createAt))
			this.createAt = createAt;
	}

	public Boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(Boolean destroy) {
		if(destroy != null)
			this.destroy = destroy;
	}

	public EditableMode getMode() {
		return mode;
	}

	public void setMode(EditableMode mode) {
		if(mode != null && mode.getValue() != null)
			this.mode = mode;
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

	public Boolean isUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		if(update != null)
			this.update = update;
	}

	public WindowVo getWindow_() {
		if(window == null)
			try {
				window = WindowVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return window;
	}
	
	public WindowVo getWindow() {
		return window;
	}

	public void setWindow(WindowVo window) {
		if(window != null)
			this.window = window;
	}

	public EditableConfigVo copyFrom(EditableConfig config) {
		setCancelDelete(config.cancelDelete());
		setConfirmationBool(config.confirmationBool().getBool());
		setConfirmationFn(config.confirmationFn());
		setConfirmationStr(config.confirmationStr());
		setConfirmDelete(config.confirmDelete());
		setCreateAt(config.createAt());
		setDestroy(config.destroy().getBool());
		setMode(config.mode());
		setTemplateFn(config.templateFn());
		setTemplateStr(config.templateStr());
		setUpdate(config.update().getBool());
		getWindow_().copyFrom(config.window());
		return this;
	}

	@Override
	public JsField resolve(String field) {
		if(field.equals("confirmation")) {
			return resolveConfirmation();
		} else if(field.equals("template")) {
			return resolveTemplate();
		}
		return null;
	}

	private JsField resolveTemplate() {
		if(StringUtils.isNotEmpty(templateFn)) {
			return new JsFieldVariable("template", templateFn, "templateFn");
		} else if(StringUtils.isNotEmpty(templateStr)) {
			return new JsFieldString("template", templateStr, "templateStr");
		}
		return null;
	}

	private JsField resolveConfirmation() {
		if(StringUtils.isNotEmpty(confirmationFn)) {
			return new JsFieldVariable("confirmation", confirmationFn, "confirmationFn");
		} else if(StringUtils.isNotEmpty(confirmationStr)) {
			return new JsFieldString("confirmation", confirmationStr, "confirmationStr");
		} else if(confirmationBool != null) {
			return new JsFieldBoolean("confirmation", confirmationBool, "confirmationBool");
		}
		return null;
	}

}
