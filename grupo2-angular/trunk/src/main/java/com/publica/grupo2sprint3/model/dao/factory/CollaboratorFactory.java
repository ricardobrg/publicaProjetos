package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.CollaboratorDAO;
import com.publica.grupo2sprint3.model.dao.DAO;

public class CollaboratorFactory extends Factory{

	public DAO createDao() {
		return CollaboratorDAO.getInstance();
	}
}
