package org.coyote.core.validator;

public class ValidatorEntity {

	public static ValidatorResult validate(Object entity) {

		ValidatorResult validatorResult = new ValidatorResult();

		return validatorResult;
	}

	private static ValidatorResult validateEntity(Object entity) {

		ValidatorResult validatorResult = new ValidatorResult();

		// Field[] fields = entity.getClass().getDeclaredFields();
		//
		// for (Field field : fields) {
		//
		// Annotation[] annotations = field.getDeclaredAnnotations();
		//
		// for (Annotation annotation : annotations) {
		// if (annotation instanceof Validator) {
		// Validator validatorAnnotation = (Validator) annotation;
		//
		// Method method =
		// entity.getClass().getMethod(formatNameMethod(field.getName()));
		//
		// System.out.println(method.getReturnType().getSimpleName());
		//
		// Object value = method.invoke(entity, null);
		//
		// if (validatorAnnotation.empty() && value == null) {
		// validatorResult.addMessage(validatorAnnotation.messageEmpty());
		// }
		//
		// }
		// }
		// }

		return validatorResult;
	}

	private static String formatNameMethod(String name) {
		return "get" + name.substring(0, 1).toUpperCase().concat(name.substring(1));
	}
}
