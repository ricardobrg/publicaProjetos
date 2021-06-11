package com.publica.grupo2sprint3.controller;


import java.util.ArrayList;

import com.publica.grupo2sprint3.model.dao.factory.PayrollFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Payroll;
import com.publica.grupo2sprint3.view.payrollView.PayrollViewList;

/***
 * This com.publica.grupo2sprint3.controller handles ControllerView requests
 * It implements the methods required for handling redirections and actions
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 * Version 1.0.0
 */
public class PayrollController extends Controller{
	
	 //Singleton{
	private static PayrollController instance;
	
	PayrollController() {
		super(null);
		this.dao = new PayrollFactory();
	}
	
	PayrollController(Collaborator collab) {
		super(collab);
		this.dao = new PayrollFactory();
	}

	public static PayrollController getInstance(Collaborator collab) {
		if (instance == null) {
			if(collab != null)
				instance = new PayrollController(collab);
			else
				instance = new PayrollController();
		}
		return instance;
	}
	
	public static void destroyInstance() {
		destroyViews();
		instance = null;
	}
	
	private static void destroyViews() {
		PayrollViewList.destroyInstance();
	}
	// }

	/***
	 * Method wich instance the 
	 * PayrollList View
	 * 
	 */
	@Override
	public void list() {
		PayrollViewList.getInstance(collab, collab.getRole().getAccessLevel()).display();
	}
	
	/***
	 * Method that gets the payroll informations
	 * about all collaborator of 1 especify
	 * department
	 * 
	 * @return ArrayList of ArrayLists<String>, 
	 * every ArrayLists<String> contains 
	 * name, role, department and salary of one collaborator
	 */
//	public ArrayList<ArrayList<String>> getAdvancedList(){
//		return PayrollDAO.getSalary(collab.getRole().getDepartment());
//	}
	
	/***
	 * Method that gets the payroll informations
	 * about all collaborators 
	 * 
	 * @return ArrayList of ArrayLists<String>, 
	 * every ArrayLists<String> contains 
	 * name, role, department and salary of one collaborator
	 */
//	public ArrayList<ArrayList<String>> getTotalList(){
//		return PayrollDAO.getSalary();
//	}
	
	/***
	 * Method for calculate the salary
	 * of one especify collaborator
	 * 
	 * @return collaboratorSalary(double)
	 */
//	public double getSalary() {
//		return PayrollDAO.getSalary(collab);
//	}
	
	/***
	 * Method for return to the main page
	 * 
	 */
	@Override
	public void goToInitial() {
		destroyViews();
		list();
	}
	
	//Unused abstract methods{
	@Override
	public void show(int id) {		
	}

	@Override
	public Payroll find(int id) {
		return  (Payroll) dao.findById(id);
	}

	@Override
	public boolean remove(int id) {
		return dao.removeById(id);
	}

	@Override
	public void create() {
	}
	
	public ArrayList<Payroll> getPayrolls(){
		return dao.getAll();
	}
	
	@Override
	public void edit(int id) {
	}
	// }

	public Boolean updatePayroll(int id, Payroll payroll) {
		return dao.updateById((id), payroll);
	}
	
}
