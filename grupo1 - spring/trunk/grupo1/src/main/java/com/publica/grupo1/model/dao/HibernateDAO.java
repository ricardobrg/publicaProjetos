package com.publica.grupo1.model.dao;

import java.util.List;

/**
 * Interface for basic hibernate operations.
 * 
 * @author Diego Leon
 *
 * @param <T> type of object
 */
public interface HibernateDAO<T> {
	
	T selectById(int id);
	int insert(T object);
	void delete(T object);
	void update(T object);
	List<T> getAll();
}
