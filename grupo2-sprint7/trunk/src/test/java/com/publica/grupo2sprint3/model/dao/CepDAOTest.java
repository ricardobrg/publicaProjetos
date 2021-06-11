package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Cep;

public class CepDAOTest {
	Cep cep = new Cep("61600-570");
	CepDAO dao = CepDAO.getInstance();

	@Test
	public void addTest() { 
		Assert.assertEquals(dao.add(cep), true);
	}

	@Test
	public void findByIdTest() {
	}

	@Test
	public void findByIdentifierTest() { 
		Cep result = (Cep) dao.findByIdentifier("61600-570");
		Assert.assertEquals(result.getLocalidade(), "Caucaia");
	}

	@Test
	public void getAllTest() { 
		ArrayList<Cep> array = dao.getAll();
		Assert.assertNotNull(array);
	}

	@Test
	public void getInstanceTest() { 
		Assert.assertEquals(dao.getClass(),
				new CepDAO().getClass());
	}

	@Test
	public void removeByIdTest() {
	}

	@Test
	public void removeByIdentifierTest() { 
		Assert.assertEquals(dao.removeByIdentifier("61600-570"), true);
	}

	@Test
	public void updateByIdTest() {

	}

	@Test
	public void updateByIdentifierTest() { 
		Cep newCep = new Cep("26443-140");
		Assert.assertEquals(dao.updateByIdentifier("61600-570", newCep), true);
	}
}
