package controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.dao.CollaboratorDAO;
import model.dao.WorkEntryDAO;


import model.entities.person.Collaborator;
import model.entities.workHours.HoursCalc;
import model.entities.workHours.WorkEntry;
import utils.convertions.StringToLocalDateTime;
import view.workHours.ListAll;

/**
 * WorkHoursController. <br></br>
 * This class connects the model and the view,
 * and is used to communicate between classes in the model and view.
 * Searches for an specific CPF and returns the list of Work Entrys.
 * It is used to keep track of Work Hours.
 * <br></br>P.S.: ClockIn = Bater Ponto
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class WorkHoursController {

	private WorkEntryDAO workEntryDAO;
	private String currentCPF;

	public WorkHoursController(String cpf) {
		this.currentCPF = cpf;
		this.workEntryDAO = new WorkEntryDAO(currentCPF);
	}

	public int clockIn() {
		CollaboratorDAO collabDAO = new CollaboratorDAO();
		Collaborator collab = collabDAO.findByCpf(currentCPF);
		WorkEntry we = new WorkEntry(collab);
		we.clockIn();
		return workEntryDAO.insert(we);
	}
	
	public WorkEntry find(String id) {
		return workEntryDAO.find(id);
	}

	public void listAllMenu() {
		ListAll listMenu = new ListAll();
		listMenu.print(listAll());
	}

	public ArrayList<LocalDateTime> showHours(String date1, String date2) {
		return workEntryDAO.findBetweenDates(
						StringToLocalDateTime.stringToLocalDateTime(date1), 
						StringToLocalDateTime.stringToLocalDateTime(date2));
	}
	
	public int calculateHours(String date1, String date2) {

		ArrayList<LocalDateTime> clocks = workEntryDAO.findBetweenDates(
						StringToLocalDateTime.stringToLocalDateTime(date1), 
						StringToLocalDateTime.stringToLocalDateTime(date2));
		
		return HoursCalc.pointDifference(clocks);
	}

	public ArrayList<JsonObject> listAll() {

		List<WorkEntry> wes = workEntryDAO.getAll();

		ArrayList<JsonObject> wesJson = new ArrayList<JsonObject>();

		Gson gson = new Gson();

		for(int i = 0; i < wes.size(); i++) {
			wesJson.add(gson.toJsonTree(wes.get(i)).getAsJsonObject());
		}
		return wesJson;
	}

}
