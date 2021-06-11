package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.DAO;
import com.publica.grupo2sprint3.model.dao.RoleDAO;

public class RoleFactory extends Factory{
	public DAO createDao() {
		return (DAO) RoleDAO.getInstance();
	}
}
