package org.coyote.core.web.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class CharConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String output) {
		
		char letter;
		
		if (output == null || output.isEmpty())
			letter = ' ';
		else
			letter = output.charAt(0);
			
		return letter;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object letter) {
		
		if ((Character)letter == ' ')
			return "";
		
		return String.valueOf(letter);
	}

}
