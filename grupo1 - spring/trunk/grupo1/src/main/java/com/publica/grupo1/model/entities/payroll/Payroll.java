package com.publica.grupo1.model.entities.payroll;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.publica.grupo1.model.entities.collaborator.Collaborator;

/**
 * Class for salary payment.
 * 
 * @author Pablo
 * @author Diego Leon
 * @author Augusto Kalahary
 *
 */
/**
 * Class for salary payment.
 * 
 * @author Pablo
 * @author Diego Leon
 * @author Augusto Kalahary
 *
 */
@Entity
public class Payroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Collaborator collaborator;

	@ManyToMany
	private List<Discount> discounts;

	private int monthlyPaiment = 0;
	private double salary, weekExtraSalary, sundayExtraSalary, weekNocturnalAdditional, totalSalary;

	/**
	 * have the monthly worked hours, based on 8 hours by day, defined by the law.
	 */
	@Transient
	public final int MONTHLY_HOURS = 220;

	/**
	 * have the monthly worked hours, based on 7 hours by day, defined by the law.
	 * If the collaborator work in nocturnal period his workload will be that.
	 */
	@Transient
	public final int MONTHLY_NOCTURNAL_HOURS = 210;

	@Transient
	public final double WEEK_NIGHTLY_EXTRA_PERCENTAGE = 1.8;

	@Transient
	public final double WEEK_DIURNAL_EXTRA_PERCENTAGE = 1.5;

	@Transient
	public final double WEEK_NOCTURNAL_ADDITIONAL = 1.2;

	@Transient
	public final double SUNDAY_EXTRA_PERCENTAGE = 2;

	public Payroll() {

	}

	public Payroll(int id, Collaborator collaborator, List<Discount> discounts) {
		this.id = id;
		this.collaborator = collaborator;
		this.discounts = discounts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMonthlyPaiment() {
		return monthlyPaiment;
	}

	public void setMonthlyPaiment(int monthlyPaiment) {
		this.monthlyPaiment = monthlyPaiment;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getWeekExtraSalary() {
		return weekExtraSalary;
	}

	public void setWeekExtraSalary(double weekExtraSalary) {
		this.weekExtraSalary = weekExtraSalary;
	}

	public double getSundayExtraSalary() {
		return sundayExtraSalary;
	}

	public void setSundayExtraSalary(double sundayExtraSalary) {
		this.sundayExtraSalary = sundayExtraSalary;
	}

	public double getWeekNocturnalAdditional() {
		return weekNocturnalAdditional;
	}

	public void setWeekNocturnalAdditional(double weekNocturnalAdditional) {
		this.weekNocturnalAdditional = weekNocturnalAdditional;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public int getMONTHLY_HOURS() {
		return MONTHLY_HOURS;
	}

	public int getMONTHLY_NOCTURNAL_HOURS() {
		return MONTHLY_NOCTURNAL_HOURS;
	}

	public double getWEEK_NIGHTLY_EXTRA_PERCENTAGE() {
		return WEEK_NIGHTLY_EXTRA_PERCENTAGE;
	}

	public double getWEEK_DIURNAL_EXTRA_PERCENTAGE() {
		return WEEK_DIURNAL_EXTRA_PERCENTAGE;
	}

	public double getWEEK_NOCTURNAL_ADDITIONAL() {
		return WEEK_NOCTURNAL_ADDITIONAL;
	}

	public double getSUNDAY_EXTRA_PERCENTAGE() {
		return SUNDAY_EXTRA_PERCENTAGE;
	}

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Payroll) {
			Payroll obj2 = (Payroll) obj;
			return obj2.getId() == id && obj2.getCollaborator().equals(collaborator)
					&& obj2.getDiscounts().equals(discounts) && obj2.getSalary() == salary
					&& obj2.getWeekExtraSalary() == weekExtraSalary && obj2.getSundayExtraSalary() == sundayExtraSalary
					&& obj2.getWeekNocturnalAdditional() == weekNocturnalAdditional
					&& obj2.getTotalSalary() == totalSalary;
		}
		return false;
	}

}