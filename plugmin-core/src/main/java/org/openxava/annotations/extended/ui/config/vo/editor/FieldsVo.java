package org.openxava.annotations.extended.ui.config.vo.editor;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.ui.config.editor.Fields;
import org.openxava.annotations.extended.ui.config.vo.InstantiationRestrictedException;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldObject;
import org.openxava.annotations.parse.JsFieldValueResolver;
import org.openxava.annotations.parse.JsFieldVariable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = GenericConfigSerializer.class, as = String.class)
public class FieldsVo implements JsFieldValueResolver {

	@CompositeField("name")
	private String nameStr;

	@CompositeField("name")
	private NameVo nameObj;

	@CompositeField("type")
	private String typeStr;

	@CompositeField("type")
	private TypeVo typeObj;

	@CompositeField("size")
	private String sizeStr;

	@CompositeField("size")
	private SizeVo sizeObj;

	
	public static FieldsVo instance() throws InstantiationRestrictedException {
		if (!calledFrom(ModelVo.class)) {
			throw new InstantiationRestrictedException(
					"No permission to instantiate");
		}
		return new FieldsVo();
	}

	
	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		if(isNotEmpty(nameStr))
			this.nameStr = nameStr;
	}

	public NameVo getNameObj_() {
		if(nameObj == null)
			try {
				nameObj = NameVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return nameObj;
	}
	
	public NameVo getNameObj() {
		return nameObj;
	}

	public void setNameObj(NameVo nameObj) {
		if(nameObj != null)
			this.nameObj = nameObj;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		if(isNotEmpty(typeStr))
			this.typeStr = typeStr;
	}

	public TypeVo getTypeObj_() {
		if(typeObj == null)
			try {
				typeObj = TypeVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return typeObj;
	}
	
	public TypeVo getTypeObj() {
		return typeObj;
	}

	public void setTypeObj(TypeVo typeObj) {
		if(typeObj != null)
			this.typeObj = typeObj;
	}

	public String getSizeStr() {
		return sizeStr;
	}

	public void setSizeStr(String sizeStr) {
		if(isNotEmpty(sizeStr))
			this.sizeStr = sizeStr;
	}

	public SizeVo getSizeObj_() {
		if(sizeObj == null)
			try {
				sizeObj = SizeVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return sizeObj;
	}
	
	public SizeVo getSizeObj() {
		return sizeObj;
	}

	public void setSizeObj(SizeVo sizeObj) {
		if(sizeObj != null)
			this.sizeObj = sizeObj;
	}

	public void copyFrom(Fields config) {
		getNameObj_().copyFrom(config.nameObj());
		setNameStr(config.nameStr());
		getSizeObj_().copyFrom(config.sizeObj());
		setSizeStr(config.sizeStr());
		getTypeObj_().copyFrom(config.typeObj());
		setTypeStr(config.typeStr());
	}


	@Override
	public JsField resolve(String field) {
		if(field.equals("name")) {
			return resolveName();
		} else if(field.equals("size")) {
			return resolveSize();
		} else if(field.equals("type")) {
			return resolveType();
		}
		return null;
	}


	private JsField resolveType() {
		if (nameObj != null) {
			return new JsFieldObject("name", nameObj, "nameObj");
		} else if (isNotEmpty(nameStr)) {
			return new JsFieldVariable("name", nameStr, "nameStr");
		}
		return null;
	}


	private JsField resolveSize() {
		if (sizeObj != null) {
			return new JsFieldObject("size", sizeObj, "sizeObj");
		} else if (isNotEmpty(sizeStr)) {
			return new JsFieldVariable("size", sizeStr, "sizeStr");
		}
		return null;
	}


	private JsField resolveName() {
		if (typeObj != null) {
			return new JsFieldObject("type", typeObj, "typeObj");
		} else if (isNotEmpty(typeStr)) {
			return new JsFieldVariable("type", typeStr, "typeStr");
		}
		return null;
	}

}
