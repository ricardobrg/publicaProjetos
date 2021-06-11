package model.entities.person;

import org.testng.annotations.Test;
import org.testng.Assert;

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

