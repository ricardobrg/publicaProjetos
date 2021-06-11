package com.publica.grupo2sprint3.controller;

import java.util.ArrayList;

import com.publica.grupo2sprint3.model.dao.ServiceProviderDAO;
import com.publica.grupo2sprint3.model.dao.factory.ServiceProviderFactory;
import com.publica.grupo2sprint3.model.person.Address;
import com.publica.grupo2sprint3.model.person.Contact;
import com.publica.grupo2sprint3.model.person.legalperson.ServiceProvider;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Department;
import com.publica.grupo2sprint3.model.util.Validators;
import com.publica.grupo2sprint3.view.providerView.ProviderViewCreate;
import com.publica.grupo2sprint3.view.providerView.ProviderViewEdit;
import com.publica.grupo2sprint3.view.providerView.ProviderViewList;
import com.publica.grupo2sprint3.view.providerView.ProviderViewMenu;
import com.publica.grupo2sprint3.view.providerView.ProviderViewShow;

/**
 * This com.publica.grupo2sprint3.controller handles ProviderView requests.
 * 
 * It features a method for redirections and actions.
 * 
 * @version 2.0.0
 * 
 * @author Caio Shimada <xcaiosr@gmail.com>
 *
 */
public class ProviderController extends Controller {

	private static ProviderController instance;

	private ProviderController(Collaborator collab) {
		super(collab);
		this.dao = new ServiceProviderFactory();
	}

	public static ProviderController getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new ProviderController(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		destroyViews();
		instance = null;
	}

	private static void destroyViews() {
		ProviderViewMenu.destroyInstance();
		ProviderViewCreate.destroyInstance();
		ProviderViewEdit.destroyInstance();
		ProviderViewShow.destroyInstance();
		ProviderViewList.destroyInstance();
	}
	
	/**
	 * Returns user to initial page of the Providers View.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	public void goToInitial() {
		destroyViews();
		ProviderViewMenu.getInstance(collab).display();
	}

	/**
	 * Calls the Provider Creation Form.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	public void create() {
		ProviderViewCreate.getInstance(collab).display();
	}

	/**
	 * Retrieves the Provider to be edited by id and redirects user to the Provider
	 * edit com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param the id of the provider to be edited
	 */
	@Override
	public void edit(int id) {
		ServiceProvider providerFound = (ServiceProvider) find(id);
		ProviderViewEdit.getInstance(collab, providerFound).display();
	}

	/**
	 * Retrieves the Provider to be edited by cnpj and redirects user to the
	 * Provider edit com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param cnpj the cnpj of the provider to be edited
	 */
	public void edit(String cnpj) {
		ServiceProvider providerFound = (ServiceProvider) find(cnpj);
		ProviderViewEdit.getInstance(collab, providerFound).display();
	}

	/**
	 * Retrieves the Providers list and redirects user to the Providers List page.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	@Override
	public void list() {
		ArrayList<ServiceProvider> providers = getProviders();
		ProviderViewList.getInstance(collab, providers).display();
	}

	/**
	 * Finds Provider by id and sends user to the display provider info com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com
	 * 
	 * @param id the id of the provider to be shown
	 */
	@Override
	public void show(int id) {
		ServiceProvider providerFound = (ServiceProvider) find(id);
		ProviderViewShow.getInstance(collab, providerFound).display();
	}

	/**
	 * Finds Provider by id.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param id an int of the id of the provider to be searched
	 * @return the Provider found
	 */
	@Override
	public Object find(int id) {
		return (ServiceProvider) dao.findById(id);
	}

	/**
	 * Finds Provider by cnpj.
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param cnpj an int of the cnpj of the provider to be searched
	 * @return the Provider found
	 */
	public Object find(String cnpj) {
		return (ServiceProvider) dao.findByIdentifier(cnpj);
	}

	/**
	 * Stores the Provider
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com
	 * 
	 * @param name
	 * @param social
	 * @param email
	 * @param cnpj
	 * @param phone
	 * @param address
	 * @param cep
	 * @param department
	 * @param price
	 * @param description
	 * @return <code>true</code> if Provider was added; <code>false</code>
	 *         otherwise
	 */
	public boolean add(String name, String social, Contact contact, Address address, String cnpj,
			Department department, double price, String description) {

		if (!isInputValid(name, social, contact, address, cnpj, department, price, description))
			return false;

		ServiceProvider provider = new ServiceProvider(name, contact, address, social, cnpj, price, description);

		return dao.add(provider);
	}
	
	public boolean add (ServiceProvider provider) {
		return ServiceProviderDAO.getInstance().add(provider);
	}

	/**
	 * Updates a Provider, identifying it by id
	 * 
	 * @author Caio Shimada
	 * 
	 * @param int
	 * @param name
	 * @param social
	 * @param email
	 * @param cnpj
	 * @param phone
	 * @param address
	 * @param cep
	 * @param department
	 * @param price
	 * @param description
	 * @return <code>true</code> if Provider was updated; <code>false</code>
	 *         otherwise
	 */
	public boolean update(int id, String name, String social, Contact contact, Address address, String cnpj, String phone,
			String cep, Department department, double price, String description) {

		if (!isInputValid(name, social, contact, address, cnpj, department, price, description))
			return false;

		ServiceProvider provider = new ServiceProvider(name, contact, address, social, cnpj, price, description);

		return dao.updateById(id , provider);
	}

	/**
	 * Updates a Provider, identifying it by cnpj
	 * 
	 * @author Caio Shimada
	 * 
	 * @param cnpj
	 * @param name
	 * @param social
	 * @param email
	 * @param newNnpj
	 * @param phone
	 * @param address
	 * @param cep
	 * @param department
	 * @param price
	 * @param description
	 * @return <code>true</code> if Provider was updated; <code>false</code>
	 *         otherwise
	 */
	public boolean update(String cnpj, String name, String social, Contact contact, Address address, String newCnpj,
			  Department department, double price, String description) {

		if (!isInputValid(name, social, contact, address,newCnpj, department, price, description))
			return false;

		ServiceProvider provider = new ServiceProvider(name, contact, address, social, newCnpj, price, description);

		return dao.updateByIdentifier(cnpj, provider);
	}
	
	public boolean update (String identifier, ServiceProvider provider) {
		return ServiceProviderDAO.getInstance().updateByIdentifier(identifier, provider);
	}

	/**
	 * Removes a Provider identifying it by id
	 * 
	 * @author Caio Shimada
	 * 
	 * @return <code>true</code> if Provider was deleted; <code>false</code>
	 *         otherwise
	 */
	public boolean remove(int id) {
		return dao.removeById(id);
	}

	/**
	 * Removes a Provider identifying it by cnpj
	 * 
	 * @author Caio Shimada
	 * 
	 * @return <code>true</code> if Provider was deleted; <code>false</code>
	 *         otherwise
	 */
	public boolean remove(String cnpj) {
		return dao.removeByIdentifier(cnpj);
	}

	/**
	 * Validates the user input
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 * 
	 * @param name
	 * @param social
	 * @param email
	 * @param cnpj
	 * @param phone
	 * @param address
	 * @param cep
	 * @param department
	 * @param price
	 * @param description
	 * @return <code>true</code> if data is valid; <code>false</code> otherwise
	 */
	public boolean isInputValid(String name, String social, Contact contact, Address address, String cnpj, 
			 Department department, double price, String description) {

		if (!Validators.isCnpjValid(cnpj) || !Validators.isCepValid(address.getCep().toString()) || !Validators.isEmailValid(contact.getEmail())
				|| !Validators.isPhoneValid(contact.getPhone()))
			return false;

		if (dao.findByIdentifier(cnpj) != null)
			return false;

		return true;
	}
	
	/**
	 * Retrieves all providers
	 * 
	 * @author Caio Shimada
	 * 
	 * @return an arraylist of the existing providers
	 */
	public ArrayList<ServiceProvider> getProviders() {
		return dao.getAll();
	}

}
