package model.dao;

import java.util.ArrayList;
import java.util.Calendar;

import org.testng.annotations.Test;

import model.WorkEntry;
import model.entities.person.Collaborator;

public class WorkEntryDAOTest {

	@Test
	public void addObjectTest() {

		Collaborator colab = new Collaborator();
		colab.setCpf("19616084054");
		
		WorkEntry point = new WorkEntry(colab);
		
		point.clockIn();
		point.clockIn();
		
		WorkEntryDAO workDAO = new WorkEntryDAO();
		
		workDAO.addObject(point);
	}
	

	@Test
	public void delObjectTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void findObjectTest() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getAllTest() {
		WorkEntryDAO workDAO = new WorkEntryDAO();
		ArrayList<WorkEntry> teste = workDAO.getAll("19616084054");
		for(int i = 0; i < teste.size(); i++) {
			
			System.out.println("------------");
			System.out.println(teste.get(i).getCollaborator().getCpf());
			System.out.println("------------");
			System.out.println(teste.get(i).getDay().get(0).get(Calendar.DAY_OF_MONTH));
			System.out.println(teste.get(i).getDay().get(1).get(Calendar.DAY_OF_MONTH));
			System.out.println("------------");
		}
	}

	@Test
	public void updateObjectTest() {
		throw new RuntimeException("Test not implemented");
	}
}






