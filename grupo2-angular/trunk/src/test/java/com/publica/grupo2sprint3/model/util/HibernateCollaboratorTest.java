package com.publica.grupo2sprint3.model.util;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

public class HibernateCollaboratorTest {
	
	@Test
	public void insertCollaboratorTest() {
	try {
		Session session =  HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		Collaborator collaborator = new Collaborator("Ana", "124.192.319-10", "906.14373.97-8");
		
		session.save(collaborator);
	
		session.getTransaction().commit();
	    session.close();
	    HibernateConnectorFactory.shutdown();
	    
	    
	}
	catch(Exception e) {
		Assert.fail(e.toString());
		}
	}
}
