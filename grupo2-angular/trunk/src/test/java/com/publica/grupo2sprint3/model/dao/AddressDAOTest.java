package com.publica.grupo2sprint3.model.dao;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;

public class AddressDAOTest {

	AddressDAO dao = AddressDAO.getInstance();
	Cep cep = Cep.getInstance("89025-400");
	Cep cep2 = Cep.getInstance("89025-420");
	Address address = new Address(cep);
	Address address2 = new Address(cep2);

	@Test
	public void addTest() { 
		Assert.assertEquals(dao.add(address), true);
		Assert.assertEquals(dao.add(address2), true);
	}

	@Test
	public void findByIdTest() { 
		Address result = (Address) dao.findById(4);
		Assert.assertEquals(result.getLocality(), "Blumenau");
		}
	

	@Test
	public void findByIdentifierTest() { 
		Address result = (Address)dao.findByIdentifier("89025-400");
		Assert.assertEquals(result.getCep(), "89025-400");
		
	}

	@Test
	public void getAllTest() { 
		ArrayList<Address> array = dao.getAll();
		Assert.assertNotNull(array);
	}

	@Test
	public void getInstanceTest() { 
		assertEquals(dao.getClass(), new AddressDAO().getClass());
	}

	@Test
	public void removeByIdTest() { 
		Assert.assertEquals(dao.removeById(3), true);
	}

	@Test
	public void removeByIdentifierTest() { 
		Assert.assertEquals(dao.removeByIdentifier("89025-400"), true);
	}

	@Test
	public void updateByIdTest() { 
		Cep newCep = Cep.getInstance("27288-080");
		Address newAddress = new Address(newCep);
		Assert.assertEquals(dao.updateById(10, newAddress), true);
	
	}

	@Test
	public void updateByIdentifierTest() {}
	
}
