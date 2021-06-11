package com.publica.grupo2sprint3.view.providerView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.dao.DepartmentDAO;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Cep;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;

/**
 * View to display and receive data to create a new Provider
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 * 
 */
public class ProviderViewCreate extends ProviderView{
	
	private static ProviderViewCreate instance;
	private Contact contact;
	private Address address;
	private Cep cep;
	
	private ProviderViewCreate(Collaborator collab) {
		super(collab);
		// TODO Auto-generated constructor stub
	}
	
	public static ProviderViewCreate getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new ProviderViewCreate(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * Overwrites display method since output and input are mixed.
	 * 
	 * Calls the create method instead.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void display() {
		create();
	}
	
	/**
	 * Displays the Create Provider form and sends the input data to the Store
	 * method in the Controller.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	private void create() {
		
		System.out.println("Formulário de Cadastro");

		System.out.print("Nome Fantasia do Prestador: ");
		Main.scan.nextLine();
		String name = Main.scan.nextLine();
		
		System.out.print("Razão Social: ");
		String social = Main.scan.nextLine();

		System.out.print("Email de Contato do Prestador: ");
		String email = Main.scan.nextLine();

		System.out.print("CNPJ: ");
		String cnpj = Main.scan.nextLine();

		System.out.print("Telefone: ");
		String phone = Main.scan.nextLine();

		System.out.print("CEP: ");
		String cep = Main.scan.nextLine();
		this.cep = Cep.getInstance(cep);

ArrayList<Department> departments = DepartmentDAO.getInstance().getAll();
		
		System.out.printf("%-5s %-25s %-25s\n", "Id", "Gerente", "Departamento");
		for (int i = 0; i < departments.size(); i++) {
			try {
				System.out.printf("%-5d %-25s %-25s\n", i+1, ((Department) departments.get(i)).getManager().getName(),
						((Department) departments.get(i)).getName());
			} catch (Exception e) {
				System.out.printf("%-5d %-25s %-25s\n", i+1, "null", ((Department) departments.get(i)).getName());
			}

		}
		
		System.out.print("Id do Departamento: ");
		int departIndex = Main.scan.nextInt()-1;
		Department department = (Department) DepartmentDAO.getInstance().findById(departIndex);
		
		System.out.print("Valor: ");
		double price = Main.scan.nextDouble();
		Main.scan.nextLine();
		
		System.out.print("Descrição: ");
		String description = Main.scan.nextLine();

		contact = new Contact(phone, email);
		address = new Address(this.cep);
		boolean added = controller.add(name, social, contact, address, cnpj, department, price, description);
		
		if (added) {
			System.out.println("Prestador Cadastrado");
			controller.goToInitial();
			
		}else {
			System.out.println("Não foi possível cadastrar o Prestador. Verifique as entradas e tente novamente.\n");
			System.out.println("1. Tentar Novamente / 0. Voltar");
			
			int input = Main.scan.nextInt();
			switch (input) {
			case 1:
				controller.create();
				break;
			case 0:
				controller.goToInitial();
				break;
			default:
				System.out.println("Opção Inválida. Retornando a Área Inicial");
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
