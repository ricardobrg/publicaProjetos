package com.publica.grupo1.model.entities.vacations;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.publica.grupo1.model.entities.collaborator.Collaborator;


/***
 * Class responsible for return the informations about the Time Off of the
 * collaborators.
 * 
 * Have methods responsibles for check if can Take Off now, how many days the
 * collaborator can stay in the take off and the payment value
 * 
 * @version 0.0.1
 * @author Diego Borba <diegoborba25@gmail.com>
 */

@Entity
public class TimeOffTracker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTimeOffTracker;
	
	@ManyToOne
	Collaborator collab;
	
	@Column
	private LocalDate date;
	
	@Column
	private int days;

	public TimeOffTracker() {}

	public TimeOffTracker(LocalDate date, int days, int idTimeOffTracker, Collaborator collab) {

		this.setDate(date);
		this.setDays(days);
		this.setIdTimeOffTracker(idTimeOffTracker);
		this.setCollab(collab);

	}

	public Collaborator getCollab() {
		return collab;
	}

	public void setCollab(Collaborator collab) {
		this.collab = collab;
	}

	public int getIdTimeOffTracker() {
		return idTimeOffTracker;
	}

	public void setIdTimeOffTracker(int idTimeOffTracker) {
		this.idTimeOffTracker = idTimeOffTracker;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {

		this.date = date;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		if (days < 5 && days > 60) {
			this.days = days;
		}
	}

	public TimeOffTracker(Collaborator collaba) {
		this.collab = collaba;

	}
}
