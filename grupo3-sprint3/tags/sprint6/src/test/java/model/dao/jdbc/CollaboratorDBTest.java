package model.dao.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import model.entities.person.Collaborator;
import model.entities.person.Contact;
import model.entities.person.Endereco;
import model.entities.security.Role;

public class CollaboratorDBTest {
	
	CollaboratorDB db;
	String cpf;
	int id;
	
	@BeforeClass
	public void setTest() {
		db = (CollaboratorDB) CollaboratorDB.getInstance();
		cpf = "01888041293";
		
		try {
			db.delete("cpf", cpf);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(priority=2)
	public void selectTest() {
		try {
			Collaborator collab = db.find("cpf", cpf);
			Gson gson = new Gson();
			System.out.println(gson.toJsonTree(collab));
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(priority=3)
	public void getAllTest() {
		try {
			ArrayList<Collaborator> collabs = db.getAll();
			
			Gson gson = new Gson();
			System.out.println(gson.toJsonTree(collabs));
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(priority=1)
	public void insertTest() {
		Collaborator collab = new Collaborator();
		collab.setName("Caio");
		collab.setCpf(cpf);
		collab.setPis("511.38341.66-9");
		collab.setPwdHash("senha");
		collab.setEndereco(new Endereco("67130-480", "68"));
		collab.setContact(new Contact("email@gmail.com", "91989898989"));
		collab.setAdmissionDate("20/12/2020");
		collab.setLastVacationDate("20/12/2020");
		collab.setRole(new Role("Presidente"));
		collab.setWorkHours(8);
		collab.setSalary(5000);
		
		try {
			db.insert(collab);
			id = db.lastId("collaborators");
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}	
	}
	
	@Test(priority=4)
	public void updateTest() {
		Collaborator collab = new Collaborator();
		collab.setName("Caio");
		collab.setCpf(cpf);
		collab.setPwdHash("senha");
		collab.setEndereco(new Endereco("67130-480", "68"));
		collab.setContact(new Contact("email@gmail.com", "91989898989"));
		collab.setAdmissionDate("20/12/2020");
		collab.setLastVacationDate("20/12/2020");
		collab.setPis("511.38341.66-9");
		collab.setRole(new Role("Presidente"));
		collab.setWorkHours(6);
		collab.setSalary(2500);
		
		try {
			db.update(collab, id);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}	
		
	}
	
	@Test(priority=5)
	public void deleteTest() {
		try {
			db.delete("id", ""+id);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	

}
