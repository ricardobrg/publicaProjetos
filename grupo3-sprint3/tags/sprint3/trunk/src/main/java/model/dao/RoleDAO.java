package model.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.entities.security.Role;

/**
 * Class RoleDAO. This class saves JSON objects in TXT files. 
 * This class implements methods for inserting, selecting, updating and deleting. Basically CRUD operations. 
 * We're using JSON Objects and turning them into TXT files by string conversion.    
 * 
 * @version 1.0.0
 * 
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class RoleDAO implements AbstractDAO{

	private File file;
	private FileWriter fileWriter;
	private Path filePath;
	private String path;

	public RoleDAO() {		
		this.path = "database/role.txt";

		try {
			this.filePath = Paths.get(path);	
			file = new File(filePath.toString());
			fileWriter = new FileWriter(file, true);
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
	public void addObject(Object obj) {
		Gson gson = new Gson();
		try {
			String newRole = gson.toJson(obj);
			fileWriter.append(newRole + "\n");
			this.fileWriter.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if (id < 0 || id >= lines.length) {
			return null;
		}
		Role ret = gson.fromJson(lines[id], Role.class);
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
	public void updateObject(Object role, int id) {
		Gson gson = new Gson();
		String content = "";

		try {
			content = Files.readString(this.filePath);
			String[] lines = content.split("\n");
			lines[id] = gson.toJson(role);

			FileWriter fileWriter = new FileWriter(new File(path), false);
			for(int i = 0; i < lines.length; i++) {
				fileWriter.append( lines[i] + "\n");				
			}
			fileWriter.close();  
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
			String[] lines = content.split("\n");

			this.fileWriter = new FileWriter(new File(path), false);
			for(int i = 0; i < lines.length; i++) {				
				if(!(id == i)) {
					this.fileWriter.append( lines[i] + "\n");
				}
			}

			this.fileWriter.close();  
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
	public ArrayList<Role> getAll() {

		ArrayList<Role> roles = new ArrayList<Role>();

		Gson gson = new Gson();
		
		String content = "";

		try {
			content = Files.readString(this.filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		String[] lines = content.split("\n");

		Role ret = null;

		for(int i = 0; i < lines.length; i++) {
			
			ret = gson.fromJson(lines[i], Role.class);
			roles.add(ret);
		}

		return roles;
	}
	
	public void fileReader() {
		
	}

}
