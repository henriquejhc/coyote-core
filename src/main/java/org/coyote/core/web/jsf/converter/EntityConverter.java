package org.coyote.core.web.jsf.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.coyote.core.persistence.model.EntityBean;

@FacesConverter("entityConverter")
public class EntityConverter implements Converter {

	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		
		if (value != null) 
			return this.getAttributesFrom(component).get(value);
		
		return null;
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {

		if (value != null && !value.equals("")) {

			EntityBean entity = (EntityBean) value;

			this.addAttribute(component, entity);

			return String.valueOf(entity.getId());
		}

		return null;
	}

	protected void addAttribute(UIComponent component, EntityBean entity) {
		String key = String.valueOf(entity.getId());
		this.getAttributesFrom(component).put(key, entity);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}