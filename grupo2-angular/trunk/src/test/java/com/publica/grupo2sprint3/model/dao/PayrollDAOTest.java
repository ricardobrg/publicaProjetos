package com.publica.grupo2sprint3.model.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Payroll;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class PayrollDAOTest {
	@Test
	public void addTest() {
		Department department = new Department("Desenvolvimento");
		Role role = new Role("Desenvolvedor", department, 2000);
		Cep cep = new Cep("89120-000");
		Address address = new Address(cep);
		Contact contact = new Contact("(91) 98181-8181", "abababababa@gmail.com");
		Collaborator collaborator = new Collaborator("Pessoa",contact, address);
		collaborator.setCpf("124.192.319-10");
		collaborator.setRole(role);

		LocalDate date = LocalDate.now();
		Payroll payroll =  new Payroll(date, collaborator);
		PayrollDAO dao = new PayrollDAO();
		
		
		dao.add(payroll);
		System.out.println(payroll.toString());
	}

	@Test
	public void getAllTest() {
		ArrayList<Payroll> points = PayrollDAO.getInstance().getAll();
		Assert.assertNotNull(points);

	}
		
	@Test
	public void findByIdTest() {
		Cep cep = new Cep("89120-000");
		Address address = new Address(cep);
		Contact contact = new Contact("(11) 98181-0220", "entaoquerdizer@gmail.com");
		
		Role role = new Role("Streammer", new Department("Suporte"), 3500.00, AccessLevel.TOTAL);
		RoleDAO.getInstance().add(role);
		
	
		Collaborator admin = new Collaborator("Manager", contact, address, role, 0.00);
		admin.setCpf("081.621.970-28");
		admin.setUser("admin");
		admin.setPassword("12345dD!");
		
		LocalDate date = LocalDate.now();
		Payroll payroll = new Payroll(date, admin);
		
		PayrollDAO.getInstance().updateById(6, payroll);
		Payroll result = (Payroll) PayrollDAO.getInstance().findById(6);
		System.out.println(result.toString());
	}

}