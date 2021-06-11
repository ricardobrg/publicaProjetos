package model.dao.file.fileAccess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class provides methods for working with files.
 * It provides methods for writing and reading files.
 * 
 * @version 1.1.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 *
 */
public class FileAccess {
	private static FileAccess instance;
	
	/**
	 * This method reads a file on a given path and return a String 
	 * containing his written content.
	 * 
	 * @param filePath String: path of the file that will be read.
	 * @return String containing content of the read file
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 */
	public String fileRead(String filePath) {
		String fileContent = null;
		try {
			fileContent = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}
	
	/**
	 * This method will provide a FileWriter object for writing in files.<br>
	 * If the <code>override</code> parameter is true, the content will be written
	 * at the end of the file. If false, the file will be written from scratch.
	 * 
	 * @param path String: Path of the file that will be read.
	 * @param override boolean: If false, the file will be written from scratch. If true, the content passed to
	 * FileWriter object will be added on the final of the file
	 * @return An FileWriter object.
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 */
	public FileWriter fileWrite(String path, boolean override) {
		FileWriter result = null;
		try {
			result = (new FileWriter(new File(path), override));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This method checks if there is an existing instance of the class.
	 * If it does, the method will return the instance already created.
	 * If it doesn't, the method will call the constructor of the class and
	 * create a new instance.
	 * 
	 * @return FileAccess: An unique instance of the class
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 */
	public static FileAccess getInstance() {
		if (instance == null) {
			instance = new FileAccess();
		}
		return instance;
	}
	
	private FileAccess() {} // DEFAULT CONSTRCTOR
}
