package com.publica.grupo1.model.entities.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Class for dynamic discounts.
 * 
 * @author Diego Leon
 * @version 1.1.0
 */
@Entity
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private boolean isPercentual;
	private double discountValue;

	@Transient
	private DiscountType type;

	public Discount() {
		
	}

	public Discount(int id, String name, boolean isPercentual, double discountValue) {
		this.id = id;
		this.name = name;
		this.isPercentual = isPercentual;
		this.discountValue = discountValue;
		
		setDiscountType(isPercentual);
	}

	public Discount(boolean isPercentual, String name, double discountValue) {
		this.name = name;
		this.isPercentual = isPercentual;
		this.discountValue = discountValue;

		setDiscountType(isPercentual);
	}

	@Deprecated
	public Discount(double discountValue, DiscountType type) {
		id = 0;
		this.discountValue = discountValue;
		this.type = type;
	}

	@Deprecated
	public Discount(DiscountType type) {
		id = 0;
		this.type = type;
	}
	
	private void setDiscountType(boolean isPercentual) {
		type = isPercentual ? DiscountType.PERCENTUAL : DiscountType.FIXED;
	}
	
	public double calculateDiscount(double valueToReceiveDiscount) {
		return type.calculate(valueToReceiveDiscount, discountValue);
	}

	private void generateDiscountType() {
		type = isPercentual ? DiscountType.PERCENTUAL : DiscountType.FIXED;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPercentual() {
		return isPercentual;
	}

	public void setPercentual(boolean isPercentual) {
		this.isPercentual = isPercentual;
		generateDiscountType();
	}

	public DiscountType getType() {
		return type;
	}

	public void setType(DiscountType type) {
		this.type = type;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
