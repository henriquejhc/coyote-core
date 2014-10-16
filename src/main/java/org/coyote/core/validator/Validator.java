package org.coyote.core.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Validator {
	
	boolean empty() default false;

	int valueGreater() default 0;

	int valueLess() default 0;

	String messageEmpty() default "O campo $name não pode ficar vazio.";
}
