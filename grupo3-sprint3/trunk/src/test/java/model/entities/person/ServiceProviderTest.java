package model.entities.person;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import models.entities.person.Contact;
import models.entities.person.Endereco;
import models.entities.person.ServiceProvider;
import models.hibernate.HibernateConector;

public class ServiceProviderTest {
	ServiceProvider testObject = new ServiceProvider();
  
  @Test
  public void getDescriptionTest() {
	  testObject.setDescription("Descri��o qualquer");
	  Assert.assertEquals(testObject.getDescription(), "Descri��o qualquer");
	  Assert.assertNotNull(testObject.getDescription());
  }
  
  @Test
  public void setDescriptionTest() {
	  String insertedDescription = "Texto qualquer";
	  testObject.setDescription(insertedDescription);
	  Assert.assertEquals(testObject.getDescription(), "Texto qualquer");
	  Assert.assertNotNull(testObject.getDescription());
  }
  
  @Test
  public void insertTest() {
	  try {
			Session session = HibernateConector.getSessionFactory().openSession();
			session.beginTransaction();
			
			ServiceProvider serviceProvider = new ServiceProvider();
			serviceProvider.setName("Teste");
			serviceProvider.setCnpj("79089741000140");
			serviceProvider.setDescription("ASAS");
			
			Endereco endereco = new Endereco("89120-000");
			
			session.save(endereco);	
			
			Contact contact = new Contact();			
			
			contact.setEmail("email@email.com");
			contact.setTelephone("4712345678");
			
			session.save(contact);	
			
			serviceProvider.setEndereco(endereco);
			serviceProvider.setContact(contact);
			session.save(serviceProvider);		 
			session.getTransaction().commit();
		    HibernateConector.shutdown();
		}catch(Exception e) {
			Assert.fail(e.toString());
		}
  }
  
  /*
  //Novo ServiceProviderServiceTest
	@Test
	public void addServiceProvider() {
		ServiceProviderDAO service = new ServiceProviderDAO();
		ServiceProvider testObject = new ServiceProvider();
		service.addProvider(testObject);
		Assert.assertEquals(service.providers.size(), 2);
		Assert.assertNotNull(service.providers.get(0));
		Assert.assertEquals(service.providers.get(1), testObject);
	}

	@Test
	public void searchServiceProvider() {
		ServiceProviderDAO service = new ServiceProviderDAO();
		ServiceProvider testObject = new ServiceProvider();
		testObject = service.searchProvider("Ana");
//		JuridicaPerson result;
//		result = service.searchProvider("P�blica Tecnologia Ltda.");
		Assert.assertEquals(testObject.getName(), "Ana");
//		Assert.assertEquals(result, service.providers.get(0));
	}
	
	@Test
	public void editServiceProviderTest() {
		ServiceProviderDAO service = new ServiceProviderDAO();
		service.editProvider("Ana", "P�blica Tech");
		Assert.assertEquals(service.providers.get(0).getName(),"P�blica Tech");
		Assert.assertNotNull(service);
		
	}
	*/
}

