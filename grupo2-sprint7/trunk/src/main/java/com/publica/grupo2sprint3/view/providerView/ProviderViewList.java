package com.publica.grupo2sprint3.view.providerView;

import java.util.ArrayList;

import com.publica.grupo2sprint3.controller.Main;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;

/**
 * This com.publica.grupo2sprint3.view lists the existing service providers
 * 
 * @version 1.0.0
 * 
 * @author Caio Shimada
 *
 */
public class ProviderViewList extends ProviderView {

	private static ProviderViewList instance;
	private ArrayList<ServiceProvider> providers;

	private ProviderViewList(Collaborator collab, ArrayList<ServiceProvider> providers) {
		super(collab);
		this.providers = providers;
	}

	public static ProviderViewList getInstance(Collaborator collab, ArrayList<ServiceProvider> providers) {
		if (instance == null) {
			instance = new ProviderViewList(collab, providers);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}

	/**
	 * It sets the list of the Providers to be printed.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @return the set up string ready to be printed
	 */
	@Override
	public String getOutput() {
		String output = "";

		if (providers.size() > 0)
			output += String.format("%-5s %-25s %-25s %-25s\n", "Id", "Nome Fantasia", "CNPJ", "Departamento");
		else
			return "Nenhum Prestador Encontrado!";

		for (int i = 0; i < providers.size(); i++) {
			output += String.format("%-5d %-25s %-25s %-25s\n", i, providers.get(i).getName(),
					providers.get(i).getCnpj());
			//getDepartment.getName;
		}

		return output;
	}

	/**
	 * Reads the user's input to select a Provider listed.
	 * 
	 * After selecting a provider, the user chooses an option between 1. Visualize
	 * data, 2. Edit data, 3. delete, and 0. to return.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	protected void readInput() {
		System.out.print("Selecionar Prestador: ");
		int input = Main.scan.nextInt();

		System.out.println("1. Visualizar / 2. Editar / 3. Deletar / 0. Voltar");
		int option = Main.scan.nextInt();

		switch (option) {
		case 1:
			controller.show(input);
			break;
		case 2:
			controller.edit(input);
			break;
		case 3:
			if (controller.remove(input))
				System.out.println("Prestador Deletado");
			else
				System.out.println("Não Foi Possível Deletar o Prestador");
			controller.goToInitial();
			break;
		case 0:
			controller.goToInitial();
			break;
		default:
			System.out.println("Opção Inválida");
			controller.goToInitial();
			break;
		}

	}

}
