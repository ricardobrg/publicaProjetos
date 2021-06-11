package model.dao.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.dao.file.fileAccess.FileAccess;

/***
 * FileCrud Class<br>
 * This concrete method implements 
 * the actual functions of the interface,
 * used to persist data throughout the database.
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class FileCrud<T> implements DatabaseCrud{

	private String path;
	protected Class<T> typeClass;

	public FileCrud(String path, Class<T> typeClass) {
		this.path = path;
		this.typeClass = typeClass;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> boolean add(T input) {
		Gson gson = new Gson();
		String stringObjectJson = gson.toJson(input);
		FileWriter fileWriter = FileAccess.getInstance().fileWrite(path, true);
		try {
			fileWriter.append(stringObjectJson + "\n");
			fileWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(int id) {
		Gson gson = new Gson();
		String content = FileAccess.getInstance().fileRead(path);

		String[] lines = content.split("\n");
		
		T ret = null;

		for(int i = 0; i < lines.length; i++) {
			ret = gson.fromJson(lines[i], typeClass);
			if(id == i) 
				break;
		}
		return ret;
	}

	@Override
	public boolean deleteById(int id) {
		String content = FileAccess.getInstance().fileRead(path);
		String[] lines = content.split("\n");
		FileWriter fileWriter = FileAccess.getInstance().fileWrite(path, false);
		try {
			for(int i = 0; i < lines.length; i++)
				if(!(id == i)) {
					fileWriter.append( lines[i] + "\n");
				}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> boolean updateById(T input, int id) {
		Gson gson = new Gson();
		String content = FileAccess.getInstance().fileRead(path);
		String[] lines = content.split("\n");

		lines[id] = gson.toJson(input);

		try {
			FileWriter fileWriter = FileAccess.getInstance().fileWrite(path, false);
			for(int i = 0; i < lines.length; i++)
				fileWriter.append( lines[i] + "\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings({ "hiding", "unchecked" })
	@Override
	public <T> ArrayList<T> getAll() {
		
		ArrayList<T> list = new ArrayList<T>();
		Gson gson = new Gson();
		String content = FileAccess.getInstance().fileRead(path);
		String[] lines = content.split("\n");

		T ret = null;
		for(int i = 0; i < lines.length; i++) {
			ret = (T) gson.fromJson(lines[i], typeClass);
			list.add(ret);
		}
		return list;
	}	

}
