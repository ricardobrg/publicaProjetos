package model.dao.file;

import java.util.ArrayList;

/***
 * DatabaseCrud Interface<br>
 * This interface defines the base methods
 * for the database CRUD by ID, 
 * which is defined by the author.
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public interface DatabaseCrud {

	boolean deleteById(int id);
	
	<T> T findById(int id);
	
	<T> boolean add(T input);
	
	<T> boolean updateById(T input, int id);
	
	<T> ArrayList<T> getAll();
	
}



