package com.publica.grupo1.controller.services;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.publica.grupo1.model.entities.endereco.CEP;

public class ServicoCEPTask implements Supplier<CEP> {

	private String cep;
	
	public ServicoCEPTask(String cep) {
		this.cep = cep;
	}

	@Override
	public CEP get() {
		try {
			System.out.println(Thread.currentThread().getName());
			return ServicoCEP.pegaCEP(cep);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public CompletableFuture<CEP> getAsync(){
		return CompletableFuture.supplyAsync(this);
	}
}
