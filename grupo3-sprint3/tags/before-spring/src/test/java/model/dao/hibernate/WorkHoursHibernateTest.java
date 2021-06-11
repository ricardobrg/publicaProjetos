package model.dao.hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.testng.annotations.Test;

import model.entities.person.Collaborator;
import model.entities.person.Contact;
import model.entities.person.Endereco;
import model.entities.workHours.WorkEntry;

public class WorkHoursHibernateTest {

	WorkHoursHibernate hibernate;
	CollaboratorHibernate collabHibernate;
	WorkEntry workEntry;
	Collaborator collab;


	/*public void deleteAfter() {
		
		collabHibernate.deleteById(collab.getId());
		
	}*/
	

//	@BeforeTest
	public void setBeforeTest() {
		hibernate = new WorkHoursHibernate();
		collabHibernate = new CollaboratorHibernate();
		
		hibernate.setTestDatabase();
		collabHibernate.setTestDatabase();
		
		Endereco address = new Endereco("67130-480", "68");
		Contact contact = new Contact("email@gmail.com", "91989898989");
		
		collab = new Collaborator();
		collab.setName("Jonas");
		collab.setCpf("08309171951");
		collab.setPis("511.38341.66-9");
		collab.setPwdHash("senha");
		collab.setEndereco(address);
		collab.setContact(contact);
		collab.setAdmissionDate("20/12/2020");
		collab.setLastVacationDate("20/12/2020");
		collab.setWorkHours(8);
		collab.setSalary(37000);
		
		int insertedId = collabHibernate.insert(collab);
		collab.setId(insertedId);
		
		workEntry = new WorkEntry(collab);
		workEntry.setClock(LocalDateTime.now());
	}
	
	@Test
	public void insertNewClockDatabase() {
		hibernate.insert(workEntry);
	}
	
	@Test
	public void findBetweenDates() {
		
		LocalDateTime date1 = LocalDateTime.of(2020, 05, 10, 0, 0);
		
		
		LocalDateTime date2 = LocalDateTime.of(2021, 05, 10, 0, 0);
		
		hibernate = new WorkHoursHibernate();
		
		ArrayList<LocalDateTime> data = hibernate.findBetweenDates("08309171951", date1, date2);
		
		for(LocalDateTime item : data) {
			System.out.println(item.toString());
		}
		
	}

}
