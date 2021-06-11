package model.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

import model.entities.security.AccessLevel;

/***
 * This class is responsible to communicate with the database,
 * checking and updating the current AccessLevel of the user.
 * 
 * @version 0.0.1
 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
 */
public class AccessLevelDAO {

	private File file;
	private Path filePath;
	private String path;

	public AccessLevelDAO() {		
		this.path = "database/accessLevel.txt";
		
		try {
			this.filePath = Paths.get(path);	
			file = new File(filePath.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/***
	 * This method updates the current AccessLevel with <code>acc</code>.
	 * 
	 * @param acc 
	 * @author Pedro Vinicius Hostert <pedrohostertt@gmail.com>
	 */
	public void updateObject(AccessLevel acc) {
		Gson gson = new Gson();
		try {
			FileWriter fileWriter = new FileWriter(file, false);
			String aLevel = gson.toJson(acc);
			
			fileWriter.write(aLevel);
			fileWriter.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Returns the current AccessLevel of the user.
	 * 
	 * @return model.entities.security.AccessLevel
	 */
	public AccessLevel findObject() {
		Gson gson = new Gson();
		String content = "";
		
		try {
			content = Files.readString(filePath);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return gson.fromJson(content, AccessLevel.class);
	}

}













