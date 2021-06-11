package model.dao.file;

import java.util.ArrayList;

import model.entities.person.Collaborator;

/***
 * CollaboratorDecorator Class<br>
 * This concrete method implements "decorators", 
 * which are additional methods than the default ones
 * (defined in DatabaseCrud). In this case they get 
 * data in the database via CPF, instead of doing it by ID.
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class CollaboratorDecorator extends DatabaseCrudDecorator{

	public CollaboratorDecorator(DatabaseCrud source) {
		super(source);
	}

	public void deleteByCpf(String cpf) {

		ArrayList<Collaborator> collabs = super.getAll();
		var coll = collabs.stream()
				.filter(o -> o.getCpf().equals(cpf))
				.findFirst();

		Collaborator coll2 = coll.orElse(null);

		if(coll2 != null) {
			super.deleteById(coll2.getId());
		}

	}
	
	public Collaborator findByCpf(String cpf) {

		ArrayList<Collaborator> collabs = super.getAll();
		var coll = collabs.stream()
				.filter(o -> o.getCpf().equals(cpf))
				.findFirst();

		Collaborator coll2 = coll.orElse(null);

		return coll2;
	}

	public void deleteByEmail(String email) {
		ArrayList<Collaborator> collabs = super.getAll();
		var coll = collabs.stream()
				.filter(o -> o.getContact().getEmail().equals(email))
				.findFirst();

		Collaborator coll2 = coll.orElse(null);

		if(coll2 != null) {
			super.deleteById(coll2.getId());
		}
	}

	public Collaborator findByEmail(String email) {
		ArrayList<Collaborator> collabs = super.getAll();
		var coll = collabs.stream()
				.filter(o -> o.getCpf().equals(email))
				.findFirst();

		Collaborator coll2 = coll.orElse(null);

		return coll2;
	}


}
