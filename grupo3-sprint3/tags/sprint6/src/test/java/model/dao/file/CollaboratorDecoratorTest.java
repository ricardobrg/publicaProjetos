package model.dao.file;

import java.util.ArrayList;

import org.testng.annotations.Test;

import model.entities.person.Collaborator;

public class CollaboratorDecoratorTest {

	@Test
	public void addTest() {

		var collab = new Collaborator("Diego","08309171951");	

		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	

		ArrayList<Collaborator> collabs = c1.getAll();

		try{
			collab.setId(collabs.get(collabs.size()-1).getId() +1);
		} catch(NullPointerException e) {
			collab.setId(0);
		}

		c1.add(collab);

	}

	@Test
	public void findTest() {
		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	

		Collaborator collab2 = c1.findById(1);

		System.out.println(collab2.getName());
	}

	@Test
	public void deleteTest() {
		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	

		c1.deleteById(0);

	}

	@Test
	public void updateTest() {
		var collab = new Collaborator("Nicole","0sadsad654");	
		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	
		c1.updateById(collab, 0);

	}

	@Test
	public void getAllTest() {
		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	

		ArrayList<Collaborator> collabs = c1.getAll();

		for(int i = 0; i < collabs.size(); i++) {
			System.out.println("Nome: " + collabs.get(i).getName());
			System.out.println("CPF: " + collabs.get(i).getCpf());
		}

	}

	@Test
	public void findByCpfTest() {
		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	

		Collaborator collab2 = c1.findByCpf("08309171951");

		System.out.println(collab2.getName());
	}

	@Test
	public void deleteByCpfTest() {
		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	

		c1.deleteByCpf("08309171951");

	}

	@Test
	public void findByEmailTest() {
		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	

		Collaborator collab2 = c1.findByEmail("email@email.com");

		System.out.println(collab2.getName());
	}

	@Test
	public void deleteByEmailTest() {
		var c1 = new CollaboratorDecorator(new FileCrud<Collaborator>
		("database/colaborratorDecorator.txt", Collaborator.class));	

		c1.deleteByEmail("email@email.com");

	}

}
