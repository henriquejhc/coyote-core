package org.coyote.core.web.jsf.converter;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("timestampConverter")
public class TimestampConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String data) {

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object valor) {
		
		if (valor != null){
			
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");  
			TimeZone.setDefault(tz);  
			
			Locale LOCAL = new Locale("pt","BR"); 
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", LOCAL);
			sdf.setTimeZone(TimeZone.getDefault());
			sdf.setTimeZone(tz);
			
			return sdf.format(valor);
		}
		else
			return "";
	}

}
