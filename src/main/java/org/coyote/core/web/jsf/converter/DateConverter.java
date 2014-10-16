package org.coyote.core.web.jsf.converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value != null && value.length() == 10){
			
			int day = Integer.parseInt(value.split("/")[0]);
			int month = Integer.parseInt(value.split("/")[1]);
			int year = Integer.parseInt(value.split("/")[2]);
			Calendar date = Calendar.getInstance();
			int currentYear = date.get(Calendar.YEAR);
			
			if (day < 32 && month < 13 && year > 1900 && year < currentYear){
				date.set(Calendar.DAY_OF_MONTH, day);
				date.set(Calendar.MONTH, month - 1);
				date.set(Calendar.YEAR, year);
				return date.getTime();
			}
		}
			
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null){
			Locale locale = new Locale("pt","BR"); 
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);
			simpleDateFormat.setTimeZone(TimeZone.getDefault());
			return simpleDateFormat.format(value);
		}
		else
			return "";
	}

}