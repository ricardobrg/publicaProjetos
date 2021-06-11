package model.hibernate;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import models.entities.person.Endereco;
import models.hibernate.AddressHibernate;

public class AddressHibernateTest {

	AddressHibernate hibernate;
	Endereco addr;
	
	@BeforeClass
	public void setTest() {
		hibernate = new AddressHibernate();
		hibernate.setTestDatabase();
	}
	
	@Test(priority=1)
	public void insertTest() {
		addr = new Endereco("67130-480", "68");
		int insertedId = hibernate.insert(addr);
		Assert.assertNotEquals(insertedId, -1);
		addr.setId(insertedId);
	}

	@Test(priority=2)
	public void findTest() {
		Endereco addrFound = hibernate.find(addr.getCEP(), addr.getComplemento());
		Assert.assertEquals(addrFound.getId(), addr.getId());
	}
	
	@Test(priority=3)
	public void updateTest() {
		addr.setComplemento("89");
		Assert.assertEquals(hibernate.update(addr), addr.getId());
	}
	
	@Test(priority=4)
	public void deleteByIdTest() {
		Assert.assertEquals(hibernate.delete(addr.getId()), addr.getId());
	}

}
