package com.publica.grupo2sprint3.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.PointDAO;
import com.publica.grupo2sprint3.model.dao.factory.PointFactory;
import com.publica.grupo2sprint3.model.person.physicalperson.Collaborator;
import com.publica.grupo2sprint3.model.person.physicalperson.Point;
import com.publica.grupo2sprint3.view.pointView.PointViewCreate;
import com.publica.grupo2sprint3.view.pointView.PointViewEdit;
import com.publica.grupo2sprint3.view.pointView.PointViewList;
import com.publica.grupo2sprint3.view.pointView.PointViewListDepartment;
import com.publica.grupo2sprint3.view.pointView.PointViewMenu;
import com.publica.grupo2sprint3.view.pointView.PointViewRemove;

/**
 * This com.publica.grupo2sprint3.controller handles PointView requests
 * 
 * It implements the methods required for handling redirections and actions
 * 
 * @version 1.0.0
 * 
 * @author Jessé Amaro <jesse.amaro7@gmail.com>
 */
public class PointController extends Controller {
	
	private static PointController instance;
	
	private PointController(Collaborator collab) {
		super(collab);
		
		this.dao = new PointFactory();
	}
	
	public static PointController getInstance(Collaborator collab) {
		if (instance == null) {
			instance = new PointController(collab);
		}
		return instance;
	}
	
	public static void destroyInstance() {
		instance = null;
	}
	
	/**
	 * Destroys previous instance of the com.publica.grupo2sprint3.view and returns user to initial page of
	 * the Points View.
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	@Override
	public void goToInitial() {
		PointViewMenu menuView = new PointViewMenu(collab);
		menuView.display();
	}
	
	/**
	 * Calls the PointCreate Views, responsible for show registering the points.
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	@Override
	public void create() {
		PointViewCreate createView = new PointViewCreate(collab);
		createView.display();
	}
	
	/**
	 * Calls the Pointlist View, resposible to show the collaborator's points list. 
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public void list(String date) {
		PointViewList viewList = new PointViewList(collab, getPointsByDate(date));
		viewList.display();
	}

	/**
	 * Calls the Point By Department View, resposible to show the collaborator's points 
	 * list of a selected sector! 
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public void listByDepartment() {
		PointViewListDepartment viewDepartmentList = new PointViewListDepartment(collab);
		viewDepartmentList.display();
	}
	
	/**
	 * Calls the Point Edit View, resposible to show the collaborator's points edit options.  
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public void editPointHourView() {
		PointViewEdit editView = new PointViewEdit(collab);
		editView.display();
	}
	
	/**
	 * Calls the Point Remove View, resposible to show the collaborator's points 
	 * remove options.  
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public void removeView() {
		PointViewRemove removeView = new PointViewRemove(collab);
		removeView.display();
	}
	
	/**
	 * Calls the findByIdentifier method, resposible to show the collaborator's points 
	 * by cpf.  
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public Object find(String cpf) {
		return CollaboratorDAO.getInstance().findByIdentifier(cpf);
	}

	/**
	 * Calls the editPointHour method, resposible to edit the collaborator's points 
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public boolean editPointHour(Point editedPoint, int dayMinute) {
		PointDAO.editCollabsPoint(dayMinute, editedPoint);
		return true;
	}

	/**
	 * Calls the removeCollabPoint method, resposible to remove the collaborator's points 
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public boolean removeCollabPoint(Point point) {
		return PointDAO.removeCollabsPoint(point);
	}
	
	/**
	 * Calls the add method, resposible to add the collaborator's points 
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public boolean add(Object collab) {
		return dao.add(collab);
	}
	
	/**
	 * Calls the getPointsByDate method, resposible to get the collaborator's points
	 * in a specify date. 
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public ArrayList<Point> getPointsByDate(String date) {
		return PointDAO.getPointsByDate(collab, date);
	}
	
//	/**
//	 * Calls the getCollabsByDepartment method, resposible to get all collaborator's
//	 * in a specify department. 
//	 * 
//	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
//	 */
//	public ArrayList<Collaborator> getCollabsByDepartment() {
//		return PointDAO.getCollabsByDepartment(collab);
//	}
//	
	/**
	 * Calls the getAllCollabsPoint method, resposible to get all collaborator's points.
	 * 
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 */
	public ArrayList<Point> getAllCollabsPoint(Collaborator collab) {
		return PointDAO.getAllCollabPoints(collab);
	}
	
	/** 
	 * This method formats a date to the specified pattern.
	 * 
	 * @return finalFormatedDate
	 * @author Jessé Amaro <jessé.amaro7@gmail.com>
	 */
	public static String dateFormatter() {

		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
		Date date = calendar.getTime();
		String finalFormatedDate = dateFormat.format(date);
		
		return finalFormatedDate;
	}
	
	public ArrayList<Point> getAllPoints() {
		return dao.getAll();
	}
	
	@Override
	public Object find(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void edit(int id) { }
	
	@Override
	public boolean remove(int id) {
		return dao.removeById(id);
	}
	
	@Override
	public void show(int id) { }
	
	@Override
	public void list() { }
}