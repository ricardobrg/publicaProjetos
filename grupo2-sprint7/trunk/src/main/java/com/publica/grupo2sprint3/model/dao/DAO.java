package com.publica.grupo2sprint3.model.dao;

import java.util.ArrayList;

/***
 * Common interface for all DAO's
 * 
 * @author Diego Borba <diegoborba25@gmail.com>
 */
public interface DAO {
	boolean add( Object obj);
	boolean updateByIdentifier(String identifier, Object collab);
	boolean updateById(int id, Object collab);
	<Type> ArrayList<Type> getAll();
	Object findById(int id);
	Object findByIdentifier(String identifier);
	boolean removeById(int id);
	boolean removeByIdentifier(String Identifier);
}
