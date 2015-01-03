package org.openxava.annotations.extended.ui.config.vo;

import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.Operators;
import org.openxava.annotations.parse.GenericConfigSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Operators.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class OperatorsVo {

	private StringOperatorsVo stringOperators;

	private NumberOperatorsVo numberOperators;

	private DateOperatorsVo dateOperators;

	private EnumOperatorsVo enumOperators;
	
	
	private OperatorsVo() {
	}
	
	public static OperatorsVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(FilterableConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new OperatorsVo();
	}

	
	public StringOperatorsVo getStringOperators_() {
		if(stringOperators == null)
			try {
				stringOperators = StringOperatorsVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return stringOperators;
	}
	
	public StringOperatorsVo getStringOperators() {
		return stringOperators;
	}

	public void setStringOperators(StringOperatorsVo stringOperators) {
		if(stringOperators != null)
			this.stringOperators = stringOperators;
	}

	public NumberOperatorsVo getNumberOperators_() {
		if(numberOperators == null)
			try {
				numberOperators = NumberOperatorsVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return numberOperators;
	}
	
	public NumberOperatorsVo getNumberOperators() {
		return numberOperators;
	}

	public void setNumberOperators(NumberOperatorsVo numberOperators) {
		if(numberOperators != null)
			this.numberOperators = numberOperators;
	}

	public DateOperatorsVo getDateOperators_() {
		if(dateOperators == null)
			try {
				dateOperators = DateOperatorsVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return dateOperators;
	}
	
	public DateOperatorsVo getDateOperators() {
		return dateOperators;
	}

	public void setDateOperators(DateOperatorsVo dateOperators) {
		if(dateOperators != null)
			this.dateOperators = dateOperators;
	}

	public EnumOperatorsVo getEnumOperators_() {
		if(enumOperators == null)
			try {
				enumOperators = EnumOperatorsVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return enumOperators;
	}
	
	public EnumOperatorsVo getEnumOperators() {
		return enumOperators;
	}

	public void setEnumOperators(EnumOperatorsVo enumOperators) {
		if(enumOperators != null)
			this.enumOperators = enumOperators;
	}

	public OperatorsVo copyFrom(Operators operators) {
		getDateOperators_().copyFrom(operators.dateOperators());
		getEnumOperators_().copyFrom(operators.enumOperators());
		getNumberOperators_().copyFrom(operators.numberOperators());
		getStringOperators_().copyFrom(operators.stringOperators());
		return this;
	}

}
