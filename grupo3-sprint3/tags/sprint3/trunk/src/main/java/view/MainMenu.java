package view;

import model.dao.AccessLevelDAO;
import model.entities.security.AccessLevel;
import view.collaborator.CollaboratorMainMenu;
import view.console.Console;
import view.role.RoleMainMenu;
import view.serviceprovider.ServiceProviderMainMenu;
import view.workHours.WorkHoursMainMenu;

/**
 * Used to display the main menu of the aplication. In its
 * print method will be displayed various options for
 * manageing data of the entities.
 * <br>
 * 
 * @version 1.0.0 
 * @author Vinicius Roosevelt Santos Dias
 *
 *
 */
public class MainMenu implements Menu {

	private AccessLevel acc = new AccessLevelDAO().findObject();
	private int max = 2;
	
	private void checkAccessLevel() {
		if(acc.equals(AccessLevel.MEDIUM))
			max = 3;
		else if(acc.equals(AccessLevel.TOTAL))
			max = 4;
	}
	
	@Override
	public void print() {
		checkAccessLevel();
		
		System.out.println();
		Console.printHeader("MENU");
		Console.printOptions(new String[] { "Controle de Ponto", "Colaborador", "Cargo", "Prestador de Serviço"});
		int valor = (int) Console.getInputNumber("Selecione uma opção - ", 0, max, "Opção inválida");
		switch (valor) {

		case 0 -> System.out.println("Sistema finalizado!");

		case 1 -> new WorkHoursMainMenu().print();

		case 2 -> new CollaboratorMainMenu().print();

		case 3 -> new RoleMainMenu().print();
		
		case 4 -> new ServiceProviderMainMenu().print();

		default -> System.out.println("Acesso Negado!");
		}
	}
}
