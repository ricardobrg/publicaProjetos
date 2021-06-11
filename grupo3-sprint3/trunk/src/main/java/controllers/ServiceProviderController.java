package controllers;

import java.util.ArrayList;

import models.entities.person.ServiceProvider;
import models.hibernate.ServiceProviderHibernate;

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
 * @version 2.0.0
 * 
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 * @author Caio Shimada
 * 
 */
public class ServiceProviderController {

	private ServiceProviderHibernate serviceProviderDAO;

	public ServiceProviderController() {
		this.serviceProviderDAO = new ServiceProviderHibernate();
	}

	/**
	 * Receives a Service Provider and calls the DAO to store it in the database.
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @param ServiceProvider the ServiceProvider object to be added
     * @return the id of the inserted object. Returns -1
     * 		   if the operation failed
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
     * @return the id of the deleted object. Returns -1
     * 		   if the operation failed
	 */
	public int delete(int id) {
		return serviceProviderDAO.delete(id);
	}

	/**
	 * Updates an existing ServiceProvider identified by its ID
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @param the role to be edited
     * @return the id of the updated object. Returns -1
     * 		   if the operation failed
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
		return (ServiceProvider) serviceProviderDAO.find("cnpj", cnpj);
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
		return (ServiceProvider) serviceProviderDAO.find("id", String.valueOf(id));
	}

	/**
	 * Retrieves the ArrayList of existing Service Providers and returns them in an ArrayList of
	 * Service Providers.
	 * 
	 * @author Caio Shimada
	 * @author Nicole Taufenbach
	 * 
	 * @return the ArrayList of existing service providers 
	 */
	public ArrayList<ServiceProvider> getAll() {
		return serviceProviderDAO.getAll();
	}

}
