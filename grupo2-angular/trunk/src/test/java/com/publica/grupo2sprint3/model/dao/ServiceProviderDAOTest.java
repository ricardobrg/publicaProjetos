package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;

public class ServiceProviderDAOTest {
	private ServiceProviderDAO dao = ServiceProviderDAO.getInstance();

	
	@Test
	public void addTest() {
		Contact contact = new Contact("(47) 98421-2581", "abcdefg@gmail.com");
		ServiceProvider provider = new ServiceProvider("Grafica Carlos LTDA", contact, "Grafica Carlos LTDA", "77.884.759/0001-09");
		Assert.assertTrue(dao.add(provider));
	}
	
	@Test
	public void findByIdTest() {
	    int id = ((ServiceProvider) dao.findByIdentifier("Proway")).getId();
		ServiceProvider provider = (ServiceProvider) dao.findById(id);
		Assert.assertEquals(provider.getClass(), ServiceProvider.class);
	}


	@Test
	public void findByIdentifierTest() {
		ServiceProvider provider = (ServiceProvider) dao.findByIdentifier("Proway");
		Assert.assertEquals(provider.getClass(), ServiceProvider.class);
	}
	
	@Test
	public void updateByIdTest() {
		Contact contact = new Contact("(47) 99554-6499", "livrariascatarinense.com.br");
		ServiceProvider provider = new ServiceProvider("Livrarias Catarinense", contact, "Livrarias Catarinense", "15.819.533/0001-77");

		int id = ((ServiceProvider) dao.findByIdentifier("Gr�fica Carlos LTDA")).getId();
		Assert.assertTrue(dao.updateById(id, provider));
	}

	@Test
	public void updateByIdentifierTest() {
		Contact contact = new Contact("(47) 99654-6499", "segurancamaster.com.br");
		ServiceProvider provider = new ServiceProvider("Seguranca Master", contact, "Seguranca Master", "15.819.533/0001-77");
		dao.add(provider);
		provider.setName("Livrarias catarinense");
		Assert.assertTrue(dao.updateByIdentifier("Livrarias Catarinense", provider));
	}

	@Test
	public void removeByIdTest() {
		Assert.assertTrue(dao.removeById(47));
	}

	@Test
	public void removeByIdentifierTest() {
		Assert.assertTrue(dao.removeByIdentifier("Friotech"));
	}


	@Test
	public void getAllTest() {
		ArrayList<ServiceProvider> list = dao.getAll();
		if (list != null) {
			for (ServiceProvider provider : list) {
				Assert.assertEquals(provider.getClass(), ServiceProvider.class);
			}
		}
	}
}















