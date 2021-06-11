package com.publica.grupo1.model.entities.payroll;

public class PaymentDiscount {

	public final int ID;
	private int fkPayroll, fkDiscount;

	public PaymentDiscount(int id, int fkPayroll, int fkDiscount) {
		this.ID = id;
		this.fkDiscount = fkDiscount;
		this.fkPayroll = fkPayroll;
	}

	public int getFkPayroll() {
		return fkPayroll;
	}

	public void setFkPayroll(int fkPayroll) {
		this.fkPayroll = fkPayroll;
	}

	public int getFkDiscount() {
		return fkDiscount;
	}

	public void setFkDiscount(int fkDiscount) {
		this.fkDiscount = fkDiscount;
	}

	public int getID() {
		return ID;
	}
}
