package model.entities.payment;

import java.util.ArrayList;

import model.entities.person.Collaborator;
/***
 * 
 * Payroll
 * Class to do collaborators payment taking into account the hours worked in the 
 * month and the calculation of overtime as well as the payroll discounts.
 * 
 * @version 1.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class Payroll {

	Collaborator colab;
	ArrayList <DiscountSalary> discount;

	public Payroll(Collaborator colab) {
		this.colab = colab;
		discount = new ArrayList<DiscountSalary>();
	}


	// TODO: Implement getters & setters for Payroll


	//TODO: Move to a service Payroll c
	/**
	 * 
	 * Method to add new discount object to payment roll
	 * 
	 * @param DiscountSalary discount
	 */
	public void addDiscount(DiscountSalary discount) {
		this.discount.add(discount);
	}

	// TODO: Move to specialized class in calculations
	/***
	 * Method to calculate payment roll with the extra hours,
	 * discounts, etc...
	 * 
	 * @param extraHours value of extra hours
	 * @param month reference month for calculation
	 * @return payment
	 */
	public double calcPayment(double extraHours, int monthHours, int extraMonthHours) {

		double payment = (monthHours * (this.colab.getSalary()/
				(this.colab.getWorkHours()) + extraMonthHours*(extraHours)));

		double totalDiscountPercentage = 0;
		double totalDiscountFix = 0;

		for(int i = 0; i < discount.size(); i++) {

			if(discount.get(i).isPercentage()) { 
				totalDiscountPercentage += discount.get(i).getValue();
			}else { 
				totalDiscountFix += discount.get(i).getValue();
			}
		}

		if(totalDiscountPercentage > 0) {
			payment = payment - payment*(totalDiscountPercentage/100); 
		}
		payment -= totalDiscountFix;

		return payment;
	}


}

