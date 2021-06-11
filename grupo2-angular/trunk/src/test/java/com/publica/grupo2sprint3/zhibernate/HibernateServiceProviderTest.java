package com.publica.grupo2sprint3.zhibernate;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;

public class HibernateServiceProviderTest {
	
	@Test
	public void insertServiceProvider() {
		try {
			Session session =  HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();
			
			Contact contact = new Contact("(47) 99233-2212", "carloscarlos@gmail.com");
			ServiceProvider serviceProvider = new ServiceProvider("Carlos", contact, 
					"Maria e T�nia Joalheria ME", "90.523.601/0001-71");
			
			session.save(serviceProvider);
			
			session.getTransaction().commit();
			session.close();
			HibernateConnectorFactory.shutdown();


		}
		catch(Exception e) {
			Assert.fail(e.toString());
		}
	}
	
	@Test
	public void deleteServiceProvider() {
		try {
			Session session =  HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();
			
			Contact contact = new Contact("(47) 99233-2212", "carloscarlos@gmail.com");
			ServiceProvider serviceProvider = new ServiceProvider("Carlos", contact, 
					"Maria e T�nia Joalheria ME", "90.523.601/0001-71");
			
			session.update(serviceProvider);
			
			session.getTransaction().commit();
			session.close();
			HibernateConnectorFactory.shutdown();


		}
		catch(Exception e) {
			Assert.fail(e.toString());
		}
	}
}
