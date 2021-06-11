package com.publica.grupo2sprint3.model.person.legalperson;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.ServiceProviderDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

public class ServiceProviderTest {
	
	Cep cep = Cep.getInstance("45245-452");
	ServiceProvider testObject = new ServiceProvider();
	Contact contact = new Contact("(47) 99999-8999", "carolsantos@publica.com");
	Address address = new Address(cep);
	Department department = new Department("Desenvolvimento de sistemas");
	
	@BeforeMethod
	public void setTest() {
		testObject.setName("Ana");
		testObject.setContact(contact);
		ServiceProviderDAO.getInstance().add(testObject);
	}

	@Test
	public void getDescriptionTest() {
		testObject.setDescription("Descricao qualquer");
		Assert.assertEquals(testObject.getDescription(), "Descricao qualquer");
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
	public void getPriceTest() {
		testObject.setPrice(2000);
		Assert.assertEquals(testObject.getPrice(), 2000);
		Assert.assertNotEquals(testObject.getPrice(), "askdo");
		Assert.assertNotNull(testObject.getPrice());
	}

	@Test
	public void setPriceTest() {
		testObject.setPrice(-100);
		Assert.assertNotEquals(testObject.getPrice(), -100);
		Assert.assertNotNull(testObject.getPrice());
	}
	
	@Test
	public void toStringTest() {
		Contact contact = new Contact("(91) 98181-8181", "abababababa@gmail.com");
		ServiceProvider provider = new ServiceProvider("Carlos", contact, "Tomas e Pedro Henrique Ferragens ME", "95.718.016/0001-40", 15.00);
		
		String result = String.format(""
				+ "Nome: %s\n"
				+ "%s\n"
				+ "%s\n"
				+ "Raz�o Social: %s\n"
				+ "Cnpj: %s\n"
				+ "Custo: %.2f\n"
				+ "Descricaoo: %s\n",
				"Carlos", 
				contact.toString(),
				"Endereco: Nao Cadastrado!", 
				"Tom�s e Pedro Henrique Ferragens ME",
				"95.718.016/0001-40", 
				 15.00,
				"Descricao: Nao Cadastrada!");
		
		Assert.assertEquals(provider.toString(), result);
		
	}
	
	
	
}
