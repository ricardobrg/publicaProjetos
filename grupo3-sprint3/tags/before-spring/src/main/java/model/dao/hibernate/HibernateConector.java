package model.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * 
 * @author Ricardo Gonçalves
 *
 */
public class HibernateConector {

  private static final SessionFactory sessionFactory = buildSessionFactory(false);
  private static final SessionFactory test_sessionFactory = buildSessionFactory(true);

  /**
   * Loads the Hibernate configuration file
   *
   * @author Ricardo Gonçalves
   * @author Caio Shimada
   * 
   * @param test a boolean that defines if the configuration file to be used is for tests or not
   * @return a SessionFactory object
   */
  private static SessionFactory buildSessionFactory(boolean test) {
    try {
      if (test)
        // return new Configuration().configure(new
        // File("src/test/resources/META-INF/hibernate.cfg.xml"))
        // .buildSessionFactory();
        return new Configuration()
            .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
            .setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost/group3_hr_tests?useTimezone=true&serverTimezone=UTC")
            .setProperty("hibernate.connection.username", "root")
            .setProperty("hibernate.jdbc.time_zone", "UTC")
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect")
            .setProperty("hibernate.show_sql", "true").setProperty("hibernate.format_sql", "false")
            .setProperty("hibernate.hbm2ddl.auto", "update")
            .setProperty("hibernate.connection.autocommit", "true")
            .addAnnotatedClass(model.entities.person.ServiceProvider.class)
            .addAnnotatedClass(model.entities.workHours.WorkEntry.class)
            .addAnnotatedClass(model.entities.person.Collaborator.class)
            .addAnnotatedClass(model.entities.person.Endereco.class)
            .addAnnotatedClass(model.entities.person.Contact.class)
            .addAnnotatedClass(model.entities.security.Role.class)
            .addAnnotatedClass(model.entities.payroll.Payroll.class)
            .addAnnotatedClass(model.entities.payroll.DiscountSalary.class).buildSessionFactory();

      else
        return new Configuration()
            .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
            .setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost/group3_hr?useTimezone=true&serverTimezone=UTC")
            .setProperty("hibernate.connection.username", "root")
            .setProperty("hibernate.jdbc.time_zone", "UTC")
            .setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect")
            .setProperty("hibernate.show_sql", "true").setProperty("hibernate.format_sql", "false")
            .setProperty("hibernate.hbm2ddl.auto", "update")
            .setProperty("hibernate.connection.autocommit", "true")
            .addAnnotatedClass(model.entities.person.ServiceProvider.class)
            .addAnnotatedClass(model.entities.workHours.WorkEntry.class)
            .addAnnotatedClass(model.entities.person.Collaborator.class)
            .addAnnotatedClass(model.entities.person.Endereco.class)
            .addAnnotatedClass(model.entities.person.Contact.class)
            .addAnnotatedClass(model.entities.security.Role.class)
            .addAnnotatedClass(model.entities.payroll.Payroll.class)
            .addAnnotatedClass(model.entities.payroll.DiscountSalary.class).buildSessionFactory();
    } catch (Throwable e) {
      System.err.println("Initial SessionFactory creation failed: " + e);
      throw new ExceptionInInitializerError(e);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static SessionFactory getTestSessionFactory() {
    return test_sessionFactory;
  }

  public static void shutdown() {
    getSessionFactory().close();
  }
}
