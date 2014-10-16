package org.coyote.core.web.jsf.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("enumConverter")
public class EnumConverter implements Converter{

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		
		if (value != null) 
			return this.getAttributesFrom(component).get(value);
		
		return null;
	}
 
	@SuppressWarnings("rawtypes")
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		
		if (value == null) 
			return null;
		
		Enum objetoEnum = (Enum) value;
		this.addAttribute(component, objetoEnum);
		
		return String.valueOf(objetoEnum.ordinal());
	}	
	
	
	@SuppressWarnings("rawtypes")
	protected void addAttribute(UIComponent component, Enum e) {
		String key = String.valueOf(e.ordinal());
		this.getAttributesFrom(component).put(key, e);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
