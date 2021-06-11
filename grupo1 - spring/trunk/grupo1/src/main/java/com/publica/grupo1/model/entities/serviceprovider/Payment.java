package com.publica.grupo1.model.entities.serviceprovider;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.publica.grupo1.util.validation.text.ValidatorClass;

/***
 * Class responsible for the payment object, object exclusive for the class
 * ServiceProvider.
 *
 * This class keeps the payment date and the type of payment.
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */
//TODO: LISTA DE PAGAMENTOS
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private ServiceProvider serviceProvider;
	
	@ManyToOne
	private PaymentType type;
	
	private String cost;
	private LocalDate date;
	private byte paid = 0;
	
	public Payment() {
		
	}
	
	/***
	 * Starts the Payment class, assigning its attributes from the parameters passed
	 * 
	 * @param String	date of payment(ddmmyy)
	 * @param String	type of payment
	 */
	public Payment(LocalDate date, PaymentType type) {
		this.date = date;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(String date) {
		if (ValidatorClass.isDateFormatted(date))
			this.date = LocalDate.parse(date);
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
	
	public byte getPaid() {
		return paid;
	}

	public void setPaid(byte paid) {
		this.paid = paid;
	}
}











