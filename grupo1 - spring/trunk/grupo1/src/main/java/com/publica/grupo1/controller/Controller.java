package com.publica.grupo1.controller;

import java.util.List;

public interface Controller<T> {
	
	T selectById(int id);
	int insert(T object);
	void delete(T object);
	void update(T object);
	List<T> getAll();
}
