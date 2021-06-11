package com.publica.grupo2sprint3.view.providerView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.dao.DepartmentDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

/**
 * View to edit a Provider.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class ProviderViewEdit extends ProviderView {

	private static ProviderViewEdit instance;
	private ServiceProvider providerFound;
	private Contact contact;
	private Address address;
	private Cep cep;

	private ProviderViewEdit(Collaborator collab, ServiceProvider providerFound) {
		super(collab);
		this.providerFound = providerFound;
	}

	public static ProviderViewEdit getInstance(Collaborator collab, ServiceProvider providerFound) {
		if (instance == null) {
			instance = new ProviderViewEdit(collab, providerFound);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Overwrites display method since output and input are mixed.
	 * 
	 * Calls the edit method instead.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void display() {
		edit();
	}

	/**
	 * Displays the form to edit a service provider as well as receive input and
	 * send it to the update method in the com.publica.grupo2sprint3.controller.
	 * 
	 * Blank input keeps current data.
	 * 
	 * @author Caio Shimada
	 */
	private void edit() {

		System.out.println("\nFormul�rio de Edi��o. Deixe em branco para manter.");

		System.out.println("\nNome Fantasia Atual: " + providerFound.getName());
		System.out.print("Novo Nome Fantasia: ");
		Main.scan.nextLine();
		String name = Main.scan.nextLine();
		if (name == "")
			name = providerFound.getName();

		System.out.println("\nRaz�o Social Atual: " + providerFound.getSocialReason());
		System.out.print("Nova Raz�o Social: ");
		String social = Main.scan.nextLine();
		if (social == "")
			social = providerFound.getSocialReason();

		System.out.println("\nEmail para Contato Atual: " + providerFound.getContact().getEmail());
		System.out.print("Novo Email de Contato: ");
		String email = Main.scan.nextLine();
		if (email == "")
			email = providerFound.getContact().getEmail();

		System.out.println("\nCNPJ Atual: " + providerFound.getCnpj());
		System.out.print("Novo CNPJ: ");
		String cnpj = Main.scan.nextLine();
		if (cnpj == "")
			cnpj = providerFound.getCnpj();

		System.out.println("\nTelefone Atual: " + providerFound.getContact().getPhone());
		System.out.print("Novo Telefone: ");
		String phone = Main.scan.nextLine();
		if (phone == "")
			phone = providerFound.getContact().getPhone();

		System.out.println("\nEndere�o Atual: " + providerFound.getAddress());
		System.out.print("Novo Endere�o: ");
		String address = Main.scan.nextLine();
		if (address == "")
			address = providerFound.getAddress().toString();

		System.out.println("\nCEP Atual: " + providerFound.getAddress().getCep());
		System.out.print("Novo CEP: ");
		String cep = Main.scan.nextLine();
		this.cep = Cep.getInstance(cep);
		if (this.cep.getCep() == "")
			this.cep.getCep().toString().equals(providerFound.getAddress().getCep().toString());

		//System.out.println("\nDepartamento Atual: " + providerFound.getDepartment());
		
		ArrayList<Department> departments = DepartmentDAO.getInstance().getAll();
		
		System.out.printf("%-5s %-25s %-25s\n", "Id", "Gerente", "Departamento");
		for (int i = 0; i < departments.size(); i++) {
			try {
			} catch (Exception e) {
				System.out.printf("%-5d %-25s %-25s\n", i+1, "null", ((Department) departments.get(i)).getName());
			}

		}
		
		System.out.print("Id do Novo Departamento: ");
		int departIndex = Main.scan.nextInt()-1;
		Department department = (Department) DepartmentDAO.getInstance().findById(departIndex);
		

		System.out.println("\nValor Atual: " + providerFound.getPrice());
		System.out.print("Novo Valor (-1 para manter): ");
		double price = Main.scan.nextDouble();
		Main.scan.nextLine();
		if (price == -1)
			price = providerFound.getPrice();

		System.out.println("\nDescri��o Atual: " + providerFound.getDescription());
		System.out.print("Novo Descri��o: ");
		String description = Main.scan.nextLine();
		if (description == "")
			description = providerFound.getDescription();

		contact = new Contact(phone, email);
		this.address = new Address(this.cep);
		boolean updated = controller.update(providerFound.getCnpj(), name, social, contact, this.address, cnpj,
				department, price, description);
		if (updated) {
			System.out.println("\nPrestador Atualizado!!");
			controller.goToInitial();
		} else {
			System.out.println("\nN�o foi poss�vel atualizar o Prestador. Verifique as entradas e tente novamente.\n");
			System.out.println("1. Tentar Novamente / 0. Voltar");
			
			int input = Main.scan.nextInt();
			switch (input) {
			case 1:
				controller.edit(providerFound.getCnpj());
				break;
			case 0:
				controller.goToInitial();
				break;
			default:
				System.out.println("Op��o Inv�lida. Retornando a �rea Inicial");
				controller.goToInitial();
				break;
			}
		}
	}

	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void readInput() {
		// TODO Auto-generated method stub

	}

}
