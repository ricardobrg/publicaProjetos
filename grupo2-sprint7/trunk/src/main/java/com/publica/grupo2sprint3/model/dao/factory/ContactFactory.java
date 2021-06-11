package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.ContactDAO;
import com.publica.grupo2sprint3.model.dao.DAO;

public class ContactFactory extends Factory {
	
	public DAO createDao() {
		return ContactDAO.getInstance();
	}
}
