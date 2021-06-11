package model.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;

import model.WorkEntry;
import model.entities.person.ServiceProvider;


/**
 * This class provides methods for managing WorkEntry objects.
 * It gives various methods for search registers and, also, offers
 * methods for adding a new WorkEntry object.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius Roosevelt>
 *
 */
public class WorkEntryDAO implements AbstractDAO {

	private FileWriter file;
	private Path filePath;
	private String path;

	public WorkEntryDAO() {
		this.path = "database/workEntry.txt";
		try {
			this.filePath = Paths.get(path);	
			file = new FileWriter(new File(filePath.toString()), true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add Object.
	 * After calling the TXT file, 
	 * this method accesses it and adds another line with information the user wants.
	 * 
	 * @param object
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	@Override
	public void addObject(Object workEntry) {

		Gson gson = new Gson();

		String stringObjectJson = gson.toJson(workEntry);

		try {
			this.file.append(stringObjectJson + "\n");
			this.file.close();  
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
	
	@Override
	public Object findObject(int id) {
		Gson gson = new Gson();
		String content = "";

		try {
			content = Files.readString(filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}  

		String[] lines = content.split("\n");
		ServiceProvider ret = gson.fromJson(lines[id], ServiceProvider.class);

		return ret;
	}


	/**
	 * Find Object
	 * This method calls the TXT file and searches for an object 
	 * by its ID (index) and returns it.
	 * 
	 * @param id : int
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	public Object findObject(String cpf) {

		Gson gson = new Gson();
		String content = "";

		try {
			content = Files.readString(filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}  

		String[] lines = content.split("\n");

		WorkEntry ret = null;

		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		
		for(int i = lines.length-1; i > 0 ; i--) {
			ret = gson.fromJson(lines[i], WorkEntry.class);
			if(ret.getCollaborator().getCpf().equals(cpf)) {
				if(ret.getDay().get(0).get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
					return ret;
				}
			}
		}

		return ret;
	}

	/***
	 * Update Object
	 * This method calls the TXT file and searches for an object 
	 * by its ID (index), after finding, overwrites it.
	 * 
	 * @param object
	 * @param id : int
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	@Override
	public void updateObject(Object workEntry, int id) {

		Gson gson = new Gson();
		String content = "";

		try {
			content = Files.readString(this.filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		String[] lines = content.split("\n");

		lines[id] = gson.toJson(workEntry);

		try {
			this.file = new FileWriter(new File(path), false);
			for(int i = 0; i < lines.length; i++)
				this.file.append( lines[i] + "\n");

			this.file.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Delete Object
	 * After calling the TXT file, searches for an object by its ID (index)
	 * and deletes.
	 * 
	 * @param int : id
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	@Override
	public void delObject(int id) {

		String content = "";

		try {
			content = Files.readString(this.filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		String[] lines = content.split("\n");

		try {
			this.file = new FileWriter(new File(path), false);
			for(int i = 0; i < lines.length; i++)
				if(!(id == i)) {
					this.file.append( lines[i] + "\n");
				}

			this.file.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/***
	 * Get All
	 * After calling the TXT file, it selects all content
	 * from inside.
	 * 
	 * @return ArrayList<Role>
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	public ArrayList<WorkEntry> getAll(String cpf) {

		ArrayList<WorkEntry> workEntry = new ArrayList<WorkEntry>();

		Gson gson = new Gson();

		String content = "";

		try {
			content = Files.readString(this.filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		String[] lines = content.split("\n");

		WorkEntry ret = null;

		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);

		for(int i = 0; i < lines.length; i++) {

			ret = gson.fromJson(lines[i], WorkEntry.class);
			if(cpf.equals(ret.getCollaborator().getCpf())) {
				if(calendar.get(Calendar.YEAR) == ret.getDay().get(ret.getDay().size()-1).get(Calendar.YEAR)
						&&(calendar.get(Calendar.MONTH) == ret.getDay().get(ret.getDay().size()-1).get(Calendar.MONTH))){
					workEntry.add(ret);
				}
			}

		}

		return workEntry;
	}

	public ArrayList<WorkEntry> getByMonthYear(String cpf) {

		ArrayList<WorkEntry> workEntry = new ArrayList<WorkEntry>();

		Gson gson = new Gson();

		String content = "";

		try {
			content = Files.readString(this.filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		String[] lines = content.split("\n");

		WorkEntry ret = null;

		for(int i = 0; i < lines.length; i++) {

			ret = gson.fromJson(lines[i], WorkEntry.class);
			if(cpf.equals(ret.getCollaborator().getCpf())) {
				workEntry.add(ret);
			}
		}

		return workEntry;
	}

}