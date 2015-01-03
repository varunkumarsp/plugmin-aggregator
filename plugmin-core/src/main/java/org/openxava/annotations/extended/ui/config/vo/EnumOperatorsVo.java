package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.EnumOperators;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(EnumOperators.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class EnumOperatorsVo {
	
	@DefaultValue("Is equal to")
	private String eq;
	
	@DefaultValue("Is not equal to")
	private String neq;
	
	
	private EnumOperatorsVo() {
	}
	
	public static EnumOperatorsVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(OperatorsVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new EnumOperatorsVo();
	}
	

	public String getEq() {
		return eq;
	}

	public void setEq(String eq) {
		if(isNotEmpty(eq))
			this.eq = eq;
	}

	public String getNeq() {
		return neq;
	}

	public void setNeq(String neq) {
		if(isNotEmpty(neq))
			this.neq = neq;
	}

	
	public EnumOperatorsVo copyFrom(EnumOperators enumOperators) {
		setEq(enumOperators.eq());
		setNeq(enumOperators.neq());
		return this;
	}

}
