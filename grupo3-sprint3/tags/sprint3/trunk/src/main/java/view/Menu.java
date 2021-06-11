package view;

import model.dao.AccessLevelDAO;
import model.dao.CPFDAO;
import model.entities.security.AccessLevel;

/**
 * Methods that every class must have.
 * 
 * @author Pedro Vinicius Hostert<pedrohostertt@gmail.com>
 *
 */
public interface Menu {
	public AccessLevel acc = new AccessLevelDAO().findObject();
	public String loggedCpf = new CPFDAO().getSessionCPF();
	
	public void print();
}
