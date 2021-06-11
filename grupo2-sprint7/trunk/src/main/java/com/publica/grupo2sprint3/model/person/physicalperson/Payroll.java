package com.publica.grupo2sprint3.model.person.physicalperson;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.publica.grupo2sprint3.model.util.Validators;


@Entity
public class Payroll {
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Collaborator collaborator;

	private LocalDate timeLog, begin, finish;
	private String payrollDiscounts;
	private Double finalSalary;


	public Payroll() {
		
	}

	public Payroll(LocalDate data, Collaborator collaborator) {
		this.setCollaborator(collaborator);
		this.setBegin(data);
		this.setTimeLog(LocalDate.now());
		if(collaborator.getDiscounts() != null) {
			generateDiscounts(data);
		}else {
			this.setPayrollDiscounts("\n");
			this.setFinalSalary(0.00);
		}
	}

	public Payroll(LocalDate begin, LocalDate finish, Collaborator collaborator) {
		this.setCollaborator(collaborator);
		this.setBegin(begin);
		this.setFinish(finish);
		this.setTimeLog(LocalDate.now());

		if(collaborator.getDiscounts() != null) {
			generateDiscounts(begin, finish);
		}else {
			this.setPayrollDiscounts("\n");
			this.setFinalSalary(0.00);
		}
	}

	public Payroll(Payroll newPayroll) {
		this.setCollaborator(newPayroll.getCollaborator());
		this.setBegin(newPayroll.getBegin());
		this.setTimeLog(LocalDate.now());

		if(newPayroll.getFinish() != null)
			this.setFinish(newPayroll.getFinish());

		if(this.getCollaborator().getDiscounts() != null) 
			if(newPayroll.getFinish() == null)
				generateDiscounts(begin);
			else
				generateDiscounts(begin, finish);
	}

	public LocalDate getTimeLog() {
		return timeLog;
	}

	public void setTimeLog(LocalDate timeLog) {
		this.timeLog = timeLog;
	}

	public void setFinalSalary(Double finalSalary) {
		this.finalSalary = finalSalary;
	}

	public double getFinalSalary() {
		return finalSalary;
	}

	public void setFinalSalary(double finalSalary) {
		this.finalSalary = finalSalary;
	}

	public String getPayrollDiscounts() {
		return payrollDiscounts;
	}

	public void setPayrollDiscounts(String payrollDiscounts) {
		this.payrollDiscounts = payrollDiscounts;
	}


	public LocalDate getBegin() {
		return begin;
	}

	public void setBegin(LocalDate begin) {
		this.begin = begin;
	}

	public LocalDate getFinish() {
		return finish;
	}

	public void setFinish(LocalDate finish) {
		this.finish = finish;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	private void generateDiscounts(LocalDate date) {
		String discountsList = "";
		Double finalSalary;
		if(this.collaborator.getInVacation() == null || this.collaborator.getInVacation() == false) {
			finalSalary = calculateSalaryBasedOnPointsRegister(date.getMonthValue(), date.getYear());
		}else {
			finalSalary = Vacation.vacationPayment(this.collaborator.getGrossSalary(), Integer.parseInt(this.collaborator.getVacationSize()), Vacation.doubleVacation(this.collaborator.getLastVacation()));
		}
		

		for(Discount discount : collaborator.getDiscounts()) {
			if(discount.getDate().getMonth().equals(date.getMonth()));
			finalSalary-= discount.getValue();
			discountsList+= String.format("%s: %.2f %s/", discount.getName(), discount.getValue(), discount.getDate().toString());
		}

		this.setFinalSalary(finalSalary);
		this.setPayrollDiscounts(discountsList);
	}
	
	private void generateDiscounts(LocalDate date, LocalDate finish) {
		Double finalSalary = calculateSalaryBasedOnPointsRegister(date.getMonthValue(), date.getYear(), date.getDayOfMonth(), finish.getDayOfMonth());
		String discountsList = "";

		for(Discount discount : collaborator.getDiscounts()) {
			if((discount.getDate().getDayOfYear() >= date.getDayOfYear()) && (discount.getDate().getDayOfYear() <= finish.getDayOfYear()));
			finalSalary-= discount.getValue();
			discountsList+= String.format("%s: %.2f %s/", discount.getName(), discount.getValue(), discount.getDate().toString());
		}

		this.setFinalSalary(finalSalary);
		this.setPayrollDiscounts(discountsList);
	}
	
	/**
	 * Method for calculate a collaborator's salary based on points register in
	 * specific month.
	 *
	 * @author Ana Caroline C dos Santos <carolsantos2002@gmail.com>
	 * @author Jessé Amaro <jesse.amaro7@gmail.com>
	 *
	 * @return salaryResult
	 */

	public Double calculateSalaryBasedOnPointsRegister(int month, int year) {
		int totalMinutes = collaborator.workedMinutesInMonth(month, year);

		double baseSalary = collaborator.getGrossSalary();
		int hoursByWeek = 40;

		double salaryByMinute = (baseSalary / (hoursByWeek * 4)) / 60;
		double salaryResult = totalMinutes * salaryByMinute;

		return salaryResult;
	}
	
	/**
	 * Aren't working.
	 * @param month
	 * @param year
	 * @param initDay
	 * @param finishDay
	 * @return
	 */
	public Double calculateSalaryBasedOnPointsRegister(int month, int year, int initDay, int finishDay) {
		int totalMinutes = 0;
		for (int dayOfMonth = initDay; dayOfMonth <= finishDay; dayOfMonth++) {
			String leading = "";
			String monthLeading = "";

			if (dayOfMonth < 10) {
				leading = "0";
			}

			if (month < 10) {
				monthLeading = "0";
			}

			String searchDate = leading + dayOfMonth + "/" + monthLeading + month + "/" + year;
			if (Validators.isDateValid(searchDate)) {
				totalMinutes += collaborator.workedMinutesInDay(searchDate);
			}
		}

		double baseSalary = collaborator.getGrossSalary();
		int hoursByWeek = 40;

		double salaryByMinute = (baseSalary / (hoursByWeek * 4)) / 60;
		double salaryResult = totalMinutes * salaryByMinute;

		return salaryResult;
	}

	/***
	 * Override of the toString method to inform all fields of this
	 * object.
	 */
	public String toString() {
		String data = String.format("%d/%d", timeLog.getMonth().getValue(), timeLog.getYear());

		return String.format(""
				+ "Data: %s\n"
				+ "Colaborador: %s\n"
				+ "Valor bruto: %.2f\n"
				+ "%s"
				+ "Valor l�quido: %.2f\n",
				data,
				this.collaborator.getName(),
				this.collaborator.getGrossSalary(),
				this.payrollDiscounts,
				this.finalSalary
				);
	}
}

















