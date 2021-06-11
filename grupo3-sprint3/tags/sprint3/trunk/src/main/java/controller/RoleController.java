package controller;

import java.util.ArrayList;

import model.dao.RoleDAO;
import model.entities.security.Role;


/**
 * RoleController. <br></br>
 * This class connects the model and the view,
 * and is used to communicate between classes in the model and view.
 * Responsible for calling the DAO for each action.
 * 
 * @version 1.0.0
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 */
public class RoleController {
	
	RoleDAO roleDao;
	
	public RoleController() {
		roleDao = new RoleDAO();
	}
	
	public ArrayList<Role> listRoles() {
		return roleDao.getAll();
	}
	
	public Role findRole(int id) {
		return (Role)roleDao.findObject(id);
	}
	
	public void addNewRole(Role newRole) {
		roleDao.addObject(newRole);
	}
	
	public void editRole(Role updatedRole, int id) {
		roleDao.updateObject(updatedRole, id);
	}
	
	public void deleteRole(int id) {
		roleDao.delObject(id);
	}
}
