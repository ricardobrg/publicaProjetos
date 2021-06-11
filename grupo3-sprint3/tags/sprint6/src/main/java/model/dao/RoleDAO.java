package model.dao;

import java.util.ArrayList;
import model.dao.hibernate.RoleHibernate;
import model.entities.security.Role;

/***
 * RoleDAO Class<br>
 * This DAO makes the connection with the DB.
 * 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 * @version 1.0.0
 */
public class RoleDAO {

	private RoleHibernate roleHibernate;

	public RoleDAO() {
		this.roleHibernate = new RoleHibernate();
	}

	/***
	 * RoleDAO Class<br>
	 * This add makes a connection with the DB. Responsible for adding a role.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @throws Exception 
	 */
	public int insert(Role role) throws Exception {
		return roleHibernate.insert(role);
	}

	/***
	 * RoleDAO Class<br>
	 * This update makes a connection with the DB. Responsible for updating a role.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @throws Exception 
	 */
	public int update(Role role) throws Exception {
		return roleHibernate.update(role);
	}

	/***
	 * RoleDAO Class<br>
	 * This delete makes a connection with the DB. Responsible for deleting a role.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception 
	 */
	public int delete(int id) throws IllegalArgumentException, Exception {
		return roleHibernate.delete(id);
	}

	/***
	 * RoleDAO Class<br>
	 * This find makes the connection with the DB. Responsible for finding a role
	 * via ID.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 * @throws Exception 
	 */
	public Role find(int id) throws Exception {
		return (Role) roleHibernate.find("id", String.valueOf(id));
	}

	/***
	 * RoleDAO Class<br>
	 * This getAll makes the connection with the DB. Responsible for listing all
	 * roles.
	 * 
	 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
	 */
	public ArrayList<Role> getAll() throws Exception {
		return roleHibernate.getAll();
	}
}
