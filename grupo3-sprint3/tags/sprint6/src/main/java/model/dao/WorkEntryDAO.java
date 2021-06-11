package model.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entities.workHours.WorkEntry;
import model.dao.hibernate.WorkHoursHibernate;

public class WorkEntryDAO {
	
	private WorkHoursHibernate workHoursHibernate;
	private String cpf;

	public WorkEntryDAO(String cpf) {
		
		this.workHoursHibernate = new WorkHoursHibernate();
		this.cpf = cpf;
	}

	public int insert(WorkEntry workHoursModel) {
		return workHoursHibernate.insert(workHoursModel);
	}

	public ArrayList<LocalDateTime> findBetweenDates(LocalDateTime date1, LocalDateTime date2) {
		return workHoursHibernate.findBetweenDates(this.cpf, date1, date2);
	}
	
	public List<WorkEntry> getAll() {
		return workHoursHibernate.getAll();
	}

	public WorkEntry find(String id) {
		return (WorkEntry) workHoursHibernate.find("id", id);
	}

}
