package org.openxava.formatters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;

import org.openxava.util.Is;
import org.openxava.util.Locales;
import org.openxava.util.Strings;

/**
 * For MONEY and DINERO stereotypes. <p>
 * 
 * @author Javier Paniza
 */

public class MoneyFormatter implements IFormatter {

	public String format(HttpServletRequest request, Object object)	throws Exception {
		if (object == null) return "";
		String result = getFormat().format(object);
		return result;		
	}

	public Object parse(HttpServletRequest request, String string) throws Exception {
		if (Is.emptyString(string)) return null; 
		string = Strings.change(string, " ", ""); // In order to work with Polish		
		return new BigDecimal(getFormat().parse(string).toString()).setScale(2);
	}
	
	private NumberFormat getFormat() {
		NumberFormat f = DecimalFormat.getNumberInstance(Locales.getCurrent());
		f.setMinimumFractionDigits(2);
		f.setMaximumFractionDigits(2);
		return f;
	}

}
