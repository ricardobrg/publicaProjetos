package model.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.entities.person.ServiceProvider;


/**
 * Class ServiceProviderDAO. This class saves JSON objects in TXT files. 
 * This class implements methods for inserting, selecting, updating and deleting. Basically CRUD operations. 
 * We're using JSON Objects and turning them into TXT files by string conversion.    
 * 
 * @version 1.0.0
 * 
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class ServiceProviderDAO implements AbstractDAO{

	private File file;
	private FileWriter fileWriter;
	private Path filePath;
	private String path;

	/**
	 * ServiceProviderDAO. 
	 * In this constructor we instantiate the objects for accessing TXT files.  
	 * We do this because this way we just need to instantiate them once, 
	 * otherwise we would need to call them more than once. 
	 * 
	 * @param filePath : String
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	public ServiceProviderDAO() {
		this.path = "database/serviceProvider.txt";
		try {
			this.filePath = Paths.get(path);
			file = new File(filePath.toString());
			fileWriter = new FileWriter(file, true);
			this.filePath = Paths.get(path);	
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
	public void addObject(Object serviceProvider) {
		Gson gson = new Gson();
		try {
			String newServiceProvider = gson.toJson(serviceProvider);
			this.fileWriter.append(newServiceProvider + "\n");
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
	 * This method calls the TXT file and searches for an Service Provider
	 * by its cnpj and returns it.
	 * 
	 * @param cnpj : String
	 * 
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 */
	public Object findObjectByCPNJ(String cnpj) {
		Gson gson = new Gson();
		String content = "";

		try {
			content = Files.readString(filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}  

		String[] lines = content.split("\n");
		for (String item : lines) {
			ServiceProvider serviceProviderObject = gson.fromJson(item, ServiceProvider.class);
			if (serviceProviderObject != null && serviceProviderObject.getCnpj().equals(cnpj)) {		
				return serviceProviderObject;
			}
			
		}
		return null;
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
	public void updateObject(Object serviceProvider, int id) {

		Gson gson = new Gson();
		String content = "";

		try {
			content = Files.readString(this.filePath);
			String[] lines = content.split("\n");
			lines[id] = gson.toJson(serviceProvider);
			
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
	 * @return ArrayList<ServiceProvider>
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	public ArrayList<ServiceProvider> getAll() {

		ArrayList<ServiceProvider> roles = new ArrayList<ServiceProvider>();

		Gson gson = new Gson();
		
		String content = "";

		try {
			content = Files.readString(this.filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		String[] lines = content.split("\n");

		ServiceProvider ret = null;

		for(int i = 0; i < lines.length; i++) {
			
			ret = gson.fromJson(lines[i], ServiceProvider.class);
			roles.add(ret);
		}

		return roles;
	}

}
