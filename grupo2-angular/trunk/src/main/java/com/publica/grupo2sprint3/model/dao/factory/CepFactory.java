package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.CepDAO;
import com.publica.grupo2sprint3.model.dao.DAO;

public class CepFactory extends Factory{

	public DAO createDao() {
		return CepDAO.getInstance();
	}
}
