package org.coyote.core.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

//	enum DateFormat {
//		DD_MM_YYYY("dd/MM/YYYY"),
//		DD_MM(""),
//		MM_YYYY(""),
//		DD_MM_YYYY_HH_MM(""),
//		DD_MM_YYYY__HH_MM_SS("");
//		
//		private String format;
//		
//		private DateFormat(String format) {
//			this.format = format;
//		}
//		
//		@Override
//		public String toString() {
//			return this.format;
//		}
//	};
	
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
	
	public static String format(Date date, DateFormat dateFormat) {
		
		return null;
	}
	
	public static String getCurrentDateFull() {
		return get(getCurrentDate(), DateFormat.FULL);
	}
	
	private static String get(Date date, int style) {
		return get(date, style, Locale.getDefault()); 
	}
	
	private static String get(Date date, int style, Locale locale) {
		return DateFormat.getTimeInstance(style, locale).format(date); 
	}
	
}
