package com.publica.grupo2sprint3.controller;

import com.publica.grupo2sprint3.model.dao.factory.Factory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.view.HomeView;

/**
 * Controller abstract class with current logged in Collaborator.
 * 
 * This class implements a default goToHome method that returns use to the Home View.
 * It also defines required methods for controlling actions such as Redirections (e.g.: 
 * return to initial page, go to create, edit, list, and show pages) and
 * Actions (e.g.: update, store, find, delete).
 * 
 * @version 1.1.0
 * 
 * @author Caio Shimada
 *
 */
public abstract class Controller {

	protected Collaborator collab;
	protected Factory dao;
	
	public Controller(Collaborator collab) {
		this.collab = collab;
	}
	
	/**
	 * Returns user to Home Page
	 * 
	 * @author Caio Shimada <xcaiosr@gmail.com>
	 */
	public void goToHome() {
		HomeView.destroyInstance();
		HomeView.getInstance(collab).display();
	}
	
	public abstract void goToInitial();
	public abstract void create();
	public abstract void edit(int id);
	public abstract void list();
	public abstract void show(int id);
	public abstract Object find(int id);
//	public abstract boolean add();
//	public abstract boolean update();
	public abstract boolean remove(int id);
	
}
