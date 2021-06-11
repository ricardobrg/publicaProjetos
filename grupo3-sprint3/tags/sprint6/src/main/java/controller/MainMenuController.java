package controller;

import com.google.gson.JsonObject;

import view.MainMenu;
import view.collaborator.CollaboratorMainMenu;
import view.payroll.PayrollMainMenu;
import view.role.RoleMainMenu;
import view.serviceprovider.ServiceProviderMainMenu;
import view.workHours.WorkHoursMainMenu;

/***
 * 
 * MainMenuController
 * 
 * Class used to make the calls of the views when necessary
 * controlling the main menu of the application.
 * 
 * @version 1.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 *
 */
public class MainMenuController {

	private JsonObject dataLogin;
	
	public MainMenuController(JsonObject dataLogin) {
		this.dataLogin = dataLogin;
	}
	
	public void mainMenu() {
		MainMenu mainMenu = new MainMenu(this.dataLogin);
		mainMenu.print();
	}
	
	public void workHours() {
		WorkHoursMainMenu workHoursMenu = new WorkHoursMainMenu(this.dataLogin);
		workHoursMenu.print();
	}

	public void role() {
		RoleMainMenu roleMenu = new RoleMainMenu(this.dataLogin);
		roleMenu.print();
	}
	
	public void collaborator() {
		CollaboratorMainMenu collabMenu = new CollaboratorMainMenu(this.dataLogin);
		collabMenu.print();
	}

	public void serviceProvider() {
		ServiceProviderMainMenu serviceProviderMenu = new ServiceProviderMainMenu(this.dataLogin);
		serviceProviderMenu.print();
	}

	public void payroll() {
		PayrollMainMenu payrollMenu = new PayrollMainMenu(this.dataLogin);
		payrollMenu.print();
	}
	
}
