package org.openxava.annotations.extended.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.DefaultValue;
import org.openxava.annotations.extended.NumberOperators;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(NumberOperators.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class NumberOperatorsVo {
	
	@DefaultValue("Is equal to")
	private String eq;
	
	@DefaultValue("Is not equal to")
	private String neq;
	
	@DefaultValue("Is greater than or equal to")
	private String gte;
	
	@DefaultValue("Is greater than")
	private String gt;
	
	@DefaultValue("Is less than or equal to")
	private String lte;
	
	@DefaultValue("Is less than")
	private String lt;

	
	private NumberOperatorsVo() {
	}
	
	public static NumberOperatorsVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(OperatorsVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new NumberOperatorsVo();
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

	public String getGte() {
		return gte;
	}

	public void setGte(String gte) {
		if(isNotEmpty(gte))
			this.gte = gte;
	}

	public String getGt() {
		return gt;
	}

	public void setGt(String gt) {
		if(isNotEmpty(gt))
			this.gt = gt;
	}

	public String getLte() {
		return lte;
	}

	public void setLte(String lte) {
		if(isNotEmpty(lte))
			this.lte = lte;
	}

	public String getLt() {
		return lt;
	}

	public void setLt(String lt) {
		if(isNotEmpty(lt))
			this.lt = lt;
	}

	public NumberOperatorsVo copyFrom(NumberOperators numberOperators) {
		setEq(numberOperators.eq());
		setGt(numberOperators.gt());
		setGte(numberOperators.gte());
		setLt(numberOperators.lt());
		setLte(numberOperators.lte());
		setNeq(numberOperators.neq());
		return this;
	}

}
