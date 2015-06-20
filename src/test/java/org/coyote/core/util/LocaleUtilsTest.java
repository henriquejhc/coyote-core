package org.coyote.core.util;

import java.util.Locale;

import org.junit.Test;

public class LocaleUtilsTest {

	@Test
	public void test() {
		Locale defaultLocale = Locale.forLanguageTag("pt_BR");
		
		for (Locale locale : Locale.getAvailableLocales()) {
			if (locale.equals(defaultLocale)) {
				System.out.println(locale);
			}
		}
	}

}