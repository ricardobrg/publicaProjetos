package com.publica.grupo2sprint3.view.providerView;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;


/**
 * This View displays the data of a specific Service Provider.
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class ProviderViewShow extends ProviderView{

	private static ProviderViewShow instance;
	private ServiceProvider providerFound;
	
	private ProviderViewShow(Collaborator collab, ServiceProvider providerFound) {
		super(collab);
		this.providerFound = providerFound;
	}
	
	public static ProviderViewShow getInstance(Collaborator collab, ServiceProvider providerFound) {
		if (instance == null) {
			instance = new ProviderViewShow(collab, providerFound);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * It sets String with the data of the Provider to be printed.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @return the set up string ready to be printed
	 */
	@Override
	public String getOutput() {
		String output = "\n--------- Dados de " + providerFound.getName() + " ---------\n"
				+ "Nome Fantasia: " + providerFound.getName() + "\n"
				+ "Razão Social: " + providerFound.getSocialReason() + "\n"
				+ "Cnpj: " + providerFound.getCnpj() + "\n"
				//+ "Departamento: " + providerFound.getDepartment().getName() + "\n"
				+ "Endereço: " + providerFound.getAddress().toString() + "\n"
				+ "Cep: " + providerFound.getAddress().getCep() + "\n"
				+ "Email de Contato: " + providerFound.getContact().getEmail() + "\n"
				+ "Telefone de Contato: " + providerFound.getContact().getPhone() + "\n"
				+ "Valor: " + providerFound.getPrice() + "\n"
				+ "Descrição: " + providerFound.getDescription() + "\n";
		return output;
	}

	/**
	 * Reads an input and sends the user to the initial page of the Providers View.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	protected void readInput() {
		System.out.println("Pressione 'enter' para voltar");
		Main.scan.nextLine();
		Main.scan.nextLine();
		controller.goToInitial();
	}
	
}
