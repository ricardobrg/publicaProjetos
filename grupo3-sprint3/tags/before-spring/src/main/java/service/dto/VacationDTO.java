package service.dto;

import model.entities.person.FisicaPerson;

public class VacationDTO extends FisicaPerson {
	
	private int vacationDays;
	private double vacationPayment;
	private Boolean canVacation;
	
	public VacationDTO () {
		super();
	}
	
	public int getVacationDays() {
		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {
		this.vacationDays = vacationDays;
	}

	public double getVacationPayment() {
		return vacationPayment;
	}

	public void setVacationPayment(double vacationPayment) {
		this.vacationPayment = vacationPayment;
	}

	public Boolean getCanVacation() {
		return canVacation;
	}

	public void setCanVacation(Boolean canVacation) {
		this.canVacation = canVacation;
	}
	
}
