package org.coyote.core.util;

import java.util.Locale;

public class StringUtilsTest {

	public static void main(String[] args) {
//		System.out.println(StringUtils.generateStringRandom(6));
//		System.out.println(StringUtils.generateStringRandom(6));
//		System.out.println(StringUtils.generateStringRandom(6));
//		System.out.println(StringUtils.generateStringRandom(6));
//		System.out.println(StringUtils.generateStringRandom(8));
//		System.out.println(StringUtils.generateStringRandom(8));
//		System.out.println(StringUtils.generateStringRandom(8));
//		System.out.println(StringUtils.generateStringRandom(8));
//		System.out.println(StringUtils.generateStringRandom(10));
//		System.out.println(StringUtils.generateStringRandom(10));
//		System.out.println(StringUtils.generateStringRandom(10));
//		System.out.println(StringUtils.generateStringRandom(10));
		
		
		for (Locale locale : Locale.getAvailableLocales()) {
			if (locale.getLanguage().equals("pt")) {
				System.out.println(locale);
			}
		}
		
	}

}