package org.openxava.formatters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openxava.util.Locales;

/**
 * Common code for DateTime formatters. <p>
 * 
 * @author Javier Paniza
 */

abstract public class DateTimeBaseFormatter {
	
	private static DateFormat [] extendedDateTimeFormats = {
		new SimpleDateFormat("dd/MM/yy HH:mm:ss"),
		new SimpleDateFormat("dd/MM/yy HH:mm"),
		new SimpleDateFormat("ddMMyy HH:mm"),
		new SimpleDateFormat("ddMMyy HH:mm:ss"),
		new SimpleDateFormat("dd.MM.yy HH:mm"),		
		new SimpleDateFormat("dd.MM.yy HH:mm:ss"),		
		new SimpleDateFormat("dd/MM/yy"),		
		new SimpleDateFormat("ddMMyy"),		
		new SimpleDateFormat("dd.MM.yy")		
	};	
	
	protected DateFormat [] getExtendedDateTimeFormats() {
		return extendedDateTimeFormats;
	}
	
	protected boolean isExtendedFormat() {
		return "es".equals(Locales.getCurrent().getLanguage()) ||
			"ca".equals(Locales.getCurrent().getLanguage()) || 
			"pl".equals(Locales.getCurrent().getLanguage()) ||
			"fr".equals(Locales.getCurrent().getLanguage());
	}

		
}
