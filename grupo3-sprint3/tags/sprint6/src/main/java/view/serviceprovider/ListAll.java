package view.serviceprovider;

import java.util.ArrayList;

import com.google.gson.JsonObject;

/***
 * ServiceProviderListAll. 
 * Lists all ServiceProviders in listServicesProviders. 
 * Which is an ArrayList of Service Providers, in Controllers.
 * Otherwise throws an error message. 
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class ListAll {
	public void print(ArrayList<JsonObject> sps) {
	

		System.out.println();
		System.out.println("=======================");
		int i = 0;

		for (JsonObject item : sps) {
			if (item != null) {
				System.out.print(i + " - ");
				System.out.println(item.get("name"));
				i++;
			} else {
				System.out.println("Nenhum Prestador de Servico Cadastrado!");
			}
		}
		System.out.println("=======================");
	}
	
}
