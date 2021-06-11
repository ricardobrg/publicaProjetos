package model.dao.hibernate;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import model.entities.person.Contact;

public class ContactHibernateTest {

	ContactHibernate hibernate;
	Contact con;
	
	@BeforeClass
	public void setTest() {
		hibernate = new ContactHibernate();
		hibernate.setTestDatabase();
	}
	
	@Test(priority=1)
	public void insertTest() {
		con = new Contact("email@gmail.com", "91989898989");
		int insertedId = hibernate.insert(con);
		Assert.assertNotEquals(con.getId(), -1);
		con.setId(insertedId);
	}

	@Test(priority=2)
	public void findTest() {
		Contact conFound = hibernate.find(con.getEmail(), con.getTelephone());
		Assert.assertEquals(conFound.getId(), con.getId());
	}
	
	@Test(priority=3)
	public void updateTest() {
		con.setEmail("editado@email.com");
		Assert.assertEquals(hibernate.update(con), con.getId());
	}
	
	@Test(priority=4)
	public void deleteByIdTest() {
		Assert.assertEquals(hibernate.delete(con.getId()), con.getId());
	}
	
}
