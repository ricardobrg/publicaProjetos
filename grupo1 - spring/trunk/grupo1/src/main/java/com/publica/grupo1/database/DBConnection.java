package com.publica.grupo1.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Production Db.
 * 
 * @author Vinicius
 *
 */
public class DBConnection {
	
	private static SessionFactory sessionFactory;
	
	private static Session session;
    
    private static SessionFactory buildSessionFactory() {
        try {
        	return new Configuration()
        			.addAnnotatedClass(com.publica.grupo1.model.entities.gender.Genre.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.permissions.role.Role.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.permissions.Permission.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.permissions.AccessLevel.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.permissions.role.Department.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.collaborator.Collaborator.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.contact.Contact.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.contact.NumberPhone.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.endereco.Endereco.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.endereco.CEP.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.payroll.Payroll.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.payroll.Discount.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.serviceprovider.PaymentType.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.serviceprovider.Payment.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.serviceprovider.ServiceProvider.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.vacations.TimeOffTracker.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.internalevents.InternalEvents.class)

        			.addAnnotatedClass(com.publica.grupo1.model.entities.point.Point.class)
        			.addAnnotatedClass(com.publica.grupo1.model.entities.point.PointType.class)
        			
					
        			.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
					.setProperty("hibernate.connection.url","jdbc:mysql://localhost/mydb?useTimezone=true&serverTimezone=UTC")
					.setProperty("hibernate.connection.username","root")
					.setProperty("hibernate.jdbc.time_zone","UTC")
					.setProperty("hibernate.dialect","org.hibernate.dialect.MariaDB53Dialect")
					.setProperty("hibernate.show_sql","true")
					.setProperty("hibernate.format_sql","false")
					.setProperty("hibernate.hbm2ddl.auto","update")
					.setProperty("hibernate.connection.autocommit","true")
					.buildSessionFactory();
        }
        catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
    	if(sessionFactory == null)
    		sessionFactory = buildSessionFactory();
    	return sessionFactory;
    }
  
    public static void shutdown() {
    	session.close();
        getSessionFactory().close();
    }
    
    public static Session getSession() {
    	getSessionFactory();
    	
    	if(session == null) 
			session = sessionFactory.openSession();
 		return session;
 	}
}
