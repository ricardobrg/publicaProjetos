package com.publica.grupo2sprint3.model.dao.factory;

import java.util.ArrayList;

import com.publica.grupo2sprint3.model.dao.DAO;



public abstract class Factory{
	
	private DAO nowDao;
	
	public Factory() {
		nowDao = createDao();
	}
	
	public boolean removeById(int id){
		return nowDao.removeById(id);
	}
	
	public boolean removeByIdentifier(String identifier){
		return nowDao.removeByIdentifier(identifier);
	}
	
	public boolean add(Object obj){
		return nowDao.add(obj);
	}
	
	public boolean updateByIdentifier(String identifier, Object obj){
		return nowDao.updateByIdentifier(identifier, obj);
	}
	
	public boolean updateById(int id, Object obj){
		return nowDao.updateById(id, obj);
	}
	
	public <Type>ArrayList<Type> getAll(){
		return nowDao.getAll();
	}
	
	public Object findById(int id){
		return nowDao.findById(id);
	}
	
	public Object findByIdentifier(String identifier){
		return nowDao.findByIdentifier(identifier);
	}
	
	 public abstract DAO createDao();
		
}
