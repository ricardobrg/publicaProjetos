package com.publica.grupo2sprint3.controller;

import com.publica.grupo2sprint3.model.dao.factory.CollaboratorFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.profileView.ProfileViewEdit;
import com.publica.grupo2sprint3.view.profileView.ProfileViewMenu;
import com.publica.grupo2sprint3.view.profileView.ProfileViewShow;

/**
 * This com.publica.grupo2sprint3.controller handles ProfileView requests.
 * 
 * @version 2.0.0
 * 
 * @author Diego Borba
 * @author Caio Shimada
 */
public class ProfileController extends Controller{
	
	private static ProfileController instance;
	
	private ProfileController(Collaborator collab){
		super(collab);
		this.dao = new CollaboratorFactory();
	}
	
	public static ProfileController getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new ProfileController(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		destroyViews();
		instance = null;
	}
	
	private static void destroyViews() {
		ProfileViewMenu.destroyInstance();
		ProfileViewShow.destroyInstance();
		ProfileViewEdit.destroyInstance();
	}
	
	/**
	 * Returns user to the initial page of the com.publica.grupo2sprint3.view.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void goToInitial() {
		destroyViews();
		ProfileViewMenu.getInstance(collab).display();
	}

	/**
	 * Redirects user to the info visualization page.
	 * 
	 * @author Caio Shimada
	 */
	@Override
	public void show(int id) {
		ProfileViewShow.getInstance(collab).display();
	}
	
	/**
	 * Redirects user to the profile edit page.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param id unused parameter for this particular operation
	 */
	@Override
	public void edit(int id) {
		ProfileViewEdit.getInstance(collab).display();
	}
	
	/**
	 * Finds Collaborator by cpf.
	 * 
	 * @author Caio Shimada
	 * 
	 * @param cpf the cpf to be searched
	 * @return the Object found (Collaborator)
	 */
	public Object find(String cpf) {
		return dao.findByIdentifier(cpf);
	}
	
	/**
	 * Password update method.
	 * 
	 * @author Diego Borba
	 * @author Caio Shimada
	 * 
	 * @param cpf
	 * @param newPassword
	 * @return <code>true</code> if successful; <code>false</code> otherwise
	 */
	public boolean update(String cpf, String newPassword) {
		Collaborator collab = (Collaborator) find(cpf);
		collab.setPassword(newPassword);
		
		return dao.updateByIdentifier(cpf, collab);
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
