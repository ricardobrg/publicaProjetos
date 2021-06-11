package com.publica.grupo2sprint3.model.person.physicalperson;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.publica.grupo2sprint3.model.util.UtilsCalendar;
import com.publica.grupo2sprint3.model.util.Validators;

/**
 * Class for checking entry hour.
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@email.com>
 * 
 * Version: 1.0.0
 */
@Entity
public class Point {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 

	@ManyToOne
	private Collaborator collaborator;
	
	private String date;
	private int dayMinute;
	
	public Point() {}
			
	public Point(String date, int minutes) {
		this.setDate(date);
		this.setDayMinute(minutes);
	}

	public Point(Collaborator collaborator, String date, int minutes) {
		this.setCollaborator(collaborator);
		this.setDate(date);
		this.setDayMinute(minutes);
	}

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	public String getDate() {
		return date;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/***
	 * Method responsible for validating the date received by String.
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 * @param date : String
	 */
	public void setDate(String date) {
		if (Validators.isDateValid(date)) {
			this.date = date;
		}
	}

	public int getDayMinute() {
		return dayMinute;
	}

	/***
	 * Method responsible for checking the minutes.
	 * 
	 * @author Pablo Mafessoli <mafessolip@gmail.com>
	 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
	 * 
	 * @param minute : int
	 */
	public void setDayMinute(int minute) {
		if (minute < 0) {
			throw new IllegalArgumentException("The input minute can't be negative!");
		}
		dayMinute = minute;
	}
	
	/**
	 * convertDayMinute Method
	 * 
	 * This method converts the getDayMinute to a formatted pattern of hours.
	 * 
	 * @author Jess� Amaro <jesse.amaro7@gmail.com>
	 * 
	 */

	public String convertDayMinute() {
		
		int hour = dayMinute / 60;
		int minute = dayMinute % 60;
		
		String sHour = Integer.toString(hour);
		String sMinute = Integer.toString(minute);
		
		if (hour < 10)  sHour = "0" + sHour; 
		if (minute < 10)  sMinute = "0" + sMinute; 
		
		return (sHour + ":" + sMinute);
		
	}
	
	@Override
	public String toString() {
		String hour = UtilsCalendar.toString(dayMinute);
		
		return String.format(""
				+ "Colaborador: %s\n"
				+ "Data: %s\n"
				+ "Hora: %s\n", 
				this.collaborator.getName(),
				this.date,
				hour);
				
	}
}