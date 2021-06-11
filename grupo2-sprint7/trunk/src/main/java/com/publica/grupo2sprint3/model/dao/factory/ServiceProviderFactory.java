package com.publica.grupo2sprint3.model.dao.factory;

import com.publica.grupo2sprint3.model.dao.DAO;
import com.publica.grupo2sprint3.model.dao.ServiceProviderDAO;


public class ServiceProviderFactory extends Factory{
	public DAO createDao() {
		return (DAO) ServiceProviderDAO.getInstance();
	}
}
