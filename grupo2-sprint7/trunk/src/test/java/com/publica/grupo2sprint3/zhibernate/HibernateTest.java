package com.publica.grupo2sprint3.zhibernate;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.Person;

public class HibernateTest {

	@Test
	public void insertPersonTest() {
		try {
			Session session =  HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();
			
			
			Cep cep = Cep.getInstance("72322-108");
			Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
			Address address = new Address(cep);
			Person person = new Person("Ana", contact, address);
		
			
			session.save(person);
			session.getTransaction().commit();
		    session.close();
		    HibernateConnectorFactory.shutdown();
		    
		    
		}catch(Exception e) {
			Assert.fail(e.toString());
		}
	}
}
