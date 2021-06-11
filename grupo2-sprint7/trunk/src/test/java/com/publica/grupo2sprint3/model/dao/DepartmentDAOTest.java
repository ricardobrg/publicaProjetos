package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

public class DepartmentDAOTest {
	DepartmentDAO dao = DepartmentDAO.getInstance();
	Department[] initialDeparts = { new Department("Desenvolvimento"), new Department("Recursos Humanos"),
						   			new Department("Ger�ncia"), new Department("Tecnologia") };
	
	@BeforeMethod
	public void start() {
		DepartmentDAO dao = DepartmentDAO.getInstance();
		ArrayList<Department> allDeparts = dao.getAll();
		
		for(Department depart : allDeparts) {
			dao.removeById(depart.getId());
		}
		
		for(Department depart : initialDeparts) {
			dao.add(depart);
		}
	}

	@Test
	public void addTest() {	
		DepartmentDAO dao = DepartmentDAO.getInstance();
		Collaborator manager = new Collaborator("Manager", "637.809.300-80");
		Department depart = new Department("Lideran�a", manager);
		Assert.assertEquals(dao.add(depart), true);
	}

	@Test
	public void findByIdTest() { 
		DepartmentDAO dao = DepartmentDAO.getInstance();
		Department result = (Department) dao.findById(initialDeparts[0].getId());
		Assert.assertEquals(result.getName(), "Desenvolvimento");
	}

	@Test
	public void findByIdentifierTest() {
		DepartmentDAO dao = DepartmentDAO.getInstance();
		Department result = dao.findByIdentifier("Desenvolvimento");
		Assert.assertEquals(result.getName(), "Desenvolvimento");
	}

	@Test
	public void updateByIdentifierTest() {
		DepartmentDAO dao = DepartmentDAO.getInstance();
		String identifier = "Desenvolvimento";
		String newName = "TecnologiaTeste";
		Department result = (Department) DepartmentDAO.getInstance().findByIdentifier(identifier);
		result.setName(newName);
		dao.updateByIdentifier(identifier, result);
		Department updatedResult = dao.findByIdentifier(newName);
		Assert.assertNotNull(updatedResult);
		Assert.assertNotNull(result.getId());
		Assert.assertEquals(result.getId(), updatedResult.getId());
	}

	@Test
	public void updateByIdTest() {
		DepartmentDAO dao = DepartmentDAO.getInstance();
		Department depart = initialDeparts[2];
		depart.setName("Financeiro");
		Assert.assertEquals(dao.updateById(initialDeparts[2].getId(), depart), true);
	}


	@Test
	public void removeByIdTest() {
		DepartmentDAO dao = DepartmentDAO.getInstance();
		Assert.assertEquals(dao.removeById(initialDeparts[0].getId()), true);
	}

	@Test
	public void removeByIdentifierTest() {
		DepartmentDAO dao = DepartmentDAO.getInstance();
		Assert.assertEquals(dao.removeByIdentifier("Tecnologia"), true);
	}

	
	@Test
	public void getAllTest() { 
		DepartmentDAO dao = DepartmentDAO.getInstance();
		ArrayList<Department> array = dao.getAll();
		Assert.assertNotNull(array);
	}
}
