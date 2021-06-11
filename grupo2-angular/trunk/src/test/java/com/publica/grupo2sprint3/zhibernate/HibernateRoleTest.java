package com.publica.grupo2sprint3.zhibernate;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.dao.factory.HibernateConnectorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;

public class HibernateRoleTest {

	@Test
	public void insertRoleTest() {
		try {
			Session session = HibernateConnectorFactory.getSessionFactory().openSession();
			session.beginTransaction();

			Collaborator manager = new Collaborator("Manager");

			Department department = new Department("Desenvolvimento");
			Role role = new Role("Desenvolvedor Jr", department, 2500.00);

			session.save(manager);
			session.save(department);
			session.save(role);
			session.getTransaction().commit();
			session.close();
			HibernateConnectorFactory.shutdown();

		} catch (Exception e) {
			Assert.fail(e.toString());
		}
	}
}
