package com.publica.grupo2sprint3.model.dao;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

public class HibernateDepartmentDAOTest {

	@Test
	public void addDepartmentTest() {
		try {
			Session session = HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();

			Collaborator analyst = new Collaborator("analyst");

			Department department = new Department("Suporte");

			session.save(analyst);
			session.save(department);
			session.getTransaction().commit();
			session.close();
			HibernateConnectorFactory.shutdown();

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}
}
