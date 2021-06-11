package com.publica.grupo2sprint3.model.person.physicalperson;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Discount {
	@Id
	@GeneratedValue
	private int id;
	
	private String name = "";
	private Double value = 0.00;
	
	private LocalDate date;
	
	public Discount(){
	}
	
	public Discount(String name, Double value) {
		this.setName(name);
		this.setValue(value);
		this.setDate(LocalDate.now());
	}
	
	public Discount(String name, Double value, LocalDate date) {
		this.setName(name);
		this.setValue(value);
		this.setDate(date);
		this.setDate(LocalDate.now());
	}
	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	public static double generateInss(double salary) {
		double inss = 0;
        if (salary <= 1556.94) {
            inss = salary * 8 / 100;
        } else if (salary >= 1556.95 && salary <= 2594.92) {
            inss = salary * 9 / 100;
        } else if (salary >= 2594.93 && salary <= 5189.82) {
            inss = salary * 11 / 100;
        } else if (salary > 5189.82) {
            inss = salary + 570.88;
        }
                
        return inss + 189.59;
    }
}
