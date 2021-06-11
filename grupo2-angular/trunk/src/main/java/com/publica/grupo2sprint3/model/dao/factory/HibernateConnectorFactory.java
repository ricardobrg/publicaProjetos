package com.publica.grupo2sprint3.model.dao.factory;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnectorFactory {
	 private static final SessionFactory sessionFactory = buildSessionFactory();
     public static Session session;
     
	    private static SessionFactory buildSessionFactory() {
	        try {
	        	return new Configuration()
						.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
						.setProperty("hibernate.connection.url","jdbc:mysql://localhost/db_rh?useTimezone=true&serverTimezone=UTC")
						.setProperty("hibernate.connection.username","root")
						.setProperty("hibernate.jdbc.time_zone","UTC")
						.setProperty("hibernate.dialect","org.hibernate.dialect.MariaDB53Dialect")
						.setProperty("hibernate.show_sql","true")
						.setProperty("hibernate.format_sql","false")
						.setProperty("hibernate.hbm2ddl.auto","update")
						.setProperty("hibernate.connection.autocommit","true")
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.Cep.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.Address.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.Contact.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.Person.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.legalperson.LegalPerson.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.physicalperson.Role.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.physicalperson.Point.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.physicalperson.Payroll.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.physicalperson.Discount.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.physicalperson.Department.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.physicalperson.Collaborator.class)
						.addAnnotatedClass(com.publica.grupo2sprint3.model.person.physicalperson.Event.class)
						.buildSessionFactory();
	         }
	        catch (Throwable e) {
	            //System.err.println("Initial SessionFactory creation failed: " + e);
	            e.printStackTrace();
	            throw new ExceptionInInitializerError(e);
	        }
	    }
	  
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	 
	    public static Session getSession() {
	    	if(session == null) {
	    		session = getSessionFactory().openSession();
	    	}
	        return session;
	    }
	    
	    public static void shutdown() {
	        getSessionFactory().close();
	    }
}
