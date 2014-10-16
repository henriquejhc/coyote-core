package org.coyote.core.util;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class Formatter {
	
	public static final int CEP = 1;
	public static final int CPF = 2;
	public static final int CNPJ = 3;
	public static final int PHONE = 4;
	
	private static final String DOT = ".";
	private static final String HYPHEN = "-";
	private static final String OPEN_FONE_PARENTHESIS = "(";
	private static final String CLOSE_FONE_PARENTHESIS = ")";
	private static final String OPEN_PARENTHESIS = "\\(";
	private static final String CLOSE_PARENTHESIS = "\\)";
	private static final String BAR = "/";
	private static final String SCAPE = "\\";
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	private static final String SEMICOLON = ";";
	private static final String UNDERLINE = "_";
	private static final String HASH = "#";
	
	
	public static String addMaskCep(String cep){
		return cep.substring(0, 2) + DOT + cep.substring(2, 5) + HYPHEN + cep.substring(5, 8);
	}
	
	public static String addMaskCpf(String cpf){
		return cpf.substring(0, 3) + DOT + cpf.substring(3, 6) + DOT + cpf.substring(6, 9) + HYPHEN + cpf.substring(9, 11);		
	}
	
	public static String addMaskCnpj(String cnpj) {
		return cnpj.substring(0, 2) + DOT + cnpj.substring(2, 5) + DOT + cnpj.substring(5, 8) + BAR + cnpj.substring(8, 12) + HYPHEN + cnpj.substring(12);
	}
	
	public static String addMaskFone(String fone){		
		if(fone.length() == 8)
			return (fone.substring(0, 4) + HYPHEN + fone.substring(4, 8));
		else
			return (OPEN_FONE_PARENTHESIS + fone.substring(0, 2) + CLOSE_FONE_PARENTHESIS +  fone.subSequence(2, 6) + HYPHEN + fone.substring(6, 10));
	}
		
	public static String addMask(String str, String mask) {
		String output  = EMPTY;
		int x = 0;
		for (int i = 0; i < mask.length(); i++) {
			if (mask.charAt(i) == '#') {
				output  += str.charAt(x);
			} else {
				output  += mask.charAt(i);
				output  += str.charAt(x);
				i++;
			}
			x++;
		}
		return output;
	}
	
	public static String addMask(String str, int PATTERN){		
		switch (PATTERN) {		
			case CEP:
				return addMaskCep(str);				
			case CPF:
				return addMaskCpf(str);	
			case CNPJ:
				return addMaskCnpj(str);	
			case PHONE:
				return addMaskFone(str);				
			default:
				return null;				
		}		
	} 	
	
	public static String removeMaskCpf(String cpf) {
		return cpf.replaceAll(SCAPE + DOT, EMPTY).replaceAll(HYPHEN, EMPTY);
	}

	public static String removeMaskCnpj(String cnpj) {
		return cnpj.replaceAll(SCAPE + DOT, EMPTY).replaceAll(HYPHEN, EMPTY).replaceAll(BAR, EMPTY);
	}

	public static String removeMaskFone(String fone) {
		return fone.replaceAll(OPEN_PARENTHESIS, EMPTY).replaceAll(CLOSE_PARENTHESIS, EMPTY).replaceAll(HYPHEN, EMPTY);
	}

	public static String removeMaskCep(String cep) {
		return cep.replaceAll(SCAPE + DOT, EMPTY).replaceAll(HYPHEN, EMPTY);
	}
	
	public static String removeMask(String delimiters[], String value){
		
		for (String delimiter : delimiters){
			if(delimiter.equals(DOT) || delimiter.equals(OPEN_PARENTHESIS) || delimiter.equals(CLOSE_PARENTHESIS))
				value = value.replaceAll(SCAPE + delimiter, EMPTY);
			else	
				value = value.replaceAll(delimiter, EMPTY);
		}
				
		return value; 
	}
	
	public static String removeMask(String value, int PATTERN){
		switch (PATTERN) {
			case CEP:
				return removeMaskCep(value);
			case CPF:
				return removeMaskCpf(value);
			case PHONE:
				return removeMaskFone(value);	
			default:
				return null;
		}		
	}
	
	public static String removeMask(String str) {
		
		if (str == null || str.isEmpty())
			return "";
		
		return str.replaceAll(DOT, EMPTY)
				   .replaceAll(BAR, EMPTY)
				   .replaceAll(OPEN_PARENTHESIS, EMPTY)
				   .replaceAll(CLOSE_PARENTHESIS, EMPTY)
				   .replaceAll(HYPHEN, EMPTY)
				   .replaceAll(SEMICOLON, EMPTY)
				   .replaceAll(HASH, EMPTY)
				   .replaceAll(UNDERLINE, EMPTY)
				   .replaceAll(SPACE, EMPTY);
	}

}