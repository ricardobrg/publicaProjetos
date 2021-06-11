package model.dao;

import org.testng.Assert;
import org.testng.annotations.Test;

import model.entities.person.Collaborator;


public class CollaboratorDAOTest {

	@Test
	public void insertSelectTest() {
		CollaboratorDAO colabDAO = new CollaboratorDAO();
		Collaborator colab = new Collaborator();
		
		colab.setName("Saviola");
		colab.setCpf("08309171951");
		
		colabDAO.addObject(colab);
		Collaborator colab2 = (Collaborator) colabDAO.findObject("08309171951");
		
		Assert.assertEquals(colab.getName(),colab2.getName());
	}

	@Test
	public void updateObjectTest() {
		
		CollaboratorDAO colabDAO = new CollaboratorDAO();		
		Collaborator colab = (Collaborator) colabDAO.findObject("01865970026");		
		Collaborator colab2 = new Collaborator();
		
		colab2.setCpf("01865970026");
		colab2.setName("Truta");		
		colabDAO.updateObject(colab2, colabDAO.getAll().size()-1);
		
		Assert.assertNotEquals(colab.getName(), colab2.getName());
		
		colab = (Collaborator) colabDAO.findObject("01865970026");
		
		Assert.assertEquals(colab.getName(), colab2.getName());
	}
	
	@Test
	public void deleteObjectTest() {
		CollaboratorDAO colabDAO = new CollaboratorDAO();
		
		int size = colabDAO.getAll().size()-1;
		
		colabDAO.delObject(size);
		
		Assert.assertEquals(size, colabDAO.getAll().size());
		
	}
	
}
