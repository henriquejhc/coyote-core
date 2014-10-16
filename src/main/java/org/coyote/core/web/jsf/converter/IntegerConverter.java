package org.coyote.core.web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("integerConverter")
public class IntegerConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String output) {
		
		int number;
		
		if (output == null || output.isEmpty())
			number = 0;
		else
			number = Integer.parseInt(output);
			
		return number;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object number) {
		
		if ((Integer)number == 0)
			return "";
		
		return String.valueOf(number);
	}

}