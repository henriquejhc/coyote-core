package org.coyote.core.web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class LongConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String output) {
		
		long number;
		
		if (output == null || output.isEmpty())
			number = 0;
		else
			number = Long.parseLong(output);
			
		return number;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object number) {
		
		Long num = (Long) number;
		
		if (num != 0)
			return String.valueOf(number);
		else
			return "";
	}

}
