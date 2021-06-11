package com.publica.grupo2sprint3.zhibernate;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.legalperson.LegalPerson;

public class HibernateLegalPersonTest {
	@Test
	public void insertLegalPerson() {
		try {
			Session session =  HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();
			
			Contact contact = new Contact("(47) 99233-2212", "carloscarlos@gmail.com");
			LegalPerson legalPerson = new LegalPerson("Carlos", contact);
			
			session.save(legalPerson);
			
			session.getTransaction().commit();
			session.close();
			HibernateConnectorFactory.shutdown();


		}
		catch(Exception e) {
			Assert.fail(e.toString());
		}
	}
}
