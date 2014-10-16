package org.coyote.core.web.json;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONObject;

public class JSONUtils {
	
	private static final String GET = "get";
	
	@SuppressWarnings({ "rawtypes" })
	public static Collection convertToJson(List list, String... propertyNames) {
		
		Collection<JSONObject> jsonList = new ArrayList<JSONObject>();
		
		try {
			
			if(list == null) 
				return jsonList;
			
			for (Object object : list) {
				JSONObject jsonObject = new JSONObject();
				
				for (String property : propertyNames) 
					jsonObject.append(property, JSONUtils.getValue(object, property));					
				
				jsonList.add(jsonObject);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonList;
	}
	
	private static Object getValue(Object object, String property) {
		try {
			String invokeMethod = GET.concat(property.substring(0,1).toUpperCase().trim().concat(property.substring(1).trim()));
			Method method = object.getClass().getMethod(invokeMethod, null);
			Object value = method.invoke(object, null);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}