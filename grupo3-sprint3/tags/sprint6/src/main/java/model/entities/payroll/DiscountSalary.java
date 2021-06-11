package model.entities.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/***
 * Class DiscountSalary
 * 
 * Class responsible for saving discounts, their values and types.
 * 
 * @version 1.0.0	
 * 
 * @author Jonathas Rocha de Souza <jonathasrochadesouza@gmail.com>
 * @author Giovanni Bruno Buzzi <buzzi.giovanni@outlook.com>
 */
@Entity
@Table (name="discount_salary")
public class DiscountSalary {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private Double value;
	
	@ManyToOne
	@JoinColumn(name="payroll_id", referencedColumnName="id") 
	private Payroll payroll;
	
	private int type;
	
	public DiscountSalary() {}
	
	public DiscountSalary(String name, Double value, boolean percentage) {
		this.name = name;
		this.value = value;

		if(percentage) {
			this.type = 0;
		}else {
			this.type = 1;
		}	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return type == 0 ? true : false;
	}
	
	public Payroll getPayroll() {
	  return this.payroll;
	}
	
	public void setPayroll(Payroll payroll) {
	  this.payroll = payroll;
	}



}
