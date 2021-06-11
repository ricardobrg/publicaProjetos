package com.publica.grupo2sprint3.view;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.person.physicalperson.Role;
import com.publica.grupo2sprint3.model.person.physicalperson.Role.AccessLevel;
import com.publica.grupo2sprint3.view.providerView.ProviderViewList;
import com.publica.grupo2sprint3.view.providerView.ProviderViewMenu;
import com.publica.grupo2sprint3.view.providerView.ProviderViewShow;

public class ProviderViewTest {

	Collaborator collab;
	ServiceProvider provider;
	Role role;
	
	Cep cep = Cep.getInstance("45245-452");

	Contact contact = new Contact("(91) 98181-8181", "carolsantos@publica.com");
	Address address = new Address(cep);
	
	
	@BeforeClass
	public void setTest() {
		Department department = new Department("Desenvolvimento");
		role = new Role("Desenvolvedor", department, 2000);
		role.setAccessLevel(AccessLevel.TOTAL);
		
		collab = new Collaborator("Caio Shimada", contact, address, "741.918.140-36", "510.87976.81-6", role, "20/05/1998", 0, 8);
		
		provider = new ServiceProvider("Fantasia", contact, address, "Alguma coisa aqui", "35.554.915/0001-23", 5, "Descrição");
	}
	
	@Test
	public void viewMainMenuTest() {
		String expectedReturn = "\n--------- Prestadores de Serviços ---------\n"
				+ "0. Voltar\n"
				+ "1. Lista de Prestador\n"
				+ "2. Cadastrar Novo Prestador\n";
		Assert.assertEquals(ProviderViewMenu.getInstance(collab).getOutput(), expectedReturn);
	}
		
	@Test
	public void viewListTest() {
		ArrayList<ServiceProvider> providers = new ArrayList<ServiceProvider>();
		
		providers.add(provider);

		String expectedReturn = String.format("%-5s %-25s %-25s %-25s\n", "Id", "Nome Fantasia", "CNPJ", "Departamento");

		for (int i = 0; i < providers.size(); i++) {
			expectedReturn += String.format("%-5d %-25s %-25s %-25s\n", i, providers.get(i).getName(), 
					providers.get(i).getCnpj()); //providers.get(i).getDepartment().getName()
		}

		Assert.assertEquals(ProviderViewList.getInstance(collab, providers).getOutput(), expectedReturn);
		
	}

	@Test
	public void viewShowTest() {
		String expectedReturn = "\n--------- Dados de " + provider.getName() + " ---------\n"
				+ "Nome Fantasia: " + provider.getName() + "\n"
				+ "Razão Social: " + provider.getSocialReason() + "\n"
				+ "Cnpj: " + provider.getCnpj() + "\n"
				//+ "Departamento: " + provider.getDepartment().getName() + "\n"
				+ "Endereço: " + provider.getAddress().getLocality() + "\n"
				+ "Cep: " + provider.getAddress().getCep() + "\n"
				+ "Email de Contato: " + provider.getContact().getEmail() + "\n"
				+ "Telefone de Contato: " + provider.getContact().getPhone() + "\n"
				+ "Valor: " + provider.getPrice() + "\n"
				+ "Descrição: " + provider.getDescription() + "\n";
		
		Assert.assertEquals(ProviderViewShow.getInstance(collab, provider).getOutput(), expectedReturn);
	}
	
}
