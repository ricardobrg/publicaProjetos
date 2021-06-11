package com.publica.grupo1.model.entities.payroll;

/**
 * Enum for discounts operations.
 * 
 * @author Diego Leon
 *
 */
public enum DiscountType implements DiscountCalc {
	PERCENTUAL(1, "Percentual") {
		public double calculate(double valueToReceiveDiscount, double discountPercentage) {
			return valueToReceiveDiscount - (valueToReceiveDiscount * (discountPercentage / 100));
		}
	},
	FIXED(2, "Fixed") {
		public double calculate(double valueToReceiveDiscount, double fixedDiscountValue) {
			return valueToReceiveDiscount - fixedDiscountValue;
		}
	};

	public int id;
	public String typeName;

	private DiscountType(int id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}
}

interface DiscountCalc {
	double calculate(double valueToReceiveDiscount, double discountValue);
}