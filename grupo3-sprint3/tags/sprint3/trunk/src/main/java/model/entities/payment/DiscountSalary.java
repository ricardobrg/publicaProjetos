package model.entities.payment;

/**
 * Responsible class for registring all discounts done in Payroll.
 * 
 * @version 1.0.0
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
public class DiscountSalary {

	private String name;
	private Double value;
	private boolean percentage;
	
	public DiscountSalary(String name, Double value, boolean percentage) {
		this.name = name;
		this.value = value;
		this.percentage = percentage;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public Double getValue() {
		return this.value;
	}

	public boolean isPercentage() {
		return this.percentage;
	}

	public DiscountSalary() {} // DEFAULT CONSTRUCTOR

}
