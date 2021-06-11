package model.dao;

import java.util.ArrayList;
import model.dao.hibernate.PayrollHibernate;
import model.entities.payroll.Payroll;

public class PayrollDAO {

	private PayrollHibernate payrollHibernate;

	public PayrollDAO() {
		this.payrollHibernate = new PayrollHibernate();
	}

	public int insert(Payroll payroll) {
		return payrollHibernate.insert(payroll);
	}

	public int update(Payroll payroll) {
		return payrollHibernate.update(payroll);
	}

	public ArrayList<Payroll> find(int id) {
		return payrollHibernate.findByCollab(id);
	}
	
	public Payroll findById(int id) {
		return (Payroll) payrollHibernate.find("id",""+id);
	}

	public ArrayList<Payroll> getAll() {
		return payrollHibernate.getAll();
	}



}
