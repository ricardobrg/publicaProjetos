package com.publica.grupo1.controller.payroll;

import java.time.LocalDate;

import com.publica.grupo1.model.dao.point.PointDAO;
import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.model.entities.payroll.Payroll;
import com.publica.grupo1.model.entities.payroll.PointRegister;

/**
 * calculate the salary and extras based on points, @see
 * {@link model.dateTime.Point}, in the point register, @see
 * {@link model.payroll.PointRegister}.
 * 
 * @author Diego Leon
 *
 */
public class PayrollGenerator {

	private PointRegister pointReg;
	private Collaborator collab;
	private Payroll payroll;

	public PayrollGenerator(Collaborator collab, PointDAO dao) {
		pointReg = new PointRegister(collab, dao);
		this.collab = collab;
		this.payroll = new Payroll();
		// this.payroll = new Payroll(collab.getId());
	}

	/**
	 * generate the payroll date, checking worked time and extra hours. calculate
	 * the total salary, normal salary based on the collaborator workload, week
	 * extra hours salary, sunday extra hours salary. This method returns a payroll
	 * of the date day until the last day of month.
	 * 
	 * @param date
	 * 
	 * @author Diego Leon
	 */
	public void calculateDatePayroll(LocalDate date) {
		pointReg.calculateDatePoints(date);
		calculateMonthSalaries();
	}

	/**
	 * Calculate the extra anual salary
	 * 
	 * @return extra anual salary based on monthly payment, in the case of it have
	 *         already paid than return 0.
	 * 
	 * @author Pablo
	 * @author Diego Leon
	 */
	public double getExtraAnualSalary() {
		if (payroll.getMonthlyPaiment() == 1)
			return collab.getSalary() + this.collab.getSalary() * 2;
		else if (payroll.getMonthlyPaiment() == 2)
			return collab.getSalary() + this.collab.getSalary() / 2;
		return 0;
	}

	/**
	 * calculate month salaries
	 * 
	 * @author Diego Leon
	 * @author Augusto Kalahary
	 */
	private void calculateMonthSalaries() {
		payroll.setSalary(collab.getSalary());
		if (collab.getPermission().getRole().getWorkLoad() == 8) {
			payroll.setWeekExtraSalary(payroll.getWeekExtraSalary() + pointReg.getWeekExtraTime()
					* getSalaryPerHourWorkload8() * payroll.WEEK_DIURNAL_EXTRA_PERCENTAGE);

			payroll.setSundayExtraSalary(payroll.getSundayExtraSalary()
					+ pointReg.getSundayExtraTime() * getSalaryPerHourWorkload8() * payroll.SUNDAY_EXTRA_PERCENTAGE);

			payroll.setTotalSalary(payroll.getSalary() + payroll.getWeekExtraSalary() + payroll.getSundayExtraSalary());
		} else {
			payroll.setWeekNocturnalAdditional(payroll.getWeekNocturnalAdditional() + getSalaryPerHourWorkload7()
					* pointReg.getNocturnalWeekAdditional() * payroll.WEEK_NOCTURNAL_ADDITIONAL);

			payroll.setTotalSalary(payroll.getSalary() + payroll.getWeekNocturnalAdditional());
		}
	}

	/**
	 * 
	 * @return payroll collaborator salary per hour when the workload is 8 hours per
	 *         day.
	 * 
	 * @author Pablo
	 * @author Diego Leon
	 * 
	 */
	public double getSalaryPerHourWorkload8() {
		return collab.getSalary() / payroll.MONTHLY_HOURS;
	}

	/**
	 * 
	 * @return salary per hour when workload is 7 hours per day.
	 * 
	 * @author Augusto Kalahary
	 */
	public double getSalaryPerHourWorkload7() {
		return collab.getSalary() / payroll.MONTHLY_HOURS;
	}

	public PointRegister getPointReg() {
		return pointReg;
	}

	public void setPointReg(PointRegister pointReg) {
		this.pointReg = pointReg;
	}

	public Collaborator getCollab() {
		return collab;
	}

	public void setCollab(Collaborator collab) {
		this.collab = collab;
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

}
