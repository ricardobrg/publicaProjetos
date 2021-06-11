package controller;

import java.util.ArrayList;

import model.dao.ServiceProviderDAO;
import model.entities.person.ServiceProvider;

/***
 * 
 * ServiceProviderController
 * 
 * Class used to manage the service provider in the application controller.
 * 
 * This controller is responsible for calling the views of the service providers
 * menus as well as for applying the methods to them. It works receiving and
 * returning JsonObjects.
 * 
 * @version 1.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 *
 */
public class ServiceProviderController {

	private ServiceProviderDAO serviceProviderDAO;

	public ServiceProviderController() {
		this.serviceProviderDAO = new ServiceProviderDAO();
	}

	/**
	 * Receives a Service Provider and calls the DAO to store it in the database.
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @param ServiceProvider : the provider to be added
	 * @return the ID of the added Service Provider
	 */
	public int insert(ServiceProvider serviceProvider) {
		return serviceProviderDAO.insert(serviceProvider);
	}

	/**
	 * Deletes a Provider using its ID.
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @param id the id of the Service Provider to be deleted
	 * @return the ID of the deleted Service Provider
	 */
	public int delete(int id) {
		return serviceProviderDAO.delete(id);
	}

	/**
	 * Updates an existing Role identified by its ID
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @param the role to be edited
	 * @return the ID of the updated Service Provider
	 */
	public int update(ServiceProvider serviceProvider) {
		return serviceProviderDAO.update(serviceProvider);
	}
	
	/**
	 * Searches a Service Provider in the database by ID and returns it.
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @param CNPJ the CNPJ of the Service Provider to be found
	 * @return the ServiceProvider found
	 */
	public ServiceProvider findByCnpj(String cnpj) {
		return serviceProviderDAO.findByCnpj(cnpj);
	}

	/**
	 * Searches a Service Provider in the database by ID and returns it.
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @param id the id of the role to be found
	 * @return the ServiceProvider found by ID
	 */
	public ServiceProvider findById(int id) {
		return serviceProviderDAO.findById(id);
	}

	/**
	 * Retrieves the ArrayList of existing Service Providers and returns them in an ArrayList of
	 * Service Providers.
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @return all service providers 
	 */
	public ArrayList<ServiceProvider> getAll() {
		return serviceProviderDAO.getAll();
	}

}
