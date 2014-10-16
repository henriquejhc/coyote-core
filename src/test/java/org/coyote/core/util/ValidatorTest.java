package org.coyote.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
public class ValidatorTest {

	private static final String CPF_INVALIDO = "049.170.969-21";
	private static final String CPF_VALIDO = "049.170.969-28";
	private static final String CNPJ_VALIDO = "14.314.050/0001-58";
	private static final String CNPJ_INVALIDO = "14.314.050/0001-59";

	@Test
	public void validateCpfTrue() {
		Assert.assertEquals(true, Validator.cpfValidate(CPF_VALIDO));
	}
	
	@Test
	public void validateCpfFalse() {
		Assert.assertEquals(false, Validator.cpfValidate(CPF_INVALIDO));
	}
	
	@Test
	public void validateCnpjTrue() {
		Assert.assertEquals(true, Validator.cnpjValidate(CNPJ_VALIDO));
	}
	
	@Test
	public void validateCnpjFalse() {
		Assert.assertEquals(false, Validator.cnpjValidate(CNPJ_INVALIDO));
	}
}
