package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.DAO;
import com.publica.grupo2sprint3.model.dao.DepartmentDAO;

public class DepartmentFactory extends Factory{
	
	public DAO createDao() {
		return (DAO) DepartmentDAO.getInstance();
	}
	
}