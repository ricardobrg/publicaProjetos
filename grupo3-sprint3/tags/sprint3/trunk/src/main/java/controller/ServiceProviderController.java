package controller;

import java.util.ArrayList;

import model.dao.ServiceProviderDAO;
import model.entities.person.ServiceProvider;


/**
 * ServiceProviderController. <br></br>
 * This class connects the model and the view,
 * and is used to communicate between classes in the model and view.
 * Responsible for calling the DAO for each action.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class ServiceProviderController {
	
	ServiceProviderDAO dao;
	public ServiceProviderController() {
		 dao = new ServiceProviderDAO();
	}
	
	public ArrayList<ServiceProvider> listServicesProviders() {
		return dao.getAll();
	}
	
	public void deleteServiceProvider(int id) {
		dao.delObject(id);
	}
	
	public void addServiceProvider(ServiceProvider newServiceProvider) {
		dao.addObject(newServiceProvider);
	}
	
	public Object findByCNPJ(String cnpj) {
		return dao.findObjectByCPNJ(cnpj);
	}
	
	public Object findObject(int id) {
		return dao.findObject(id);
	}
	
	public void editServiceProvider(ServiceProvider updatedServiceProvider, int id) {
		dao.updateObject(updatedServiceProvider, id);
	}
	
	public ServiceProvider findServiceProvider(int id) {
		return (ServiceProvider)dao.findObject(id);
	}
}
