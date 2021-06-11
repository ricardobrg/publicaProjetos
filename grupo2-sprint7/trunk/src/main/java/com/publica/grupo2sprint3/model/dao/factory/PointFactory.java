package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.DAO;
import com.publica.grupo2sprint3.model.dao.PointDAO;

public class PointFactory extends Factory{
	public DAO createDao() {
		return (DAO) PointDAO.getInstance();
	}
}
