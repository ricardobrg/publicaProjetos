package view.serviceprovider;

import view.MainMenu;
import view.Menu;
import view.console.Console;

/**
 * This class provides a menu containing various 
 * options for managing ServiceProvider objects
 * <br><br>
 * Just like any other page class in the system, this one
 * contains a print method, that show the options in
 * console.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 *
 */
public class ServiceProviderMainMenu implements Menu{
	public void print() {
		System.out.println();
		System.out.println();

		Console.printHeader("MENU - Prestador De Servi�os");
		Console.printOptions(new String[] {
				"Listar todos Prestadores de Servi�o",
				"Buscar Prestador pelo ID",
				"Buscar Prestador pelo CNPJ", 
				"Adicionar Prestador de Servi�os", 
				"Editar Prestador de Servi�os",
				"Deletar Prestador de Servi�os", 
				"Voltar", });

		int valor = (int) Console.getInputNumber("Selecione uma op��o - ", 0, 7, "Op��o inv�lida");

		switch (valor) {

		case 0: {
			break;
		}

		case 1: {
			new ServiceProviderListAll().print();
			this.print();
			break;
		}

		case 2: {
			new ServiceProviderFindId().print();
			this.print();
			break;
		}

		case 3: {
			new ServiceProviderFindCNPJ().print();
			this.print();
			break;
		}

		case 4: {
			new ServiceProviderAdd().print();
			this.print();
			break;
		}

		case 5: {
			new ServiceProviderEdit().print();
			this.print();
			break;
		}

		case 6: {
			new ServiceProviderDelete().print();
			this.print();
			break;
		}

		case 7: {
			new MainMenu().print();
			break;
		}

		}
	}
}
