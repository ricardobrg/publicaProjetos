package model.dao.file;

import java.util.ArrayList;

import model.entities.person.ServiceProvider;

/***
 * ServiceProviderDecorator Class<br>
 * This concrete method implements "decorators", 
 * which are additional methods than the default ones
 * (defined in DatabaseCrud). In this case they get 
 * data in the database via CNPJ, instead of doing it by ID.
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class ServiceProviderDecorator extends DatabaseCrudDecorator{

	public ServiceProviderDecorator(DatabaseCrud source) {
		super(source);
	}

	public void deleteByCnpj(String cnpj) {

		ArrayList<ServiceProvider> sps = super.getAll();
		var coll = sps.stream()
				.filter(o -> o.getName().equals(cnpj))
				.findFirst();

		ServiceProvider coll2 = coll.orElse(null);

		if(coll2 != null) {
			super.deleteById(coll2.getId());
		}else{
			System.out.println("CNPJ nao cadastrado");
		}

	}
	
	public ServiceProvider findByCnpj(String cnpj) {

		ArrayList<ServiceProvider> sps = super.getAll();
		var coll = sps.stream()
				.filter(o -> o.getName().equals(cnpj))
				.findFirst();

		ServiceProvider coll2 = coll.orElse(null);

		return coll2;
	}
	
}
