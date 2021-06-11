package com.publica.grupo2sprint3;

import java.time.LocalDate;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.PayrollDAO;
import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Payroll;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class AdminGenerator {
	public static void main(String[] args) {
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		PayrollDAO dao2 = PayrollDAO.getInstance();
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
		admin.addDiscount("Brotheragem", 500.00);
		Payroll payroll = new Payroll(date, admin);
		
		dao.add(admin);
		dao2.add(payroll);
	}
}
