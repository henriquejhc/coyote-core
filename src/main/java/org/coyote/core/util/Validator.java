package org.coyote.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class Validator {
	
	public static boolean cpfValidate(String cpf) {

		cpf = cpf.replaceAll("-", "").replaceAll("\\.", "");

		if (cpf.length() != 11) {
			return false;
		}

		// Verifica se todos os digitos sao iguais.
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

		// Laco que calcula o primeiro digito verificador
		for (int j = 0; j < (cpf.length() - 2); j++) {
			sum = sum + (Integer.parseInt(cpf.charAt(j) + "") * initial);
			sum2 = sum2 + (Integer.parseInt(cpf.charAt(j) + "") * (initial + 1));
			initial--;
		}

		int digit = 0;
		// A variavel resto recebe o resto da divisão do somatorio dividido por 11
		int rest = (sum % 11);
		if (rest < 2) {// Se resto for menor que 2 digito recebe 0
			digit = 0;
		} else { // Se digito for maior que 2, digito recebe 11 - resto
			digit = 11 - rest;
		}
		// Se a variavel digito for diferente ao valor na posicao 9, sera retornado
		// o false e o metodo termina aqui, caso contrario irA� continuar
		if (digit != Integer.parseInt(cpf.charAt(9) + "")) {
			return false;
		}

		// Acrescenta o segundo digito a variavel soma2 e multiplicado por 2
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


	/**
	 * 
	 * @param cnpj
	 * @return
	 */
	public static boolean cnpjValidate(String cnpj) {

		int validators[] = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
		int sum1 = 0;
		int sum2 = 0;
		int digit = 0;
		String value = cnpj.replaceAll("\\.", "");
		value = value.replaceAll("-", "");
		value = value.replaceAll("/", "");

		/** Verifico se o valor informado tem 14 digitos */
		if (value.length() != 14) {
			return false;
		}

		for (int i = 0; i < 12; i++) {
			sum1 += (Integer.parseInt(value.charAt(i) + "") * (validators[i + 1]));
			sum2 += (Integer.parseInt(value.charAt(i) + "") * validators[i]);
		}

		/** A variavel resto recebe o resto da divisao do somatario dividido por 11 */
		int rest = (sum1 % 11);
		if (rest < 2) {// Se resto for menor que 2 digito recebe 0
			digit = 0;
		} else { // Se digito for maior que 2, digito recebe 11 - resto
			digit = 11 - rest;
		}

		/** Se a variavel digito for diferente ao valor na posicao 9, sera retornado
		 false e o metodo termina aqui, caso contrario ira� continuar */
		if (digit != Integer.parseInt(value.charAt(12) + "")) {
			return false;
		}

		/** Acrescento o digito verificado multiplicado pelo verificador[12] a variavel soma2 */
		sum2 += (digit * validators[12]);

		digit = 0; // Seto valor zero ao digito verificador

		/** A variavel resto recebe o resto da divisao do somatorio dividido por 11 */
		rest = (sum2 % 11);
		if (rest < 2) {// Se resto for menor que 2 digito recebe 0
			digit = 0;
		} else { // Se digito for maior que 2, digito recebe 11 - resto
			digit = 11 - rest;
		}

		/** Se a variavel digito for diferente ao valor na posicao 9, sera retornado
		    false e o metodo termina aqui, caso contrario ira continuar */
		if (digit != Integer.parseInt(value.charAt(13) + "")) {
			return false;
		}

		/** Se chegar ate aqui o cnpj informado e valido */
		return true;
	}
	
	 public static boolean emailValidate(String email){
        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");   
        Matcher search = pattern.matcher(email);
        return search.matches();
    }

}