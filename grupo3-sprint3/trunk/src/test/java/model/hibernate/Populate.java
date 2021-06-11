package model.hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import models.entities.events.Event;
import models.entities.person.Collaborator;
import models.entities.person.Contact;
import models.entities.person.Endereco;
import models.entities.person.ServiceProvider;
import models.entities.security.AccessLevel;
import models.entities.security.Role;
import models.entities.workHours.WorkEntry;
import models.hibernate.CollaboratorHibernate;
import models.hibernate.EventHibernate;
import models.hibernate.HibernateConector;
import models.hibernate.RoleHibernate;
import models.hibernate.ServiceProviderHibernate;
import models.hibernate.WorkHoursHibernate;

public class Populate {

	ArrayList<Role> roles = new ArrayList<Role>();
	RoleHibernate roleHibernate;
	CollaboratorHibernate collabHibernate;
	ServiceProviderHibernate servHibernate;
	WorkHoursHibernate workHibernate;
	EventHibernate eventHibernate;
	SessionFactory sessionFactory;
	String database;
	
	@BeforeClass
	public void setTest() {
		roleHibernate   = new RoleHibernate();
		collabHibernate = new CollaboratorHibernate();
		servHibernate   = new ServiceProviderHibernate();
		workHibernate   = new WorkHoursHibernate();
		eventHibernate = new EventHibernate();
		sessionFactory  = HibernateConector.getSessionFactory();
		database = "group3_hr";
		
		// ----- Se não for teste, comentar as linhas abaixo ----- //
//		roleHibernate  .setTestDatabase();
//		collabHibernate.setTestDatabase();
//		servHibernate  .setTestDatabase();
//		workHibernate  .setTestDatabase();
//		servHibernate  .setTestDatabase();
//		eventHibernate .setTestDatabase();
//		sessionFactory = HibernateConector.getTestSessionFactory();
//		database = "group3_hr_tests";
		// --------------------------||--------------------------- //
	}
	
	@Test(priority=1)
	public void populate() throws Exception {
		roles.add(new Role("Presidente", AccessLevel.TOTAL));
		roles.add(new Role("Gerente", 	 AccessLevel.MEDIUM));
		roles.add(new Role("Dev Junior", AccessLevel.BASIC));

		for (Role role : roles) 
			roleHibernate.insert(role);

		populateCollaborators();
		populateServiceProviders();
		populateWorkEntries();
	}

	public void populateCollaborators() {
		ArrayList<Endereco> addresses = new ArrayList<Endereco>();
		addresses.add(new Endereco("78090-296", "595"));
		addresses.add(new Endereco("67130-480", "251"));
		addresses.add(new Endereco("79904-001", "420, apt 69"));

		ArrayList<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact("giovanni@email.com", "47956565656"));
		contacts.add(new Contact("shimada@email.com",  "91989898989"));
		contacts.add(new Contact("gmail@email.com",    "47958654785"));

		Collaborator giov = new Collaborator();
		giov.setName("Giovanni");
		giov.setCpf("083.091.719-51");
		giov.setPis("817.05300.82-6");
		giov.setPwdHash("senha");
		giov.setEndereco(addresses.get(0));
		giov.setContact(contacts.get(0));
		giov.setAdmissionDate("01/01/2005");
		giov.setLastVacationDate("24/12/2019");
		giov.setRole(roles.get(0));
		giov.setWorkHours(8);
		giov.setSalary(3000);
		collabHibernate.insert(giov);

		Collaborator caio = new Collaborator();
		caio.setName("Caio");
		caio.setCpf("018.880.412-93");
		caio.setPis("511.38341.66-9");
		caio.setPwdHash("senha");
		caio.setEndereco(addresses.get(1));
		caio.setContact(contacts.get(1));
		caio.setAdmissionDate("20/12/2015");
		caio.setLastVacationDate("20/12/2015");
		caio.setRole(roles.get(1));
		caio.setWorkHours(18);
		caio.setSalary(1000);
		collabHibernate.insert(caio);

		Collaborator fulano = new Collaborator();
		fulano.setName("Fulano");
		fulano.setCpf("959.984.910-84");
		fulano.setPis("919.19831.82-0");
		fulano.setPwdHash("senha");
		fulano.setEndereco(addresses.get(2));
		fulano.setContact(contacts.get(2));
		fulano.setAdmissionDate("18/05/2020");
		fulano.setLastVacationDate("18/05/2020");
		fulano.setRole(roles.get(2));
		fulano.setWorkHours(8);
		fulano.setSalary(4800);
		collabHibernate.insert(fulano);
		
		Event event = new Event();
		ArrayList<Collaborator> collabs = new ArrayList<>();
		collabs.add(giov);
		collabs.add(caio);
		event.setName("Evento Qualquer");
		event.setCollaborator(fulano);
		event.setSubscribers(collabs);
		eventHibernate.insert(event);
	}


	public void populateServiceProviders() {
		Endereco address = new Endereco("67130-480", "68");
		Contact contact = new Contact("email@gmail.com", "91999999999");
		
		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setName("Um provider");
		serviceProvider.setDescription("Uma desc");
		serviceProvider.setSocialReason("uma social reason");
		serviceProvider.setCnpj("83459260000157");
		serviceProvider.setEndereco(address);
		serviceProvider.setContact(contact);
		servHibernate.insert(serviceProvider);
	}
	
	public void populateWorkEntries() {
		Collaborator giov2 = (Collaborator) collabHibernate.find("cpf", "08309171951");
		Collaborator caio  = (Collaborator) collabHibernate.find("cpf", "01888041293");

		WorkEntry wk  = new WorkEntry(giov2);
		WorkEntry wk2 = new WorkEntry(caio);
		
		for(int i = 0; i < 22; i++) {
			wk.setClock(LocalDateTime.of(2020,10,01+i,6,0));
			wk2.setClock(LocalDateTime.of(2020,10,01+i,6,0));
			workHibernate.insert(wk);
			workHibernate.insert(wk2);
			
			wk.setClock(LocalDateTime.of(2020,10,01+i,14,0));
			wk2.setClock(LocalDateTime.of(2020,10,01+i,14,0));
			workHibernate.insert(wk);
			workHibernate.insert(wk2);
			
		}
	}

	@Test(priority=2)
	public void destroy() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.createSQLQuery("DROP   DATABASE " + database).executeUpdate();
		session.createSQLQuery("CREATE DATABASE " + database).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

}