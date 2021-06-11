package model.dao.file;

import java.util.ArrayList;

import model.entities.security.Role;

/***
 * RoleDecorator Class<br>
 * This concrete method implements "decorators", 
 * which are additional methods than the default ones
 * (defined in DatabaseCrud). In this case they get 
 * data in the database via Name, instead of doing it by ID.
 * 
 * @version 1.0.0
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @author Giovanni Buzzi <buzzi.giovanni@outlook.com>
 */
public class RoleDecorator extends DatabaseCrudDecorator {

		public RoleDecorator(DatabaseCrud source) {
			super(source);
		}

		public void deleteByRole(String role) {

			ArrayList<Role> collabs = super.getAll();
			var coll = collabs.stream()
					.filter(o -> o.getName().equals(role))
					.findFirst();

			Role coll2 = coll.orElse(null);

			if(coll2 != null) {
				super.deleteById(coll2.getId());
			}else{
				System.out.println("CPF nao cadastrado");
			}

		}
		
		public Role findByRole(String role) {

			ArrayList<Role> collabs = super.getAll();
			var coll = collabs.stream()
					.filter(o -> o.getName().equals(role))
					.findFirst();

			Role coll2 = coll.orElse(null);

			return coll2;
		}

	
}
