package controller.collaborator;

import java.util.ArrayList;

import model.dao.CollaboratorDAO;
import model.entities.person.Collaborator;


public class CollaboratorController {

	public ArrayList<Collaborator> collaboratorFindAll() {

		CollaboratorDAO colabDAO = new CollaboratorDAO();
		
		ArrayList<Collaborator> colab = colabDAO.getAll();
		
		return colab;
	}
	
	public Collaborator collaboratorFind(String cpf) {

		Collaborator colab;

		CollaboratorDAO colabDAO = new CollaboratorDAO();

		colab = (Collaborator) colabDAO.findObject(cpf);
		
		return colab;
	}

	public void collaboratorAdd(Collaborator colab) {

		CollaboratorDAO colabDAO = new CollaboratorDAO();

		colabDAO.addObject(colab);

	}
	
	public String collaboratorUpdate(String cpf, Collaborator colab) {

		CollaboratorDAO colabDAO = new CollaboratorDAO();
		
		ArrayList<Collaborator> colabs = colabDAO.getAll();
		
		int i = 0;
		
		for(i = 0; i < colabs.size(); i++) {
			if(cpf.equals(colabs.get(i).getCpf())) {
				colabDAO.updateObject(colab, i);
				return "Alterado com sucesso!!!";
			}
		}
		
		return "CPF não encontrado";
		
	}
	
	public String collaboratorDelete(String cpf) {

		CollaboratorDAO colabDAO = new CollaboratorDAO();
		
		ArrayList<Collaborator> colabs = colabDAO.getAll();
		
		int i = 0;
		
		for(i = 0; i < colabs.size(); i++) {
			if(cpf.equals(colabs.get(i).getCpf())) {
				colabDAO.delObject(i);
				return "Deletadp com sucesso!!!";
			}
		}
		
		return "CPF não encontrado";
		
	}

}
