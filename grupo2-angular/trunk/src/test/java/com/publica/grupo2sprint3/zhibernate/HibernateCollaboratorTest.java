package com.publica.grupo2sprint3.zhibernate;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class HibernateCollaboratorTest {
	@Test
	public void insertCollaboratorTest() {
	try {
		Session session =  HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		Contact contact2 = new Contact("(91) 98181-8181", "abababababa@gmail.com");
		Cep cep2 = Cep.getInstance("72322-108");
		Address address2 = new Address(cep2);
		

		Collaborator collab = new Collaborator("Teste", contact2, address2, "124.192.319-10", "974.04483.78-2",null, 0);
		Department department = new Department("Desenvolvimento");
		Role role = new Role("Desenvolvedor", department, 2000);
		
		role.setAccessLevel(AccessLevel.TOTAL);
		RoleDAO.getInstance().add(role);
		
		session.save(collab);
		session.save(department);
		session.save(role);
		
		Cep cep = Cep.getInstance("72322-108");
		Address address = new Address(cep);
		
		Contact contact = new Contact("(91) 98181-8181", "abababababa@gmail.com");
		Collaborator manager = new Collaborator("Manager", contact, address, "124.192.319-10", "974.04483.78-2",role, 0);
		session.save(manager);
		session.getTransaction().commit();
	    session.close();
	    HibernateConnectorFactory.shutdown();
	    
	    
	}
	catch(Exception e) {
		Assert.fail(e.toString());
		}
	}
}
