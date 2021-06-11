package model.dao.hibernate;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import model.entities.person.Contact;
import model.entities.person.Endereco;
import model.entities.person.ServiceProvider;

public class ServiceProviderHibernateTest {

	ServiceProviderHibernate hibernate;
	ServiceProvider serviceProvider;

	@BeforeClass
	public void setTest() {
		hibernate = new ServiceProviderHibernate();
		hibernate.setTestDatabase();
	}

	@Test(priority=1)
	public void insertTest() {
		Endereco address = new Endereco("67130-480", "68");
		Contact contact = new Contact("email@gmail.com", "91989898989");

		serviceProvider = new ServiceProvider();
		serviceProvider.setName("Um provider");
		serviceProvider.setDescription("Uma desc");
		serviceProvider.setSocialReason("uma social reason");
		serviceProvider.setCnpj("83459260000157");
		serviceProvider.setEndereco(address);
		serviceProvider.setContact(contact);
		int insertedId = hibernate.insert(serviceProvider);
		Assert.assertNotEquals(insertedId, -1);
		serviceProvider.setId(insertedId);
	}

	@Test(priority=2)
	public void findByIdTest() {
		ServiceProvider serviceProviderFound = (ServiceProvider) hibernate.find("id",
				String.valueOf(serviceProvider.getId()));
		Assert.assertEquals(serviceProviderFound.getCnpj(), serviceProvider.getCnpj());
	}

	@Test(priority=3)
	public void findByCnpjTest() {
		ServiceProvider serviceProviderFound = (ServiceProvider) hibernate.find("cnpj", serviceProvider.getCnpj());
		Assert.assertEquals(serviceProviderFound.getCnpj(), serviceProvider.getCnpj());
	}

	@Test(priority=4)
	public void getAllTest() {
		Assert.assertNotNull(hibernate.getAll());
	}

	@Test(priority=5)
	public void updateTest() {
		serviceProvider.getEndereco().setComplemento("420");
		serviceProvider.setName("setado");

		Assert.assertEquals(hibernate.update(serviceProvider), serviceProvider.getId());
	}

	@Test(priority=6)
	public void deleteTest() {
		Assert.assertEquals(hibernate.delete(serviceProvider.getId()), serviceProvider.getId());
	}
}
