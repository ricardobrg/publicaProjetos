package model.entities.person;

import org.testng.annotations.Test;
import org.testng.Assert;

public class FisicaPersonTest {
	FisicaPerson fisicaperstwo = new FisicaPerson("Ana","89035-070", "carolsantos@publica.com", "(47) 99468-6862", "613.306.180-41", "981.70710.15-8");
	
//	@Test
//	public void isPhoneValid() {
//		//The method setPhone is not valid for this number, so it takes the next fitting value, then it passes.
//		fisicaperstwo.setPhone("(32) 4443769-8786");
//		Assert.assertEquals(fisicaperstwo.getPhone(),"(47) 99468-6862" );
//		Assert.assertNotEquals(fisicaperstwo.getPhone(), "(23) 18754-6997");
//		Assert.assertNotNull(fisicaperstwo.getPhone());
//		
//		fisicaperstwo.setPhone("(47) 94576-7845");
//		Assert.assertNotNull(fisicaperstwo.getPhone());
//	}
//
//	@Test
//	public void isEmailValidTest() {
//		fisicaperstwo.setEmail("@v@gmail.com.com");
//		Assert.assertEquals(fisicaperstwo.getEmail(),"carolsantos@publica.com");
//		Assert.assertNotEquals(fisicaperstwo.getEmail(), "faladev@gmail.com");
//		Assert.assertNotNull(fisicaperstwo.getEmail());
//		
//		fisicaperstwo.setEmail("rh@publica.com");
//		Assert.assertNotNull(fisicaperstwo.getEmail());
//	}

//	@Test
//	public void isCepValidTest() {
//		fisicaperstwo.setCep("298106-11555");
//		Assert.assertEquals(fisicaperstwo.getCep(), "89035070");
//		Assert.assertNotEquals(fisicaperstwo.getCep(), "64710-970");
//		Assert.assertNotNull(fisicaperstwo.getCep());
//		
//		fisicaperstwo.setCep("68512-390");
//		Assert.assertNotNull(fisicaperstwo.getCep());
//	}

	@Test
	public void isPisValidTest() {
		fisicaperstwo.setPis("267.56158.93-4575");
		Assert.assertNull(fisicaperstwo.getPis());
		
		fisicaperstwo.setPis("854.76368.55-2");
		Assert.assertNotNull(fisicaperstwo.getPis());
	}
	
	@Test
	public void setCpfTest() {
		fisicaperstwo.setCpf("734.411.240-0645");
		Assert.assertEquals(fisicaperstwo.getCpf(), "613.306.180-41");
		Assert.assertNotEquals(fisicaperstwo.getCpf(), "456.633.640-99");
		Assert.assertNotNull(fisicaperstwo.getCpf());
		
		fisicaperstwo.setCpf("040.330.570-56");
		Assert.assertNotNull(fisicaperstwo.getCpf());
	}
}
