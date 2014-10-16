package org.coyote.core.util;


public class StringUtils {

	public static String[] characters = { 
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", 
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" 
	};

	public static String generateStringRandom(Integer length) {
		String stringGenerate = "";
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * characters.length);
			stringGenerate += characters[index];
		}
		return stringGenerate;
	}

	private static final String LETTERS_WITH_ACCENT_ARRAY = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
	private static final String UNACCENTED_LETTERS_ARRAY = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
	private static char[] TABLE;
	static {
		TABLE = new char[256];
		for (int i = 0; i < TABLE.length; ++i) {
			TABLE[i] = (char) i;
		}
		for (int i = 0; i < LETTERS_WITH_ACCENT_ARRAY.length(); ++i) {
			TABLE[LETTERS_WITH_ACCENT_ARRAY.charAt(i)] = UNACCENTED_LETTERS_ARRAY.charAt(i);
		}
	}

	public static String removeAccentFromString(final String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			if (ch < 256) {
				sb.append(TABLE[ch]);
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

}