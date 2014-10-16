package org.coyote.core.util;

import org.junit.Assert;
import org.junit.Test;

public class FormatterTest {
	
	private static final String FONE_SEM_MASCARA = "4899473250";
	private static final String FONE_COM_MASCARA = "(48)9947-3250";	
	
	private static final String CEP_SEM_MASCARA = "88102210";	
	private static final String CEP_COM_MASCARA = "88.102-210";
	
	private static final String CPF_SEM_MASCARA = "04917096928";
	private static final String CPF_COM_MASCARA = "049.170.969-28";	
	
	private static final String CNPJ_SEM_MASCARA = "14314050000158";
	private static final String CNPJ_COM_MASCARA = "14.314.050/0001-58";	
	
	@Test
	public void addMaskCepTest(){
		Assert.assertEquals(CEP_COM_MASCARA, Formatter.addMaskCep(CEP_SEM_MASCARA));
	}
	
	@Test
	public void addMaskCpfTest(){
		Assert.assertEquals(CPF_COM_MASCARA, Formatter.addMaskCpf(CPF_SEM_MASCARA));		
	}
	
	@Test
	public void addMaskCnpjTest() {
		Assert.assertEquals(CNPJ_COM_MASCARA, Formatter.addMaskCnpj(CNPJ_SEM_MASCARA));
	}
	
	@Test
	public void addMaskFoneTest(){		
		Assert.assertEquals(FONE_COM_MASCARA, Formatter.addMaskFone(FONE_SEM_MASCARA));
	}
		
	public void addMaskTest() {
		Assert.assertEquals(0, 0);
	}
	
	/*public void addMaskTest(){		
				
	} 	*/
	
	@Test
	public void replaceCpfTest() {
		Assert.assertEquals(CPF_SEM_MASCARA, Formatter.removeMaskCpf(CPF_COM_MASCARA));
	}

	@Test
	public void replaceCnpjTest() {
		Assert.assertEquals(CNPJ_SEM_MASCARA, Formatter.removeMaskCnpj(CNPJ_COM_MASCARA));
	}

	@Test
	public void replaceFoneTest() {
		Assert.assertEquals(FONE_SEM_MASCARA, Formatter.removeMaskFone(FONE_COM_MASCARA));
	}

	@Test
	public void replaceCepTest() {
		Assert.assertEquals(CEP_SEM_MASCARA, Formatter.removeMaskCep(CEP_COM_MASCARA));
	}
	
	public void replaceTest(){
		Assert.assertEquals(0, 0);
	}
	
//	public void replaceTest(){
//				
//	}
	
	public void removeMaskTest() {
		Assert.assertEquals(0, 0);
	}
	
}
