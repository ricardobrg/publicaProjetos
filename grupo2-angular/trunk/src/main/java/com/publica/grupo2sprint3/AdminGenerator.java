package com.publica.grupo2sprint3;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.RoleDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;

public class AdminGenerator {
	public static void main(String[] args) {
		CollaboratorDAO dao =  CollaboratorDAO.getInstance();
		
		Cep cep = new Cep("89120-000");
		
		Address address = new Address(cep);
		Contact contact = new Contact("(11) 98181-0220", "entaoquerdizer@gmail.com");
		
		Role role = new Role("Streammer", new Department("ADM"), 3500.00, AccessLevel.TOTAL);
		RoleDAO.getInstance().add(role);
		
	
		Collaborator admin = new Collaborator("Manager", contact, address, role, 0.00);
		admin.setAdminCpf();
		admin.setUser("admin");
		admin.setPassword("Senha!@#123");
		admin.setInVacation(false);
				
		dao.add(admin);
	}
}
