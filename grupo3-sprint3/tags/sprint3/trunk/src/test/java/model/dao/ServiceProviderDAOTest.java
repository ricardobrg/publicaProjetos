package model.dao;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import model.entities.person.ServiceProvider;

public class ServiceProviderDAOTest {

	ServiceProviderDAO dao = new ServiceProviderDAO();

	@Test
	public void addObjectTest() {
		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setCep("89120-000");
		serviceProvider.setCnpj("98.928.462/0001-78");
		serviceProvider.setDescription("Vendedor de balao");
		serviceProvider.setName("Baloes LTDA Novo");
		dao.addObject(serviceProvider);
		
		ServiceProvider lastObject = (ServiceProvider)dao.findObject(dao.getAll().size()-1);
		Assert.assertEquals(serviceProvider.getName(), lastObject.getName());
	}

	@Test
	public void delObjectTest() {
		int oldSize = dao.getAll().size();
		dao.delObject(1);		
		int newSize = dao.getAll().size();
		Assert.assertEquals(oldSize-1, newSize);
	}

	@Test
	public void findObjectTest() {
		ServiceProvider object = (ServiceProvider) dao.findObject(0);
		Assert.assertEquals(object.getName(), "Baloes LTDA");
		Assert.assertNotNull(object);		
	}
	
	@Test
	public void findObjectByCnpjTest() {
		ServiceProviderDAO dao = new ServiceProviderDAO();

		ServiceProvider object = (ServiceProvider) dao.findObjectByCPNJ("98.928.462/0001-78");
		Assert.assertEquals(object.getName(), "Baloes LTDA");
		Assert.assertNotNull(object);
	}

	@Test
	public void getAllTest() {
	
		ServiceProviderDAO dao = new ServiceProviderDAO();

		ArrayList<ServiceProvider> servicersProviders = dao.getAll();
		Assert.assertNotNull(servicersProviders);
	}

}
