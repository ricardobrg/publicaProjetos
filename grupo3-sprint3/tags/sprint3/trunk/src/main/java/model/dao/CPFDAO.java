package model.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CPFDAO {
	
	private File file;
	private Path filePath;
	private String path;

	public CPFDAO() {
		this.path = "database/currentCPF.txt";
		
		try {
			this.filePath = Paths.get(path);
			file = new File(filePath.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateCPF(String cpf) {
		try {
			FileWriter fileWriter = new FileWriter(file, false);			
			fileWriter.write(cpf);
			fileWriter.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String getSessionCPF() {
		String content = "";
		
		try {
			content = Files.readString(filePath);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
}
