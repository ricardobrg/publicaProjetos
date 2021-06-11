package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.DAO;
import com.publica.grupo2sprint3.model.dao.PayrollDAO;

public class PayrollFactory extends Factory {
	public DAO createDao() {
		return (DAO) PayrollDAO.getInstance();
	}
}
