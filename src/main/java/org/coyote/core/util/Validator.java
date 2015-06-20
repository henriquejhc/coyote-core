package org.coyote.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public final class Validator {
	
	public static boolean cpfValidate(String cpf) {

		cpf = Formatter.removeMaskCpf(cpf);

		if (cpf.length() != 11) {
			return false;
		}

		int i = 0;
		for (i = 0; i < cpf.length() - 1; i++) {
			if (cpf.charAt(i) != cpf.charAt(i + 1)) {
				break;
			}
		}
		
		if (i == cpf.length() - 1) {
			return false;
		}

		int initial = 10;
		int sum = 0;
		int sum2 = 0;

		for (int j = 0; j < (cpf.length() - 2); j++) {
			sum = sum + (Integer.parseInt(cpf.charAt(j) + "") * initial);
			sum2 = sum2 + (Integer.parseInt(cpf.charAt(j) + "") * (initial + 1));
			initial--;
		}

		int digit = 0;

		int rest = (sum % 11);
		if (rest < 2) {
			digit = 0;
		} else { 
			digit = 11 - rest;
		}

		if (digit != Integer.parseInt(cpf.charAt(9) + "")) {
			return false;
		}

		sum2 = sum2 + Integer.parseInt(cpf.charAt(9) + "") * 2;
		rest = (sum2 % 11);
		
		if (rest < 2) {
			digit = 0;
		} else {
			digit = 11 - rest;
		}
		if (digit != Integer.parseInt(cpf.charAt(10) + "")) {
			return false;
		}

		return true;
	}

	public static boolean cnpjValidate(String cnpj) {

		int validators[] = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int sum1 = 0;
		int sum2 = 0;
		int digit = 0;
		
		cnpj = Formatter.removeMaskCnpj(cnpj);

		if (cnpj.length() != 14) {
			return false;
		}

		for (int i = 0; i < 12; i++) {
			sum1 += (Integer.parseInt(cnpj.charAt(i) + "") * (validators[i + 1]));
			sum2 += (Integer.parseInt(cnpj.charAt(i) + "") * validators[i]);
		}

		int rest = (sum1 % 11);
		if (rest < 2) {
			digit = 0;
		} else { 
			digit = 11 - rest;
		}

		if (digit != Integer.parseInt(cnpj.charAt(12) + "")) {
			return false;
		}

		sum2 += (digit * validators[12]);

		digit = 0; 

		rest = (sum2 % 11);
		if (rest < 2) {
			digit = 0;
		} else { 
			digit = 11 - rest;
		}

		if (digit != Integer.parseInt(cnpj.charAt(13) + "")) {
			return false;
		}

		return true;
	}
	
	 public static boolean emailValidate(String email){
        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");   
        Matcher search = pattern.matcher(email);
        return search.matches();
    }

}