package model.entities.security;

public class Role {

/**
 * Class that provide the name and the salary of a object.
 * Objects of this class will be used as attribute in Collaborator Class
 * 
 * @Version: 1.0.1
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@gmail.com>
 */	
	private String nameRole;
	private AccessLevel accessLevel;

	public Role(String name) {
		this.nameRole = name;
	}
	
	public Role() {
		
	}

	public String getName() {
		return nameRole;
	}

	public void setName(String name){
		this.nameRole = name;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

}

