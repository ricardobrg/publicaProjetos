package com.publica.grupo2sprint3.zhibernate;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

public class HibernateDepartmentTest {
	
	@Test
	public void insertDepartmentTest() {
	try {
		Session session =  HibernateConnectorFactory.getSessionFactory().openSession();
		session.beginTransaction();
		
		Collaborator manager = new Collaborator("Manager");
		Department department = new Department("Suporte");

		session.save(manager);
		session.save(department);
		session.getTransaction().commit();
	    session.close();
	    HibernateConnectorFactory.shutdown();
	    
	}
	catch(Exception e) {
		Assert.fail(e.toString());
		}
	}
}
