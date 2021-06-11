package model.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.entities.person.Collaborator;
import model.entities.person.Endereco;

/**
 * Class CollaboratorDAO. This class saves JSON objects in TXT files. 
 * This class implements methods for inserting, selecting, updating and deleting. Basically CRUD operations. 
 * We're using JSON Objects and turning them into TXT files by string conversion.    
 * 
 * @version 1.0.0
 * 
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class CollaboratorDAO implements AbstractDAO{
	
	private File file;
	private FileWriter fileWriter;
	private Path filePath;
	private String path;
	
	/**
	 * CollaboratorDAO. 
	 * In this constructor we instantiate the objects for accessing TXT files.  
	 * We do this because this way we just need to instantiate them once, 
	 * otherwise we would need to call them more than once. 
	 * 
	 * @param filePath : String
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */

	public CollaboratorDAO() {
		this.path = "database/collaborator.txt";
		
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
		String stringObjectJson = gson.toJson(obj);

		try {
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);
			
			printWriter.print(stringObjectJson + "\n");
			printWriter.close();
		} 
		catch (Exception e) {
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
		Collaborator ret = null;
		
		for(int i = 0; i < lines.length; i++) {
			ret = gson.fromJson(lines[i], Collaborator.class);
			if(id == i) 
				break;
		}

		ret.setEndereco(new Endereco(ret.getCep()));
		
		return ret;
	}
	
	
	/**
	 * Find Object
	 * This method calls the TXT file and searches for an object 
	 * by its ID (index) and returns it.
	 * 
	 * @param id : int
	 * @return Collaborator
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
		Collaborator ret = null;
		
		for(int i = 0; i < lines.length; i++) {
			ret = gson.fromJson(lines[i], Collaborator.class);
			if(ret.getCpf().equals(cpf)) {
				//ret.setEndereco(new Endereco(ret.getCep()));
				return ret;
			}
		}

		ret = null;
		
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
	public void updateObject(Object obj, int id) {

		Gson gson = new Gson();
		String content = "";

		try {
			content = Files.readString(this.filePath);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		String[] lines = content.split("\n");

		lines[id] = gson.toJson(obj);

		try {
			this.fileWriter = new FileWriter(new File(path), false);
			for(int i = 0; i < lines.length; i++)
				this.fileWriter.append( lines[i] + "\n");

			this.fileWriter.close();  
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
			this.fileWriter = new FileWriter(new File(path), false);
			for(int i = 0; i < lines.length; i++)
				if(!(id == i)) {
					this.fileWriter.append( lines[i] + "\n");
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
	 * @return ArrayList<Collaborators>
	 * 
	 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	public ArrayList<Collaborator> getAll() {

		ArrayList<Collaborator> colabs = new ArrayList<Collaborator>();

		Gson gson = new Gson();
		
		String content = "";

		try {
			content = Files.readString(this.filePath);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		String[] lines = content.split("\n");

		Collaborator ret = null;

		for(int i = 0; i < lines.length; i++) {
			
			ret = gson.fromJson(lines[i], Collaborator.class);
			colabs.add(ret);
		}

		return colabs;
	}

}


