package com.publica.grupo1.model.entities.point;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


import com.publica.grupo1.model.entities.collaborator.Collaborator;
import com.publica.grupo1.util.convertion.LocalDateTimeAdapter;


/**
 * Class for checking entry hour.
 * 
 * @author Vinicius Roosevelt <vinicius_roose@hotmail.com>
 * @author Pablo Mafessoli <mafessolip@email.com>
 * @author Diego Leon
 * 
 *         Version: 1.1.0
 */
@Entity
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Collaborator collaborator;
	
	@Column
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime date;
	
	@Column
	private char isDayOfWeek;
	
	@Enumerated(EnumType.STRING) 
	private PointType pointType;
	
	public Point() {
		
	}
	
	public Point(Collaborator colaborador, LocalDateTime date, PointType pointType) {
		this.collaborator = colaborador;
		setDate(date);
		setPointType(pointType);
	}

	public char isDayOfWeek() {
		return isDayOfWeek;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(Collaborator collaborators) {
		this.collaborator = collaborators;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
		if (date.getDayOfWeek().getValue() < 6) {
			this.isDayOfWeek = 'y';
		}
	}

	public PointType getPointType() {
		return pointType;
	}

	public void setPointType(PointType pointType) {
		this.pointType = pointType;
	}

}
