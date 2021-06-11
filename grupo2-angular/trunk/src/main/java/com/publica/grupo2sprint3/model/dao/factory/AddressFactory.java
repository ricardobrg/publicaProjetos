package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.AddressDAO;
import com.publica.grupo2sprint3.model.dao.DAO;

public class AddressFactory extends Factory {

	public DAO createDao() {
		return (DAO) AddressDAO.getInstance();
	}
}
