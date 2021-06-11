package model.dao;

import java.util.ArrayList;

import model.dao.hibernate.CollaboratorHibernate;
import model.entities.person.Collaborator;

public class CollaboratorDAO {

	private CollaboratorHibernate collaboratorHibernate;
	
	public CollaboratorDAO() {
		this.collaboratorHibernate = new CollaboratorHibernate();
	}

	public int insert(Collaborator collaborator) {
		return this.collaboratorHibernate.insert(collaborator);
	}
	
	public int update(Collaborator collaborator) {
		return this.collaboratorHibernate.update(collaborator);
	}

	public int delete(int id) {
		return this.collaboratorHibernate.delete(id);
	}	

	public Collaborator findByCpf(String cpf) {
		return (Collaborator) this.collaboratorHibernate.find("cpf", cpf);
	}

	public Collaborator findById(int id) {
		return (Collaborator) this.collaboratorHibernate.find("id", ""+id);
	}
	
	public ArrayList<Collaborator> getAll() {
		return this.collaboratorHibernate.getAll();
	}
	
}
	
