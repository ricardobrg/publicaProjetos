package model.hibernate;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import models.entities.person.Collaborator;
import models.entities.person.Contact;
import models.entities.person.Endereco;
import models.entities.security.AccessLevel;
import models.entities.security.Role;
import models.hibernate.CollaboratorHibernate;
import models.hibernate.RoleHibernate;

public class CollaboratorHibernateTest {
	
	CollaboratorHibernate hibernate;
	Collaborator collab;
	
	RoleHibernate roleHibernate;
	Role role;
	
	@BeforeClass
	public void setTest() throws Exception {
		hibernate = new CollaboratorHibernate();
		hibernate.setTestDatabase();
		
		roleHibernate = new RoleHibernate();
		roleHibernate.setTestDatabase();
		
		role = new Role("Presidente", AccessLevel.TOTAL);
		
		int insertedId = roleHibernate.insert(role);
		role.setId(insertedId);
	}
	
	@AfterClass
	public void erase() throws IllegalArgumentException, Exception {
		roleHibernate.delete(role.getId());
	}
	
	@Test(priority=1)
	public void insertTest() {
		Endereco address = new Endereco("67130-480", "198");
		Contact contact = new Contact("shimada@email.com", "91989898989");
		
		collab = new Collaborator();
		collab.setName("Caio");
		collab.setCpf("339.821.380-43");
		collab.setPis("511.38341.66-9");
		collab.setPwdHash("senha");
		collab.setEndereco(address);
		collab.setContact(contact);
		collab.setAdmissionDate("20/12/2020");
		collab.setLastVacationDate("20/12/2020");
		collab.setRole(role);
		collab.setWorkHours(8);
		collab.setSalary(5000);
		int insertedId = hibernate.insert(collab);
		Assert.assertNotEquals(insertedId, -1);
		collab.setId(insertedId);
	}
	
	@Test(priority=2)
	public void findByIdTest() {
		Collaborator collabFound = (Collaborator) hibernate.find("id", String.valueOf(collab.getId()));
		Assert.assertEquals(collabFound.getCpf(), collab.getCpf());
	}
	
	@Test(priority=3)
	public void findByCpfTest() {
		Collaborator collabFound = (Collaborator) hibernate.find("cpf", collab.getCpf());
		Assert.assertEquals(collabFound.getCpf(), collab.getCpf());
	}
	
	@Test(priority=4)
	public void getAllTest() {
		Assert.assertNotNull(hibernate.getAll());
	}
	
	@Test(priority=5)
	public void updateTest() {
		collab.getEndereco().setComplemento("420");
		collab.setWorkHours(6);
		collab.setSalary(2500);
		
		Assert.assertEquals(hibernate.update(collab), collab.getId());
	}
	
	@Test(priority=6)
	public void deleteTest() {
		Assert.assertEquals(hibernate.delete(collab.getId()), collab.getId());
	}
}
